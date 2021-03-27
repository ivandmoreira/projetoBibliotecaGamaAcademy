package br.com.projetofinal.bibliotecaGama.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetofinal.bibliotecaGama.model.Cadastro;
import br.com.projetofinal.bibliotecaGama.repository.CadastroRepository;

@Service
public class CadastroService {

	@Autowired
	private CadastroRepository cadastroRespository;
	
	public Cadastro salva(Cadastro cad) {
		return cadastroRespository.save(cad);
	}
}
