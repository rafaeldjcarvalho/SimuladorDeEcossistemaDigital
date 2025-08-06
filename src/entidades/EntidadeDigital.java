package entidades;

public abstract class EntidadeDigital {
	
	private String id;
	private int posicaoX;
	private int posicaoY;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getPosicaoX() {
		return posicaoX;
	}
	public void setPosicaoX(int posicaoX) {
		this.posicaoX = posicaoX;
	}
	public int getPosicaoY() {
		return posicaoY;
	}
	public void setPosicaoY(int posicaoY) {
		this.posicaoY = posicaoY;
	}
	
	public abstract String getSimbolo(); // retorna um caractere que representa a entidade no grid, ex: "A" para Agente.
	
	public String getPosicao() { //retorna a posição formatada, ex: "(10, 5)".
		//String pos = "(" + this.getPosicaoX() + ", " + this.getPosicaoY() + ")";
		return "(" + this.getPosicaoX() + ", " + this.getPosicaoY() + ")";
	}
}
