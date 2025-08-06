<h1 align="center" style="font-weight: bold;">Simulador de um ecossistema digital 💻</h1>

<p align="center">
    <a href="#technologies">Tecnologias</a> • 
    <a href="#started">Começando</a> • 
    <a href="#functions">Funcionalidades</a> •
    <a href="#colab">Collaborators</a> •
</p>

<p align="center">
    <b>Criar uma aplicação de console em Java que simula um ecossistema digital. Neste ecossistema, "Agentes" autônomos se movem em um ambiente 2D (grid), consomem "Recursos" para ganhar energia e devem evitar "Obstáculos". O projeto focará em modelagem, gerenciamento de estado e lógica de interação.</b>
</p>

<h2 id="technologies">💻 Tecnologias</h2>

Lista de todas as Tecnologias usadas:
- Java

<h2 id="started">🚀 Começando</h2>

Aqui está a descrição detalhada para rodar o projeto localmente.

<h3>Pré-requisitos</h3>

Aqui está a lista de todos os pré-requisitos necessários para rodar o projeto.

- [Java JDK 17+](https://adoptium.net/pt-BR/temurin/releases?version=17)
- [Git](https://git-scm.com/downloads)
- IDE (opcional):
    - Intellij
    - VSCode
    - Eclipse

<h3>Clonando projeto</h3>

Como clonar o projeto

```bash
git clone https://github.com/rafaeldjcarvalho/SimuladorDeEcossistemaDigital
```

<h3>Descrição Geral</h3>

O mundo da simulação é um grid 2D de tamanho configurável (ex: 20x20). Cada célula do grid pode estar vazia ou conter uma única entidade. 

As entidades são a base do nosso ecossistema e podem ser de três tipos:
- Agente Autônomo: Uma entidade que pode se mover e realizar ações.
- Recurso: Uma entidade estática que, quando consumida por um Agente, fornece energia.
- Obstáculo: Uma entidade estática que bloqueia o movimento dos Agentes.

A simulação ocorre em "turnos". A cada turno, cada Agente tenta executar uma ação.

<h3>Conceitos aplicados</h3>

- Programação Orientada a Objetos: Herança, Polimorfismo, Encapsulamento.
- Estruturas de Dados: HashMap para o gerenciamento eficiente do grid e Stack para o histórico de ações.
- Lógica de Programação: Controle de fluxo (loops, condicionais), operadores e manipulação de objetos.

<h3>Rodar o projeto</h3>

- Via IDE(IntelliJ, VSCode, Eclipse):
    - Abra o projeto na IDE.
    - Vá até a pasta de cada serviço(BookingApplication, etc).
    - Execute as classes Application de cada serviço.
- Via terminal:
    - Dentro da pasta de cada serviço, execute:
    
    ```bash
    cd SimuladorDeEcossistemaDigital
    java src/simulacao/Simulador.java
    ``` 


<h2 id="functions">📍 Funcionalidades</h2>

No laço principal, permite que o usuário digite comandos simples para controlar a simulação:

- Fluxo Principal


| Functions            | description                                          
|----------------------|-----------------------------------------------------
| <kbd>avancar</kbd>      | Executa um [turno](#turno-simulacao) da simulação.
| <kbd>add agente x y</kbd>     | Adiciona um novo Agente na posição (x, y).
| <kbd>add recurso x y</kbd>     | Adiciona um novo Recurso na posição (x, y).
| <kbd>add obstaculo x y</kbd>     | Adiciona um novo Obstáculo na posição (x, y).
| <kbd>info x y</kbd>     | Se houver uma entidade na posição (x,y), exibe seus detalhes (ID, energia, etc.) e, se for um agente, seu histórico de ações (desempilhando e mostrando o conteúdo da Stack).
| <kbd>sair</kbd>     | Termina a aplicação.


<h3 id="turno-simulacao">Avançar Turno</h3>

A cada turno:
- O grid atual é exibido.
- O simulador percorre todos os Agentes no ambiente.
- Para cada Agente, ele executa uma ação (ex: mover()). 
    - Se o Agente tentar se mover para uma célula com um Recurso, o Agente consome o recurso: a energia do agente aumenta (energia += recurso.getValorEnergetico();), o recurso é removido do ambiente, e o agente move-se para a nova célula.
    - Se o Agente tentar se mover para uma célula com um Obstáculo ou outro Agente, o movimento falha.
    - Toda ação (bem-sucedida ou não) é registrada no historicoAcoes do Agente usando a Stack.

<h2 id="colab">🤝 Collaborators</h2>

Special thank you for all people that contributed for this project.

<table>
  <tr>
    <td align="center">
      <a href="#">
        <img src="https://avatars.githubusercontent.com/u/141766102?v=4" width="100px;" alt="Rafael Profile Picture"/><br>
        <sub>
          <b>Rafael de Jesus Carvalho</b>
        </sub>
      </a>
    </td>
  </tr>
</table>
