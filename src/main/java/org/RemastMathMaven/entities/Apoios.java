package org.RemastMathMaven.entities;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;

@Entity
public class Apoios {

    private String tipo;

    private int posicao;
    private int forcaReacaoDoApoio;

    public Apoios(String tipo, int posicao) {
        this.posicao = posicao;
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public int getForcaReacaoDoApoio() {
        return forcaReacaoDoApoio;
    }

    public void setForcaReacaoDoApoio(int forcaReacaoDoApoio) {
        this.forcaReacaoDoApoio = forcaReacaoDoApoio;
    }
}
