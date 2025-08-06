package entidades;

public class Recurso extends EntidadeDigital{
	
	private int valorEnergetico;

	public Recurso(int valorEnergetico) {
		super();
		this.valorEnergetico = valorEnergetico;
	}

	public Recurso() {
		super();
	}

	public int getValorEnergetico() {
		return valorEnergetico;
	}

	public void setValorEnergetico(int valorEnergetico) {
		this.valorEnergetico = valorEnergetico;
	}

	@Override
	public String getSimbolo() {
		return "R";
	}

	@Override
	public String toString() {
		return "Recurso" + 
			   " - ValorEnergetico: " + valorEnergetico + 
			   " - Id: " + getId() + 
			   " - Posicao: " + getPosicao();
	}
}
