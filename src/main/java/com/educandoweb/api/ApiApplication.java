package com.educandoweb.api;

import java.util.Arrays;
import java.util.Set;

import com.educandoweb.api.domain.Categoria;
import com.educandoweb.api.domain.Cidade;
import com.educandoweb.api.domain.Cliente;
import com.educandoweb.api.domain.Endereco;
import com.educandoweb.api.domain.Estado;
import com.educandoweb.api.domain.Produto;
import com.educandoweb.api.domain.enums.TipoCliente;
import com.educandoweb.api.repositories.CategoriaRepository;
import com.educandoweb.api.repositories.CidadeRepository;
import com.educandoweb.api.repositories.ClienteRepository;
import com.educandoweb.api.repositories.EnderecoRepository;
import com.educandoweb.api.repositories.EstadoRepository;
import com.educandoweb.api.repositories.ProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null,"Informática");
		Categoria cat2 = new Categoria(null,"Escritório");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		Estado est1 = new Estado(null,"Minas Gerais"); 
		Estado est2 = new Estado(null,"Sao Paulo"); 
		
		Cidade c1 = new Cidade(null,"Uberlandia",est1);
		Cidade c2 = new Cidade(null,"Sao Paulo",est2);
		Cidade c3 = new Cidade(null,"Campinas",est2);

		Cliente cliente1 = new Cliente(null,"Maria Silca","maria@gmail.com","36378912377", TipoCliente.PESSOA_FISICA);
		
		cliente1.getTelefones().addAll(Arrays.asList("123456789","369852147"));
		
		Endereco e1 = new Endereco(null, "Rua Flores","300","apto 303", "Jardim", "04040000", cliente1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos","150","sala 404", "Centro", "06003000", cliente1, c2);
		
		cliente1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));

		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));

		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));			
		
		clienteRepository.saveAll(Arrays.asList(cliente1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
	}

}
