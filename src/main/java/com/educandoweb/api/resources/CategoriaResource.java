package com.educandoweb.api.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.educandoweb.api.domain.Categoria;
import com.educandoweb.api.dto.CategoriaDto;
import com.educandoweb.api.services.CategoriaService;


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
        
        responseData = categoriaService.find(id);

        return ResponseEntity.ok().body(responseData);
    }
    
    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDto categoriaDto){
    	Categoria categoria = categoriaService.fromDto(categoriaDto);
    	Categoria novaCategoria = categoriaService.insert(categoria);
    	
    	URI uri = ServletUriComponentsBuilder
    			.fromCurrentRequest()
    			.path("/{id}")
    			.buildAndExpand(novaCategoria.getId())
    			.toUri();
    	
    	return ResponseEntity.created(uri).build();
    	
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> update(
    		@PathVariable Integer id, 
    		@Valid @RequestBody CategoriaDto categoriaDto){
    	Categoria categoria = categoriaService.fromDto(categoriaDto);
    	categoria.setId(id);    	
    	Categoria categoriaAtualizada = categoriaService.update(categoria);    	
    	
    	return ResponseEntity.ok().body(categoriaAtualizada);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
    	categoriaService.delete(id);
    	
    	return ResponseEntity.ok().build();
    }
    
    @GetMapping
    public ResponseEntity<List<CategoriaDto>> findAll(){
    	List<CategoriaDto> categorias = categoriaService.findAll();
    	
    	return ResponseEntity.ok().body(categorias);
    }
    
    @GetMapping("/page")
    public ResponseEntity<Page<CategoriaDto>> findPage(
    		@RequestParam(name="page", defaultValue="0") Integer page, 
    		@RequestParam(name="lines", defaultValue="24") Integer linesPerPage, 
    		@RequestParam(name="orderby", defaultValue="nome") String orderBy, 
    		@RequestParam(name="direction", defaultValue="ASC") String direction
    		){
    	Page<Categoria> categorias = categoriaService.findPage(page,linesPerPage,orderBy,direction);
    	Page<CategoriaDto> categoriasDto = categorias.map(categoria -> new CategoriaDto(categoria));
    	
    	return ResponseEntity.ok().body(categoriasDto);
    }
    
    
}
