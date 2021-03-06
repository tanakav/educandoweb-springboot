package com.educandoweb.api.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.educandoweb.api.domain.Cidade;
import com.educandoweb.api.domain.Cliente;
import com.educandoweb.api.domain.Endereco;
import com.educandoweb.api.domain.enums.TipoCliente;
import com.educandoweb.api.dto.ClienteDto;
import com.educandoweb.api.dto.ClienteNewDto;
import com.educandoweb.api.repositories.CidadeRepository;
import com.educandoweb.api.repositories.ClienteRepository;
import com.educandoweb.api.repositories.EnderecoRepository;
import com.educandoweb.api.services.exceptions.DataIntegrityException;
import com.educandoweb.api.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private CidadeRepository cidadeRepository;
    
    @Autowired
    private EnderecoRepository enderecoRepository;

    public Cliente find(Integer id){
        Cliente returnedCliente = new Cliente();

        if(clienteRepository.findById(id).isPresent()){
            returnedCliente = clienteRepository.findById(id).get();
        }else{
            throw new ObjectNotFoundException("Usuario nao encontrado. Id: "+ id);
        }

        return returnedCliente;
    }
    
    public Cliente update(Cliente clienteRecebido) {
    	Cliente cliente = find(clienteRecebido.getId());
    	
    	cliente = updateData(cliente,clienteRecebido);   
    	enderecoRepository.saveAll(cliente.getEnderecos());
    	
    	return clienteRepository.save(cliente);
    }
    
    public void delete(Integer id) {
    	find(id);
    	
    	try {
    		clienteRepository.deleteById(id);
    	}catch(DataIntegrityViolationException e) {
    		throw new DataIntegrityException("Não é possível excluir um usuario que possui pedidos");
    	}    	    	    	
    }
    
    public List<ClienteDto> findAll(){
    	List<Cliente> clientes = (List<Cliente>) clienteRepository.findAll();
    	List<ClienteDto> clientesDto = clientes.stream()
    			.map(cliente-> new ClienteDto(cliente))
    			.collect(Collectors.toList());
    	
    	return clientesDto;
    }
    
    @Transactional
    public Cliente insert(Cliente cliente) {
    	cliente.setId(null);
    	Cliente newCliente = clienteRepository.save(cliente);
    	enderecoRepository.saveAll(newCliente.getEnderecos());
    	return newCliente;
    }
    
    public Page<Cliente> findPage(
    		Integer pages,
    		Integer lines,
    		String orderBy,
    		String direction
    		){
    	PageRequest pageRequest = PageRequest.of(pages, lines , Direction.valueOf(direction), orderBy);
    	
    	return clienteRepository.findAll(pageRequest);
    }
    
    private Cliente updateData(Cliente cliente, Cliente clienteRecebido) {
    	cliente.setNome(clienteRecebido.getNome());
    	cliente.setEmail(clienteRecebido.getEmail());
    	
    	return cliente;
    }
    
    public Cliente fromDto(ClienteDto clienteDto) {
    	
    	return new Cliente(clienteDto.getId(),clienteDto.getNome(),clienteDto.getEmail());
    }
    
    public Cliente fromDto(ClienteNewDto clienteNewDto) {
    	Cliente cliente = new Cliente(null,clienteNewDto.getNome(),clienteNewDto.getEmail(),clienteNewDto.getCpfOuCnpj(),TipoCliente.toEnum(clienteNewDto.getTipo()));    	
    	Cidade cidade = cidadeRepository.findById(clienteNewDto.getCidadeId()).get();    	
    	Endereco endereco = new Endereco(null,clienteNewDto.getLogradouro(),clienteNewDto.getNumero(),clienteNewDto.getComplemento(),clienteNewDto.getBairro(),clienteNewDto.getCep(),cliente,cidade);
    	    	
    	cliente.getEnderecos().add(endereco);
    	cliente.getTelefones().add(clienteNewDto.getTelefone());    	
    	
    	if(clienteNewDto.getTelefone2() != null) {
    		cliente.getTelefones().add(clienteNewDto.getTelefone2());
    	}
    	if(clienteNewDto.getTelefone3() != null) {
    		cliente.getTelefones().add(clienteNewDto.getTelefone3());
    	}
    	
    	return cliente;
    	
    }
    
}
