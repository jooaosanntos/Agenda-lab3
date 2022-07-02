package agenda;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Interface com menus texto para manipular uma agenda de contatos.
 * 
 * @author nazarenoandrade e João Santos
 *
 */
public class MainAgenda {

	/**
	 * Método main
	 * 
	 * @param args um array de Strings
	 * */
	public static void main(String[] args) {
		Agenda agenda = new Agenda();

		System.out.println("Carregando agenda inicial");
		try {
			/*
			 * Essa é a maneira de lidar com possíveis erros por falta do arquivo. 
			 */
			carregaAgenda("agenda_inicial.csv", agenda);
		} catch (FileNotFoundException e) {
			System.err.println("Arquivo não encontrado: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("Erro lendo arquivo: " + e.getMessage());
		}

		Scanner scanner = new Scanner(System.in);
		String escolha = "";
		while (true) {
			escolha = menu(scanner);
			comando(escolha, agenda, scanner);
		}

	}

	/**
	 * Exibe o menu e captura a escolha do/a usuário/a.
	 * 
	 * @param scanner Para captura da opção do usuário.
	 * @return O comando escolhido.
	 */
	private static String menu(Scanner scanner) {
		System.out.print(
				"\n---\nMENU\n" + 
						"(C)adastrar Contato\n" +
						"(L)istar Contatos\n" +
						"(E)xibir Contato\n" +
						"(F)avoritos\n" +
						"(A)dicionar Favorito\n" +
						"(T)ags\n" +
						"(R)Remover Contato\n" +
						"(M)udar Telefone\n" +
						"(Re)mover Tag\n" +
						"(Rem)over Favorito\n" +
						"(B)uscar Contato por Nome\n" +
						"(BU)scar Contato por Sobrenome\n" +
						"(BUS)car Contato por tag\n" +
						"(S)air\n" +
						"\n" +
						"Opção> ");
		return scanner.nextLine().toUpperCase();
	}

	/**
	 * Interpreta a opção escolhida por quem está usando o sistema.
	 * 
	 * @param opcao   Opção digitada.
	 * @param agenda  A agenda que estamos manipulando.
	 * @param scanner Objeto scanner para o caso do comando precisar de mais input.
	 */
	private static void comando(String opcao, Agenda agenda, Scanner scanner) {
		switch (opcao) {
		case "C":
			cadastraContato(agenda, scanner);
			break;
		case "L":
			listaContatos(agenda);
			break;
		case "E":
			exibeContato(agenda, scanner);
			break;
		case "F":
			listarFavoritos(agenda);
			break;
		case "A":
			adicinarFavorito(agenda, scanner);
			break;
		case "T":
			adicionarTag(agenda, scanner);
			break;
		case "R":
			removeContato(agenda, scanner);
			break;
		case "M":
			editaTelefone(agenda, scanner);
			break;
		case "RE":
			removeTag(agenda, scanner);
			break;
		case "REM":
			removeFavorito(agenda, scanner);
			break;
		case "B":
			buscaContatoNome(agenda, scanner);
			break;
		case "BU":
			buscaContatoSobrenome(agenda, scanner);
			break;
		case "BUS":
			buscaContatoTag(agenda, scanner);
			break;
		case "S":
			sai();
			break;
		default:
			System.out.println("Opção inválida!");
		}
	}
	
	/**
	 * Método que pega o que é necessário e faz o objeto agenda adiconar um contato do seu array de contatos no seu array de favoritos
	 * 
	 * @param agenda uma referência ao objeto Agenda
	 * @param scanner uma referência ao objeto Scanner
	 * */
	private static void adicinarFavorito(Agenda agenda, Scanner scanner) {
		System.out.print("Contato> ");
		int contato = Integer.parseInt(scanner.nextLine());
		System.out.print("Posição do Favorito> ");
		int posicao = Integer.parseInt(scanner.nextLine());
		if (agenda.jaExisteEmFavoritos(contato)) {
			System.out.println("O CONTATO JÁ É FAVORITO");
		} else {
			agenda.adicinarFavorito(contato, posicao);
			System.out.println("CONTATO FAVORITADO NA POSIÇÃO " + posicao + "!");			
		}
	}
	
	/**
	 * Método que imprime a lista de contatos favoritos do Objeto Agenda.
	 * */
	private static void listarFavoritos(Agenda agenda) {
		String[] favoritos = agenda.getFavoritos();
		for (int i = 0; i < favoritos.length; i++) {
			if (favoritos[i] != null) {
				System.out.println(favoritos[i]);
			}
		}
	}

	/**
	 * Imprime lista de contatos da agenda.
	 * 
	 * @param agenda A agenda sendo manipulada.
	 */
	private static void listaContatos(Agenda agenda) {
		System.out.println("\nLista de contatos: ");
		String[] contatos = agenda.getContatos();
		for (int i = 0; i < contatos.length; i++) {
			if (contatos[i] != null) {
				System.out.println(contatos[i]);
			}
		}
	}

	/**
	 * Imprime os detalhes de um dos contatos da agenda, caso ele exista. Caso contrário, imprimi "POSICAO INVALIDA!" 
	 * 
	 * @param agenda A agenda.
	 * @param scanner Scanner para capturar qual contato.
	 */
	private static void exibeContato(Agenda agenda, Scanner scanner) {
		System.out.print("\nQual contato> ");
		int posicao = Integer.parseInt(scanner.nextLine());
		String contato = agenda.getContato(posicao);
		System.out.print(contato);
	}
	
	/**
	 * Cadastra um contato na agenda. 
	 * 
	 * @param agenda A agenda.
	 * @param scanner Scanner para pedir informações do contato.
	 */
	private static void cadastraContato(Agenda agenda, Scanner scanner) {
		System.out.print("\nPosição na agenda> ");
		int posicao = Integer.parseInt(scanner.nextLine());
		System.out.print("\nNome> ");
		String nome = scanner.nextLine();
		System.out.print("\nSobrenome> ");
		String sobrenome = scanner.nextLine();
		System.out.print("\nTelefone> ");
		String telefone = scanner.nextLine();
		
		if (posicao < 1 || posicao > 100) {
			System.out.println("POSIÇÃO INVÁLIDA");
		} else if (nome.equals("")) {
			System.out.println("CONTATO INVALIDO");
		} else if (telefone.equals("")) {
			System.out.println("CONTATO INVALIDO");
		}  else {
			if (agenda.verificarExistencia(nome, sobrenome)) {
				System.out.println("CONTATO JA CADASTRADO");				
			} else {
				agenda.cadastraContato(posicao, nome, sobrenome, telefone);
				System.out.println("CADASTRO REALIZADO");
			}
		}
	}
	
	/**
	 * Método que obtém as informações necessárias e chama o método adicionarTag do objeto Agenda
	 * 
	 * @param agenda uma instância do objeto Agenda
	 * @param scanner uma instância do objeto Scanner
	 * */
	private static void adicionarTag(Agenda agenda, Scanner scanner) {
		System.out.print("Contato(s)> ");
		String[] contatos = scanner.nextLine().split(" ");
		System.out.print("Tag> ");
		String tag = scanner.nextLine();
		System.out.print("Posicao tag> ");
		int posicaoTag = Integer.parseInt(scanner.nextLine());
		agenda.adicionarTag(contatos, tag, posicaoTag);
	}
	
	/**
	 * Obtém a posição de alguns contatos e passa esses valores para o método removerContatos do Objeto Agenda, para que os contato sejam 
	 * removidos do array de contatos e do array de favoritos
	 * 
	 * @param agenda uma instância do objeto Agenda
	 * @param scanner uma instância do objeto Scanner 
	 * */
	private static void removeContato(Agenda agenda, Scanner scanner) {
		System.out.print("Contato(s)> ");
		String[] posicoesContatos = scanner.nextLine().split(" ");
		agenda.removeContatos(posicoesContatos);
	}

	/**
	 * Obtém a posição de um Contato e um número de telefone e chama o método editaTelefone de Agenda passando essas
	 * informações por parâmetro
	 *  
	 * @param agenda uma instância do objeto Agenda
	 * @param scanner uma instância do objeto Scanner 
	 * */
	private static void editaTelefone(Agenda agenda, Scanner scanner) {
		System.out.print("Posição do Contato> ");
		int contato = Integer.parseInt(scanner.nextLine());
		System.out.print("Novo telefone> ");
		String telefone = scanner.nextLine();
		agenda.editaTefefone(contato, telefone);
	}
	
	/**
	 * Obtém a posição de um Contato, a posição de uma Tag e chama o método removeTag de Agenda passando essas
	 * informações por parâmetro
	 *  
	 * @param agenda uma instância do objeto Agenda
	 * @param scanner uma instância do objeto Scanner 
	 * */
	private static void removeTag(Agenda agenda, Scanner scanner) {
		System.out.print("Posição do Contato> ");
		int contato = Integer.parseInt(scanner.nextLine());
		System.out.print("Posição da tag> ");
		int tag = Integer.parseInt(scanner.nextLine());
		agenda.removeTag(contato, tag);
	}
	
	/**
	 * Obtém a posição de um Contato Favorito e chama o método removeFavorito da classe Agenda passando essa informação 
	 * como parâmetro
	 * 
	 * @param agenda uma instância do objeto Agenda
	 * @param scanner uma instância do objeto Scanner
	 * */
	private static void removeFavorito(Agenda agenda, Scanner scanner) {
		System.out.print("Posição do Favorito> ");
		int favorito = Integer.parseInt(scanner.nextLine());
		agenda.removeFavorito(favorito);
	}
	
	/**
	 * Obtém o nome de um Contato e chama o método buscaContatoNome da classe Agenda passando essa informação 
	 * como parâmetro
	 * 
	 * @param agenda uma instância do objeto Agenda
	 * @param scanner uma instância do objeto Scanner
	 * */
	private static void buscaContatoNome(Agenda agenda, Scanner scanner) {
		System.out.print("Nome do Contato> ");
		String nome =  scanner.nextLine();
		String contatos = agenda.buscaContatoNome(nome);
		System.out.print(contatos);
	}
	
	/**
	 * Obtém o sobrenome de um Contato e chama o método buscaContatoSobrenome da classe Agenda passando essa informação 
	 * como parâmetro
	 * 
	 * @param agenda uma instância do objeto Agenda
	 * @param scanner uma instância do objeto Scanner
	 * */
	private static void buscaContatoSobrenome(Agenda agenda, Scanner scanner) {
		System.out.print("Sobrenome do Contato> ");
		String sobrenome =  scanner.nextLine();
		String contatos = agenda.buscaContatoSobrenome(sobrenome);
		System.out.print(contatos);
	}
	
	/**
	 * Obtém a tag de um Contato e chama o método buscaContatoSobrenome da classe Agenda passando essa informação 
	 * como parâmetro
	 * 
	 * @param agenda uma instância do objeto Agenda
	 * @param scanner uma instância do objeto Scanner
	 * */
	private static void buscaContatoTag(Agenda agenda, Scanner scanner) {
		System.out.print("Tag> ");
		String tag =  scanner.nextLine();
		String contatos = agenda.buscaContatoTag(tag);
		System.out.print(contatos);
	}

	/**
	 * Sai da aplicação.
	 */
	private static void sai() {
		System.out.println("\nVlw flw o/");
		System.exit(0);
	}

	/**
	 * Lê uma agenda de um arquivo csv. 
	 * 
	 * @param arquivoContatos O caminho para o arquivo.
	 * @param agenda A agenda que deve ser populada com os dados. 
	 * @throws IOException Caso o arquivo não exista ou não possa ser lido.
	 */
	private static void carregaAgenda(String arquivoContatos, Agenda agenda) throws FileNotFoundException, IOException {
		LeitorDeAgenda leitor = new LeitorDeAgenda();
		
		int carregados =  leitor.carregaContatos(arquivoContatos, agenda);
		System.out.println("Carregamos " + carregados + " registros.");
	}
	
}
