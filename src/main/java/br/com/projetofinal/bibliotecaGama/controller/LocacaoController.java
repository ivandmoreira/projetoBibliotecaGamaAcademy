package br.com.projetofinal.bibliotecaGama.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetofinal.bibliotecaGama.dto.LocacaoDto;
import br.com.projetofinal.bibliotecaGama.model.Locacao;
import br.com.projetofinal.bibliotecaGama.services.LocacaoService;

@RestController
@RequestMapping(value="/locacao")
public class LocacaoController {

	
	@Autowired
	private LocacaoService locacaoService;
	
	@PostMapping(value="/agendar")
	public void Locacao(@RequestBody LocacaoDto locDto) {
		
		locacaoService.salvar(locDto);
		
	}
		
	@CrossOrigin
	@GetMapping
	public Iterable<Locacao> exibirTodos(){
		return locacaoService.buscarLocacao();
	}
	
	@CrossOrigin
	@GetMapping(value="/{id}")
	public Optional<Locacao> exibirTodos(@RequestParam int id){
		return locacaoService.buscarPorId(id);
	}
	
	@CrossOrigin
	@GetMapping("/buscaEspeficica")
	public List<Locacao> locacaoEspecifica(LocacaoDto locacao){
		
		return locacaoService.buscarPorLocacaoEspecifica(locacao);
	}
	
	
	@CrossOrigin
	@PutMapping(value="/retirar/{id}")
	public void retirarLivro(@RequestParam int id){
		
		locacaoService.retirarLivro(id);
	}
	
	@CrossOrigin
	@PutMapping(value="/entregarLivro/{id}")
	public void entregarLivro(@RequestParam int id) {
		locacaoService.entregarLivro(id);
	}
	
	
}
