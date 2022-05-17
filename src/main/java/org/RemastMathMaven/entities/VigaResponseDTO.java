package org.RemastMathMaven.entities;

import org.springframework.stereotype.Component;

@Component
public class VigaResponseDTO {

    private final int tamanhodaViga;

    private final ForcasInternas forcasInternas;
    private final Apoios apoioInicial;
    private final Apoios apoioFinal;

    public VigaResponseDTO(Viga viga) {
        this.tamanhodaViga = viga.getTamanhodaViga();
        this.forcasInternas = viga.getForcasInternas();
        this.apoioInicial = viga.getApoioInicial();
        this.apoioFinal = viga.getApoioFinal();
    }


}
