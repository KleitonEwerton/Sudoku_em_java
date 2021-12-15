/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kleitonewerton.sudoku;

import java.util.Random;

import java.util.Scanner;



/**
 *
 * @author KleitonEwerton
 */
public class sudoku {
    
    private static final int DIMENSAO = 9;
    
    private static void iniciaMatriz(String [][] matriz, int quantidadeInicial){
        
        //Função para inicializar a matriz principal
        
        for(int i = 0; i <DIMENSAO;i++ )
            
            for(int j =0;j<DIMENSAO;j++)
                //Valor de posição nulla
                matriz[i][j] = "*";
        
        Random gerar = new Random();
        
        int quantidadeGerar = 0;
        
        while(quantidadeGerar < quantidadeInicial){
            
            String valor = Integer.toString(1 + gerar.nextInt(9));
            
            int linha = gerar.nextInt(9);
            
            int coluna = gerar.nextInt(9);
            
            //Inseri se não ocorre erro na linha ou coluna
            if(inserir(matriz, linha, coluna, valor)){

                quantidadeGerar += 1;
                
            }
        }
        
        
         
    }
    
    private static void imprimeTabuleiro(String[][] matriz){
        //Imprime a matriz e formato de tabuleiro
        
        System.out.println("\n___________________________________________________");
        for(int i = 0; i < DIMENSAO;i++ ){
            if(i % 3 == 0 && i != 0){
                System.out.println("___________________________________________________");
            }
            for(int j =0;j< DIMENSAO;j++){
                
                if(j % 3 == 0 && j != 0){
                    System.out.print(" | ");
            }
                System.out.print(" " + matriz[i][j]);
                
            }
            System.out.print("\n");
        
        }
        System.out.println("___________________________________________________");
    }
    
    private static boolean valorNaColuna(String[][] matriz, int coluna, String valor){
        
        
        
        for(int i = 0; i<DIMENSAO; i++)
            
            if(matriz[i][coluna].equals(valor) && i != coluna && !matriz[i][coluna].equals("*")){
                System.out.println("Na Coluna" + i + matriz[i][coluna]);
                return true;
            }
                
                
        
        return false;
        
    }
    
    private static boolean valorNaLinha(String[][] matriz, int linha, String valor){
        
        for(int i = 0; i<DIMENSAO; i++)
            
            if(matriz[linha][i].equals(valor)){
               //System.out.println("Na linha");
               return true; 
            }
                
                
        
        return false;
        
    }
    
    private static boolean validaPosicao(String[][] matriz, int linha, int coluna){
        
        return !matriz[linha][coluna].equals("*");
        
    }
   
    private static boolean validaInsercao(String[][] matriz, int linha, int coluna, String valor){
        
        return !valorNaColuna(matriz, coluna, valor) && !valorNaLinha(matriz, linha, valor);
        
    }
    
    private static boolean inserir(String[][] matriz, int linha, int coluna, String valor){
        
        if(validaInsercao(matriz, linha, coluna, valor)){
            
            matriz[linha][coluna] = valor;
            
            //System.out.println("Adicionado");
            
            return true;
            
        }else{
            
            //System.out.println("Já adicionado");
            return false;
            
        }
    }
    
    private static boolean pergunta(String[][] matriz){
        
        Scanner teclado = new Scanner(System.in);
        System.out.println("\nDESEJA VER O TABULEIRO? \n 1 - Para sim \n 2 - Parar de jogar \n Outra para apenas continuar");
        String resp = teclado.nextLine();
        
        if(resp.equals("1"))
            imprimeTabuleiro(matriz);
        
       
        if(resp.toUpperCase().equals("2")){
            
            imprimeTabuleiro(matriz);
            return true;
            
        }
        return false;
            
    }
    
    private static boolean validaNumero(int linha, int coluna, String valor){
        
        //Valida o numero deacordo com os criterios
        
        try {
            int val = Integer.parseInt(valor);
        
             return linha > 9 || linha < 1 || coluna > 9 || coluna < 1 || val > 9 || val < 1;
        }

        catch (Exception e) {
            
            return true;
        }
        
    }
    
    private static boolean verificaMatriz(int linha, int coluna, String valor, String[][] matriz) {
        //Função auxiliar que percorrer por todos os pontos verificando os criterios de linha, coluna e quadrado
        int valorX = 0;
        int valorY = 0;

        //Verifica a linha
        for (int i = 0; i < 9; i++) {
            if(matriz[linha][i].equals(valor))
                return false;
            

        }
        
        //Verifica a coluna
        for (int i = 0; i < 9; i++) {
            if(matriz[i][coluna].equals(valor))
                return false;
            
        }
        //Faz a separação
        if (linha > 2) 
            if (linha > 5) 
                valorX = 6;
            else 
                valorX = 3;
            
        
        if (coluna > 2) 
            if (coluna > 5) 
                valorY = 6;
             else 
                valorY = 3;
            
        //Verifica o quadrado
        for (int i = valorX; i < 10 && i < valorX + 3; i++) 
            for (int j = valorY; j < 10 && j < valorY + 3; j++) 
                if (matriz[i][j].equals(valor)) 
                    return false;
                
            
        
        return true;
    }
    
