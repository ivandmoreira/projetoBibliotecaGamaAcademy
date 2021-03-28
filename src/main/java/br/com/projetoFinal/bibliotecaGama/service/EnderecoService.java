package br.com.projetoFinal.bibliotecaGama.service;

import java.net.URL;

import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class EnderecoService {
	private static String webService = "http://viacep.com.br/ws/";
	private static int codigoSucesso = 200;
	
	public int getCodigoSucesso() {
		return codigoSucesso;
	}
	
	public static String viaCep(String cep) throws Exception {
        String urlChamada = webService + cep + "/json";

        try {
            URL url = new URL(urlChamada);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            if (conn.getResponseCode() != codigoSucesso)
                throw new RuntimeException("HTTP error code : " + conn.getResponseCode());

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream()), "utf-8"));

            String resposta, jsonEmString = "";
            while ((resposta = br.readLine()) != null) {
                jsonEmString += resposta;
            }

            return jsonEmString;
        } catch (Exception e) {
            throw new Exception("ERRO: " + e);
        }
    }
    
}
