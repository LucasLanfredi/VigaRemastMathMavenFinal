package org.RemastMathMaven.services;

import org.RemastMathMaven.entities.EnumForcaTipo;
import org.RemastMathMaven.entities.ExplicacaoExercise;
import org.RemastMathMaven.entities.Fletor;
import org.RemastMathMaven.entities.Forcas;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FletorService {

    public List<Fletor> criarUmaListaDeSecoesWithForcas(List<Forcas> allForcas, List<Integer> positionsList) {
        List<Fletor> secoesFletoras = new ArrayList<>();
        int[] positionY = {0, 0, 100};
        for (int i = 0; i < (positionsList.size() - 1); i++) {
            int[] positionX = {positionsList.get(i), positionsList.get(i+1), 100};
            secoesFletoras.set(i,new Fletor(positionX, positionY));
        }

        for (int i = 0; i <= secoesFletoras.size(); i++) { // Em cada seção
            int xmax = secoesFletoras.get(i).findIndexOfPositionY(secoesFletoras.get(i).getPositionX().length - 1);
            int xmin = secoesFletoras.get(i).findIndexOfPositionX(0);

            for (int j = 0; j < secoesFletoras.get(i).getPositionX().length; j++) { // Para cada valor de X
                int xatual = secoesFletoras.get(i).findIndexOfPositionX(j);
                int matual = 0;

                for (int k = 0; k < allForcas.size(); k++) { // Para cada carga

                    if (allForcas.get(k).tipo == EnumForcaTipo.FORCA_PONTUAL ||
                            allForcas.get(k).tipo == EnumForcaTipo.FORCA_REACAO) { // Verifica se é força pontual
                        if (allForcas.get(k).getPosition() < xatual) { // Verifica se está dentro da seção
                            matual -= allForcas.get(k).getForcaAplicada() * (xatual - allForcas.get(k).getPosition());
                        }
                    }

                    if (allForcas.get(k).tipo == EnumForcaTipo.FORCA_DISTRIBUIDA) { // Verifica se é forca distribuida

                        int wi = allForcas.get(k).getForcaAplicada();
                        int wf = allForcas.get(k).getForcaAplicadaFinal();
                        int xi = allForcas.get(k).getPosition();
                        int xf = allForcas.get(k).getPositionFinal();

                        if (xatual > xi && xatual < xf) {
                            int wfatual = wi + (wf - wi) * (xatual - xi) / (xf - xi);
                            int larguraAtual = xatual - xi;
                            int forcaTotalAtual = larguraAtual * (wi + wfatual) / 2;
                            int posicaoMediaAtual = xi + larguraAtual * (wi / 3 + 2 * wfatual / 3) / (wi + wfatual);

                            matual -= forcaTotalAtual * (xatual - posicaoMediaAtual);
                        }

                        if (xatual >= xf) {
                            matual -= allForcas.get(k).getForcaResultanteForcaDistribuidaRetangular() * (xatual - allForcas.get(k).getPositionMedia());
                        }
                    }

                    if (allForcas.get(k).tipo == EnumForcaTipo.FORCA_MOMENTO) {
                        if (allForcas.get(k).getPosition() <= xatual && allForcas.get(k).getPosition() != xmax) { // Verifica se está dentro da seção
                            matual -= allForcas.get(k).getForcaAplicada();
                        }
                    }

                }
                secoesFletoras.get(i).setValuseInsidePosition(j,matual);
            }
        }

        return secoesFletoras;
    }

}
