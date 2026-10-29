# Algoritmo Hill Climbing em Java

## Descrição

Este programa demonstra o funcionamento do algoritmo **Hill Climbing (subida de encosta)** por meio de um problema de otimização de rotas. Uma empresa precisa sair do depósito A, visitar as cidades B, C e D e retornar ao ponto de partida, procurando reduzir a distância total percorrida.

O programa utiliza uma matriz para representar as distâncias entre as cidades. A busca começa com uma rota inicial e testa alternativas obtidas pela troca de posição entre duas cidades.

A aplicação foi desenvolvida em **Java 8** e utiliza o **Apache Maven** para gerenciar a compilação e a execução do projeto.

## Objetivos

-   Compreender a lógica do algoritmo Hill Climbing.
-   Representar distâncias entre cidades utilizando uma matriz.
-   Calcular o custo total de uma rota.
-   Gerar soluções vizinhas por meio da troca de posições em um vetor.
-   Utilizar estruturas condicionais e de repetição para comparar
    soluções.
-   Identificar a condição de parada do algoritmo.

## Tecnologias utilizadas

-   **Java 8** --- linguagem de programação.
-   **Estruturas utilizadas:** matriz, vetor, métodos, estruturas `for`, `while` e `if`
-   **Apache Maven** --- automação da compilação e execução.

## Estrutura do projeto

O projeto utiliza a estrutura convencional do Maven:

``` text
hill_climbing_java/
├── pom.xml
├── README.md
└── src/
    └── main/
        └── java/
            └── Principal.java
```

-   `pom.xml`: configura o projeto Maven e a versão do Java.
-   `README.md`: documentação do projeto.
-   `src/main/java/Principal.java`: classe principal que implementa o
    algoritmo.

## Funcionamento do programa

### 1. Representação das distâncias

A matriz `distancias` armazena as distâncias entre as cidades. Os índices correspondem às cidades na seguinte ordem:

    Índice  Cidade
  -------- --------
         0    A
         1    B
         2    C
         3    D

Por exemplo, a distância entre B e C é de 5 km. Como a matriz é simétrica, a distância de B para C é igual à distância de C para B.

### 2. Rota inicial

O programa começa com o vetor:

``` java
String[] atual = {"B", "D", "C"};
```

Como o veículo parte do depósito A e retorna a ele, a rota completa é:

**A → B → D → C → A**

A distância dessa rota é:

10 + 12 + 8 + 15 = **45 km**.

### 3. Avaliação da rota

O método `avaliar()` percorre o vetor, soma as distâncias entre as cidades consecutivas e acrescenta a distância de retorno ao depósito A. O resultado representa o custo da solução: neste problema, **quanto menor a distância, melhor a rota**.

### 4. Geração de rotas vizinhas

Os dois laços `for` tradicionais selecionam pares de posições do vetor. O programa copia a rota atual e troca duas cidades de posição para produzir uma rota vizinha. 

A partir de `B → D → C`, por exemplo, uma das alternativas é `B → C → D`. A distância dessa nova rota é:

10 + 5 + 8 + 20 = **43 km**.

Como 43 km é menor que 45 km, essa alternativa passa a ser a nova solução atual.

### 5. Critério de parada

A variável booleana `continuar` controla o laço `while`. Se uma rota vizinha apresentar uma distância menor, o algoritmo atualiza a solução e continua. Se nenhuma troca melhorar a rota, `continuar` recebe `false` e a repetição termina.

### 6. Resultado

Neste exemplo, o programa encontra a rota:

**A → B → C → D → A**

A distância total é de **43 km**. A rota inversa, **A → D → C → B → A**, também possui 43 km.

O Hill Climbing não testa necessariamente todas as rotas possíveis. Ele examina as rotas vizinhas da solução atual e para quando não encontra uma melhoria entre as trocas permitidas.

## Requisitos

Antes de executar o projeto, instale e configure:

-   **JDK 8**.
-   **Apache Maven** compatível com o ambiente Java utilizado.

Verifique as instalações no terminal:

``` bash
java -version
javac -version
mvn -version
```

Confirme que o Java utilizado pelo Maven é compatível com Java 8.

## Como compilar e executar

Execute os comandos a partir do diretório raiz do projeto, onde está o arquivo `pom.xml`.

### 1. Compilar o projeto

``` bash
mvn clean compile
```

O Maven remove os arquivos de compilação anteriores e compila o código-fonte localizado em `src/main/java`.

### 2. Executar o programa

``` bash
mvn exec:java -Dexec.mainClass="Principal"
```

Esse comando executa o método `main` da classe `Principal`. Se o projeto ainda não tiver o plugin `exec-maven-plugin` configurado no `pom.xml`, será necessário adicioná-lo à seção `<build><plugins>`.

## Saída esperada

A saída apresenta a rota inicial, as rotas vizinhas avaliadas, a melhoria encontrada e o resultado final. Os trechos principais serão semelhantes a:

``` text
Rota inicial:
A -> B -> D -> C -> A
Distancia: 45 km

Rotas vizinhas:
A -> D -> B -> C -> A
Distancia: 52 km
A -> C -> D -> B -> A
Distancia: 45 km
A -> B -> C -> D -> A
Distancia: 43 km

Nova melhor rota:
A -> B -> C -> D -> A
Distancia: 43 km
```

Ao final, o programa apresenta:

``` text
Resultado final:
A -> B -> C -> D -> A
Distancia total: 43 km
```

## Conceitos fundamentais

  -----------------------------------------------------------------------
  Conceito                            Aplicação no programa
  ----------------------------------- -----------------------------------
  Matriz                              Armazena as distâncias entre as
                                      cidades.

  Vetor (`String[]`)                  Representa a ordem de visita das
                                      cidades.

  Método `indice()`                   Converte o nome de uma cidade em
                                      índice da matriz.

  Método `avaliar()`                  Calcula a distância total de uma
                                      rota.

  Método `mostrarRota()`              Exibe a rota no console.

  `for` tradicional                   Percorre as cidades e testa as
                                      trocas entre posições.

  `if` e `else`                       Comparam distâncias e verificam se
                                      houve melhoria.

  `while` e variável booleana         Repetem a busca até não haver
                                      melhoria.

  `clone()`                           Cria uma cópia do vetor para testar
                                      uma alternativa sem alterar a rota
                                      atual.

  Hill Climbing                       Seleciona uma solução vizinha
                                      melhor a cada etapa.
  -----------------------------------------------------------------------

## Limitações

O algoritmo depende da rota inicial e da definição de vizinhança. Neste programa, os vizinhos são gerados apenas pela troca de duas cidades. Por isso, o Hill Climbing pode parar em uma solução que seja melhor que suas vizinhas, mas não seja a melhor solução global para problemas maiores. Para este exemplo pequeno, é possível verificar todas as ordens de visita e confirmar que 43 km é a menor distância.

## Conclusão

O programa demonstra como o algoritmo Hill Climbing pode ser aplicado a um problema de otimização. A cada iteração, ele avalia alternativas próximas, mantém uma rota de menor distância quando encontra uma melhoria e encerra a busca quando nenhuma troca testada melhora a solução atual.

O exemplo também permite praticar conceitos fundamentais de Java, como matrizes, vetores, métodos, laços `for` tradicionais, estruturas condicionais e controle de repetição por variável booleana.
