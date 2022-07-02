package agenda;

/**
 * Classe repositório que representa um contato. Todo contato possui um nome, um sobrenome, um número de telefone e pode estar na lista
 * de favoritos ou não
 * 
 * @author João Santos
 * */

public class Contato {
	private String nome;
	private String sobrenome;
	private String telefone;
	private boolean ehFavorito;
	private String[] tags;
	
	/**
	 * Constrói um contato
	 * 
	 * @param nome uma String que representa um nome
	 * @param sobrenome uma String que representa um sobrenome
	 * @param telefone uma String que representa um telefone
	 * */
	public Contato(String nome, String sobrenome, String telefone) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.telefone= telefone;
		this.ehFavorito = false;
		this.tags = new String[5];
	}
	
	/**
	 * Método que fornece o nome do contato atual
	 * 
	 * @return String o nome do contato atual
	 * */
	public String getNome() {
		return this.nome;
	}
	
	/**
	 * Método que fornece o sobrenome do contato atual
	 * 
	 * @return String o sobrenome do contato atual
	 * */
	public String getSobrenome() {
		return this.sobrenome;
	}
	
	/**
	 * Método que retorna informações gerais sobre o contato atual
	 * 
	 * @return informacoesGerais uma String com informações gerais sobre o contato atual
	 * */
	public String toString() {
		String informacoesGerais = this.nome + " " + this.sobrenome + "\n" + this.telefone +  "\n";
		for (int i  = 0; i < this.tags.length; i++) {
			if(this.tags[i] != null) {
				informacoesGerais += this.tags[i] + " ";
			}
		}
		if(this.ehFavorito) {
			return "❤️ " + informacoesGerais;
		} else {
			return informacoesGerais;
		}
	}
	
	/**
	 * Método que fornece o nome completo do contato atual
	 * 
	 * @return String o nome completo do usuário
	 * */
	public String getNomeCompleto() {
		return this.nome + " " + this.sobrenome;
	}
	
	/**
	 * Método que favorita o contato atual
	 * 
	 * */
	public void favoritarContato() {
		this.ehFavorito = true;
	}
	
	/**
	 * Método que desfavorita o contato atual
	 * 
	 * */
	public void desfavoritarContato() {
		this.ehFavorito = false;
	}
	
	/**
	 * Método que verifica se o contato atual é igual ao contato recebido
	 * 
	 * @param contato um objeto Contato recebido
	 * 
	 * @return true/false indicando se os contatos são iguais ou não
	 * */
	public boolean ehIgual(Contato contato) {
		if (this.nome.equals(contato.getNome()) && this.sobrenome.equals(contato.getSobrenome())) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Método que adiciona uma tag ao array de tags do contato atual
	 * 
	 * @param tag uma String que representa a tag que o contato passará a possuir
	 * @param posicaoTag um inteiro, de 1 a 5, que representa a futura posição da tag no array de tags do contato atual
	 * */
	public void adicionarTag(String tag, int posicaoTag) {
		this.tags[posicaoTag - 1] = tag;
	}
	
	/**
	 * Método que altera o valor da varável telefone
	 * 
	 * @param novoTelefone uma String com o valor do novo telefone
	 * */
	public void editaTelefone(String novoTelefone) {
		this.telefone = novoTelefone;
	}
	
	/**
	 * Método que seleciona, a partir da posição passada, uma Tag e seta ela como null
	 * 
	 * @param posicaoTag um inteiro que representa a posição de um elemento no array de Tags
	 * */
	public void removeTag(int posicaoTag) {
		posicaoTag -= 1;
		this.tags[posicaoTag] = null; 
	}
	
	/**
	 * Método que verifica se o Contato atual possui a Tag recebida por parâmetro
	 * 
	 * @param tag String que representa uma tag
	 * 
	 * @return true/false indicando se o Contato atual possui ou não a Tag recebida por parâmetro
	 * */
	public boolean contaimTag(String tag) {
		for (int i = 0; i < this.tags.length; i++) {
			if (this.tags[i] != null) {
				if (this.tags[i].equals(tag)) {
					return true;
				}				
			}
		}
		
		return false;
	}
}
