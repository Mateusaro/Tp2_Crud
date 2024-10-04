# Assessment Projeto de Bloco

A aplicação consiste em um sistema de gerenciamento de Cinemas e Filmes.

## Pré-requisitos

Antes de começar, você precisará ter instalado:
- [Docker](https://www.docker.com/)
- [Minikube](https://minikube.sigs.k8s.io/docs/start/)

Link dos Microsserviços:
- [CEP](https://github.com/Mateusaro/cep)
- [Cinema](https://github.com/Mateusaro/cinema_micro)
- [Filme](https://github.com/Mateusaro/Filme)

## Clonando o Projeto

Clone este repositório em sua máquina local:

```bash
git clone <URL-do-repositorio>
cd <nome-do-repositorio>
```
## Executando o Docker Compose
O projeto inclui um arquivo docker-compose.yml 

***Atentar aos endereços do Dockerfile no arquivo DockerCompose pois cada microsserviço tem o seu próprio Dockerfile e precisa ser relacionado corretamente.***

Para executar o Docker Compose, siga os passos abaixo:

- Execute o Docker Compose com o seguinte comando: ```docker-compose up```

## Deploy no Kubernetes

### Executando o Minikube:

Execute o comando: ```minikube start```

Após executar o Docker Compose, você pode aplicar os arquivos de configuração do Kubernetes que estão localizados na pasta ***kubernetes*** na raiz do projeto.

### Comandos para aplicar as configurações:
```
kubectl apply -f cep-service-deployment.yaml
kubectl apply -f cep-service-service.yaml
kubectl apply -f springboot-app-deployment.yaml
kubectl apply -f springboot-app-service-nodeport.yaml
kubectl apply -f cinema-service-deployment.yaml
kubectl apply -f cinema-service-service.yaml
kubectl apply -f filme-service-deployment.yaml
kubectl apply -f filme-service-service.yaml
kubectl apply -f rabbitmq-deployment.yaml
```
### Port Forwarding

Após aplicar os arquivos, você pode usar os seguintes comandos para expor os serviços nas portas desejadas:
```
kubectl get pods
```
```
kubectl port-forward {Pod do springboot-app-deployment} 8080:8080
kubectl port-forward {Pod do cep-service-deployment} 8081:8081
kubectl port-forward {Pod do cinema-service-deployment} 8082:8082
kubectl port-forward {Pod do filme-service-deployment} 8083:8083
kubectl port-forward {Pod do rabbitmq-deployment.yaml} 5672:5672
kubectl port-forward {Pod do rabbitmq-deployment.yaml} 15672:15672
```
<sub>É necessário fazer o Port Forward do Rabbit duas vezes devido as duas portas do RabbitMQ sendo a 5672 a porta padrão para comunicação e a 15672 a porta reservada para a interface de gerenciamento WEB</sub>

### Caso ocorra problemas com ImagePullBackOff
- Utilize o comando para parar o Minikube: ```minikube stop```
- Use o comando do login do docker: ```docker login```
- Faça a tag da imagem: ```docker tag banco-banco:latest {Seu Usuario Docker}/{Nome da Imagem}:{Tag da Imagem}```
- Depois faça o Push da imagem: ```docker push {Seu Usuario Docker}/{Nome da Imagem}:{Tag da Imagem}```
- Delete o Minikube: ```minikube delete```
- Inicie novamente o Minikube ```minikube start```

## Utilização no Postman

### Criar um Cinema
***POST***
- http://localhost:8080/cinemas

````
{
    "nome" : "{Nome do Cinema}"
    "cep" : "22775046" 
}
````
<sub>O CEP inserido acima está mockado na aplicação de CEP.</sub>


### Criar um Filme
***POST***
- http://localhost:8080/filmes
```
{
    "titulo": "{Nome do Filme}",
    "diretor": "{Nome do Diretor}",
    "anoLancamento": {Ano de Lançamento}
    "cinemaId" : {ID do cinema que estreiará o filme}
}
```
<sub>O Cinema precisa existir para funcionar.</sub>
