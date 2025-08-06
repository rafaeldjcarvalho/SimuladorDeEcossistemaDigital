package simulacao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ambiente.Ambiente;
import entidades.AgenteAutonomo;
import entidades.EntidadeDigital;
import entidades.Obstaculo;
import entidades.Recurso;

public class Simulador {
	
	private static int idControl = 0;
	private static Scanner tc = new Scanner(System.in);
	private static int turnos = 0;
	private static Ambiente grid = new Ambiente(5, 5);

	public static void main(String[] args) {
		while(true) {
			mostrarGrid();
			menu();
			System.out.print(" -> ");
			String comando = tc.nextLine();
			if(comando.split(" ")[0].toLowerCase().equals("sair")) {
				System.out.println("Turnos executados: " + turnos);
				System.out.println("Encerrando...");
				break;
			}
			analisarComando(comando);
		}
	}
	
	private static void mostrarGrid() {
		System.out.println(" Grid:");
		grid.exibirGrid();
		System.out.println();
	}

	private static void menu() {
		System.out.println(" -- MENU -- ");
		System.out.println("Digite um dos comandos abaixo: ");
		System.out.println(" - [avancar] Executa um turno da simulação.");
		System.out.println(" - [add agente <x> <y>] Adiciona um novo Agente na posição (x, y).");
		System.out.println(" - [add recurso <x> <y>] Adiciona um novo Recurso na posição (x, y).");
		System.out.println(" - [add obstaculo <x> <y>] Adiciona um novo Obstaculo na posição (x, y).");
		System.out.println(" - [info <x> <y>] Exibe os detalhes da entidade na posicao.");
		System.out.println(" - [sair] Termina a aplicacao.");
	}

	private static void analisarComando(String comando) {
		String[] comandoFormatado = comando.toLowerCase().split(" ");
		try {
			if(comandoFormatado[0].equals("avancar")) {
				avancarTurno();
			} else if (comandoFormatado[0].equals("info") && comandoFormatado.length == 3) {
				exibirDetalhes(stringToInt(comandoFormatado[1]), stringToInt(comandoFormatado[2]));
			} else if ((comandoFormatado[0].equals("add") && comandoFormatado[1].equals("agente")) && comandoFormatado.length == 4) {
				AgenteAutonomo agente = new AgenteAutonomo(5);
				adicionarEntidade(agente, stringToInt(comandoFormatado[2]), stringToInt(comandoFormatado[3]));
			} else if ((comandoFormatado[0].equals("add") && comandoFormatado[1].equals("recurso")) && comandoFormatado.length == 4) {
				Recurso recurso = new Recurso(2);
				adicionarEntidade(recurso, stringToInt(comandoFormatado[2]), stringToInt(comandoFormatado[3]));
			} else if ((comandoFormatado[0].equals("add") && comandoFormatado[1].equals("obstaculo")) && comandoFormatado.length == 4) {
				Obstaculo obstaculo = new Obstaculo();
				adicionarEntidade(obstaculo, stringToInt(comandoFormatado[2]), stringToInt(comandoFormatado[3]));
			} else {
				System.out.println("Comando invalido ou falta dados: " + comando);
			}
		} catch(ArrayIndexOutOfBoundsException ex) {
			System.out.println("Dados insuficientes...");
		} catch(Exception ex) {
			System.out.println("Erro: " + ex.getMessage());
		}
	}
	
	private static int stringToInt(String num) {
		return Integer.parseInt(num);
	}
	
	private static void exibirDetalhes(int x, int y) {
		//System.out.println(x + ", " + y);
		EntidadeDigital entidade = grid.getEntidade(x, y);
		if(entidade == null) System.out.println("Nao ha entidade nessa posicao: " + x + ", " + y); else System.out.println(entidade);
	}
	
	private static void adicionarEntidade(EntidadeDigital entidade, int posX, int posY) {
		entidade.setId(entidade.getSimbolo() + ++idControl);
		entidade.setPosicaoX(posX);
		entidade.setPosicaoY(posY);
		grid.adicionarEntidade(entidade);
	}
	
	private static void avancarTurno() {
		// Exibir o grid atual
		mostrarGrid();
		turnos++;
		// Simulador percorre todos os agentes no ambiente
		List<String> idMaps = new ArrayList<String>();
		for(int x = 1; x <= grid.getTamanhoMaximoX(); x++) {
			for(int y = 1; y <= grid.getTamanhoMaximoY(); y++) {
				EntidadeDigital entidade = grid.getEntidade(x, y);
				if((entidade != null && entidade.getSimbolo().equals("A")) && !idMaps.contains(entidade.getId())) {
					// Executar um metodo (ex.: mover())
					// Para um agente se mover de forma valida, o valor da posicao nao pode ser menor ou igual
					// a zero e nao pode ser maior que o numero maximo definido na criacao do grid. Senao o
					// agente podera se mover para fora do grid.
					AgenteAutonomo agente = (AgenteAutonomo) entidade;
					// 1 - Pegar a posicao atual
					int posX = agente.getPosicaoX();
					int posY = agente.getPosicaoY();
					
					// 2 - Mover o agente
					agente.mover();
					
					// 3 - Verificar se a movimentacao foi valida
					if(isMoveValidate(agente.getPosicaoX(), grid.getTamanhoMaximoX()) 
							|| isMoveValidate(agente.getPosicaoY(), grid.getTamanhoMaximoY())) {
						// posicao invalida
						falhaAoMoverAgente("Tentou movimentar-se para " + agente.getPosicao() + ": Invalido!", 
								"Retornou para ", posX, posY, agente);
					} else {
						// posicao valida
						EntidadeDigital entidadeNaPosicao = grid.getEntidade(agente.getPosicaoX(), agente.getPosicaoY());
						if(entidadeNaPosicao == null) { // celula vazia
							moverAgente("Moveu-se para " + agente.getPosicao(), posX, posY, agente);
						} else if(entidadeNaPosicao.getSimbolo().equals("R")) { // celula com recurso
							// Aumentar a energia do agente
							Recurso recurso = (Recurso) entidadeNaPosicao;
							agente.setEnergia(agente.getEnergia() + recurso.getValorEnergetico());
							
							grid.removerEntidade(recurso.getPosicaoX(), recurso.getPosicaoY());
							moverAgente("Moveu-se para " + agente.getPosicao() + " e consumiu recurso +" + recurso.getValorEnergetico() + " energia", posX, posY, agente);
						} else if (entidadeNaPosicao.getSimbolo().equals("O") || entidadeNaPosicao.getSimbolo().equals("A")) { // celula com obstaculo ou agente
							// Movimento falha
							falhaAoMoverAgente("Tentou movimentar-se para " + agente.getPosicao() + ": Invalido!", 
									"Retornou para ", posX, posY, agente);
						}
					}
					idMaps.add(entidade.getId());
				}
			}
		}
	}
	
	private static boolean isMoveValidate(int posicao, int posicaoMaxima) {
		return posicao <= 0 || posicao > posicaoMaxima;
	}
	
	private static void moverAgente(String mensagem, int oldPosX, int oldPosY, AgenteAutonomo novaEntidade) {
		novaEntidade.registrarAcao(mensagem);
		grid.removerEntidade(oldPosX, oldPosY);
		grid.adicionarEntidade(novaEntidade);
	}
	
	private static void falhaAoMoverAgente(String aviso, String mensagem, int posX, int posY, AgenteAutonomo agente) {
		agente.registrarAcao(aviso);
		agente.setPosicaoX(posX);
		agente.setPosicaoY(posY);
		moverAgente(mensagem + agente.getPosicao(), posX, posY, agente);
	}
}
