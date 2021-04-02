package br.com.projetofinal.bibliotecaGama.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetofinal.bibliotecaGama.model.Livro;
import br.com.projetofinal.bibliotecaGama.services.LivroService;

@RestController
@RequestMapping(value = "/livros")
public class LivroController {

	@Autowired
	private LivroService livroService;

	@CrossOrigin
	@RequestMapping(method = RequestMethod.POST)
	public void salvar(@RequestBody Livro livro) {
		livroService.salva(livro);
	}
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET)
	public Iterable<Livro> exibirTodos(){
		return livroService.buscarLivros();
	}
	
	@CrossOrigin
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public Optional<Livro> exibirPorId(@PathVariable int id) {
		
		return livroService.buscarPorId(id);
	}
	
	
}
