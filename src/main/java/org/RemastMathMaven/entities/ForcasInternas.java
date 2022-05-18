package org.RemastMathMaven.entities;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ForcasInternas {

    private Optional<List<ForcaPontual>> pontual;
    private Optional<List<ForcaDistribuida>> distribuida;
    private Optional<List<ForcaMomento>> momento;

    public ForcasInternas(Optional<List<ForcaPontual>> pontual, Optional<List<ForcaDistribuida>> distribuida, Optional<List<ForcaMomento>> momento) {
        this.pontual = pontual;
        this.distribuida = distribuida;
        this.momento = momento;
    }

    public Optional<List<ForcaPontual>> getPontual() {
        return pontual;
    }

    public void setPontual(Optional<List<ForcaPontual>> pontual) {
        this.pontual = pontual;
    }

    public Optional<List<ForcaDistribuida>> getDistribuida() {
        return distribuida;
    }

    public void setDistribuida(Optional<List<ForcaDistribuida>> distribuida) {
        this.distribuida = distribuida;
    }

    public Optional<List<ForcaMomento>> getMomento() {
        return momento;
    }

    public void setMomento(Optional<List<ForcaMomento>> momento) {
        this.momento = momento;
    }

    public List<ForcaPontual> listarForcasPontuais(ForcasInternas forcasInternas) {
        return forcasInternas.getPontual().get();
    }

    public List<ForcaDistribuida> listarForcasDistribuida(ForcasInternas forcasInternas) {
        return forcasInternas.getDistribuida().get();
    }

    public List<ForcaMomento> listarForcasMomento(ForcasInternas forcasInternas) {
        return forcasInternas.getMomento().get();
    }
}
