package br.com.projetofinal.bibliotecaGama.controller;


import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.projetofinal.bibliotecaGama.model.Cadastro;
import br.com.projetofinal.bibliotecaGama.model.Endereco;
import br.com.projetofinal.bibliotecaGama.repository.EnderecoRepository;
import br.com.projetofinal.bibliotecaGama.services.CadastroService;


@RestController
@RequestMapping(value="/cadastro")
public class CadastroController {
	
	@Autowired
	private CadastroService cadastroService;
	
	@Autowired EnderecoRepository enderecoRepository;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Cadastro> listar() {
		
		Endereco end = new Endereco("123456789", "rua", "123", "Bangu", "Rio de Janeiro", "RJ", 321654);		
		
		
		Cadastro cad1 = new Cadastro("12345678", "Lazaro", "lazaro@lazaro.com", "21986458745", "Lazaro.marinho", "123456", end);
		
		enderecoRepository.save(end);
		cadastroService.salva(cad1);
		
		List<Cadastro> lista = new ArrayList<Cadastro>();
		lista.add(cad1);
		return lista;
	}
	
//	@RequestMapping(method=RequestMethod.POST)
//	public ResponseEntity<Void> salvar(@RequestBody Cadastro cad){
//		
//		cad = cadastroService.salva(cad);
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
//				.path("/{id}").buildAndExpand(cad.getId()).toUri();
//		
//		return ResponseEntity.created(uri).build();
//		
//	}
}
