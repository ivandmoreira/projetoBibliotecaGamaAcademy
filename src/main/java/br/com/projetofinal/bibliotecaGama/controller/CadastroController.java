package br.com.projetofinal.bibliotecaGama.controller;


import java.net.URI;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.projetofinal.bibliotecaGama.model.Cadastro;
import br.com.projetofinal.bibliotecaGama.services.CadastroService;


@RestController
@RequestMapping(value="/cadastro")
public class CadastroController {
	
	@Autowired
	private CadastroService cadastroService;
	
	
	@CrossOrigin
	@GetMapping
	public ArrayList<Cadastro> listar() {
		
		ArrayList<Cadastro> listaCad = cadastroService.buscarTodos();
		return listaCad;
	}
	
	@CrossOrigin
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> buscarId(@PathVariable Integer id) {
		
		Optional<Cadastro> cad = cadastroService.buscarPorId(id);
		return ResponseEntity.ok().body(cad);
	}
	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> salvar(@RequestBody Cadastro cad){
		
		cad = cadastroService.salva(cad);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(cad.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
}
