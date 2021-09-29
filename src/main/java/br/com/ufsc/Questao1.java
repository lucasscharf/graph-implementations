package br.com.ufsc;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.stream.Collectors;

import io.smallrye.mutiny.tuples.Tuple2;

public class Questao1 {
  private List<Boolean> observado;
  private List<Integer> custo;
  private List<Integer> antecessor;
  private Map<Integer, List<Tuple2<Integer, Integer>>> adjacencias;
  private Queue<Integer> visitas;

  /**
   * O algoritmo abaixo retorna o menor caminho em número de passos de acordo com
   * as especificaçãos do exercício 1. Ele implementa a busca em largura (BFS) em
   * grafos. Por fins de simplicidade acadêmica, assume-se que a lista de centrais
   * foram geradas automaticamente e são 0-indexadas. Dada essas características,
   * apenas a quantidade de centrais seria necessária para esse problema,
   * manteu-se como uma lista para manter a compatibilidade com o que foi passado
   * no problema.
   * 
   * No enunciado, o grafo é do tipo não direcionado, porém, para simplificar o
   * desenvolvimento, é recebido uma lista de tuplas (origem,destino). Essas
   * tuplas são duplicadas e invertidas, fazendo com que não seja necessário olhar
   * os dois elementos para saber se existe uma aresta para um determinado
   * vértice.
   * 
   * O grafo foi implementado através de uma lista de adjacências.
   * O método não possui tratamentos para elementos nulos ou índices fora do range
   * 
   * @param centrais lista de centrais
   * @param pares    lista com pares de centrais que trocam encomendas entre si
   * @param inicio   vértice de origem
   * @param fim      vértice de destino
   * @return uma lista ordenada com as centrais que a encomeda deverá passar para
   *         sair da inicio e chegar até o fim. Ou uma lista contendo apenas o
   *         endereço de fim caso não seja possível chegar do início ao fim.
   */

  public List<Integer> encontrarCaminho(List<Integer> centrais, List<Tuple2<Integer, Integer>> pares, Integer inicio,
      Integer fim) {
    criarListaDeAdjacencia(pares);
    inicializarAsEstrtururas(centrais, inicio);
    configurarVerticeDeOrigem(inicio);
    executarBfs();
    return montarListaDeRetorno(fim);
  }

  private List<Integer> montarListaDeRetorno(Integer fim) {
    Integer vertice = fim;

    List<Integer> caminho = new ArrayList<>();

    do {
      caminho.add(vertice);
      vertice = antecessor.get(vertice);
    } while (vertice != null);

    Collections.reverse(caminho);
    return caminho;
  }

  private void executarBfs() {
    // propagação das visitas
    while (!visitas.isEmpty()) {
      Integer verticeDeOrigem = visitas.poll();
      List<Tuple2<Integer, Integer>> adjacenciasDeVertice = adjacencias.get(verticeDeOrigem);

      if (Objects.isNull(adjacenciasDeVertice))
        continue;

      for (Tuple2<Integer, Integer> aresta : adjacenciasDeVertice) {
        Integer verticeDeDestino = aresta.getItem2();
        if (observado.get(verticeDeDestino) == false) {
          observado.set(verticeDeDestino, true);
          custo.set(verticeDeDestino, custo.get(verticeDeOrigem) + 1);
          antecessor.set(verticeDeDestino, verticeDeOrigem);
          visitas.add(verticeDeDestino);
        }
      }
    }
  }

  private void configurarVerticeDeOrigem(Integer inicio) {
    observado.set(inicio, true);
    custo.set(inicio, 0);
  }

  private void inicializarAsEstrtururas(List<Integer> centrais, Integer inicio) {
    observado = new ArrayList<>(centrais.size());
    custo = new ArrayList<>(centrais.size());
    antecessor = new ArrayList<>(centrais.size());

    for (int i = 0; i < centrais.size(); i++) {
      observado.add(false);
      custo.add(Integer.MAX_VALUE);
      antecessor.add(null);
    }

    visitas = new ArrayDeque<>();
    visitas.add(inicio);
  }

  private void criarListaDeAdjacencia(List<Tuple2<Integer, Integer>> pares) {
    List<Tuple2<Integer, Integer>> paresReversos = pares.stream().map(m -> Tuple2.of(m.getItem2(), m.getItem1()))
        .collect(Collectors.toList());

    List<Tuple2<Integer, Integer>> paresEParesReversos = new ArrayList<>(pares.size() * 2);
    paresEParesReversos.addAll(pares);
    paresEParesReversos.addAll(paresReversos);

    adjacencias = paresEParesReversos.stream().collect(Collectors.groupingBy(Tuple2::getItem1));
  }
}