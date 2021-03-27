package br.com.projetofinal.bibliotecaGama.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetofinal.bibliotecaGama.model.Cadastro;
import br.com.projetofinal.bibliotecaGama.model.Endereco;


@RestController
@RequestMapping(value="/cadastro")
public class CadastroController {
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Cadastro> listar() {
		
		Endereco end = new Endereco(1, "21825390", "Rua A", "123", "Bangu", "Rio de Janeiro", "RJ", 123456789);
		
		Cadastro cad1 = new Cadastro("12345678", "Lazaro", "lazaro@lazaro.com", "21986458745", "Lazaro.marinho", "123456", end);
		
		List<Cadastro> lista = new ArrayList<Cadastro>();
		lista.add(cad1);
		return lista;
	}
}
