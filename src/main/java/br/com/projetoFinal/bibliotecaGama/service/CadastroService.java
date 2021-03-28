package br.com.projetoFinal.bibliotecaGama.service;

import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.projetoFinal.bibliotecaGama.model.Cadastro;
import br.com.projetoFinal.bibliotecaGama.repository.JpaCadastroRepository;

public class CadastroService {
	private static boolean isApelidoValid(String apelido) {
		return apelido.length() >= 3 && apelido.length() <= 20;
	}
	
	public static boolean isTelefoneValid(String numero) {
		boolean isTelefoneValid = false;
	    if (numero != null && numero.length() > 0) {
	        String expression = "^\\(?[1-9]{2}\\)?(?:[2-8]|9[1-9])[0-9]{3}?[0-9]{4}$";
	        Pattern pattern = Pattern.compile(expression);
	        Matcher matcher = pattern.matcher(numero);
	        if (matcher.matches()) {
	        	isTelefoneValid = true;
	        }
	    }
	    return isTelefoneValid;
	}
	
	public static boolean isCpfValid(String cpf) {
		if(!isCpf(cpf)) {
			return false;
		}
		
		JpaCadastroRepository jpaCadastroRepository = new JpaCadastroRepository();
		Cadastro auxCadastro = jpaCadastroRepository.selectCpf(cpf);
		jpaCadastroRepository.fechar();
		if(auxCadastro != null) 
			return false;
		return true;
	}
	
	public static boolean isLoginCpfValid(String login) {
		JpaCadastroRepository jpaCadastroRepository = new JpaCadastroRepository();
		Cadastro auxCadastro = jpaCadastroRepository.selectLogin(login);
		jpaCadastroRepository.fechar();
		if(auxCadastro != null) 
			return false;
		return true;
	}
	
	public static boolean isLoginTelefoneValid(long login) {
		JpaCadastroRepository jpaCadastroRepository = new JpaCadastroRepository();
		Cadastro auxCadastro = jpaCadastroRepository.selectLogin(login);
		jpaCadastroRepository.fechar();
		if(auxCadastro != null) 
			return false;
		return true;
	}
	
	public static boolean isLoginApelidoValid(String login) {
		if(!isApelidoValid(login)) {
			return false;
		}
		
		JpaCadastroRepository jpaCadastroRepository = new JpaCadastroRepository();
		Cadastro auxCadastro = jpaCadastroRepository.selectLogin(login);
		jpaCadastroRepository.fechar();
		if(auxCadastro != null) 
			return false;
		return true;
	}
	
	public static boolean isEmailValid(String email) {
	    boolean isEmailIdValid = false;
	    if (email != null && email.length() > 0) {
	        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
	        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
	        Matcher matcher = pattern.matcher(email);
	        if (matcher.matches()) {
	            isEmailIdValid = true;
	        }
	    }
	    return isEmailIdValid;
	}
	
	private static boolean isCpf(String CPF) {
		// considera-se erro CPF's formados por uma sequencia de numeros iguais
		if (CPF.equals("00000000000") || CPF.equals("11111111111") || CPF.equals("22222222222")
				|| CPF.equals("33333333333") || CPF.equals("44444444444") || CPF.equals("55555555555")
				|| CPF.equals("66666666666") || CPF.equals("77777777777") || CPF.equals("88888888888")
				|| CPF.equals("99999999999") || (CPF.length() != 11))
			return (false);

		char dig10, dig11;
		int sm, i, r, num, peso;

		// "try" - protege o codigo para eventuais erros de conversao de tipo (int)
		try {
			// Calculo do 1o. Digito Verificador
			sm = 0;
			peso = 10;
			for (i = 0; i < 9; i++) {
				// converte o i-esimo caractere do CPF em um numero:
				// por exemplo, transforma o caractere '0' no inteiro 0
				// (48 eh a posicao de '0' na tabela ASCII)
				num = (int) (CPF.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig10 = '0';
			else
				dig10 = (char) (r + 48); // converte no respectivo caractere numerico

			// Calculo do 2o. Digito Verificador
			sm = 0;
			peso = 11;
			for (i = 0; i < 10; i++) {
				num = (int) (CPF.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig11 = '0';
			else
				dig11 = (char) (r + 48);

			// Verifica se os digitos calculados conferem com os digitos informados.
			if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10))) {
				return (true);
			} else {
				return (false);
			}
		} catch (InputMismatchException erro) {
			return (false);
		}
	}
}
