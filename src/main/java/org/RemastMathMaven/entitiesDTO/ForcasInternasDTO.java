package org.RemastMathMaven.entitiesDTO;

import org.RemastMathMaven.entities.ForcaDistribuida;
import org.RemastMathMaven.entities.ForcaMomento;
import org.RemastMathMaven.entities.ForcaPontual;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class ForcasInternasDTO {
    private List<ForcaPontual> listaForcasPontual;
    private List<ForcaDistribuida> listaForcasDistribuida;
    private List<ForcaMomento> listaForcasMomento;

    public ForcasInternasDTO(List<ForcaPontual> listaForcaPontual, List<ForcaDistribuida> distribuida, List<ForcaMomento> momento) {
        this.listaForcasPontual = listaForcaPontual;
        this.listaForcasDistribuida = distribuida;
        this.listaForcasMomento = momento;
    }

    public ForcasInternasDTO(List<ForcaPontual> listaForcaPontual) {
        this.listaForcasPontual = listaForcaPontual;
    }

    public List<ForcaPontual> getListaForcasPontual() {
        return listaForcasPontual;
    }

    public void setListaForcasPontual(List<ForcaPontual> listaForcasPontual) {
        this.listaForcasPontual = listaForcasPontual;
    }

    public List<ForcaDistribuida> getListaForcasDistribuida() {
        return listaForcasDistribuida;
    }

    public void setListaForcasDistribuida(List<ForcaDistribuida> listaForcasDistribuida) {
        this.listaForcasDistribuida = listaForcasDistribuida;
    }

    public List<ForcaMomento> getListaForcasMomento() {
        return listaForcasMomento;
    }

    public void setListaForcasMomento(List<ForcaMomento> listaForcasMomento) {
        this.listaForcasMomento = listaForcasMomento;
    }

    public void setValueOnForcasPontual(int referencePoint,int sumForcasReacoes){
        this.listaForcasPontual.add(new ForcaPontual(referencePoint, sumForcasReacoes));
    }

    public void setValueOnForcasMomento(int referencePoint,int sumForcasReacoes){
        this.listaForcasMomento.add(new ForcaMomento(referencePoint, sumForcasReacoes));
    }

    public void setForcaDeReacao(int forcaPosition, int forcaVetor) {
        ForcaPontual forcaPontual = new ForcaPontual(forcaPosition, forcaVetor);
        this.listaForcasPontual.add(forcaPontual);
    }
}
