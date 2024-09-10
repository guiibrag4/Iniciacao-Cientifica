package ambiente;

import agente.AgenteLabirinto;
import geral.PosicaoXY;

import java.util.Random; 

public class Labirinto {

    private int tamanhoLabirinto;
    private String[][] labirinto;	
    private AgenteLabirinto agente;
    private Random random;  // Adiciona um objeto Random
    private PosicaoXY ultimaPosicaoLimpa;  // Adiciona uma variável para rastrear a última posição limpa

    public Labirinto(int tamanhoLabirinto) {
        this.tamanhoLabirinto = tamanhoLabirinto;
        this.random = new Random();
        this.construirNovoLabirinto();
    }
    
    public void sujarLabirinto() {
        int x, y;
        PosicaoXY posAgente = this.agente.getPosicao();
        
        // Continua gerando novas coordenadas até encontrar uma posição diferente da do agente
        do {
            x = random.nextInt(this.tamanhoLabirinto);
            y = random.nextInt(this.tamanhoLabirinto);
        } while (x == posAgente.getPosX() && y == posAgente.getPosY() || 
                (ultimaPosicaoLimpa != null && x == ultimaPosicaoLimpa.getPosX() && y == ultimaPosicaoLimpa.getPosY()));
        
        // Suja a posição escolhida
        labirinto[x][y] = "S"; 
    }


    private void construirNovoLabirinto() {
        labirinto = new String[this.tamanhoLabirinto][this.tamanhoLabirinto];
        for (int i = 0; i < this.tamanhoLabirinto; i++) {
            for (int j = 0; j < this.tamanhoLabirinto; j++) {
                this.labirinto[i][j] = "S";
            }
        }
    }

    public void exibirLabirinto() {
        atualizarPosicaoAgente();
        for (int i = 0; i < tamanhoLabirinto; i++) {
            for (int j = 0; j < tamanhoLabirinto; j++) {
                if (labirinto[i][j].equals("*A*")) {
                    System.out.print("|" + labirinto[i][j] + "|");
                } else {
                    System.out.print("| " + labirinto[i][j] + " |");
                }
            }
            System.out.println("");
        }
        System.out.println("");
    }

    private void atualizarPosicaoAgente() {
        if (this.agente != null) {
            PosicaoXY posAgente = this.agente.getPosicao();
            labirinto[posAgente.getPosX()][posAgente.getPosY()] = "*A*";  // "*A*" indica a posição atual do agente.
        }
    }

    public int getTamanhoLabirinto() {
        return this.tamanhoLabirinto;
    }


    public String retornarValorPosicaoLabirinto(PosicaoXY posicao) {
        return this.labirinto[posicao.getPosX()][posicao.getPosY()];
    }

    public void setAgente(AgenteLabirinto agente) {
        this.agente = agente;
    }

    public void limpar() {
        PosicaoXY posicao = this.agente.getPosicao();
        labirinto[posicao.getPosX()][posicao.getPosY()] = "L";  // "L" indica que a célula foi limpa.
        ultimaPosicaoLimpa = posicao;  // Atualiza a última posição limpa (para ter certeza que ela vai atualizar)
    }
}
