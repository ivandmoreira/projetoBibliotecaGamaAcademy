package br.com.projetofinal.bibliotecaGama.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetofinal.bibliotecaGama.repository.CadastroRepository;

@Service
public class CadastroSevice {
	
	@Autowired
	CadastroRepository cadastroRepository;
	

}
