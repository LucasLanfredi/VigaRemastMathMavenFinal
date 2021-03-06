package org.RemastMathMaven.entities;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;

@Entity
public class ForcaMomento {

    private int position;
    private int forcaRotacaoAplicada;

    public ForcaMomento(int position, int forcaAplicada) {
        this.position = position;
        this.forcaRotacaoAplicada = forcaAplicada;
    }

    public void setForcaRotacaoAplicada(int forcaRotacaoAplicada) {
        this.forcaRotacaoAplicada = forcaRotacaoAplicada;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getForcaRotacaoAplicada() {
        return forcaRotacaoAplicada;
    }

    public int getPosition() {
        return position;
    }
}