    private static void verificaSudoku(String[][] matriz){
        
        //Função que verifica se ocorre tudo certo
        
        String[][] auxMatriz = {
            
            {"*", "*", "*", "*", "*", "*", "*", "*", "*"},
            {"*", "*", "*", "*", "*", "*", "*", "*", "*"},
            {"*", "*", "*", "*", "*", "*", "*", "*", "*"},
            {"*", "*", "*", "*", "*", "*", "*", "*", "*"},
            {"*", "*", "*", "*", "*", "*", "*", "*", "*"},
            {"*", "*", "*", "*", "*", "*", "*", "*", "*"},
            {"*", "*", "*", "*", "*", "*", "*", "*", "*"},
            {"*", "*", "*", "*", "*", "*", "*", "*", "*"},
            {"*", "*", "*", "*", "*", "*", "*", "*", "*"}
            };
            
            boolean certo = true;
            
            //Percorre toda a matriz, e verifica as condições usando a função auxilar
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++)
                    if (verificaMatriz(i, j, matriz[i][j],auxMatriz) == false) {
                        certo = false;
                    
                    }
            }
            
            if (certo == true) 
                
                System.out.println("PARABENS!!!!, SUDOKU!!!");
                
            else
                
                System.out.println("Ha algo de errado, tente novamente...");
          
    }
    
    private static int menu(String[][] matriz){
        
        //Função usada no menu 
        while(true){
            
            Scanner tec = new Scanner(System.in);
            System.out.println("Voce deseja \n1 - Adicionar jogada \n2 - Remover jogada \n3 - Verificar \n4 - Sair");
            
          
            int resp = tec.nextInt();
            
            if(resp == 1){
                
                try{
                    
                    System.out.println("INSERINDO:");
                    System.out.println("DIGITE A LINHA: ");
                    int linha = tec.nextInt();
                    System.out.println("DIGITE A COLUNA: ");
                    int coluna = tec.nextInt();
                    System.out.println("DIGITE O VALOR: ");

                    String valor = tec.next();

                    if(!validaNumero(linha, coluna, valor)){

                        if(!validaPosicao(matriz, linha - 1, coluna - 1))
                            matriz[linha - 1][coluna - 1] = valor;

                        else
                            System.out.println("JA POSSUI UM VALOR NESSA CORDEENADA, TENTE NOVAMENTE");
                    }else{

                        System.out.println("NNMERO DE COORDENADA OU VALOR INVALIDO");

                    }
                    if(pergunta(matriz))
                        return 0;

                }catch (Exception e){
                    
                    System.out.println("NUMERO DE COORDENADA OU VALOR INVALIDO");
                    
                }
                
                
            }
                    
            if(resp == 2){
                
                System.out.println("REMOVENDO:");
                System.out.println("DIGITE A LINHA: ");
                int lin = tec.nextInt();
                System.out.println("DIGITE A COLUNA: ");
                int col = tec.nextInt();
                
                matriz[lin - 1][col - 1] = "*";
                
                if(pergunta(matriz))
                    return 0;
                
            }
            
            
            if(resp == 3){
           
                verificaSudoku(matriz);
                break;
            }
            
        if(resp == 4)
             break;
        
        }
        return 0;
    }
    
    public static void main(String[] args) {
        
        //Matriz do tabuleiro
        String[][] matriz = new String[9][9];
        int inicial = 0;
        
        //While do menu inicial
        while(true){
           
            System.out.println("\n\nVoce deseja criar um jogo aleatorio, ele respeitara todas a regras do jogo (DIGITE 1) ou criar seu proprio(DIGITE 2)? ");
        
            Scanner tec = new Scanner(System.in);
            
            //Para leitura, se opção: 1 é gerado altomaticamente, opção 2 o usuário montará do zero
            int inicialMatriz = tec.nextInt();
            
            if(inicialMatriz == 1){
                
                System.out.println("DIGITE A QUANTIDADE DE NUMEROS E POSICOES A SEREM ADICIONADOS JA RESPEITANDO A RESGRA DO JOGO (MAX 60), MUITAS POSICOES RANDON REQUER MUITO TEMPO");
                
                //Leitura da quantidade de números a ser gerado no modo random
                inicial = tec.nextInt();
                
                //Limita para um maximo de 60 números, pois por ser aleatório pode chegar a uma condição de loop infinito, não tendo posições que não violam as regras
                if(inicial > 60)
                    inicial = 60;
                
                if(inicial < 0)
                    inicial = 0;
                
                //Inicializador da matriz com n elementos
                iniciaMatriz(matriz, inicial);
                
                imprimeTabuleiro(matriz);
                
                break;
                
            }
              
            if(inicialMatriz == 2){
                
                //Inicializador da matriz nula
                iniciaMatriz(matriz, 0);
                
                System.out.println("DIGITE AS ENTRADAS");
            
                Scanner teclado = new Scanner(System.in);

                String entrada = teclado.nextLine();

                String[] elementos = entrada.split("\\)");

                    
                //Percorre todos os elementos de entrada os analizando
                for(int i = 0; i <elementos.length;i++){

                    try{
                        //Finaliza as inserções
                        if(elementos[i].equals("(-1,-1,-1"))
                            break;

                        //Convertendo os valores para seus respectivos finais
                        int linha   =   (Character.getNumericValue(elementos[i].charAt(1)));
                        int coluna  =   (Character.getNumericValue(elementos[i].charAt(3)));
                        char valor  =   elementos[i].charAt(5);

                        String val  =   String.valueOf(valor);  


                        //Limitante das condições
                        if(linha > 9 || linha < 1 || coluna > 9 || coluna < 1 || Character.getNumericValue(valor) > 9 || Character.getNumericValue(valor) < 1)
                            System.out.println("Local ou valor " + linha + " - " + coluna + " " + val + " nao sao permitidos");

                        

                        matriz[linha][coluna] = val;
                        
                    }catch (Exception e){

                        System.out.println("NUMERO DE COORDENADA OU VALOR INVALIDO");

                    }
                
                imprimeTabuleiro(matriz);
                break;
                }
            }
        }   
        
        
        
        while(true)
   
            if(menu(matriz) == 0 || menu(matriz) == 2)
                break;
          
    }
    
}
