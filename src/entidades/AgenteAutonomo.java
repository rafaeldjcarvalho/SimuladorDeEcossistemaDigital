package entidades;

import java.util.Stack;

public class AgenteAutonomo extends EntidadeDigital {
	
	private int energia;
	private Stack<String> historicoDeAcoes = new Stack<String>();
	
	public AgenteAutonomo() {
		super();
	}

	public AgenteAutonomo(int energia) {
		super();
		this.setEnergia(energia);
	}

	public int getEnergia() {
		return energia;
	}

	public void setEnergia(int energia) {
		this.energia = energia;
	}

	public Stack<String> getHistoricoDeAcoes() {
		return historicoDeAcoes;
	}

	public void setHistoricoDeAcoes(Stack<String> historicoDeAcoes) {
		this.historicoDeAcoes = historicoDeAcoes;
	}

	@Override
	public String getSimbolo() {
		return "A";
	}
	
	public void mover() {
		// Implementa a lógica de movimento do agente (ex: mover-se aleatoriamente para uma célula adjacente).
		int numeroAleatorio = (int) (Math.random() * 4);
		switch(numeroAleatorio) {
			case 0:
				moverPraCima();
				break;
			case 1:
				moverPraBaixo();
				break;
			case 2:
				moverPraEsquerda();
				break;
			case 3:
				moverPraDireita();
				break;
			default:
				System.out.println("Não pode mover.");
				break;
		}
	}
	
	public void registrarAcao(String acao) {
		// Adiciona uma descrição da ação (ex: "Moveu-se para (11, 5)") ao historicoAcoes.
		this.historicoDeAcoes.add(acao);
	}
	
	private void moverPraCima() {
		this.setPosicaoX(this.getPosicaoX() - 1);
	}
	
	private void moverPraBaixo() {
		this.setPosicaoX(this.getPosicaoX() + 1);
	}
	
	private void moverPraEsquerda() {
		this.setPosicaoY(this.getPosicaoY() - 1);
	}
	
	private void moverPraDireita() {
		this.setPosicaoY(this.getPosicaoY() + 1);
	}

	@Override
	public String toString() {
		return "AgenteAutonomo" +
			   " - Energia: " + energia + 
			   " - Id: " + getId() +
			   " - Posicao: " + getPosicao() +
			   " - HistoricoDeAcoes: " + historicoDeAcoes;
	}
	
	
}
