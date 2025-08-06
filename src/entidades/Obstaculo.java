package entidades;

public class Obstaculo extends EntidadeDigital {

	@Override
	public String getSimbolo() {
		return "O";
	}

	@Override
	public String toString() {
		return "Obstaculo" + 
			   " - Id: " + getId() + 
			   " - Posicao: " + getPosicao();
	}
	
	
}
