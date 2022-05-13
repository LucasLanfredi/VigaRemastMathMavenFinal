package org.RemastMathMaven.entities;


import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public class Viga {

    private int tamanhodaViga;
    private List<ForcasInternas> forcasInternas;
    private List<Integer> momento;
    private List<Apoios> apoiosViga;

    public Viga(int tamanhodaViga, Optional<List<Integer>> forcasInternas, Optional<List<Integer>> momento, Optional<List<Apoios>> apoiosViga) {
        super();
    }

    public void setApoiosViga(List<Apoios> apoiosViga) {
        this.apoiosViga = apoiosViga;
    }

    public void setForcasInternas(List<ForcasInternas> forcasInternas) {
        this.forcasInternas = forcasInternas;
    }

    public void setMomento(List<Integer> momento) {
        this.momento = momento;
    }

    public void setTamanhodaViga(int tamanhodaViga) {
        this.tamanhodaViga = tamanhodaViga;
    }

    public int getTamanhodaViga() {
        return tamanhodaViga;
    }

    public List<Apoios> getApoiosViga() {
        return apoiosViga;
    }

    public List<ForcasInternas> getForcasInternas() {
        return forcasInternas;
    }

    public List<Integer> getMomento() {
        return momento;
    }
}