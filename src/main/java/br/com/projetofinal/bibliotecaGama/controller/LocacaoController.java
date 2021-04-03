package br.com.projetofinal.bibliotecaGama.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetofinal.bibliotecaGama.dto.LocacaoDto;
import br.com.projetofinal.bibliotecaGama.services.LocacaoService;

@RestController
@RequestMapping(value="/locacao")
public class LocacaoController {

//	@Autowired
//	private LocacaoRepository locacaoRepository;
	
	@Autowired
	private LocacaoService locacaoService;
	
	@PostMapping(value="/agendar")
	public void Locacao(@RequestBody LocacaoDto locDto) {
		
		locacaoService.salvar(locDto);
//		locacaoRepository.save(locDto);
		
	}
}
