package org.RemastMathMaven.entitiesDTO;

import java.util.List;

public class VigaDTO {

    private int tamanhodaViga;

    private ForcasInternasDTO forcasInternas;
    private ApoiosDTO apoioInicial;
    private ApoiosDTO apoioFinal;
    private List<SecoesDTO> secoesList;

    public VigaDTO(int tamanhodaViga, ForcasInternasDTO forcasInternas, ApoiosDTO opoioInicial, ApoiosDTO apoioFinal) {
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

    public ForcasInternasDTO getForcasInternas() {
        return forcasInternas;
    }

    public void setForcasInternas(ForcasInternasDTO forcasInternas) {
        this.forcasInternas = forcasInternas;
    }

    public ApoiosDTO getApoioInicial() {
        return apoioInicial;
    }

    public void setApoioInicial(ApoiosDTO apoioInicial) {
        this.apoioInicial = apoioInicial;
    }

    public ApoiosDTO getApoioFinal() {
        return apoioFinal;
    }

    public void setApoioFinal(ApoiosDTO apoioFinal) {
        this.apoioFinal = apoioFinal;
    }

    public List<SecoesDTO> getSecoesList() {
        return secoesList;
    }

    public void setSecoesList(List<SecoesDTO> secoesList) {
        this.secoesList = secoesList;
    }
}

