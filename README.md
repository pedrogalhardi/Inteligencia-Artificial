Nome:Pedro Henrique Galhardi 
Trabalho: Jogo Dots com algoritmo minMax implementado.

Introdução:Este é um jogo em Java da disciplina Inteligência Artificial lecionado pelo professor Douglas Castilho na qual o objetivo foi criar um jogo chamado Dots and Boxes com um algoritmo de IA implementado chamado minMax

Para rodar o código é necessário estar com algum interpretador de texto que reconheça java.

Aqui vai uma breve explicaçãao do código.
Esse trabalho foi dividido em 3 classes, Dots.java, No.java e Ponto.java.

Na classe Dots é onde fica armazenado toda lógica do game, primeiro passo foi colocar todas as jogadas possiveis como true para que possa entrar no método "preencheJogadas" na qual cria uma arvore com todas as 
possibilidades, depois a IA jogará na posição 1, logo em seguida vem a jogada do player e setam ambos como false. Em seguida vem a minha heurística que foi a "profundidade", na qual ela determina a dificuldade da IA
jogar, basicamente o raciocínio utilizado é, profundidade = a profundidade de busca na arvore criada, ou seja se quiser um jogo mais difícil basta colocar a profundidade como a total, porém se quiser uma mais fácil, colocar um número
menor para a busca nessa árvore e o restante é jogado aleatoriamente com a função Random. No código também é possivel notas algumas partes comentadas, tais como minMaxTeste e jogadaIa sendo implementados a minha heurística,
os resultados não foram conforme os esperados é a lógica do professor foi melhor.

Na classe No onde fica meu tabuleiro e as condiçoes de jogo, começo fazendo o construtor do meu No e setando todos os parâmetros necessários patra o jogo, logo em seguida vem os métodos para formatar e imprimir o tabuleiro, logo depois
o mapearPonto, que mapeia os pontos do meu tabuleiro, copiarTabuleiro na qual tira um "print" do meu tabuleiro atual, o método de fazer jogada, suas condiçoes para jogo e por fim o método finalizado, que verifica se o jogo acabou,
esse metodo finaliza é usado em todas as jogadas do meu jogo.

Na classe Ponto é o lugar que seta o X e o Y do tabuleiro.

Você pode contribuir para esse projeto fazendo um fork e melhorando-o.

### Referências Bibliográficas 
- https://www.organicadigital.com/blog/algoritmo-minimax-introducao-a-inteligencia-artificial/ 
- https://www.javatpoint.com/mini-max-algorithm-in-ai
- https://www.baeldung.com/cs/minimax-algorithm