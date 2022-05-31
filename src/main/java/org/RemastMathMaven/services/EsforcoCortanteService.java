package org.RemastMathMaven.services;

import org.RemastMathMaven.entities.Forcas;
import org.RemastMathMaven.entities.Secoes;
import org.RemastMathMaven.entities.Viga;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EsforcoCortanteService extends ForcasService {

    private final PositionFindService positionFindService;

    public EsforcoCortanteService(PositionFindService positionFindService) {
        this.positionFindService = positionFindService;
    }

    public void calculoDoEsforcoCortante(Viga viga) {
        List<Forcas> positionsList = getAllListaForcas(viga);
        int quantidadeDeSecoes = ((positionFindService.getAllPositionListWithoutDuplicateToSecoes(viga).size()));
        List<Secoes> listOfSecoes = new ArrayList<>();
        for(int i = 0; i<=quantidadeDeSecoes ; i++) {

        }

    }

}
