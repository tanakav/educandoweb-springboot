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

import com.educandoweb.api.domain.Cliente;
import com.educandoweb.api.dto.ClienteDto;
import com.educandoweb.api.dto.ClienteNewDto;
import com.educandoweb.api.services.ClienteService;


@RestController
@RequestMapping("/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @RequestMapping(
        value="/{id}",
        method = RequestMethod.GET
    )
    public ResponseEntity<Cliente> find(@PathVariable Integer id){
    	Cliente responseData = new Cliente();
        
        responseData = clienteService.find(id);

        return ResponseEntity.ok().body(responseData);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(
    		@PathVariable Integer id,
    		@Valid @RequestBody ClienteDto clienteDto
    		){
    	Cliente cliente = clienteService.fromDto(clienteDto);
    	cliente.setId(id);
    	Cliente clienteAtualizado = clienteService.update(cliente);
    	
    	return ResponseEntity.ok().body(clienteAtualizado);
    }  
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
    	clienteService.delete(id);
    	
    	return ResponseEntity.noContent().build();
    }
    
    @GetMapping
    public ResponseEntity<List<ClienteDto>> findAll(){
    	List<ClienteDto> clientesDto = clienteService.findAll();
    	
    	return ResponseEntity.ok().body(clientesDto);
    }
    
    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDto clienteNewDto){
    	Cliente cliente = clienteService.fromDto(clienteNewDto);
    	cliente = clienteService.insert(cliente);
    	URI uri = ServletUriComponentsBuilder
    			.fromCurrentRequest()
    			.path("/{id}")
    			.buildAndExpand(cliente.getId())
    			.toUri();
    	
    	return ResponseEntity.created(uri).build();
    }
    
    @GetMapping("/page")
    public ResponseEntity<Page<ClienteDto>> findPage(
    		@RequestParam(name="page", defaultValue="0") Integer page,
    		@RequestParam(name="lines", defaultValue="24") Integer lines,
    		@RequestParam(name="orderby", defaultValue="nome") String orderBy,
    		@RequestParam(name="direction", defaultValue="ASC") String direction
    		){
    	Page<Cliente> clientes = clienteService.findPage(page,lines,orderBy,direction);
    	Page<ClienteDto> clientesDto = clientes.map(cliente -> new ClienteDto(cliente));
    	
    	return ResponseEntity.ok().body(clientesDto);
    }
    
}
