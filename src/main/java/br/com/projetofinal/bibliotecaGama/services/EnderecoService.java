package br.com.projetofinal.bibliotecaGama.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.projetofinal.bibliotecaGama.dto.EnderecoDTO;
import br.com.projetofinal.bibliotecaGama.model.Endereco;


@Service
public class EnderecoService {
	private static String webService = "http://viacep.com.br/ws/";
	private static int codigoSucesso = 200;
	
	public int getCodigoSucesso() {
		return codigoSucesso;
	}
	
	public Endereco viaCep(Endereco cep) throws Exception {
        String urlChamada = webService + cep.getCep() + "/json";

        try {
        	
        	RestTemplate restTemplate = new RestTemplate();
        	EnderecoDTO enderecDto = restTemplate.getForObject(urlChamada, EnderecoDTO.class);
        	
        	Endereco enderecJson = new Endereco(enderecDto, cep.getNumero());
        	
            return enderecJson;
            
        } catch (Exception e) {
            throw new Exception("ERRO: " + e);
        }
    }
    
}
