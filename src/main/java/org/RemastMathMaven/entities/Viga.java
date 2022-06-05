package org.RemastMathMaven.entities;


import org.RemastMathMaven.entitiesDTO.ApoiosDTO;
import org.RemastMathMaven.entitiesDTO.ForcasExternasDTO;

import javax.persistence.Entity;
import java.util.List;
@Entity
public class Viga {

    private int tamanhodaViga;

    private ForcasExternas forcasExternas;
    private Apoios apoioInicial;
    private Apoios apoioFinal;

    public Viga(int tamanhodaViga, ForcasExternas forcasExternas, Apoios opoioInicial, Apoios apoioFinal) {
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

    public ForcasExternas getForcasInternas() {
        return forcasExternas;
    }

    public void setForcasInternas(ForcasExternas forcasExternas) {
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

    public List<Apoios> getListOfApoios(){
        return List.of(this.apoioInicial,this.apoioFinal);
    }

    public List<ForcaPontual> listarForcasPontuais() {
        return this.forcasExternas.getListaForcasPontual();
    }

    public List<ForcaDistribuida> listarForcasDistribuida() {
        return this.forcasExternas.getListaForcasDistribuida();
    }

    public List<ForcaMomento> listarForcasMomento() {
        return this.forcasExternas.getListaForcasMomento();
    }
}