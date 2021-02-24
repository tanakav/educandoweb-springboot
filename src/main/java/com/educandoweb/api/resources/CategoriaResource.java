package com.educandoweb.api.resources;

import java.util.ArrayList;
import java.util.List;

import com.educandoweb.api.domain.Categoria;
import com.educandoweb.api.services.CategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javassist.NotFoundException;


@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;

    @RequestMapping(
        value="/{id}",
        method = RequestMethod.GET
    )
    public ResponseEntity<Categoria> find(@PathVariable Integer id){
        Categoria responseData = new Categoria();
        try{
            responseData = categoriaService.buscar(id);
        }catch(NotFoundException e){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(responseData);
    }
    
}
