package org.RemastMathMaven.entities;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Forcas {

    private int position;
    private int positionFinal;
    private int forcaAplicada;
    private int forcaAplicadaFinal;

    public Forcas() {
    }

    public Forcas(int position, int value) {
        this.position = position;
        this.forcaAplicada = value;
    }

    public Forcas(int position, int positionFinal, int forcaAplicada, int forcaAplicadaFinal) {
        this.position = position;
        this.positionFinal = positionFinal;
        this.forcaAplicada = forcaAplicada;
        this.forcaAplicadaFinal = forcaAplicadaFinal;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getForcaAplicada() {
        return forcaAplicada;
    }

    public void setForcaAplicada(int forcaAplicada) {
        this.forcaAplicada = forcaAplicada;
    }

    public static List<Forcas> generateList() {
        return List.of(new Forcas());
    }
}
