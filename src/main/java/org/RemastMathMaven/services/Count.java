package org.RemastMathMaven.services;

import org.RemastMathMaven.entities.Viga;
import org.RemastMathMaven.entities.VigaResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Count {

    @Autowired
    private final EsforcoCortanteService esforcoCortanteService;

    @Autowired
    private final ReacoesService reacoesService;

    public Count(EsforcoCortanteService esforcoCortanteService, ReacoesService reacoesService) {
        this.esforcoCortanteService = esforcoCortanteService;
        this.reacoesService = reacoesService;
    }

    public VigaResponseDTO countForcasDaViga(Viga viga) {
        reacoesService.setApoioValues(viga);
        esforcoCortanteService.calculoDoEsforcoCortante(viga);

        VigaResponseDTO vigaResponseDTO = new VigaResponseDTO(viga);
        return vigaResponseDTO;
    }

}
