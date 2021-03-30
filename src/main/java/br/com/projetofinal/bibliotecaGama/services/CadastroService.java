package br.com.projetofinal.bibliotecaGama.services;

import java.util.ArrayList;
import java.util.Optional;

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
		
		Endereco end = this.buscarCep(cad.getEndereco().getCep());
		//aqui estava o problema
		//se teu dto vai permitir só digitar o CEP pra buscar o resto dos dados 
		// vc deveria mudar o DTOS
		//POIS FICA CONFUSO A PASSAGEM DE VALOR
		end.setNumero(cad.getEndereco().getNumero());
		
		cad.setEndereco(end);
		enderecoRepository.save(cad.getEndereco());
		return cadastroRespository.save(cad);
	}

	private Endereco buscarCep(String cep) {
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
