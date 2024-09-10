import agente.AgenteLabirinto;
import ambiente.Labirinto;
import geral.PosicaoXY;

public class Main{

	public static void main(String[] args) throws InterruptedException	 {
		
		/* Instância um objeto "labirinto" da classe "Labirinto", esse objeto agora poderá ser manipulado para
		utilizar os métodos, gets e setters contidos na classe "Labirinto". o 3 é um parâmetro sendo passado 
		para um construtor
		 */
		Labirinto labirinto = new Labirinto(4);	
		
		
		// método de exibição do labirinto.
		labirinto.exibirLabirinto();
		
		// Instância um objeto "agente" da classe "AgenteLabirinto". Passa o objeto "labirinto" como parâmetro.
		AgenteLabirinto agente = new AgenteLabirinto(labirinto);
		
		// O agente é definido numa nova posição, nas coordenadas x0 e y0.
		agente.setPosicao(new PosicaoXY(0,0));

		/* Enquanto o agente está limpando (é boolean, true or false, e se a pilha de movimentos for < 4, 
		então é considerado true. Se for maior que isso, então é false e sai do loop while.
		
		* O método "zerarPilha()" deixa a pilha zerada após o Agente *A* se locomover, isso significa que
		* ele poderá verificar novamente as posições "CIMA, BAIXO, ESQUERDA, DIREITA" 4 vezes para dar con-
		* tinuidade na limpeza.
		
		* O método "movimentar()" move o agente, claro. Depois o labirinto é exibido e o processo acontece
		* novamente. */
		
		/* while(agente.isAindaLimpando()) {
			agente.zerarPilha();
			agente.movimentar(); 
			labirinto.exibirLabirinto();
			Thread.sleep(1500);
		} */ 
		
		// Transforma o while antigo (o de cima) em um while infinito com o valor true
		while(true) {
		agente.zerarPilha();
		agente.movimentar(); 
		labirinto.exibirLabirinto();
		Thread.sleep(1500);}
	}

}
