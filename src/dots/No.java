package dots;

import java.util.ArrayList;

/**
 *
 * @author 201911020023
 */
public class No {

    public String[][] tabuleiro = new String[5][5];
    public ArrayList<ArrayList<Integer>> mapa;
    public ArrayList<No> filhos = new ArrayList();
    public int valorMinmax = 0;
    public boolean turno;
    int jogada;
    int jogador;

    public No(String[][] tabuleiro, int valorMinmax, int jogada, int jogador, ArrayList<ArrayList<Integer>> mapa) {

        for (int i = 0; i < 5; i++) {
            System.arraycopy(tabuleiro[i], 0, this.tabuleiro[i], 0, 5);
        }
        this.valorMinmax = valorMinmax;
        this.jogada = jogada;
        this.jogador = jogador;
        this.filhos = new ArrayList<>();
        this.mapa = new ArrayList<>();
        this.mapa = mapa;

    }

    public No(String[][] tabuleiro, int valorMinmax, int jogada, int jogador, int profundidade) {

        this.tabuleiro = tabuleiro;
        this.valorMinmax = valorMinmax;
        this.jogada = jogada;
        this.jogador = jogador;
        this.filhos = new ArrayList<>();
        this.mapa = new ArrayList<>();

    }

    /*public No getMelhorJogada(No raiz, int profundidade, boolean max) {
        No melhorJogada = null;
        int melhorValor = Integer.MIN_VALUE;

        for (No novo : filhos) {
            if (novo.valorMinmax > melhorValor) {
                melhorValor = novo.valorMinmax;
                melhorJogada = novo;
                System.out.println(melhorJogada);
            }
        }

        return melhorJogada;
    }*/
    public void formata() { //cria meu tabuleiro colocando " * " nos pontos 3x3
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i % 2 == 0 && j % 2 == 0) {
                    tabuleiro[i][j] = "*";
                }
            }
        }

        //da espaço entre os " * "
        tabuleiro[1][1] = " ";
        tabuleiro[1][3] = " ";
        tabuleiro[3][1] = " ";
        tabuleiro[3][3] = " ";

        int count = 1;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (tabuleiro[i][j] == null) {
                    tabuleiro[i][j] = Integer.toString(count);
                    count++;
                    ArrayList<Integer> entrada = new ArrayList();
                    entrada.add(i);
                    entrada.add(j);
                    mapa.add(entrada);
                }
            }
        }
    }

    public void imprime() { //mostra um tabuleiro 3x3
        System.out.println("\n");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(tabuleiro[i][j] + "  ");
            }
            System.out.println();
        }
    }

    public Ponto mapearPonto(int posicao) {
        switch (posicao) {
            case 1:
                return new Ponto(0, 1);
            case 2:
                return new Ponto(0, 3);
            case 3:
                return new Ponto(1, 0);
            case 4:
                return new Ponto(1, 2);
            case 5:
                return new Ponto(1, 4);
            case 6:
                return new Ponto(2, 1);
            case 7:
                return new Ponto(2, 3);
            case 8:
                return new Ponto(3, 0);
            case 9:
                return new Ponto(3, 2);
            case 10:
                return new Ponto(3, 4);
            case 11:
                return new Ponto(4, 1);
            case 12:
                return new Ponto(4, 3);

        }
        return null;

    }

    public void copiarTabuleiro(No no) {
        for (int i = 0; i < 5; i++) {
            System.arraycopy(no.tabuleiro[i], 0, tabuleiro[i], 0, 5);
        }
    }

    public boolean jogada(int jogada) { // cria dois tipos de jogadas
        int jogadaX;
        int jogadaY;

        if (jogada > 12 || jogada < 1) { //se minhas jogadas for diferente do meu tabuleiro fica invalido e joga novamente
            //System.out.println("Posicao Invalida! Tente novamente:");
            return false;
        } else {
            jogadaX = mapa.get(jogada - 1).get(0);
            jogadaY = mapa.get(jogada - 1).get(1);
        }

        if (jogadaX >= 5 || jogadaX < 0 || jogadaY >= 5 || jogadaY < 0) {
            // System.out.println("Local Invalido, tente novamente:");
            return false;

        } else if (tabuleiro[jogadaX][jogadaY].equals("-") || tabuleiro[jogadaX][jogadaY].equals("|")) { //funcao de comparação para ver se o local ja foi jogado
            //System.out.println("Local ja ocupado por um traco! Tente novamente:");
            return false;

        } else if (jogadaX % 2 != 0 && jogadaY % 2 == 0) {
            tabuleiro[jogadaX][jogadaY] = "|";
            return true;

        } else if (jogadaX % 2 == 0 && jogadaY % 2 != 0) {
            tabuleiro[jogadaX][jogadaY] = "-";
            return true;

        }
        // System.out.println("Local Inexistente no jogo. Digite outro:");
        return false;
    }

    public int getX(int jogada) {
        return mapa.get(jogada).get(0);
    }

    public int getY(int jogada) {
        return mapa.get(jogada).get(1);
    }

    public boolean condicao(int jogadaX, int jogadaY, boolean erro, int jogador) {
        int novoQuadrado = 0;
        if (erro == false) {
            if (jogadaX >= 1 && jogadaY >= 2) {
                if ((tabuleiro[jogadaX][jogadaY].equals("|")) && (tabuleiro[jogadaX][jogadaY - 2].equals("|")) && (tabuleiro[jogadaX - 1][jogadaY - 1].equals("-") && tabuleiro[jogadaX + 1][jogadaY - 1].equals("-"))) {
                    switch (jogador) {
                        case 1:
                            tabuleiro[jogadaX][jogadaY - 1] = "1";
                            break;
                        case 2:
                            tabuleiro[jogadaX][jogadaY - 1] = "2";
                            break;
                        default:
                            //System.out.println("Ocorreu algum erro!");
                            break;
                    }
                    novoQuadrado++;
                }
            }
            if ((jogadaX + 1) < 5 && (jogadaY + 2) < 5 && (jogadaX - 1 >= 0)) {
                if (tabuleiro[jogadaX][jogadaY].equals("|") && (tabuleiro[jogadaX][jogadaY + 2].equals("|")) && (tabuleiro[jogadaX + 1][jogadaY + 1].equals("-") && tabuleiro[jogadaX - 1][jogadaY + 1].equals("-"))) {
                    switch (jogador) {
                        case 1:
                            tabuleiro[jogadaX][jogadaY + 1] = "1";
                            break;
                        case 2:
                            tabuleiro[jogadaX][jogadaY + 1] = "2";
                            break;
                        default:
                            // System.out.println("Ocorreu algum erro!");
                            break;
                    }
                    novoQuadrado++;
                }
            }
            if ((jogadaX + 2) < 5 && (jogadaY + 1) < 5 && (jogadaY - 1 >= 0)) {
                if (tabuleiro[jogadaX][jogadaY].equals("-") && (tabuleiro[jogadaX + 2][jogadaY].equals("-")) && (tabuleiro[jogadaX + 1][jogadaY + 1].equals("|")) && (tabuleiro[jogadaX + 1][jogadaY - 1].equals("|"))) {
                    switch (jogador) {
                        case 1:
                            tabuleiro[jogadaX + 1][jogadaY] = "1";
                            break;
                        case 2:
                            tabuleiro[jogadaX + 1][jogadaY] = "2";
                            break;
                        default:
                            //System.out.println("Ocorreu algum erro!");
                            break;
                    }
                    novoQuadrado++;
                }
            }
            if ((jogadaX - 2) >= 0 && (jogadaY - 1) >= 0 && (jogadaY + 1) < 5) {
                if ((tabuleiro[jogadaX][jogadaY].equals("-")) && (tabuleiro[jogadaX - 2][jogadaY].equals("-")) && (tabuleiro[jogadaX - 1][jogadaY + 1].equals("|")) && (tabuleiro[jogadaX - 1][jogadaY - 1].equals("|"))) {
                    switch (jogador) {
                        case 1:
                            tabuleiro[jogadaX - 1][jogadaY] = "1";
                            break;
                        case 2:
                            tabuleiro[jogadaX - 1][jogadaY] = "2";
                            break;
                        default:
                            // System.out.println("Ocorreu algum erro!");
                            break;
                    }
                    novoQuadrado++;
                }
            }
            if (novoQuadrado > 0) {
                return true;
            }

        }
        return false;
    }

    public int finalizado() {
        int pontuacao1 = 0, pontuacao2 = 0;
        int contador = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (tabuleiro[i][j].equals(" ")) {
                    contador++;
                }
                if (tabuleiro[i][j].equals("-1")) {
                    pontuacao1++;
                }
                if (tabuleiro[i][j].equals("1")) {
                    pontuacao2++;
                }
            }
        }
        if (pontuacao1 > pontuacao2) {
            return 1;
        } else if (pontuacao1 > pontuacao2) {
            return -1;
        } else if (pontuacao1 == pontuacao2) {
            return 0;
        }
        if (contador == 0) {
            System.out.println("\nPontuacao FINAL:\nJogador 1: " + pontuacao1 + " ponto(s);\nJogador 2: " + pontuacao2 + " ponto(s);\n");
            if (pontuacao1 > pontuacao2) {
                System.out.println("Vencedor:\n IA 1!");
            }
            if (pontuacao1 < pontuacao2) {
                System.out.println("Vencedor:\n Jogador!");
            }
            if (pontuacao1 == pontuacao2) {
                System.out.println("Resultado: Empate!");
            }
        } else {
            System.out.println("\nPontuacao atual:\nJogador 1: " + pontuacao1 + " ponto(s);\nJogador 2: " + pontuacao2 + " ponto(s);\n");
        }
        return contador;
    }

    public String[][] getTabuleiro() {
        return tabuleiro;
    }

    public void setTabuleiro(String[][] tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    public ArrayList<ArrayList<Integer>> getMapa() {
        return mapa;
    }

    public void setMapa(ArrayList<ArrayList<Integer>> mapa) {
        this.mapa = mapa;
    }

    public ArrayList<No> getFilhos() {
        return filhos;
    }

    public void setFilhos(ArrayList<No> filhos) {
        this.filhos = filhos;
    }

    public int getValorMinmax() {
        return valorMinmax;
    }

    public void setValorMinmax(int valorMinmax) {
        this.valorMinmax = valorMinmax;
    }

    public boolean isTurno() {
        return turno;
    }

    public void setTurno(boolean turno) {
        this.turno = turno;
    }

    public int getJogada() {
        return jogada;
    }

    public void setJogada(int jogada) {
        this.jogada = jogada;
    }

    public int getJogador() {
        return jogador;
    }

    public void setJogador(int jogador) {
        this.jogador = jogador;
    }

}
