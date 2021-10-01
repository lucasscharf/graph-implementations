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

public class Questao3 {
  private List<Boolean> observado;
  private List<Integer> custo;
  private List<Integer> antecessor;
  private Map<Integer, List<Tuple2<Integer, Integer>>> adjacencias;

  private Queue<Integer> visitas;

  /**
   * @param capacidadesDeCarga
   * @param pesos
   * @param lucros
   * @return
   */
  public Integer encontrarLucro(Integer[] capacidadesDeCarga, Integer[] pesos, Integer[] lucros) {
    int maxCarga = 0;
    for (Integer carga : capacidadesDeCarga) {
      if (maxCarga < carga)
        maxCarga = carga;
    }
    maxCarga++;

    Integer itens = pesos.length;
    Integer caminhoes = capacidadesDeCarga.length;
    Integer matriz[][][] = new Integer[itens][caminhoes][maxCarga];

    for (int i = 1; i < itens; i++) {
      for (int j = 1; j < caminhoes; j++) {
        for (int k = 0; k < maxCarga; k++) {
          matriz[i][j][k] = -1;
        }
      }
    }

    for (int i = 0; i < caminhoes; i++) {
      for (int j = 0; j < capacidadesDeCarga[i]; j++)
        matriz[0][i][j] = 0;
    }
    for (int i = 1; i < itens; i++) {
      for (int j = 1; j < caminhoes; j++) {
        for (int k = 0; k < capacidadesDeCarga[j]; k++) {
          if (k < pesos[i]) {
            matriz[i][j][k] = matriz[i - 1][j][k];
            continue;
          }

          int solucao = matriz[i - 1][j][k];

          for (int l = 0; l < caminhoes; l++) {
            int candidato = lucros[i] + matriz[i][l][k - pesos[i]];
            if (solucao < candidato) {
              solucao = candidato;
            }
          }

          matriz[i][j][k] = solucao;
        }
      }
    }

    return matriz[itens - 1][caminhoes - 1][capacidadesDeCarga[caminhoes] - 1];
  }
}