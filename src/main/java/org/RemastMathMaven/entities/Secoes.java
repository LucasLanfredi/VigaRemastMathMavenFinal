package org.RemastMathMaven.entities;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import java.util.List;
import java.util.stream.IntStream;

@Entity
public class Secoes {

    int[] positionX;
    int[] positionY;
    List<Forcas> forcasDentroDaSecao;

    public Secoes(int[] positionInit, int[] positionFinal) {
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

    public int findIndexOfPositionX(int value) {
        return IntStream.range(0, this.positionY.length)
                .filter(i->this.positionY[i]==value)
                .findFirst()
                .getAsInt();
    }

    public int findIndexOfPositionY(int j) {
        return IntStream.range(0, this.positionY.length)
                .filter(i->this.positionY[i]==j)
                .findFirst()
                .getAsInt();
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
