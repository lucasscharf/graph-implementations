package br.com.ufsc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import io.smallrye.mutiny.tuples.Tuple2;

public class Questao3Test {
  Questao3 questao3 = new Questao3();

  @Test
  public void teste1CaminhaoE1Item() {
    Integer[] capacidadesDeCarga = { -1, 10 };
    Integer[] pesos = { -1, 5 };
    Integer[] lucros = { -1, 6 };

    System.out.println(questao3.encontrarLucro(capacidadesDeCarga, pesos, lucros));
  }

  @Test
  public void teste1CaminhaoMesmoLucro() {
  }

  @Test
  public void teste1CaminhaoMesmoLucrosDiferentes() {
  }

  @Test
  public void teste2CaminhoesComMesmaCarga() {
  }
}