package 10-November;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Forca {

	String solucao;
	String palavra;
	String jogador;
	String nomeJogador;
	int pts;
	int tentativas;
	String categoria;
	Scanner scan = new Scanner(System.in);
	
	public Forca(){
		definicoesIniciais();
	}
	
	public Forca(String[] forcaParams)
	{
		
	}
	
	/**
	 * Função responsavel por executar o jogo da forca
	 */
	public void execForca(){
		while(true)
		{
			criaGUI();
			System.out.println("Insira uma letra");
			String letra = scan.next();
			if(letra.length() != 1)
			{
				System.out.println("Deve introduzir 1 letra (nem mais nem menos!)");
			}
			else
			{
				if(letra.equals("0"))
				{
					break;
				}
				else
				{
					validaJogada(letra);
				}
			}
		}
	}
	
	
	/**
	 * Função responsável por 
	 */
	private void criaGUI()
	{
		String gui = "******************************************\n";
		gui +=       "*                                        *\n";
		gui +=       "*              Jogo da Forca             *\n";
		gui +=       "*                                        *\n";
		gui += 		 "******************************************\n";
		gui +=       "******************************************\n";
		gui +=       "*                                        *\n";
		gui +=       "*                                        *\n";
		gui +=       "*"+centrar(categoria)+"*\n";
		gui +=       "*                                        *\n";
		gui +=       "*"+centrar(palavra)+"*\n";
		gui +=       "*                                        *\n";
		gui +=       "*                                        *\n";
		gui += 		 "******************************************\n";
		gui +=       "*                                        *\n";
		gui +=       "*"+centrar("Jogador: " + nomeJogador)+"*\n";
		gui +=       "*"+centrar("Pts: " + pts)+"*\n";
		gui +=		 "*"+centrar("Tentativas Restantes: " + tentativas)+"*\n";
		gui +=       "*                                        *\n";
		gui +=       "*"+centrar("(0 = SAIR)")+"*\n";
		gui +=       "*                                        *\n";
		gui += 		 "******************************************\n";
		gui +=       "******************************************\n";
		System.out.println(gui);
		
	}
	
	/**
	 * Sistema de perguntas para definir variaveis iniciais
	 */
	private void definicoesIniciais()
	{
		defineNomeJogador();
		defineCategoria();
		defineSolucao(categoria);
		tentativas = 6;
		pts = 0;
	}
	
	/**
	 * O utilizador define o nome
	 */
	private void defineNomeJogador()
	{
		System.out.println("Insira o seu nome");
		nomeJogador = scan.next();
	}
	
	/**
	 * O utilizador define a categoria que quer jogar
	 */
	private void defineCategoria()
	{
		String escolha = "0";
		while(!escolha.equals("1") && !escolha.equals("2") && !escolha.equals("3") && !escolha.equals("4"))
		{
			System.out.println("Escolha a categoria:");
			System.out.println("1- Animais");
			System.out.println("2- Cidades");
			System.out.println("3- Jogadores de Futebol");
			System.out.println("4- Marcas de Carros");	
			escolha = scan.next();
		}
		switch(escolha)
		{
			case "1": categoria = "Animais"; break;
			case "2": categoria = "Cidades"; break;
			case "3": categoria = "Jogadores de Futebol"; break;
			case "4": categoria = "Marcas de Carros"; break;
		}
	}
	
	/**
	 * Define uma nova palavra com base na categoria
	 * @param ficheiro
	 */
	private void defineSolucao(String ficheiro)
	{
		File fCategoria = new File(ficheiro + ".txt");
		FileReader fr;
		BufferedReader br;
		try {
			fr = new FileReader(fCategoria);
			br = new BufferedReader(fr);
			int nLinhas = Menu.contaLinhas(ficheiro + ".txt");
			int cont = 0;
			if(nLinhas > 0)
			{
				double nLinha = Math.random() * nLinhas;
				while(cont <= (int)nLinha)
				{
					try {
						solucao = br.readLine();
						if(cont == (int)nLinha)
						{
							palavra = "";
							for(int i = 0; i < solucao.length(); i++)
							{
								palavra += "_";
							}
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
					cont++;
				}
			}
			else
			{
				System.out.println("A categoria escolhida não tem palavras agregadas!");
				br.close();
				fr.close();
				defineCategoria();
				defineSolucao(categoria);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Verifica se a letra introduzida é valida
	 * @param letra
	 */
	private void validaJogada(String letra)
	{
		if(palavra.toUpperCase().contains(letra.toUpperCase()))
		{
			tentativas--;
		}
		else if(solucao.toUpperCase().contains(letra.toUpperCase()))
		{
			for(int i = 0; i < solucao.length(); i++)
			{
				char[] letraChar = letra.toUpperCase().toCharArray();
				char[] palavraTemp = palavra.toCharArray();
				
				if(solucao.toUpperCase().charAt(i) == letraChar[0])
				{
					palavraTemp[i] = solucao.charAt(i);
				}
				palavra = new String(palavraTemp);
			}
		}
		else
		{
			tentativas--;
		}
		if(tentativas < 0)
		{
			defineSolucao(categoria);
			tentativas = 6;
		}
		if(palavra.equals(solucao))
		{
			defineSolucao(categoria);
			tentativas = 6;
			pts++;
		}
	}
	
	
	/**
	 * Centra as strings
	 * @param string
	 * @return
	 */
	private String centrar(String string)
	{
		int nColunas = 40;
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
	
	
	
	
	

}
