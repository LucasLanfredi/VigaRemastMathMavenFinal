package org.RemastMathMaven.entities;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;

@Entity
public class ForcaPontual {

    private int position;
    private int forcaAplicada;

    public ForcaPontual( int position, int forcaAplicada) {
        this.position = position;
        this.forcaAplicada = forcaAplicada;
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
}
