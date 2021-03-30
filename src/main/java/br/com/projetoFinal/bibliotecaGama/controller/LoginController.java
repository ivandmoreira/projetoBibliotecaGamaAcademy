package br.com.projetoFinal.bibliotecaGama.controller;

public class LoginController {
	public boolean logarUsuario() {
//		LoginService loginService = new LoginService();
//		loginService.isValid(login, senha);
//		return false;
		
		System.out.println("\nUsuario autenticado com sucesso!");
		
		LocacaoController locacao = new LocacaoController();
		locacao.run();
		return true;
	}
}
