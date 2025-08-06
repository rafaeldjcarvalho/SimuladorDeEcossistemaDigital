package ambiente;

import java.util.HashMap;
import java.util.Map;

import entidades.EntidadeDigital;

public class Ambiente {
	
	private Map<String, EntidadeDigital> grid = new HashMap<String, EntidadeDigital>();
	private int tamanhoMaximoX;
	private int tamanhoMaximoY;
	
	public Ambiente(int tamanhoMaximoX, int tamanhoMaximoY) {
		this.tamanhoMaximoX = tamanhoMaximoX;
		this.tamanhoMaximoY = tamanhoMaximoY;
	}

	public Map<String, EntidadeDigital> getGrid() {
		return grid;
	}

	public int getTamanhoMaximoX() {
		return tamanhoMaximoX;
	}

	public int getTamanhoMaximoY() {
		return tamanhoMaximoY;
	}

	public void adicionarEntidade(EntidadeDigital entidade) {
		// Adiciona uma entidade ao mapa.
		if(getEntidade(entidade.getPosicaoX(), entidade.getPosicaoY()) == null) {
			grid.put(entidade.getPosicao(), entidade);
			System.out.println("Entidade adicionada!");
		} else {
			System.out.println("Existe uma entidade nessa posicao!");
		}
		
	}
	
	public EntidadeDigital getEntidade(int x, int y) {
		// Retorna a entidade em uma dada coordenada, ou null se estiver vazia.
		String posicao = formatarPosicao(x, y);
		if(grid.containsKey(posicao)) {
			return grid.get(posicao);
		}
		return null;
	}
	
	public void removerEntidade(int x, int y) {
		// Remove a entidade de uma coordenada.
		if(getEntidade(x, y) == null) {
			System.out.println("Nao ha entidade nessa coordenada.");
		} else {
			String posicao = formatarPosicao(x, y);
			grid.remove(posicao);
			System.out.println("Entidade removida! Posicao: " + posicao + " esta livre.");
		}
	}
	
	public void exibirGrid() {
		// Percorre as dimensões do grid e, para cada célula, consulta o HashMap para imprimir o símbolo 
		// da entidade ou um "." se a célula estiver vazia.
		for(int x = 1; x <= tamanhoMaximoX; x++) {
			for(int y = 1; y <= tamanhoMaximoY; y++) {
				EntidadeDigital entidade = getEntidade(x, y);
				if(entidade == null) {
					System.out.print(". ");
				} else {
					System.out.print(entidade.getSimbolo() + " ");
				}
			}
			System.out.println();
		}
	}
	
	private String formatarPosicao(int x, int y) {
		return "(" + x + ", " + y + ")";
	}
}
