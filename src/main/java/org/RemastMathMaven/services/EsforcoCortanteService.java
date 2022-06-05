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

    public void calculoDoEsforcoCortante(Viga viga, ExplicacaoExerciseService resposta) {

        List<Integer> positionsList = positionFindService.getAllPositionListWithoutDuplicateToSecoes(viga);
        List<Forcas> allForcas = forcasService.getAllListaForcasWithReacao(viga, List.of(viga.getApoioFinal(),
                viga.getApoioInicial()));

        List<Cortante> listOfSecoes = secoesService.criarUmaListaDeSecoesWithForcas(allForcas , positionsList);
        List<Fletor> listOfFletor = fletorService.criarUmaListaDeSecoesWithForcas(allForcas , positionsList);

        criarRespostaWithValues(resposta, allForcas, listOfSecoes, listOfFletor, viga.getListOfApoios());

    }

    private void criarRespostaWithValues(ExplicacaoExerciseService resposta, List<Forcas> allForcas, List<Cortante> listOfSecoes,
                                         List<Fletor> listOfFletor, List<Apoios> listApoios){
        resposta.setExplicacaoReacoes(allForcas, listApoios);
        resposta.setResultadoEsforcoCortante(allForcas, listOfSecoes);
        resposta.setResultadoMomentoFletor(allForcas, listOfFletor );
    }

}
