package org.RemastMathMaven.entities;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Secoes {

    int positionInit;
    int positionFinal;
    List<Forcas> forcasDentroDaSecao;

    public Secoes(int positionInit, int positionFinal) {
        this.positionInit = positionInit;
        this.positionFinal = positionFinal;
    }

    public int getPositionInit() {
        return positionInit;
    }

    public void setPositionInit(int positionInit) {
        this.positionInit = positionInit;
    }

    public int getPositionFinal() {
        return positionFinal;
    }

    public void setPositionFinal(int positionFinal) {
        this.positionFinal = positionFinal;
    }

    public List<Forcas> getForcasDentroDaSecao() {
        return forcasDentroDaSecao;
    }

    public void setForcasDentroDaSecao(List<Forcas> forcasDentroDaSecao) {
        this.forcasDentroDaSecao = forcasDentroDaSecao;
    }

}
