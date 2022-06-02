package org.RemastMathMaven.entities;


import javax.persistence.Entity;
import java.util.List;
@Entity
public class Viga {

    private int tamanhodaViga;

    private ForcasInternas forcasInternas;
    private Apoios apoioInicial;
    private Apoios apoioFinal;

    public Viga(int tamanhodaViga, ForcasInternas forcasInternas, Apoios opoioInicial, Apoios apoioFinal) {
        this.tamanhodaViga = tamanhodaViga;
        this.forcasInternas = forcasInternas;
        this.apoioInicial = opoioInicial;
        this.apoioFinal = apoioFinal;
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

    public List<Apoios> getListOfApoios(){
        return List.of(this.apoioInicial,this.apoioFinal);
    }

    public List<ForcaPontual> listarForcasPontuais() {
        return this.forcasInternas.getPontual();
    }

    public List<ForcaDistribuida> listarForcasDistribuida() {
        return this.forcasInternas.getDistribuida();
    }

    public List<ForcaMomento> listarForcasMomento() {
        return this.forcasInternas.getMomento();
    }
}