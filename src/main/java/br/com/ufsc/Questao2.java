package br.com.ufsc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import io.smallrye.mutiny.tuples.Tuple2;

public class Questao2 {
  List<Boolean> observado;
  List<Double> custo;
  List<Integer> antecessor;
  List<Integer> localidades;
  List<Tuple2<Integer, Integer>> arcos;
  BiFunction<Integer, Integer, Double> funcaoDeCusto;
  Map<Integer, List<Tuple2<Integer, Integer>>> adjacencias;
  Double precoDoCombustivel;
  Double autonomiaDoVeiculo;
  Integer inicio;
  Integer fim;

  /**
   * O algoritmo abaixo retorna o menor caminho em número de passos de acordo com
   * as especificaçãos do exercício 1. Ele implementa o algoritmo de Dijkstra em
   * grafos. Por fins de simplicidade acadêmica, assume-se que a lista de
   * localidades foram geradas automaticamente e são 0-indexadas. Dada essas
   * características, apenas a quantidade de localidades seria necessária para
   * esse problema, manteu-se como uma lista para manter a compatibilidade com o
   * que foi passado no problema.
   * 
   * A automia é dada em km/l. Assume-se que existe um caminho válido entre origem
   * e destino, bem como que o grafo é conexo. Para simplifacação, considerou-$e
   * que a função de distância e pedágios são funções do tipo function e
   * bi-function, implementadas no java 8.
   * 
   * O grafo foi implementado através de uma lista de adjacências. O método não
   * possui tratamentos para elementos nulos ou índices fora do range.
   * 
   * @param localidades lista de localidades
   * @param arcos       lista com caminhos entre as localdiades
   * @param inicio      vértice de origem
   * @param fim         vértice de destino
   * @param funcaoDeDistancia uma função que recebe duas localidades e retorna a distância entre elas
   * @param funcaoDePedagio uma função que recebe uma localidade e retorna o custo para se passar por ela
   * @param precoDoCombustivel o valor do combustível
   * @param autonomiaDoVeiculo a autonomia do veículo
   * @return uma lista ordenada com as localidades para fazer o menor caminho
   */
  public List<Integer> menorRota(List<Integer> localidades, List<Tuple2<Integer, Integer>> arcos, //
      Integer inicio, Integer fim, BiFunction<Integer, Integer, Double> funcaoDeDistancia,
      Function<Integer, Double> funcaoDePedagio, Double precoDoCombustivel, Double autonomiaDoVeiculo) {

    funcaoDeCusto = construirFuncaoDeCusto(funcaoDeDistancia, funcaoDePedagio, precoDoCombustivel, autonomiaDoVeiculo);
    this.localidades = localidades;
    this.arcos = arcos;
    this.precoDoCombustivel = precoDoCombustivel;
    this.autonomiaDoVeiculo = autonomiaDoVeiculo;
    this.inicio = inicio;
    this.fim = fim;
    custo = new ArrayList<>();
    antecessor = new ArrayList<>();
    observado = new ArrayList<>();

    adjacencias = arcos.stream().collect(Collectors.groupingBy(Tuple2::getItem1));

    for (int i = 0; i < localidades.size(); i++) {
      custo.add(Double.MAX_VALUE);
      antecessor.add(null);
      observado.add(false);
    }

    custo.set(inicio, 0D);

    while (existeLocalidadeQueNaoFoiObservada(observado)) {
      Integer verticeDeMenorCusto = pegarVerticeAdjacenteAosVerticesAdicionadosComMenorCustoQueNaoFoiVisitado();
      observado.set(verticeDeMenorCusto, true);

      List<Tuple2<Integer, Integer>> adjacenciasDeVertice = adjacencias.get(verticeDeMenorCusto);
      if (adjacenciasDeVertice == null)
        adjacenciasDeVertice = new ArrayList<>();

      for (Tuple2<Integer, Integer> adjacencia : adjacenciasDeVertice) {
        Integer verticeDeOrigem = adjacencia.getItem1();
        Integer verticeDeDestino = adjacencia.getItem2();
        if (observado.get(verticeDeDestino))
          continue;
        if (custo.get(verticeDeDestino) > custo.get(verticeDeOrigem)
            + funcaoDeCusto.apply(verticeDeOrigem, verticeDeDestino)) {
          antecessor.set(verticeDeDestino, verticeDeOrigem);
          custo.set(verticeDeDestino,
              custo.get(verticeDeOrigem) + funcaoDeCusto.apply(verticeDeOrigem, verticeDeDestino));
        }
      }

    }
    Integer vertice = fim;

    List<Integer> caminho = new ArrayList<>();

    do {
      caminho.add(vertice);
      vertice = antecessor.get(vertice);
    } while (vertice != null);

    Collections.reverse(caminho);
    return caminho;
  }

  private Integer pegarVerticeAdjacenteAosVerticesAdicionadosComMenorCustoQueNaoFoiVisitado() {
    double menorCusto = Double.MAX_VALUE;
    int menorVertice = -1;

    for (Integer verticeDeOrigem : localidades) {
      if (observado.get(verticeDeOrigem))
        continue;
      if (custo.get(verticeDeOrigem) < menorCusto) {
        menorVertice = verticeDeOrigem;
        menorCusto = custo.get(verticeDeOrigem);
      }
    }
    return menorVertice;
  }

  private boolean existeLocalidadeQueNaoFoiObservada(List<Boolean> observado) {
    for (Boolean elemento : observado) {
      if (!elemento)
        return true;
    }
    return false;
  }

  private BiFunction<Integer, Integer, Double> construirFuncaoDeCusto(
      BiFunction<Integer, Integer, Double> funcaoDeDistancia, Function<Integer, Double> funcaoDePedagio,
      Double precoDoCombustivel, Double autonomiaDoVeiculo) {

    BiFunction<Integer, Integer, Double> funcaoDeCusto = (cidadeDeOrigem, cidadeDeDestino) -> //
    (funcaoDeDistancia.apply(cidadeDeOrigem, cidadeDeDestino) / autonomiaDoVeiculo) * precoDoCombustivel
        + funcaoDePedagio.apply(cidadeDeDestino);

    return funcaoDeCusto;
  }
}
