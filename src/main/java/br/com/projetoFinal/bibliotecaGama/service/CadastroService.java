package br.com.projetoFinal.bibliotecaGama.service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;

import br.com.projetoFinal.bibliotecaGama.model.Cadastro;
import br.com.projetoFinal.bibliotecaGama.model.Endereco;
import br.com.projetoFinal.bibliotecaGama.repository.JpaCadastroRepository;

public class CadastroService {
	
	private JpaCadastroRepository jpaCadastroRepository;
	private Scanner scanner;

	public List<Cadastro> buscarTodos() {
		jpaCadastroRepository = new JpaCadastroRepository();
		List<Cadastro> cadastro =jpaCadastroRepository.selectAll();
		jpaCadastroRepository.fechar();
		return cadastro;
	}
	
	public Cadastro buscarPorId(Integer id) {
		jpaCadastroRepository = new JpaCadastroRepository();
		Cadastro cadastro = jpaCadastroRepository.select(id);
		jpaCadastroRepository.fechar();
		return cadastro;
	}
	
	public Cadastro cadastrarUsuario(Cadastro cadastro) {
		scanner = new Scanner(System.in);
		System.out.println("tela de cadastrar pessoas\n");
		int option = 0;

		String cpf = null;
		do {
			if (cpf != null)
				System.out.println("Cpf invalido, por favor digite novamente..");
			System.out.print("Digite seu cpf: ");
			cpf = scanner.nextLine();
		} while (!isCpfValid(cpf));

		cadastro.setCpf(cpf);

		String nome = null;
		
		do {
			if (nome != null)
				System.out.println("Nome invalido, por favor digite novamente..");
			System.out.print("Digite seu nome: ");
			nome = scanner.nextLine();
		} while (!validaNome(nome));
		
		cadastro.setNome(trataNome(nome));
		
		String email = null;
		do {
			if (email != null)
				System.out.println("E-mail invalido, por favor digite novamente..");
			System.out.print("Digite seu e-mail: ");
			email = scanner.nextLine();
		} while (!isEmailValid(email));
		
		cadastro.setEmail(email);

		String telefone = null;
		do {
			if (telefone != null)
				System.out.println("Telefone invalido, por favor digite novamente..");
			System.out.print("Digite seu telefone: ");
			telefone = scanner.nextLine();
		} while (!isTelefoneValid(telefone));
		
		cadastro.setTelefone(telefone);

		System.out.println("\nAgora... vamos cadastrar seu endereco!");
		System.out.print("Digite seu cep: ");
		String cep = scanner.nextLine();

		Gson gson = new Gson();
		Endereco endereco = null;
		try {
			endereco = gson.fromJson(EnderecoService.viaCep(cep), Endereco.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		cadastro.setEndereco(endereco);

		System.out.println("\nPara finalizar... seu login podera¡ ser feito de 3 formas:");
		do {
			System.out.println("1 - com Cpf");
			System.out.println("2 - com Telefone");
			System.out.println("3 - com Apelido de ate 20 caracteres");
			System.out.println("_______________________");
			System.out.print("Digite sua melhor forma de logar: ");
			option = Integer.parseInt(scanner.nextLine());
		} while (!(option == 1 | option == 2 | option == 3));

		String login = null;
		if (option == 1) {
			System.out.println("\nForma selecionada: cpf");
			login = cadastro.getCpf();
		} else if (option == 2) {
			System.out.println("\nForma selecionada: telefone");
			login = cadastro.getTelefone();
		} else {
			System.out.println("\nForma selecionada: apelido");

			do {
				if(login != null) {
					System.out.println("Apelido invalido ou indisponivel!");
				}
				System.out.print("Digite seu apelido com no maximo 20 caracteres: ");
				login = scanner.nextLine();
			} while (!isApelidoValid(login) && !isLoginValid(login));
			
		}

		cadastro.setLogin(login);

		System.out.print("Digite sua senha: ");
		String senha = scanner.nextLine();
		cadastro.setSenha(senha);

		jpaCadastroRepository = new JpaCadastroRepository();
		jpaCadastroRepository.insert(cadastro);
		jpaCadastroRepository.fechar();

		System.out.println("Cadastro efetuado com sucesso!\n");
		return cadastro;
	}
		
	private boolean validaNome(String nome) {
		Pattern pattern = Pattern.compile("[0-9]");
		Matcher matcher = pattern.matcher(nome);
		
		if(matcher.find()){
			System.out.println("Nao deve conter numeros!");
			return false;
		}
		
		Pattern pattern2 = Pattern.compile("/[$\\%&*()}{@#?><>,|=_+Â¬-]/");
		Matcher matcher2 = pattern2.matcher(nome);
		if(matcher2.find()){
			System.out.println("Nao pode conter caracteres especiais.");
			return false;
		}

	    if (nome.length() > 70 || nome.length()  < 3) {
			System.out.println("Nome invalido, o tamanho do nome deve ter entre 3 e 70 caracteres.");
			return false;
	    }

    	return true;
	}
	
	private String trataNome(String nome) {
		String after = nome.trim().replaceAll(" +", " ");
	    return toTitleCase(after);
	}
	
	private String toTitleCase(String givenString) {
        String[] arr = givenString.split(" ");
        StringBuffer sb = new StringBuffer();
    
        for (int i = 0; i < arr.length; i++) {
            sb.append(Character.toUpperCase(arr[i].charAt(0)))
                .append(arr[i].substring(1)).append(" ");
        }          
        return sb.toString().trim();
    } 
	
	private boolean isApelidoValid(String apelido) {
		return apelido.length() >= 3 && apelido.length() <= 20;
	}
	
	private boolean isTelefoneValid(String numero) {
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
	
	private boolean isCpfValid(String cpf) {
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
	
	private boolean isLoginValid(String login) {
		JpaCadastroRepository jpaCadastroRepository = new JpaCadastroRepository();
		Cadastro auxCadastro = jpaCadastroRepository.selectLogin(login);
		jpaCadastroRepository.fechar();
		if(auxCadastro != null) 
			return false;
		return true;
	}
	
	private boolean isEmailValid(String email) {
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
	
	private boolean isCpf(String CPF) {
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
