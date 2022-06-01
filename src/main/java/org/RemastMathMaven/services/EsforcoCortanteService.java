package org.RemastMathMaven.services;

import org.RemastMathMaven.entities.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EsforcoCortanteService extends ForcasService {

    private final PositionFindService positionFindService;
    private final SecoesService secoesService;
    private final ForcasService forcasService;
    private final FletorService fletorService;

    public EsforcoCortanteService(PositionFindService positionFindService, SecoesService secoesService, ForcasService forcasService,
                                  FletorService fletorService) {
        this.positionFindService = positionFindService;
        this.secoesService = secoesService;
        this.forcasService = forcasService;
        this.fletorService = fletorService;
    }

    public void calculoDoEsforcoCortante(Viga viga, ExplicacaoExercise resposta) {
        resposta.setCabecalhoEsforçoCortante();
        List<Integer> positionsList = positionFindService.getAllPositionListWithoutDuplicateToSecoes(viga);
        int quantidadeDeSecoes = ((positionFindService.getAllPositionListWithoutDuplicateToSecoes(viga).size()));
        List<Forcas> allForcas = forcasService.getAllListaForcasWithReacao(viga, List.of(viga.getApoioFinal(),viga.getApoioFinal()));

        List<Secoes> listOfSecoes = secoesService.criarUmaListaDeSecoesWithForcas(allForcas , positionsList);
        List<Fletor> listOfFletor = fletorService.criarUmaListaDeSecoesWithForcas(allForcas , positionsList);


    }

}
