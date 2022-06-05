package org.RemastMathMaven.entities;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import java.util.List;
import java.util.stream.IntStream;

@Entity
public class Fletor {

    int[] positionX;
    int[] positionY;
    List<Forcas> forcasDentroDaSecao;

    public Fletor(int[] positionInit, int[] positionFinal) {
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

    public void setValuseInsidePosition(int j, int value) {
        this.positionY[j] = value;
    }
}
