package com.example.Crud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cep-client", url = "http://cep-service:8081/api/cep")
public interface CepClient {

    @GetMapping("/{cep}")
    CepResponse getEnderecoPorCep(@PathVariable("cep") String cep);
}
