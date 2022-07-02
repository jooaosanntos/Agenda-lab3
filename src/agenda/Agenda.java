package agenda;

/**
 * Uma agenda que mantém uma lista de contatos com posições. Podem existir 100 contatos. 
 * 
 * @author nazareno e João Santos
 *
 */
public class Agenda {
	
	private static final int TAMANHO_AGENDA = 100;
	private Contato[] contatos;
	
	private static final int TAMANHO_FAVORITOS = 10;
	private Contato[] favoritos; 

	/**
	 * Cria uma agenda.
	 */
	public Agenda() {
		this.contatos = new Contato[TAMANHO_AGENDA];
		this.favoritos = new Contato[TAMANHO_FAVORITOS];
	}
	
	/**
	 * Retorna um array de Strings, com cada String seguindo o formato: "posiçãoDoContatoNaAgenda nomeCompletoDoContato"
	 * @return Um array com informações gerais de todos os contatos.
	 */
	public String[] getContatos() {
		String[] informacoesContatos = new String[100];
		for (int i = 0; i < 100; i++) {
			if (this.contatos[i] != null) {
				informacoesContatos[i] =  (i + 1) + " - " + this.contatos[i].getNomeCompleto();			
			}
		}
		return informacoesContatos;
	}
	
	/**
	 * Acessa os dados de um contato específico.
	 * @param posicao Posição do contato na agenda.
	 * @return Dados do contato. "POSICAO INVALIDA!" se não há contato na posição.
	 */
	public String getContato(int posicao) {
		posicao -= 1;
		if (this.contatos[posicao] == null) {
			return "\nPOSICAO INVALIDA!\n";
		} else {
			return "\nDados do contato:\n" + this.contatos[posicao].toString();			
		}
	}

	/**
	 * Cadastra um contato em uma posição. Um cadastro em uma posição que já existe sobrescreve o anterior. 
	 * @param posicao Posição do contato.
	 * @param nome Nome do contato.
	 * @param sobrenome Sobrenome do contato.
	 * @param telefone Telefone do contato.
	 */
	public void cadastraContato(int posicao, String nome, String sobrenome, String telefone) {
		posicao -= 1;
		Contato cd = new Contato(nome, sobrenome, telefone);
		this.contatos[posicao] = cd;
	}
	
	
	/**
	 * Verifica se um contato já existe no sistema a partir dos parâmetros nome e sobrenome. Caso algum contato da
	 * lista de contatos possua o nome e sobrenome de entradas, o método retornará o valor booleano true; caso contrário,
	 * retornará o valor booleano false.
	 * 
	 * @param nome Nome do contato a ser verificado
	 * @param sobrenome Sobrenome do contato a ser verificado
	 * 
	 * @return true/false O valor boolano que indica a existência ou não do contato
	 * */
	public boolean verificarExistencia(String nome, String sobrenome) {
		for (int i = 0; i < this.contatos.length; i++) {
			if (this.contatos[i] != null) {
				if (this.contatos[i].getNome().equals(nome)) {
					if (this.contatos[i].getSobrenome().equals(sobrenome)) {
						return true;
					}
				}				
			}
		}
		
		return false;
	}

	/**
	 * Método que adiciona um contato do array de contatos no array de favoritos
	 * 
	 * @param posicaoContatos a posição do contato no array de contatos
	 * @param posicaoFavoritos a posição que o contato ocupará no array de favoritos
	 * */
	public void adicinarFavorito(int posicaoContatos, int posicaoFavoritos) {
		posicaoContatos -= 1;
		posicaoFavoritos -= 1;
		this.contatos[posicaoContatos].favoritarContato();
		if (favoritos[posicaoFavoritos] != null) {
			favoritos[posicaoFavoritos].desfavoritarContato();
		}
		this.favoritos[posicaoFavoritos] = this.contatos[posicaoContatos];			
	}
	
