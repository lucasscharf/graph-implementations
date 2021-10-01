# Implementações para a lista 2

Os exercícios estão dentro do diretório src/main/java/br/cp/ufsc com o nome QuestaoX.java onde X ∈ {1,2,3}.
Um exemplo de cada um desses exercícios sendo executados pode ser verficado usando o comando mvn test -Dtest=QuestaoXTest, onde X ∈ {1,2,3}. Ps, é necessário ter o Java versão 8 ou superior e o maven para poder executá-los com esses comandos.

A descrição de cada método, incluindo o que faz e o que foi assumido, está nos comentários do mesmo. Esse projeto foi feito com fins acadêmicos, por isso, priorizou-se a facilidade de entendimento sobre a utilização de estruturas de dados mais eficientes ou escrita de código mais modularizada.

Questão 1 -
A)
O algoritmo será resolvido através de uma solução simples do BFS e reversão da lista do caminho dado (enquanto o BFS retorna a lista do destino para a origem, nós queremos a lista feita da origem para o destino).

Considere a estrutura auxiliar Antecessor_v que indica o ancenstral do vértice V. Custo_v o custo em passos entre o vértice V e o vértice de origem. Caminho a lista com o caminho entre origem e destino (no exercício é chamado de p). Observado_v para indicar se o vértice V já foi observado ou não durante a execução do algoritmo. Visitas a fila de elementos que serão sendo visitados.

Para fins de entendimento, o conjunto de centrais C será chamado de Centrais, os pares de centrais L será chamado de Pares, o vértice de origem cx será chamado de Inicio e o de destino cy será chamado de Fim

```
encontrarCaminho(Centrais, Pares, Início, Fim)
  //Iniciar as estruturas
  Observado_v ← false ∀v ∈ V
  Custo_v ← ∞ ∀v ∈ V
  Antecessor_v ← null ∀v ∈ V
  Visitas ← Fila()
  Visitas.enqueue(Início)

  // Configurar o vértice de origem
  Observado_origime ← true
  Custo_origim ← 0

  // executar o bfs
  while Visitas.empty() = false do
     vérticeDeOrigem ← Visitas.dequeue()
     foreach vérticeDeDestino ∈ N(vérticeDeOrigem) do
     if Observado_vérticeDeDestino = false then
       Observado_vérticeDeDestino ← true
       Custo_vérticeDeDestino ← Custo_vérticeDeOrigem +1
       Antecessor_vérticeDeDestino ← vérticeDeOrigem
       Visitas.enqueue(vérticeDeDestino)

  //montar lista de retorno
  
  vértice = Fim
  do
    Caminho.add(vértice)
    vértice <- Antecessor_vértice
  while vértice != null

  reverse(Caminho)
  return Caminho

```

B) A complexidade do algoritmo é O(|V| + |E|), o V é o conjunto de Vértice e E é o conjunto de arestas.

Questão 2 - 

Deseja-se desenvolver um algoritmo que receba um grafo G, uma func¸ao˜ p, um vertice de origem s ∈ V, um vertice de destino t ∈ V, preço do combustível, a autonomia em km por litro e retorne a rota de menor custo considerando o valor gasto em viagem

A) 
A solução consiste em criar um grafo G'=(V,A,c) com p : A → R+ representando o custo da viagem, sendo esse custo dado pela fórmula

```
distância entre as cidades/autonomia*preço do combustível + pedágio para entrar na cidade
```

Após isso, será necessário implementar Dijkstra e pegar a menor rota entre o ponto de origem e destino. 

Considere a estrutura auxiliar Antecessor_v que indica o ancenstral do vértice V. Custo_v o custo em passos entre o vértice V e o vértice de origem. Caminho a lista com o caminho entre origem e destino (no exercício é chamado de p). Observado_v para indicar se o vértice V já foi observado ou não durante a execução do algoritmo. Visitas a fila de elementos que serão sendo visitados.

menorRota(Localidades,Arcos,FunçãoDeDistância,Início,Fim,FunçãoDePedágio, preçoDoCombustivel, autonomiaDoVeículo)
  funçãoDeCusto <- ConstruirFuncaoDeCusto(FunçãoDeDistância,FunçãoDePedágio,preçoDaGasolina,autonomiaDoVeículo)
  Custo_v ← ∞ ∀v ∈ Localidades
  Antecessor_v ← null ∀v ∈ Localidades
  Observado_v ← false ∀v ∈ Localidades
  Custo_Início ← 0
  while ∃v ∈ Localidades com Observado_v = false do
    vérticeDeMenorCusto ← pegarVérticeAdjacenteAosVérticesAdicionadosComMenorCustoQueNãoFoiVisitado()
    Observado_v ← true
    foreach v ∈ N(vérticeDeMenorCusto) :Observado_v = false do
      if Custo_v > Custo_u + funçãoDeCusto(u, v) then
        Custo_v ← Custo_u + funçãoDeCusto(u, v)
        Antecessor_v ← u
  
  vértice = Fim
  do
    Caminho.add(vértice)
    vértice <- Antecessor_vértice
  while vértice != null

  reverse(Caminho)

B) o algoritmo de Dijkstra é um algoritmo guloso que, através de repetidas escolhas da aresta de menor custo até então. Após a execução, ele retornará o menor custo do vértice de origem até todos os vértices alcançáveis no grafo. Depois disso, basta selecionar o caminho de menor custo entre a nossa origem e nosso destino


3) 


4)

A) Teríamos uma solução polinomial para todos os problemas do tipo NP-completo.

B) sabe-se que a classe de problemas NP-difíceis são difíceis de se resolver e que, até o momento, não se encontrou solução para ela.
Logo, se existe uma transformação polinomial de um problema para NP-dificíl, então esse problema entra na classe de NP-difícil  e será difícil de resolver.

c) a verificação consiste em você confirmar se uma resposta é válida ou não. Enquanto a resolução consiste em descobrir realmente a solução ao problema.
Por exemplo, dado um grafo G e uma sequência de arestas A e um custo C, é possível verificar facilmente se essa sequência percorre todas as arestas com o custo C. 
Basta verificar se a sequência não tem ciclos, percorre todos os vértices e a soma dos custos é igual a C.

Porém, não ainda não foi descoberto uma forma de dado um grafo G', saber se a sequência A' tem o menor custo possível, em tempo polinomial.

D) Algoritmos aproximados são algoritmos que não resolvem perfeitamente o problema, mas conseguem uma boa aproximação. Isso é muito utilizado em problemas de otimização onde não se sabe como se chegar na melhor solução ou a melhor solução não é praticável. O algoritmo 46 do material de apoio mostra um exemplo de uma aproximação com solução em tempo polinomial para o problema do caixeiro viajante.

Heurísticas são metodos específicos para problemas de otimização e exploram algum conhecimento específico do problema em questão mas sem garantia de solução ótima.