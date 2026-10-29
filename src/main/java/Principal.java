
/**
 * Classe principal que implementa o algoritmo Hill Climbing
 * para encontrar uma rota de menor distancia entre cidades.
 *
 * O problema considera o deposito A e as cidades B, C e D.
 * O algoritmo comeca com uma rota inicial, gera rotas vizinhas
 * por meio da troca de posicao entre duas cidades e seleciona
 * uma alternativa quando ela apresenta menor distancia.
 *
 * A busca termina quando nenhuma troca testada melhora
 * a distancia total da rota atual.
 */
public class Principal {

    /**
     * Matriz que armazena as distancias entre as cidades.
     *
     * Os indices representam as cidades na seguinte ordem:
     * 0 = A, 1 = B, 2 = C e 3 = D.
     *
     * A matriz e simetrica, pois a distancia entre duas
     * cidades e igual nos dois sentidos.
     */
    static int[][] distancias = {
        {0, 10, 15, 20}, // A
        {10, 0, 5, 12},  // B
        {15, 5, 0, 8},   // C
        {20, 12, 8, 0}   // D
    };

    /**
     * Converte o nome de uma cidade no indice correspondente
     * utilizado na matriz de distancias.
     *
     * @param cidade nome da cidade (A, B, C ou D)
     * @return indice da cidade na matriz
     */
    static int indice(String cidade) {
        if (cidade.equals("A")) return 0;
        if (cidade.equals("B")) return 1;
        if (cidade.equals("C")) return 2;
        return 3;
    }

    /**
     * Calcula a distancia total de uma rota.
     *
     * A rota comeca no deposito A, percorre as cidades
     * armazenadas no vetor e retorna ao deposito A.
     *
     * @param rota vetor contendo a ordem de visita das cidades
     * @return distancia total percorrida
     */
    static int avaliar(String[] rota) {
        int total = 0;
        String anterior = "A";

        for (int i = 0; i < rota.length; i++) {
            total += distancias[indice(anterior)] [indice(rota[i])];

            anterior = rota[i];
        }

        // Retorna ao deposito A
        total = total + distancias[indice(anterior)][indice("A")];

        return total;
    }

    /**
     * Exibe uma rota completa no console, incluindo
     * a saida e o retorno ao deposito A.
     *
     * @param rota vetor contendo a ordem de visita das cidades
     */
    static void mostrarRota(String[] rota) {
        System.out.print("A");

        for (int i = 0; i < rota.length; i++) {
            System.out.print(" -> " + rota[i]);
        }

        System.out.println(" -> A");
    }

    /**
     * Executa o algoritmo Hill Climbing.
     *
     * O algoritmo inicia com a rota B-D-C, gera vizinhos
     * trocando duas cidades de posicao, calcula suas
     * distancias e guarda a melhor alternativa encontrada.
     *
     * Se uma rota vizinha for melhor que a atual, ela
     * passa a ser a nova solucao. Caso contrario, a busca
     * termina.
     */
    static void hillClimbing() {

        // Define a rota inicial
        String[] atual = {"B", "D", "C"};

        // Controla a repeticao do algoritmo
        boolean continuar = true;

        System.out.println("Rota inicial:");
        mostrarRota(atual);
        System.out.println("Distancia: " + avaliar(atual) + " km");

        while (continuar) {

            // Inicialmente, a melhor rota e a rota atual
            String[] melhorVizinho = atual.clone();
            int melhorDistancia = avaliar(atual);

            System.out.println("\nRotas vizinhas:");

            // Percorre as posicoes da rota
            for (int i = 0; i < atual.length; i++) {

                // Testa as trocas com as posicoes seguintes
                for (int j = i + 1; j < atual.length; j++) {

                    // Cria uma copia para testar uma alternativa
                    String[] vizinho = atual.clone();

                    // Troca duas cidades de posicao
                    String temp = vizinho[i];
                    vizinho[i] = vizinho[j];
                    vizinho[j] = temp;

                    // Calcula a distancia da rota vizinha
                    int distancia = avaliar(vizinho);

                    // Exibe a alternativa testada
                    mostrarRota(vizinho);
                    System.out.println("Distancia: " + distancia + " km");

                    // Guarda a alternativa se ela for melhor
                    if (distancia < melhorDistancia) {
                        melhorDistancia = distancia;
                        melhorVizinho = vizinho;
                    }
                }
            }

            // Verifica se houve melhoria
            if (melhorDistancia < avaliar(atual)) {
                // Atualiza a solucao atual
                atual = melhorVizinho;

                System.out.println("\nNova melhor rota:");
                mostrarRota(atual);
                System.out.println("Distancia: " + avaliar(atual) + " km");

            } else {
                // Encerra a busca quando nao ha melhoria
                continuar = false;
            }
        }

        // Exibe a solucao final encontrada
        System.out.println("\nResultado final:");
        mostrarRota(atual);
        System.out.println("Distancia total: " + avaliar(atual) + " km");
    }

    /**
     * Metodo principal do programa.
     *
     * Inicia a execucao do algoritmo Hill Climbing.
     *
     * @param args argumentos recebidos pela linha de comando
     */
    public static void main(String[] args) {
        hillClimbing();
    }
}
