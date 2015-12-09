package quatroemum;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;

/**
 * 
 * @author diogogomes<br>
 *
 * É a classe responsavel por apresentar o menu, a melhor pontuação em cada jogo e fazer a ligação aos restantes modulos.
 * 	
 *
 */

public class Menu {

	public static void main(String[] args) {
		criarFicheiros();
		Scanner scan = new Scanner(System.in);
		String acao;
		
		//Ciclo adicionado para garantir que ao introduzir um comando falso o programa não pare
		while(true)
		{
			criarMenuGUI();
			acao = scan.next();
			if(eInteiroValido(acao) && Integer.parseInt(acao) >= 0 && Integer.parseInt(acao) <= 6)
			{
				//Terminar o programa
				if( acao.equals("0") )
				{
					break;
				}
				else if( acao.equals("1") ) //JOGO DO GALO
				{
					
					String [] galoParams = getUltimoJogoGalo();
					if(galoParams.length > 0) //Se existe um jogo gravado
					{
						String response = "";
						while(true)
						{
							System.out.println("Existe um jogo guardado, pretende continuar? (S/N)");
							response = scan.next().toUpperCase();
							if(response.equals("S")) //Se escolheu continuar o jogo
							{
								Galo galo = new Galo(galoParams);
								galo.execGalo();
								break;
							}
							else if(response.equals("N"))//Se escolheu começar um jogo novo
							{
								Galo galo = new Galo();
								galo.execGalo();
								break;
							}
						}
						
					}
					else //se não existem jogos gravados
					{
						Galo galo = new Galo();
						galo.execGalo();
					}
				}
				else if(acao.equals("2"))
				{
					Forca forca = new Forca();
					forca.execForca();
				}
				else if(acao.equals("3"))
				{
					BatalhaNaval bn = new BatalhaNaval();
					bn.execBatalhaNaval();
				}				
				else if(acao.equals("4"))
				{
					Minas minas = new Minas();
					minas.execMinas();
				}
				else if( acao.equals("6") )
				{
					Tops tops = new Tops();
					tops.getTopsMenu();
				}
			}
			else
			{
				System.out.println("Ação inválida!");
			}
		}
		scan.close();
	}
	
	/**
	 * Função responsavel por gerar a parte gráfica do menu 
	 */
	public static void criarMenuGUI()
	{
		getUltimoJogoGalo();
		System.out.println("**********************************************");
		System.out.println("**"+centrar("QUATRO EM UM")+"**");
		System.out.println("**********************************************");
		System.out.println("**                                          **");
		System.out.println("**   1- JOGO DO GALO                        **");
		System.out.println("**                                          **");
		System.out.println("**   2- JOGO DA FORCA                       **");
		System.out.println("**                                          **");
		System.out.println("**   3- BATALHA NAVAL                       **");
		System.out.println("**                                          **");
		System.out.println("**   4- CAMPO DE MINAS                      **");
		System.out.println("**                                          **");
		System.out.println("**   5- CONFIGURAÇÕES                       **");
		System.out.println("**                                          **");
		System.out.println("**   6- TOP 5                               **");
		System.out.println("**                                          **");
		System.out.println("**   0- SAIR                                **");
		System.out.println("**                                          **");
		System.out.println("**********************************************");
		System.out.println("**********************************************");
		System.out.println("**                                          **");
		System.out.println("**"+centrar("MELHORES PONTUAÇÕES")+"**");
		System.out.println("**                                          **");
		System.out.println("**"+centrar("JOGO DO GALO")+"**");
		System.out.println("**"+centrar(getPontuacaoMaximaGalo()) +"**" );
		System.out.println("**                                          **");
		System.out.println("**"+centrar("JOGO DA FORCA")+"**");
		System.out.println("                                              ");
		System.out.println("**                                          **");
		System.out.println("**"+centrar("BATALHA NAVAL")+"**");
		System.out.println("                                              ");
		System.out.println("**                                          **");
		System.out.println("**"+centrar("CAMPO DE MIAS")+"**");
		System.out.println("                                              ");
		System.out.println("**                                          **");
		System.out.println("**********************************************");
		System.out.println("**********************************************");
		
	}
	
