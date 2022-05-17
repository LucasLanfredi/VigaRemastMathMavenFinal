package org.RemastMathMaven.entities;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VigaDTO {

    private int tamanhodaViga;
    private List<ForcasInternas> forcasInternas;
    private List<Apoios> apoiosViga;

    public VigaDTO() {
    }

    public VigaDTO(int tamanhodaViga, List<ForcasInternas> forcasInternas, List<Apoios> apoiosViga) {
        this.tamanhodaViga = tamanhodaViga;
        this.forcasInternas = forcasInternas;
        this.apoiosViga = apoiosViga;
    }

    public int getTamanhodaViga() {
        return tamanhodaViga;
    }

    public void setTamanhodaViga(int tamanhodaViga) {
        this.tamanhodaViga = tamanhodaViga;
    }

    public List<ForcasInternas> getForcasInternas() {
        return forcasInternas;
    }

    public void setForcasInternas(List<ForcasInternas> forcasInternas) {
        this.forcasInternas = forcasInternas;
    }

    public List<Apoios> getApoiosViga() {
        return apoiosViga;
    }

    public void setApoiosViga(List<Apoios> apoiosViga) {
        this.apoiosViga = apoiosViga;
    }
}
