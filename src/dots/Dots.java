/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// nao to fazendo a recursividade nos nós;
package dots;

import java.util.Random;
import java.util.Scanner;

public class Dots {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[][] tab = new String[5][5];

        No raiz = new No(tab, 0, 0, 1, 0);

        raiz.formata();

        boolean[] jogadasPossiveis = new boolean[12];
        for (int j = 0; j < jogadasPossiveis.length; j++) {
            jogadasPossiveis[j] = true;
        }

        System.out.println("IA Comeca jogando na posicao 1");
        raiz.jogada(1);

        jogadasPossiveis[0] = false;

        boolean erro;

        raiz.imprime();
        System.out.println("Digite a posicao da jogada (Jogador 2):");

        int jogada0 = sc.nextInt();
        erro = raiz.jogada(jogada0);
        if (erro == false) {
            while (erro == false) {
                jogada0 = sc.nextInt();
                if (jogada0 > 12 || jogada0 < 2) {
                    System.out.println("Posicao Invalida! Tente novamente:");
                }
                erro = raiz.jogada(jogada0);
            }
        }
        jogadasPossiveis[jogada0 - 1] = false;

        int profundidade = 12;

        preencherJogadas(jogadasPossiveis, raiz, erro, true);

        while (raiz.finalizado() == 0) {

            int jogada = 0;
            int jogadaX = 0;
            int jogadaY = 0;

            // No melhorJogada = raiz.getMelhorJogada(raiz, 12, true);
            // System.out.println(raiz.getMelhorJogada(raiz, 12, true));
            minMax(raiz, profundidade, true, jogadasPossiveis);

            int result = melhorJogada(raiz, profundidade, true, jogadasPossiveis);

            if (result != -1) {
                raiz.copiarTabuleiro(raiz);
                System.out.println("IA jogou na posicao: " + result);
            } else {
                System.out.println("Nao foi possivel fazer jogada.");
            }

            raiz.condicao(jogadaX, jogadaY, erro, 2);

            jogadasPossiveis[jogada] = false;

            raiz.imprime();

            // vez da IA
            jogada = result;

            jogadaX = raiz.getX(jogada);
            jogadaY = raiz.getY(jogada);

            System.out.println("IA joga em: " + jogada);

            raiz.jogada(jogada);

            raiz.condicao(jogadaX, jogadaY, erro, 0);

            jogadasPossiveis[jogada] = false;

            raiz.imprime();

            // vez do jogador 2
            System.out.println("Digite a posicao da jogada (Jogador 2):");
            jogada = sc.nextInt();

            if (jogada > 12 || jogada < 2 || jogadasPossiveis[jogada - 1] == false) {
                System.out.println("Posicao Invalida! Tente novamente:");
            }

            erro = raiz.jogada(jogada);
            if (erro == false) {
                while (erro == false) {
                    jogada = sc.nextInt();
                    if (jogada > 12 || jogada < 2) {
                        System.out.println("Posicao Invalida! Tente novamente:");
                    }
                    erro = raiz.jogada(jogada);
                }
            }
        }

        System.out.println("Fim de Jogo!");
    }

    public int getJogador(int jogador) {
        return jogador;
    }

    public static void preencherJogadas(boolean[] jogadasPossiveis, No no, boolean erro, boolean max) {

        for (int i = 0; i < jogadasPossiveis.length; i++) {
            if (jogadasPossiveis[i] == true) {
                jogadasPossiveis[i] = false;

                No novo = new No(no.tabuleiro, no.valorMinmax, no.jogada, no.jogador, no.mapa);

                novo.copiarTabuleiro(no);

                novo.jogada(i + 1);
                boolean novoPonto;

                novoPonto = novo.condicao(novo.getX(no.jogada), novo.getY(no.jogada), erro, 0);
                //novo.jogador = novoPonto ? 1 : 2;
                //int valor = minMax(novo, 2, true);
                no.filhos.add(novo);
                if (max) {
                    novo.valorMinmax = Integer.MIN_VALUE;
                } else {
                    novo.valorMinmax = Integer.MAX_VALUE;
                }

                if (novoPonto) {

                    preencherJogadas(jogadasPossiveis, novo, erro, max);

                } else {
                    preencherJogadas(jogadasPossiveis, novo, erro, !max);
                }
                jogadasPossiveis[i] = true;

            }

        }
    }

    /*public static int minMaxTeste(No no, int profundidade, boolean max) {

        int vencedor = no.finalizado();
        switch (vencedor) {
            case 1:
                return 1;
            case 2:
                return -1;
            case 3:
                return 0;

            default:
        }
        if (profundidade == 0 || no.finalizado() != 0) {
            return no.valorMinmax;

        } else if (max) {
            for (int i = 0; i < no.filhos.size(); i++) {
                No filho = no.filhos.get(i);
                int valor = minMaxTeste(filho, profundidade, !max);
                if (valor > no.valorMinmax) {
                    no.valorMinmax = valor;

                }
            }
            return no.valorMinmax;
        } else {

            for (int i = 0; i < no.filhos.size(); i++) {
                No filho = no.filhos.get(i);
                int valor = minMaxTeste(filho, profundidade, true);
                if (valor < no.valorMinmax) {
                    no.valorMinmax = valor;

                }
            }
            return no.valorMinmax;

        }
    }*/
    public static int minMax(No no, int profundidade, boolean max, boolean[] jogadasPossiveis) {

        int vencedor = no.finalizado();
        switch (vencedor) {
            case 1:
                return 1;
            case 2:
                return -1;
            case 3:
                return 0;

            default:
        }
        if (profundidade == 0 || no.finalizado() != 0) {
            return no.valorMinmax;
        }

        if (max) {
            int melhorValor = Integer.MIN_VALUE;

            for (No novo : no.filhos) {
                int v = minMax(novo, 12, true, jogadasPossiveis);
                melhorValor = Math.max(melhorValor, v);
            }

            return melhorValor;

        } else {
            int melhorValor = Integer.MAX_VALUE;

            for (No novo : no.filhos) {
                int v = minMax(novo, 12, false, jogadasPossiveis);
                melhorValor = Math.min(melhorValor, v);
            }

            return melhorValor;
        }
    }

    public static int melhorJogada(No no, int profundidade, boolean max, boolean[] jogadasPossiveis) {

        Random gerador = new Random();

        int melhorJogadas = 0;

        for (int i = 0; i < 11; i++) {
            melhorJogadas = gerador.nextInt(11);
        }
        int melhorValor = Integer.MIN_VALUE;

        for (int i = 0; i < no.filhos.size(); i++) {
            No novo = no.filhos.get(i);

            int valor = minMax(novo, 11, true, jogadasPossiveis);

            if (valor > melhorValor) {

                melhorValor = valor;
                melhorJogadas = valor;
            }
        }

        return melhorJogadas;
    }

    /*public static int jogadaIA(No no, int profundidadeMaxima) {
        int melhorJogada = -1;
        int melhorValor = Integer.MIN_VALUE;
        for (int i = 0; i < no.filhos.size(); i++) {
            No filho = no.filhos.get(i);
            int valor = minMaxTeste(filho, profundidadeMaxima, false);
            if (valor > melhorValor) {
                melhorValor = valor;
                melhorJogada = filho.jogada;
            }
        }
        return melhorJogada;
    }*/
}
