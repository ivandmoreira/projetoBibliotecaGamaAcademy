package br.com.projetofinal.bibliotecaGama.services;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetofinal.bibliotecaGama.model.Cadastro;
import br.com.projetofinal.bibliotecaGama.model.Endereco;
import br.com.projetofinal.bibliotecaGama.repository.CadastroRepository;
import br.com.projetofinal.bibliotecaGama.repository.EnderecoRepository;

@Service
public class CadastroService {

	@Autowired
	private CadastroRepository cadastroRespository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private EnderecoService endService;
	
	public Cadastro salva(Cadastro cad) {
		
		Endereco end = this.buscarCep(cad.getEndereco());
		cad.setEndereco(end);
		enderecoRepository.save(cad.getEndereco());
		return cadastroRespository.save(cad);
	}

	private Endereco buscarCep(Endereco cep) {
		Endereco end = null;
		
		try {
			end = endService.viaCep(cep);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return end;
	
}


	public Optional<Cadastro> buscarPorId(Integer id) {
		Optional<Cadastro> cad = cadastroRespository.findById(id);
		
		return cad;
	}

	public ArrayList<Cadastro> buscarTodos() {
		return (ArrayList<Cadastro>) cadastroRespository.findAll();
	}
}
