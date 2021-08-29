package br.com.ufsc;

import java.util.ArrayList;
import java.util.List;

public class SimpleVertix {
  private final Integer id;
  private List<SimpleEdge> incomingEdges;
  private List<SimpleEdge> outcomingEdges;

  public SimpleVertix(Integer id) {
    this.id = id;
    this.incomingEdges = new ArrayList<>();
    this.outcomingEdges = new ArrayList<>(); 
  }

  public void addEdge(SimpleEdge edge) {
    incomingEdges.add(edge);
  }

  public void addIncomingEdge(SimpleEdge incomingEdge) {
    incomingEdges.add(incomingEdge);
  }

  public void addOutcomingEdge(SimpleEdge outcomingEdge) {
    outcomingEdges.add(outcomingEdge);
  }

  public void removeEdge(SimpleEdge edge) {
    removeIncomingEdge(edge);
    removeOutcomingEdge(edge);
  }

  public void removeIncomingEdge(SimpleEdge incomingEdge) {
    incomingEdges.remove(incomingEdge);
  }

  public void removeOutcomingEdge(SimpleEdge outcomingEdge){
    outcomingEdges.remove(outcomingEdge);
  }

  public Integer getDegree() {
    return getIncomingDegree();
  }

  public Integer getIncomingDegree() {
    return incomingEdges.size();
  }

  public Integer getOutcomingDegree() {
    return outcomingEdges.size();
  }

  public Integer getId() {
    return id;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("SimpleVertix [id=");
    builder.append(id);
    builder.append("]");
    return builder.toString();
  }
}