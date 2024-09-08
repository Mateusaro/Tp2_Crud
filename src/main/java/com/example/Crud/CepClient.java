package com.example.Crud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cep-client", url = "https://viacep.com.br/ws")
public interface CepClient {

    @GetMapping("/{cep}/json")
    CepResponse getEnderecoPorCep(@PathVariable("cep") String cep);
}
