package org.RemastMathMaven.entities;

import javax.persistence.Entity;
import java.util.List;
import java.util.stream.IntStream;

@Entity
public class Cortante {

    int[] positionX;
    int[] positionY;
    List<Forcas> forcasDentroDaSecao;

    public Cortante(int[] positionInit, int[] positionFinal) {
        this.positionX = positionInit;
        this.positionY = positionFinal;
    }

    public int[] getPositionX() {
        return positionX;
    }

    public void setPositionX(int[] positionX) {
        this.positionX = positionX;
    }

    public int[] getPositionY() {
        return positionY;
    }

    public void setPositionY(int[] positionY) {
        this.positionY = positionY;
    }

    public List<Forcas> getForcasDentroDaSecao() {
        return forcasDentroDaSecao;
    }

    public void setForcasDentroDaSecao(List<Forcas> forcasDentroDaSecao) {
        this.forcasDentroDaSecao = forcasDentroDaSecao;
    }

    public void setValuseInsidePosition(int k, int value) {
        this.positionY[k] = value;
    }
}
