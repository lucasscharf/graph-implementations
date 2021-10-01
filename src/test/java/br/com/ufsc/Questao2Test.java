package br.com.ufsc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import io.smallrye.mutiny.tuples.Tuple2;

public class Questao2Test {
  Questao2 questao2 = new Questao2();

  @Test
  public void teste1VerticeSemAresta() {
    List<Integer> caminho = questao2.menorRota(Arrays.asList(0), new ArrayList<>(), 0, 0, this::funcaoDeDistancia,
        this::funcaoDoPedagio, 1D, 1D);
    System.out.println(caminho);
  }

  @Test
  public void teste1VerticeComAutoAresta() {
    List<Integer> caminho = questao2.menorRota(Arrays.asList(0), //
        Arrays.asList(Tuple2.of(0, 0)), 0, 0, this::funcaoDeDistancia, this::funcaoDoPedagio, 1D, 1D);
    System.out.println(caminho);
  }

  @Test
  public void teste2VerticesCom1Aresta() {
    List<Integer> caminho = questao2.menorRota(Arrays.asList(0, 1), //
        Arrays.asList(Tuple2.of(0, 1)), 0, 1, this::funcaoDeDistancia, this::funcaoDoPedagio, 1D, 1D);
    System.out.println(caminho);
  }

  @Test // exemplo baseado G da aula
  public void testeGrafoComplexo() {
    List<Integer> caminho = questao2.menorRota(Arrays.asList(0, 1, 2, 3, 4), //
        Arrays.asList(//
            Tuple2.of(0, 1), //
            Tuple2.of(1, 2), //
            Tuple2.of(0, 3), //
            Tuple2.of(3, 4), //
            Tuple2.of(4, 2), //
            Tuple2.of(4, 3)//
        ), 0, 4, this::funcaoDeDistancia, this::funcaoDoPedagio, 1D, 1D);
    System.out.println(caminho);
  }

  public Double funcaoDoPedagio(Integer verticeDeDestino) {
    return 0D;
  }

  public Double funcaoDeDistancia(Integer verticeDeOrigem, Integer verticeDeDestino) {
    if (verticeDeOrigem == 0 && verticeDeDestino == 1)
      return 4D;

    if (verticeDeOrigem == 1 && verticeDeDestino == 2)
      return 4D;

    if (verticeDeOrigem == 0 && verticeDeDestino == 3)
      return 5D;

    if (verticeDeOrigem == 3 && verticeDeDestino == 4)
      return 1D;

    if (verticeDeOrigem == 4 && verticeDeDestino == 2)
      return 1D;

    if (verticeDeOrigem == 4 && verticeDeDestino == 3)
      return 7D;

    return Double.MAX_VALUE;
  }
}