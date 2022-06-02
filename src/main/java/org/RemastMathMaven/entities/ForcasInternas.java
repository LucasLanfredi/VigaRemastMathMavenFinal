package org.RemastMathMaven.entities;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
public class ForcasInternas {

    private List<ForcaPontual> pontual;
    private List<ForcaDistribuida> distribuida;
    private List<ForcaMomento> momento;

    public ForcasInternas(List<ForcaPontual> pontual, List<ForcaDistribuida> distribuida, List<ForcaMomento> momento) {
        this.pontual = pontual;
        this.distribuida = distribuida;
        this.momento = momento;
    }

    public ForcasInternas(List<ForcaPontual> pontual) {
        this.pontual = pontual;
    }

    public List<ForcaPontual> getPontual() {
        return pontual;
    }

    public void setPontual(List<ForcaPontual> pontual) {
        this.pontual = pontual;
    }

    public List<ForcaDistribuida> getDistribuida() {
        return distribuida;
    }

    public void setDistribuida(List<ForcaDistribuida> distribuida) {
        this.distribuida = distribuida;
    }

    public List<ForcaMomento> getMomento() {
        return momento;
    }

    public void setMomento(List<ForcaMomento> momento) {
        this.momento = momento;
    }

    public void setValueOnForcasPontual(int referencePoint,int sumForcasReacoes){
        this.pontual.add(new ForcaPontual(referencePoint, sumForcasReacoes));
    }

    public void setValueOnForcasMomento(int referencePoint,int sumForcasReacoes){
        this.momento.add(new ForcaMomento(referencePoint, sumForcasReacoes));
    }

    public void setForcaDeReacao(int forcaPosition, int forcaVetor) {
        ForcaPontual forcaPontual = new ForcaPontual(forcaPosition, forcaVetor);
        this.pontual.add(forcaPontual);
    }
}
