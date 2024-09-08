package com.example.Crud;

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
