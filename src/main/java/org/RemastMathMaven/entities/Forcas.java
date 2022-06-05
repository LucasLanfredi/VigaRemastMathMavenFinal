package org.RemastMathMaven.entities;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Forcas {

    public EnumForcaTipo tipo;
    private int position;
    private int positionFinal;
    private int forcaAplicada;
    private int forcaAplicadaFinal;

    private int positionEquivalente;
    private int forcaResultante;

    public Forcas() {
    }

    public Forcas(EnumForcaTipo tipo, int position, int value) {
        this.tipo = tipo;
        this.position = position;
        this.forcaAplicada = value;
    }

    public Forcas(EnumForcaTipo tipo,int positionEquivalente, int forcaResultante, int positionInitial, int positionFinal, int forcaAplicadaInicial, int forcaAplicadaFinal) {
        this.tipo = tipo;
        this.positionEquivalente = positionEquivalente;
        this.forcaResultante = forcaResultante;
        this.position = positionInitial;
        this.positionFinal = positionFinal;
        this.forcaAplicada = forcaAplicadaInicial;
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

    public EnumForcaTipo getTipo() {
        return tipo;
    }

    public void setTipo(EnumForcaTipo tipo) {
        this.tipo = tipo;
    }

    public int getPositionFinal() {
        return positionFinal;
    }

    public void setPositionFinal(int positionFinal) {
        this.positionFinal = positionFinal;
    }

    public int getForcaAplicadaFinal() {
        return forcaAplicadaFinal;
    }

    public void setForcaAplicadaFinal(int forcaAplicadaFinal) {
        this.forcaAplicadaFinal = forcaAplicadaFinal;
    }

    public int getForcaResultante() {
        return forcaResultante;
    }


    public static List<Forcas> generateList() {
        return new ArrayList<>();
    }

    public int getPositionMedia() {
        return positionEquivalente;
    }
}
