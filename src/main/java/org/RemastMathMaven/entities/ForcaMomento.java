package org.RemastMathMaven.entities;

import org.springframework.stereotype.Component;

@Component
public class ForcaMomento {

    private int position;
    private int forcaRotacaoAplicada;

    public ForcaMomento(int id, int userId, int position, int forcaAplicada) {
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
