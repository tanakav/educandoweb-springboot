package com.educandoweb.api.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Endereco {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;

    @ManyToOne
    @JoinColumn(name="cliente_id")
    private Cliente cliente;
    
}
