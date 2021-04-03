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
		List<Cadastro> cadastro = jpaCadastroRepository.selectAll();
		jpaCadastroRepository.fechar();
		return cadastro;
	}

	public Cadastro buscarPorId(Integer id) {
		jpaCadastroRepository = new JpaCadastroRepository();
		Cadastro cadastro = jpaCadastroRepository.select(id);
		jpaCadastroRepository.fechar();
		return cadastro;
	}

	public Cadastro cadastrar(Cadastro cadastro) {
		scanner = new Scanner(System.in);
		System.out.println("tela de cadastrar pessoas");

		String cpf;
		do {
			System.out.print("Cpf: ");
			cpf = scanner.nextLine();
		} while (!isCpfValid(cpf));

		cadastro.setCpf(cpf);

		String nome;
		do {
			System.out.print("Nome: ");
			nome = scanner.nextLine();
		} while (!validaNome(nome));

		cadastro.setNome(trataNome(nome));

		String email;
		do {
			System.out.print("E-mail email@email.com: ");
			email = scanner.nextLine();
		} while (!isEmailValid(email));

		cadastro.setEmail(email);

		String telefone;
		do {
			System.out.print("Telefone xxxxxxxxxxx: ");
			telefone = scanner.nextLine();
		} while (!isTelefoneValid(telefone));

		cadastro.setTelefone(telefone);

		System.out.println("Agora... vamos cadastrar seu endereco!");

		String cep;
		do {
			System.out.print("Cep xxxxx-xxx: ");
			cep = scanner.nextLine();
		} while (!isCepValid(cep));

		Gson gson = new Gson();
		Endereco endereco = null;
		try {
			endereco = gson.fromJson(EnderecoService.viaCep(cep), Endereco.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		cadastro.setEndereco(endereco);

		String login;
		do {
			login = definirLogin(cadastro);
		} while (login == null);

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

	private String definirLogin(Cadastro cadastro) {
		System.out.println("Para finalizar... seu login podera ser realizado de 3 formas:");

		String option = "0";

		System.out.println("1 - com Cpf");
		System.out.println("2 - com Telefone");
		System.out.println("3 - com Apelido de ate 20 caracteres");
		System.out.println("_______________________");

		while (!(option.equals("1") || option.equals("2") || option.equals("3"))) {
			System.out.print("Digite sua melhor forma de logar: ");
			option = scanner.nextLine();
		}

		switch (option) {
		case "1":
			return loginComCpf(cadastro);
		case "2":
			return loginComTelefone(cadastro);
		case "3":
			return loginComApelido();
		default:
			return null;
		}
	}

	private String loginComApelido() {
		System.out.println("Forma selecionada: apelido");

		scanner = new Scanner(System.in);

		String login = scanner.nextLine();

		while (!isApelidoValid(login) && !isLoginValid(login)) {
			if (login != null) {
				System.err.println("Apelido invalido ou indisponivel!");
			}
			System.err.print("Digite seu apelido com no maximo 20 caracteres: ");
			login = scanner.nextLine();
		}
		return login;
	}

	private String loginComTelefone(Cadastro cadastro) {
		String login;
		System.out.println("Forma selecionada: telefone");
		login = cadastro.getTelefone();

		if (isLoginValid(login)) {
			return login;
		} else {
			System.err.println("Esse login ja esta definido na nossa base de dados.");
			return null;
		}
	}

	private String loginComCpf(Cadastro cadastro) {
		String login;
		System.out.println("Forma selecionada: cpf");
		login = cadastro.getCpf();

		if (isLoginValid(login)) {
			return login;
		} else {
			System.err.println("Esse login ja esta definido na nossa base de dados.");
			return null;
		}
	}

	private boolean validaNome(String nome) {
		Pattern pattern = Pattern.compile("[0-9]");
		Matcher matcher = pattern.matcher(nome);
		if (matcher.find()) {
			System.err.println("Nao deve conter numeros!");
			return false;
		}

		pattern = Pattern.compile("[$\\\\%&*()}{@#?><>,|=_+¬-]");
		matcher = pattern.matcher(nome);
		if (matcher.find()) {
			System.err.println("Nao pode conter caracteres especiais.");
			return false;
		}

		if (nome.length() > 70 || nome.length() < 3) {
			System.err.println("Nome invalido, o tamanho do nome deve ter entre 3 e 70 caracteres.");
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
			sb.append(Character.toUpperCase(arr[i].charAt(0))).append(arr[i].substring(1)).append(" ");
		}
		return sb.toString().trim();
	}

	private boolean isApelidoValid(String apelido) {
		if (apelido.length() >= 3 && apelido.length() <= 20) {
			return true;
		} else {
			System.err.println("Apelido invalido ou indisponivel!");
			return false;
		}
	}

	private boolean isCepValid(String cep) {
		Pattern pattern = Pattern.compile("[0-9]{5}-[0-9]{3}");
		Matcher matcher = pattern.matcher(cep);
		if (!matcher.find()) {
			System.err.println("Cep invalido.");
			return false;
		}

		return true;
	}

	private boolean isTelefoneValid(String numero) {
		boolean isTelefoneValid = false;

		if (numero != null && numero.length() > 0) {
			String expression = "^\\(?[1-9]{2}\\)?(?:[2-8]|9[1-9])[0-9]{3}?[0-9]{4}$";
			Pattern pattern = Pattern.compile(expression);
			Matcher matcher = pattern.matcher(numero);
			if (matcher.matches()) {
				isTelefoneValid = true;
			} else {
				System.err.println("Telefone invalido.");
			}
		}

		return isTelefoneValid;
	}

	private boolean isCpfValid(String cpf) {
		if (!isCpf(cpf)) {
			System.err.println("Cpf invalido");
			return false;
		}

		JpaCadastroRepository jpaCadastroRepository = new JpaCadastroRepository();
		Cadastro auxCadastro = jpaCadastroRepository.selectCpf(cpf);
		jpaCadastroRepository.fechar();
		if (auxCadastro != null) {
			System.err.println("Cpf ja cadastrado na base");
			return false;
		}
		return true;
	}

	private boolean isLoginValid(String login) {
		JpaCadastroRepository jpaCadastroRepository = new JpaCadastroRepository();
		Cadastro auxCadastro = jpaCadastroRepository.selectLogin(login);
		jpaCadastroRepository.fechar();
		if (auxCadastro != null)
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
			} else {
				System.err.println("\nE-mail inválido");
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
