package br.com.projetofinal.bibliotecaGama.controller;


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

import br.com.projetofinal.bibliotecaGama.model.Cadastro;
import br.com.projetofinal.bibliotecaGama.services.CadastroService;


@RestController
@RequestMapping(value="/cadastro")
public class CadastroController {
	
	@Autowired
	private CadastroService cadastroService;
	
	
	@CrossOrigin
	@GetMapping
	public Iterable<Cadastro> listar() {
		
		Iterable<Cadastro> listaCad = cadastroService.buscarTodos();
		return listaCad;
	}
	
	@CrossOrigin
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> buscarId(@PathVariable Integer id) {
		
		Optional<Cadastro> cad = cadastroService.buscarPorId(id);
		return ResponseEntity.ok().body(cad);
	}
	
	@CrossOrigin
	@RequestMapping(method=RequestMethod.POST)
	public void salvar(@RequestBody Cadastro cad){
		
		cadastroService.salva(cad);
		
	}
}