	/**
	 * Método que veifica se um contato já existe na lista de favoritos
	 * 
	 * @param posicaoContatos a posição do contato no array de contatos
	 * 
	 * @return true/false indicando se o contato já está presente no array de favoritos ou não
	 * */
	public boolean jaExisteEmFavoritos(int posicaoContatos) {
		posicaoContatos -= 1;
		for (int i = 0; i < this.favoritos.length; i++) {
			if (this.favoritos[i] !=  null && this.contatos[posicaoContatos] != null) {
				if (this.favoritos[i].ehIgual(this.contatos[posicaoContatos])) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Método que retorna uma Array com a posição e o nome completo de todos os contatos favoritos
	 * 
	 * @return informacoesFavoritos um Array com a posição e o nome completo de todos os contatos favoritos
	 * */
	public String[] getFavoritos() {
		String[] informacoesFavoritos = new String[10];
		for (int i = 0; i < 10; i++) {
			if (this.favoritos[i] != null) {
				informacoesFavoritos[i] =  (i + 1) + " - " + this.favoritos[i].getNomeCompleto();			
			}
		}
		return informacoesFavoritos;
	}
	
	/**
	 * Chama o método adicionarTag(de Contatos) para cada um dos contatos recebidos como parâmetro
	 * 
	 * @param posicoesContatos um array com Strings, cada uma representando a posição de um elemento no array de contatos
	 * @param tag uma String que representa a tag que os contatos irão passar a possuir
	 * @param posicaoTag um inteiro, de 1 a 5, que representa a futura posição da tag no array de tags
	 * */
	public void adicionarTag(String[] posicoesContatos, String tag, int posicaoTag) {
		for (int i = 0; i < posicoesContatos.length; i++) {
			this.contatos[Integer.parseInt(posicoesContatos[i]) - 1].adicionarTag(tag, posicaoTag);
		}
	}
	
	/**
	 * Método que recebe a posição de vários contatos no array de contatos e remove cada um desses contatos do array de contatos.
	 * Também é responsável por chamar o método que remove cada um dos contatos do array de favoritos
	 * 
	 * @param posicoesContatos um array com Strings, cada uma representando a posição de um elemento no array de contatos
	 * */
	public void removeContatos(String[] posicoesContatos) {
		for (int i = 0; i < posicoesContatos.length; i++) {
			this.removeFavorito(this.contatos[Integer.parseInt(posicoesContatos[i]) - 1]);
			this.contatos[Integer.parseInt(posicoesContatos[i]) - 1] = null;
		}
	}
	
	/**
	 * Método que recebe um Contato e, se ele existir no array de favoritos, remove-o do mesmo
	 * 
	 * @param contato uma instância da classe Contato
	 * */
	private void removeFavorito(Contato contato) {
		for (int i = 0; i < this.favoritos.length; i++) {
			if (this.favoritos[i] != null) {
				if (this.favoritos[i].ehIgual(contato)) {
					this.favoritos[i] = null;
				}				
			}
		}
	}
	
	/**
	 * Método que seleciona, através da posição passada, o Contato e e chama o método editaTefone do mesmo, passando a
	 * String que representa o novo telefone
	 * 
	 * @param posicaoContato um inteiro que representa a posição de um Contato no array de Contatos
	 * @param novoTelefone uma String que representa o novo valor do telefone
	 * */
	public void editaTefefone(int posicaoContato, String novoTelefone) {
		posicaoContato -= 1;
		this.contatos[posicaoContato].editaTelefone(novoTelefone);
	}
	
	/**
	 * Método que seleciona, a partir da posição, um Contato e chama o método removeTag desse contato, passando a posição 
	 * de uma tag no array de tags
	 * 
	 * @param posicaoContato um inteiro que representa a posição de um Contato no array de Contatos
	 * @param posicaoTag um inteiro que representa a posição de uma Tag no array de Tags
	 * */
	public void removeTag(int posicaoContato, int posicaoTag) {
		posicaoContato -= 1;
		this.contatos[posicaoContato].removeTag(posicaoTag);
	}
	
	/**
	 * Seleciona o Contato Favorito a partir da posião informada e chama o método desfavoritaContato passado esse contato como
	 * parâmetro. Além disso, seta como null o valor da posição recebida como parâmetro no array de Favoritos
	 * 
	 * @param posicaoFavorito a posição de um elemento no array de Favoritos
	 * */
	public void removeFavorito(int posicaoFavorito) {
		posicaoFavorito -= 1;
		desfavoritaContato(this.favoritos[posicaoFavorito]);
		this.favoritos[posicaoFavorito] = null;
	}
	
	/**
	 * Método que procura um Contato e chama o método desfavoritarContato para ele
	 * 
	 * @param contato uma instância da classe Contato
	 * */
	private void desfavoritaContato(Contato contato) {
		for (int i = 0; i < this.contatos.length; i ++) {
			if (this.contatos[i] != null) {
				if (this.contatos[i].ehIgual(contato)) {
					this.contatos[i].desfavoritarContato();
				}
			}
		}
	}
	
	/**
	 * Método que busca todos os contatos com o nome igual ao passado por parâmetro e retorna todos eles em formato de 
	 * String
	 * 
	 * @param nome uma String que representa o nome de um possível Contato
	 * 
	 * @return toString a representação textual de todos os contatos cujo nome é igual ao nome recebido por parâmetro
	 * */
	public String buscaContatoNome(String nome) {
		String toString = "";
		for (int i = 0; i < this.contatos.length; i++) {
			if (this.contatos[i] != null) {
				if (this.contatos[i].getNome().equals(nome)) {
					toString += i + 1 + " - " + this.contatos[i].getNomeCompleto() + "\n";
				}
			}
		}
		if (toString.length() > 0) {
			toString = "Contatos encontrados:\n" + toString;
		}
		return toString;
	}
	
	/**
	 * Método que busca todos os contatos com o sobrenome igual ao passado por parâmetro e retorna todos eles em formato de 
	 * String
	 * 
	 * @param sobrenome uma String que representa o sobrenome de um possível Contato
	 * 
	 * @return toString a representação textual de todos os contatos cujo sobrenome é igual ao sobrenome recebido por parâmetro
	 * */
	public String buscaContatoSobrenome(String sobrenome) {
		String toString = "";
		for (int i = 0; i < this.contatos.length; i++) {
			if (this.contatos[i] != null) {
				if (this.contatos[i].getSobrenome().equals(sobrenome)) {
					toString += i + 1 + " - " + this.contatos[i].getNomeCompleto() + "\n";
				}
			}
		}
		if (toString.length() > 0) {
			toString = "Contatos encontrados:\n" + toString;
		}
		return toString;
	}
	
	/**
	 * Método que busca todos os Contatos que possuem a Tag recebida por parâmetro e retorna todos eles em formato de 
	 * String
	 * 
	 * @param tag uma String que representa uma possível tag de um contato
	 * 
	 * @return toString a representação textual de todos os contatos que possuem a Tag recebida por parâmetro
	 * */
	public String buscaContatoTag(String tag) {
		String toString = "";
		for (int i = 0; i < this.contatos.length; i++) {
			if (this.contatos[i] != null) {
				if (this.contatos[i].contaimTag(tag)) {
					toString += i + 1 + " - " + this.contatos[i].getNomeCompleto() + "\n";
				}
			}
		}
		if (toString.length() > 0) {
			toString = "Contatos encontrados:\n" + toString;
		}
		return toString;
	}
	
}
