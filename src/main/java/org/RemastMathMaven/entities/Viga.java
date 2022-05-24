package org.RemastMathMaven.entities;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Viga {

    private Long IdViga;
    private int tamanhodaViga;

    private ForcasInternas forcasInternas;
    private Apoios apoioInicial;
    private Apoios apoioFinal;
    private List<Secoes> secoesList;

    public Viga(Long idViga, int tamanhodaViga, ForcasInternas forcasInternas, Apoios opoioInicial, Apoios apoioFinal) {
        IdViga = idViga;
        this.tamanhodaViga = tamanhodaViga;
        this.forcasInternas = forcasInternas;
        this.apoioInicial = opoioInicial;
        this.apoioFinal = apoioFinal;
    }

    public Long getIdViga() {
        return IdViga;
    }

    public void setIdViga(Long idViga) {
        IdViga = idViga;
    }

    public int getTamanhodaViga() {
        return tamanhodaViga;
    }

    public void setTamanhodaViga(int tamanhodaViga) {
        this.tamanhodaViga = tamanhodaViga;
    }

    public ForcasInternas getForcasInternas() {
        return forcasInternas;
    }

    public void setForcasInternas(ForcasInternas forcasInternas) {
        this.forcasInternas = forcasInternas;
    }

    public Apoios getApoioInicial() {
        return apoioInicial;
    }

    public void setApoioInicial(Apoios apoioInicial) {
        this.apoioInicial = apoioInicial;
    }

    public Apoios getApoioFinal() {
        return apoioFinal;
    }

    public void setApoioFinal(Apoios apoioFinal) {
        this.apoioFinal = apoioFinal;
    }

    public List<Secoes> getSecoesList() {
        return secoesList;
    }

    public void setSecoesList(List<Secoes> secoesList) {
        this.secoesList = secoesList;
    }
}