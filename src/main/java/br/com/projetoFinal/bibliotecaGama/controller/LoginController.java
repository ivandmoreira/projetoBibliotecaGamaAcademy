package br.com.projetoFinal.bibliotecaGama.controller;

import br.com.projetoFinal.bibliotecaGama.service.LoginService;

public class LoginController {
	
	public boolean logarUsuario(String login, String senha) {
		LoginService loginService = new LoginService();
		loginService.isValid(login, senha);
		return false;
	}
}
