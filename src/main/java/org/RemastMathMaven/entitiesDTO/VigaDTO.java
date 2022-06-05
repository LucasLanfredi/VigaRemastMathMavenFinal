package org.RemastMathMaven.entitiesDTO;


import org.RemastMathMaven.entities.*;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class VigaDTO {

    private int tamanhodaViga;

    private ForcasExternas forcasExternas;
    private Apoios apoioInicial;
    private Apoios apoioFinal;

    public VigaDTO(int tamanhodaViga, ForcasExternas forcasExternas, Apoios opoioInicial, Apoios apoioFinal) {
        this.tamanhodaViga = tamanhodaViga;
        this.forcasExternas = forcasExternas;
        this.apoioInicial = opoioInicial;
        this.apoioFinal = apoioFinal;
    }

    public int getTamanhodaViga() {
        return tamanhodaViga;
    }

    public void setTamanhodaViga(int tamanhodaViga) {
        this.tamanhodaViga = tamanhodaViga;
    }

    public ForcasExternas getForcasExternas() {
        return forcasExternas;
    }

    public void setForcasExternas(ForcasExternas forcasExternas) {
        this.forcasExternas = forcasExternas;
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
}