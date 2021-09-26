# Implementações para a lista 2

Os exercícios estão dentro do diretório src/main/java/br/cp/ufsc com o nome QuestaoX.java onde X ∈ {1,2,3}.
Um exemplo de cada um desses exercícios sendo executados pode ser verficado usando o comando mvn test -Dtest=QuestaoXTest, onde X ∈ {1,2,3}. Ps, é necessário ter o Java versão 8 ou superior e o maven para poder executá-los com esses comandos.

A descrição de cada método, incluindo o que faz e o que foi assumido, está nos comentários do mesmo. Esse projeto foi feito com fins acadêmicos, por isso, priorizou-se a facilidade de entendimento sobre a utilização de estruturas de dados mais eficientes ou escrita de código mais modularizada.


Questão 1 -

O algoritmo será dado por 

Considere a estrutura auxiliar Antecessor_v que indica o ancenstral do vértice V. Custo_v o custo em passos entre o vértice V e o vértice de origem. Caminho a lista com o caminho entre origem e destino (no exercício é chamado de p). Observado_v para indicar se o vértice V já foi observado ou não durante a execução do algoritmo. Visitas a fila de elementos que serão sendo visitados.

Para fins de entendimento, o conjunto de centrais C será chamado de Centrais, os pares de centrais L será chamado de Pares, o vértice de origem cx será chamado de Inicio e o de destino cy será chamado de Fim

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

A complexidade do algoritmo é O(|V| + |E|), o V é o conjunto de Vértice e E é o conjunto de arestas.