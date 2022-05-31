package org.RemastMathMaven.services;

import org.RemastMathMaven.entities.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PositionFindService {

    private final ForcasService forcasService;

    public PositionFindService(ForcasService forcasService) {
        this.forcasService = forcasService;
    }

    public List<Integer> getAllPositionListWithoutDuplicate(Viga viga) {
        return removeDuplicatePositions(getAllPositionList(viga));
    }

    public List<Integer> getAllPositionListWithoutDuplicateToSecoes(Viga viga) {
        return removeDuplicatePositionsWithValueBase(getAllPositionList(viga), viga.getTamanhodaViga());
    }

    public List<Integer> getAllPositionList(Viga viga) {
        return Stream.concat(getPositionOfApoioEmUmaLista(viga.getApoioInicial(), viga.getApoioFinal()).stream(),
                setPositionOfForcasEmUmaLista(viga).stream()).collect(Collectors.toList());
    }

    public List<Integer> getPositionOfApoioEmUmaLista(Apoios apoioInicial, Apoios apoioFinal){
        List<Integer> list = new ArrayList<>();
        list.add(apoioInicial.getPosition());
        list.add(apoioFinal.getPosition());
        return list;
    }

    public List<Integer> setPositionOfForcasEmUmaLista(Viga viga) {
        List<ForcaPontual> listaForcasPontual = viga.listarForcasPontuais();
        List<ForcaMomento> listaForcasMomento = viga.listarForcasMomento();
        List<ForcaDistribuida> listaForcasDistribuida = viga.listarForcasDistribuida();
        List<Integer> list = new ArrayList<>();

        if (!listaForcasPontual.isEmpty()) {
            for (ForcaPontual forcaPontual : listaForcasPontual) {
                list.add(forcaPontual.getPosition());
            }
        }
        if (!listaForcasDistribuida.isEmpty()) {
            for (ForcaDistribuida forcaDistribuida : listaForcasDistribuida) {
                list.add(forcaDistribuida.getPositionInit());
                list.add(forcaDistribuida.getPositionEnd());
            }
        }

        if (!listaForcasMomento.isEmpty()) {
            for (ForcaMomento forcaMomento : listaForcasMomento) {
                list.add(forcaMomento.getPosition());
            }
        }
        return list;
    }

    public List<Integer> removeDuplicatePositionsWithValueBase(List<Integer> positionList, int tamanhoDaViga)
    {
        List<Integer> newList = new ArrayList<>();
        newList.add(0);
        newList.add(tamanhoDaViga);
        for (Integer position : positionList) {
            if (!newList.contains(position)) {
                newList.add(position);
            }
        }
        return newList;
    }

    public List<Integer> removeDuplicatePositions(List<Integer> positionList)
    {
        List<Integer> newList = new ArrayList<>();
        for (Integer position : positionList) {
            if (!newList.contains(position)) {
                newList.add(position);
            }
        }
        return newList;
    }
}
