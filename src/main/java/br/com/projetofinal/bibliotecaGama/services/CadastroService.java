package br.com.projetofinal.bibliotecaGama.services;

import java.util.ArrayList;
import java.util.Optional;

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

	public Optional<Cadastro> buscarPorId(Integer id) {
		Optional<Cadastro> cad = cadastroRespository.findById(id);
		
		return cad;
	}

	public ArrayList<Cadastro> buscarTodos() {

		return (ArrayList<Cadastro>) cadastroRespository.findAll();
	}
}
