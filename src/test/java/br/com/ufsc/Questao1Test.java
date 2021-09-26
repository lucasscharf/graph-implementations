package br.com.ufsc;

import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.mutiny.tuples.Tuple2;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Questao1Test {
  Questao1 questao1 = new Questao1();

  @Test
  public void teste1VerticeSemAresta() {
    List<Integer> caminho = questao1.encontrarCaminho(Arrays.asList(0), new ArrayList<>(), 0, 0);
    System.out.println(caminho);
  }

  @Test
  public void teste1VerticeComAutoAresta() {
    List<Integer> caminho = questao1.encontrarCaminho(Arrays.asList(0), //
        Arrays.asList(Tuple2.of(0, 0)), 0, 0);
    System.out.println(caminho);
  }

  @Test
  public void teste2VerticesCom1Aresta() {
    List<Integer> caminho = questao1.encontrarCaminho(Arrays.asList(0, 1), //
        Arrays.asList(Tuple2.of(1, 0)), 1, 0);
    System.out.println(caminho);
  }

  @Test
  public void testeOrigemEDestinoNaoConectados() {
    List<Integer> caminho = questao1.encontrarCaminho(Arrays.asList(0, 1, 2), //
        Arrays.asList(Tuple2.of(1, 2)), 0, 2);
    System.out.println(caminho);
  }

  @Test // exemplo baseado Giv da aula
  public void testeGrafoComplexo() {
    List<Integer> caminho = questao1.encontrarCaminho(Arrays.asList(0, 1, 2, 3, 4, 5), //
        Arrays.asList(//
            Tuple2.of(0, 1), //
            Tuple2.of(0, 2), //
            Tuple2.of(1, 2), //
            Tuple2.of(2, 3), //
            Tuple2.of(3, 4), //
            Tuple2.of(1, 4), //
            Tuple2.of(3, 5)),
        0, 5);
    System.out.println(caminho);
  }
}