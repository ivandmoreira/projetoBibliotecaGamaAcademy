package br.com.projetoFinal.bibliotecaGama.controller;

import java.util.Scanner;

import br.com.projetoFinal.bibliotecaGama.model.Cadastro;
import br.com.projetoFinal.bibliotecaGama.service.LoginService;

public class LoginController {
	private String login;
	private String senha;
	private Scanner scanner;
	
	
	private LoginService loginService;
	
	public void logarUsuario() {
		LocacaoController locacao = new LocacaoController();
		locacao.run();	
//		scanner = new Scanner(System.in);
//		
//		System.out.println("Login: ");
//		login = scanner.nextLine();
//		loginService = new LoginService();
//		Cadastro cadastro = loginService.loginExiste(login);
//		
//		if(cadastro.getLogin().equals(login)) {
//			System.out.println("Senha: ");
//			senha = scanner.nextLine();
//			if(cadastro.getSenha().equals(senha)) {
//				System.out.println("Logado com Sucesso!");
//				LocacaoController locacao = new LocacaoController();
//				locacao.run();				
//			}else {
//				System.out.println("senha incorreta!");
//			}
//		}else{
//			System.out.println("Login nao cadastrado");
//		}
	}
}
