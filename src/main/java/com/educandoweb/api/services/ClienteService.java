package com.educandoweb.api.services;

import com.educandoweb.api.domain.Cliente;
import com.educandoweb.api.repositories.ClienteRepository;
import com.educandoweb.api.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente buscar(Integer id){
        Cliente returnedCliente = new Cliente();

        if(clienteRepository.findById(id).isPresent()){
            returnedCliente = clienteRepository.findById(id).get();
        }else{
            throw new ObjectNotFoundException("Usuario nao encontrado. Id: "+ id);
        }

        return returnedCliente;
    }
    
}
