package com.example.Crud.service;

import com.example.Crud.CepResponse;
import com.example.Crud.feign.CepClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    @Autowired
    private CepClient cepClient;

    public CepResponse getEnderecoPorCep(String cep) {
        return cepClient.getEnderecoPorCep(cep);
    }
}
