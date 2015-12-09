import java.util.Random;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author danielsilva
 *
 */

public class JogoForca {

	/**
	 * @param args
	 * @return 
	 */
	public static String randomPalavra (){
		String palavra1 =  "Progamacao";
		String palavra2 =  "Matematica";
		String palavra3 =  "TPPI";
		String palavraSelecionada = null;
		
		 Random r = new Random();

	     int i = r.nextInt(3);
	     
	     System.out.print(i);

	        switch (i) {
	        
	            case 0:
	                 palavraSelecionada = palavra1;
	                 break;
	            case 1:
	                 palavraSelecionada = palavra2;
	                 break;
	            case 2:
	                 palavraSelecionada = palavra3;
	                 break;
	            default:
	                break;
	        }
	        
			return palavraSelecionada;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner letra = new Scanner(System.in);
		System.out.println("Jogo esta prestes a começar .....");
	
		String palavra = randomPalavra();
		System.out.println(palavra);
        
		System.out.println("A Palavra foi gerada, intruduza as letras para a palavra correcta so tem 6 vidas , GOO!!");
		


	}

}
