package org.RemastMathMaven.entitiesDTO;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class ForcasExternasDTO {
    private List<ForcaPontualDTO> listaForcasPontual;
    private List<ForcaDistribuidaDTO> listaForcasDistribuida;
    private List<ForcaMomentoDTO> listaForcasMomento;

    public ForcasExternasDTO(List<ForcaPontualDTO> listaForcaPontual, List<ForcaDistribuidaDTO> distribuida, List<ForcaMomentoDTO> momento) {
        this.listaForcasPontual = listaForcaPontual;
        this.listaForcasDistribuida = distribuida;
        this.listaForcasMomento = momento;
    }

    public List<ForcaPontualDTO> getListaForcasPontual() {
        return listaForcasPontual;
    }

    public void setListaForcasPontual(List<ForcaPontualDTO> listaForcasPontual) {
        this.listaForcasPontual = listaForcasPontual;
    }

    public List<ForcaDistribuidaDTO> getListaForcasDistribuida() {
        return listaForcasDistribuida;
    }

    public void setListaForcasDistribuida(List<ForcaDistribuidaDTO> listaForcasDistribuida) {
        this.listaForcasDistribuida = listaForcasDistribuida;
    }

    public List<ForcaMomentoDTO> getListaForcasMomento() {
        return listaForcasMomento;
    }

    public void setListaForcasMomento(List<ForcaMomentoDTO> listaForcasMomento) {
        this.listaForcasMomento = listaForcasMomento;
    }
}
