package org.RemastMathMaven.entitiesDTO;

import java.util.List;
public class ForcasInternasDTO {

    private List<ForcaPontualDTO> pontual;
    private List<ForcaDistribuidaDTO> distribuida;
    private List<ForcaMomentoDTO> momento;

    public ForcasInternasDTO(List<ForcaPontualDTO> pontual, List<ForcaDistribuidaDTO> distribuida, List<ForcaMomentoDTO> momento) {
        this.pontual = pontual;
        this.distribuida = distribuida;
        this.momento = momento;
    }

    public ForcasInternasDTO(List<ForcaPontualDTO> pontual) {
        this.pontual = pontual;
    }

    public List<ForcaPontualDTO> getPontual() {
        return pontual;
    }

    public void setPontual(List<ForcaPontualDTO> pontual) {
        this.pontual = pontual;
    }

    public List<ForcaDistribuidaDTO> getDistribuida() {
        return distribuida;
    }

    public void setDistribuida(List<ForcaDistribuidaDTO> distribuida) {
        this.distribuida = distribuida;
    }

    public List<ForcaMomentoDTO> getMomento() {
        return momento;
    }

    public void setMomento(List<ForcaMomentoDTO> momento) {
        this.momento = momento;
    }

    public void setValueOnForcasPontual(int referencePoint,int sumForcasReacoes){
        this.pontual.add(new ForcaPontualDTO(referencePoint, sumForcasReacoes));
    }

    public void setValueOnForcasMomento(int referencePoint,int sumForcasReacoes){
        this.momento.add(new ForcaMomentoDTO(referencePoint, sumForcasReacoes));
    }

    public void setForcaDeReacao(int forcaPosition, int forcaVetor) {
        ForcaPontualDTO forcaPontual = new ForcaPontualDTO(forcaPosition, forcaVetor);
        this.pontual.add(forcaPontual);
    }
}
