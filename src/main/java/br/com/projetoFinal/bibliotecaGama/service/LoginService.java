package br.com.projetoFinal.bibliotecaGama.service;

public class LoginService {
	public boolean isValid(String login, String senha) {
		if (login == null || senha == null) {
			System.out.println("O campo login ou senha está nulo");
//			throw new IllegalArgumentException();
		}
		System.out.println("entrou login service");
		return false;
	}
}
