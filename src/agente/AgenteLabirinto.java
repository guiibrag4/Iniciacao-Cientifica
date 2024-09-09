package agente;

import ambiente.Labirinto;
import geral.PosicaoXY;

public class AgenteLabirinto {
	
	/* Instância um objeto da classe "Labirinto", outro da classe "MovimentosAgenteLabirinto", outro da clas-
	 * se "PosicaoXY" e cria uma variável do tipo inteiro com nome "pilhaMovimentos", para fazer a lógica
	 * da quantidade de movimentos e ver se o robô limpará algo ao redor. */
    private Labirinto labirinto;
    private MovimentosAgenteLabirinto movimento;
    private PosicaoXY posXY;
    private int pilhaMovimentos;

    
    /* Recebe como parâmetro da função um objeto da classe "Labirinto", instânciando localmente. Depois com
     * "this.labirinto = labirinto" está armazenando o valor na variável global.
     
     * labirinto.setAgente(this) está definindo e setando o agente nesse labirinto. essa linha define o agente dentro do labirinto, faci-
     * litando o acesso a esse agente para operações como atualização de posição, verificação de estado, entre outras interações que o 
     * agente realiza no ambiente do labirinto.
     
     * Depois, define a posição inicial do Agente com o construtor padrão, porém, passando os parâmetros na classe "Main" o segundo 
     * construtor (o construtor com sobrecarga), sobrescreve os valores definidos pelo construtor padrão.
     
     * Após isso, o agente faz o primeiro movimento dele para a direita.  */
    public AgenteLabirinto(Labirinto labirinto) {
        this.labirinto = labirinto;
        labirinto.setAgente(this);
        this.posXY = new PosicaoXY();
        this.movimento = MovimentosAgenteLabirinto.DIREITA;
    }

    
    /* Se a pilha de movimentos ultrapassar o limite de 4 tentativas (CIMA, BAIXO, ESQUERDA e DIREITA), então o código é encerrado*/
    public void movimentar() {
       /* if (this.pilhaMovimentos >= 4) {
            return;
        } */
    	
    	// ^
    	// |
    	// |
    	
    	/* A condição de verificação do tamanho da pilha foi removida, assim, os movimentos ficam de forma infinita, sem ter uma quantidade
    	definida. */
        PosicaoXY proximoMovimento = retornarMovimento();
        String valor = this.labirinto.retornarValorPosicaoLabirinto(proximoMovimento);

        if (valor.equals("L") || valor.equals("*A*")) {
            proximoMovimento();
            // aumentarPilha(); // removido tbm, já que n precisa mais utilizar a pilha para limitar o número de tentativas de move;
            movimentar();
        } else {
            this.labirinto.limpar();
            this.posXY = proximoMovimento;
        }
        labirinto.sujarLabirinto();
    }

    private void aumentarPilha() {
        this.pilhaMovimentos++;
    }
    
    /* Determina o próximo movimento do agente de forma cíclica. CIMA -> BAIXO -> ESQUERDA -> DIREITA -> CIMA */

    private void proximoMovimento() {
        switch(this.movimento) {
            case CIMA:
                this.movimento = MovimentosAgenteLabirinto.BAIXO;
                break;
            case BAIXO:
                this.movimento = MovimentosAgenteLabirinto.ESQUERDA;
                break;
            case ESQUERDA:
                this.movimento = MovimentosAgenteLabirinto.DIREITA;
                break;
            case DIREITA:
                this.movimento = MovimentosAgenteLabirinto.CIMA;
                break;
        }
    }

    public PosicaoXY retornarMovimento() {
        int retornoPosX = this.posXY.getPosX();
        int retornoPosY = this.posXY.getPosY();
        switch(movimento) {
            case CIMA:
                if (retornoPosX > 0) {
                    retornoPosX -= 1;
                }
                break;
            case BAIXO:
                if (retornoPosX < this.labirinto.getTamanhoLabirinto() - 1) {
                    retornoPosX += 1;
                }
                break;
            case ESQUERDA:
                if (retornoPosY > 0) {
                    retornoPosY -= 1;
                }
                break;
            case DIREITA:
                if (retornoPosY < this.labirinto.getTamanhoLabirinto() - 1) {
                    retornoPosY += 1;
                }
                break;
        }
        return new PosicaoXY(retornoPosX, retornoPosY);
    }

    public PosicaoXY getPosicao() {
        return this.posXY;
    }

    public boolean isAindaLimpando() {
        return pilhaMovimentos <4;
    }

    public void zerarPilha() {
        this.pilhaMovimentos = 0;
    }

    public void setPosicao(PosicaoXY posicaoXY) {
        this.posXY = posicaoXY;
    }
}
