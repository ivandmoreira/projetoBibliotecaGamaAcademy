package br.com.projetoFinal.bibliotecaGama.service;

import br.com.projetoFinal.bibliotecaGama.model.Cadastro;
import br.com.projetoFinal.bibliotecaGama.repository.JpaCadastroRepository;

public class LoginService {
	
	private JpaCadastroRepository jpaCadastroRepository;
	
	public Cadastro loginExiste(String login) {
		
		jpaCadastroRepository = new JpaCadastroRepository();
		Cadastro cadastro = jpaCadastroRepository.selectLogin(login);
		jpaCadastroRepository.fechar();
		
		return cadastro;
	}

}
