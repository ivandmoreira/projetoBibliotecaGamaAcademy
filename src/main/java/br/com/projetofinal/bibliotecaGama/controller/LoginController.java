package br.com.projetofinal.bibliotecaGama.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetofinal.bibliotecaGama.model.Login;
import br.com.projetofinal.bibliotecaGama.model.Sessao;
import br.com.projetofinal.bibliotecaGama.services.LoginService;

@CrossOrigin
@RestController
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private LoginService service;
	@PostMapping
	public Sessao login(@RequestBody Login login) {
		return service.logar(login);
	}
	
}
