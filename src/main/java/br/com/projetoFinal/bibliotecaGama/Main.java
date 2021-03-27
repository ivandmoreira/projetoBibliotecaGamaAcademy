package br.com.projetoFinal.bibliotecaGama;

import java.util.Scanner;

import br.com.projetoFinal.bibliotecaGama.controller.CadastroController;
import br.com.projetoFinal.bibliotecaGama.controller.LoginController;
import br.com.projetoFinal.bibliotecaGama.controller.LivroController;

public class Main {
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		int option = 0;
			
	do {

		System.out.println("## Escolha uma das opcoes abaixo ##");
		System.out.println("1 - Logar-se");
		System.out.println("2 - Tela de usuario");
		System.out.println("3 - Tela de livro");
		System.out.println("0 - Sair do programa");
		System.out.println("_______________________");
		System.out.print("Digite sua opcao: ");
		
		option = Integer.parseInt(scanner.nextLine());

		switch(option) {
		case 0: break;
		case 1:
			LoginController loginController = new LoginController();
			loginController.logarUsuario(null, null);
			break;
		case 2: 
			CadastroController controllerCadastro = new CadastroController();
			controllerCadastro.run();
			break;
		case 3:
			LivroController livroController = new LivroController();
			livroController.run();
			break;
		default:
			System.out.println("Opcao nao disponivel\n");
			break;
		}
		
	} while(option != 0);

	scanner.close();
	System.out.println("Programa finalizado!");
}

}
