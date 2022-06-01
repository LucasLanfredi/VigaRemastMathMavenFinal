package org.RemastMathMaven.services;

import org.RemastMathMaven.entities.ExplicacaoExercise;
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

    public String countForcasDaViga(Viga viga, ExplicacaoExercise resposta ) {
        reacoesService.setApoioValues(viga, resposta);
        esforcoCortanteService.calculoDoEsforcoCortante(viga, resposta);
        return resposta.getExplicacaoExercise();
    }

}
