package br.com.projetofinal.bibliotecaGama.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.projetofinal.bibliotecaGama.model.Endereco;


@Service
public class EnderecoService {
	private static String webService = "http://viacep.com.br/ws/";
	private static int codigoSucesso = 200;
	
	public int getCodigoSucesso() {
		return codigoSucesso;
	}
	
	public Endereco viaCep(String cep) throws Exception {
        String urlChamada = webService + cep + "/json";

        try {
        	
        	RestTemplate restTemplate = new RestTemplate();
        	Endereco enderec = restTemplate.getForObject(urlChamada, Endereco.class);
        	
            return enderec;
            
        } catch (Exception e) {
            throw new Exception("ERRO: " + e);
        }
    }
    
}