	/**
	 * Função que valida numeros inteiros em forma de string.
	 * @param valor
	 * @return String
	 */
	public static boolean eInteiroValido(String valor)
	{
		boolean resposta = true;
		char [] numeros = "1234567890".toCharArray();
		for(int i = 0; i < valor.trim().length(); i++)
		{
			if(valor.charAt(i) != numeros[0] && 
					valor.charAt(i) != numeros[1] && 
					valor.charAt(i) != numeros[2] && 
					valor.charAt(i) != numeros[3] &&
					valor.charAt(i) != numeros[4] &&
					valor.charAt(i) != numeros[5] &&
					valor.charAt(i) != numeros[6] &&
					valor.charAt(i) != numeros[7] &&
					valor.charAt(i) != numeros[8] &&
					valor.charAt(i) != numeros[9] )
			{
				resposta = false;
				break;
			}
			
		}
		return resposta;
	}
	
	/**
	 * Cria todos os ficheiros necessários para guardar dados
	 */
	private static void criarFicheiros()
	{
		criarFicheirosGalo();
		criarFicheirosForca();
	}
	
	/**
	 * Cria os ficheiros relativos ao jogo do galo
	 */
	private static void criarFicheirosGalo()
	{
		criarFicheiro("pontuacoesGalo");
		criarFicheiro("saveGalo");
	}
	
	/**
	 * Cria os ficheiros relativos ao jogo da forca
	 */
	private static void criarFicheirosForca()
	{
		criarFicheiro("pontuacoesForca");
		criarFicheiro("saveForca");
		criarFicheiro("Animais");
		criarFicheiro("Cidades");
		criarFicheiro("Jogadores de Futebol");
		criarFicheiro("Marcas de Carros");
	}
	
	/**
	 * Cria um ficheiro com o nome indicado
	 * @param nome
	 */
	private static void criarFicheiro(String nome)
	{
		File file = new File( nome + ".txt");
		if(!file.exists())
		{
			try {
				file.createNewFile();
			} catch (IOException e) {
				System.out.println("Erro ao criar o ficheiro " + nome);
			}
		}
	}
	
	/**
	 * Apanha a pontuação máxima do jogo do galo
	 * @return
	 */
	private static String getPontuacaoMaximaGalo()
	{
		File pontuacoes = new File("pontuacoesGalo.txt");
		FileReader r;
		BufferedReader br;
		String lines = "";
		String line = "";
		String resultado = "";
		try {
			r = new FileReader(pontuacoes);
			br = new BufferedReader(r);
			line = br.readLine();
			
			if(line != null)
			{
				String[] registo = line.split("@,@");
				String nome = registo[0].split("@:@")[1];
				String vitorias = registo[1].split("@:@")[1];
				resultado =  nome + " - " + vitorias + " Vitorias";
			}
			else
			{
				resultado = "- - - - -";
			}
			
			
		} catch (FileNotFoundException e) {
			System.out.println("Erro ao aceder às pontuações!");
		} catch (IOException e)
		{
			System.out.println("Erro ao aceder às pontuações!");
		}
		return centrar(resultado);
	}
	
	/**
	 * Centra as strings da parte inferior do menu
	 * @param string
	 * @return
	 */
	private static String centrar(String string)
	{
		int nColunas = 42;
		String resultado = "";
		for(int i = 0; i < (nColunas - string.length())/2; i++)
		{
			resultado += " ";
		}
		resultado += string;
		for(int i = 0; i < (nColunas - string.length())/2; i++)
		{
			resultado += " ";
		}
		if(string.length() % 2 != 0)
		{
			resultado += " ";
		}
		return resultado;
	}
	
	/**
	 * Apanhar os resultados do ultimo jogo gravado (se for o caso)
	 * @return
	 */
	private static String[] getUltimoJogoGalo()
	{
		File saveGalo = new File("saveGalo.txt");
		FileReader fr;
		BufferedReader br;
		String line = "";
		String lines = "";
		String[] jogoParams = new String[0];
		try {
			
			fr = new FileReader(saveGalo);
			br = new BufferedReader(fr);
			line = br.readLine();
			while(line != null)
			{
				lines += line+"\n";
				line = br.readLine();
			}
			jogoParams = lines.split("\n");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		if(jogoParams.length<=1)
		{
			jogoParams = new String[0];
		}
		return jogoParams;
	}
	
	/**
	 * Devolve o numero de linhas de um ficheiro
	 * @param ficheiro
	 * @return
	 */
	public static int contaLinhas(String ficheiro)
	{
		File f = new File(ficheiro);
		FileReader fr;
		BufferedReader br;
		int nLinhas = 0;
		String line = "";
		try {
			
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			line = br.readLine();
			while(line != null)
			{
				nLinhas++;
				line = br.readLine();
			}
			fr.close();
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return nLinhas;
	}
	

}
