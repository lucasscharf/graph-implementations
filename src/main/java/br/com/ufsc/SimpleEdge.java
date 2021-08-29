package br.com.ufsc;

import java.util.Objects;

public class SimpleEdge {
  final private Integer origin;
  final private Integer destination;
  final private Integer weight;

  public SimpleEdge(Integer origin, Integer destination, Integer weight) {
    this.origin = origin;
    this.destination = destination;
    this.weight = weight;
  }

  public SimpleEdge(Integer origin, Integer destination) {
    this.origin = origin;
    this.destination = destination;
    this.weight = 1;
  }

  public Integer getOrigin() {
    return origin;
  }

  public Integer getDestination() {
    return destination;
  }

  public Integer getWeight() {
    return weight;
  }

  @Override
  public int hashCode() {
    return Objects.hash(destination, origin, weight);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    SimpleEdge other = (SimpleEdge) obj;
    return Objects.equals(destination, other.destination) && Objects.equals(origin, other.origin)
        && Objects.equals(weight, other.weight);
  }


  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("SimpleEdge [destination=");
    builder.append(destination);
    builder.append(", origin=");
    builder.append(origin);
    builder.append(", weight=");
    builder.append(weight);
    builder.append("]");
    return builder.toString();
  }
}