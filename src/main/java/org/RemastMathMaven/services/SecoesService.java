package org.RemastMathMaven.services;

import org.RemastMathMaven.entities.EnumForcaTipo;
import org.RemastMathMaven.entities.ExplicacaoExercise;
import org.RemastMathMaven.entities.Forcas;
import org.RemastMathMaven.entities.Secoes;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SecoesService {


    public List<Secoes> criarUmaListaDeSecoesWithForcas(List<Forcas> forcas, List<Integer> pontosImportantes, ExplicacaoExercise resposta){

        List<Secoes> secoesCortantes = new ArrayList<>();
        int[] positionY = {0, 0, 100};
        for (int i = 0; i < (pontosImportantes.size() - 1); i++) {
            int[] positionX = {pontosImportantes.get(i), pontosImportantes.get(i+1), 100};
            secoesCortantes.set(i, new Secoes(positionX, positionY));
        }

        for (int i = 0; i <= secoesCortantes.size(); i++) {
            int xmax = secoesCortantes.get(i).findIndexOfPositionX(secoesCortantes.get(i).getPositionX().length - 1);
            int xmin = secoesCortantes.get(i).findIndexOfPositionX(0);

            for (int j = 0; j < secoesCortantes.get(i).getPositionX().length; j++) {
                int xatual = secoesCortantes.get(i).findIndexOfPositionX(j);
                int vatual = 0;

                for (int k = 0; k < forcas.size(); k++) {
                    if (forcas.get(k).tipo == EnumForcaTipo.FORCA_PONTUAL ||
                            forcas.get(k).tipo == EnumForcaTipo.FORCA_REACAO) {
                        if (forcas.get(k).getPosition() <= xatual && forcas.get(k).getPosition() != xmax) {
                            vatual -= forcas.get(k).getForcaAplicada();
                        }
                    }

                    if (forcas.get(k).tipo == EnumForcaTipo.FORCA_DISTRIBUIDA) {

                        int wi = forcas.get(k).getForcaAplicada();
                        int wf = forcas.get(k).getForcaAplicadaFinal();
                        int xi = forcas.get(k).getPosition();
                        int xf = forcas.get(k).getPositionFinal();

                        if (xatual > xi && xatual < xf) {
                            int wfatual = wi + (wf - wi) * (xatual - xi) / (xf - xi);
                            int larguraAtual = xatual - xi;
                            int forcaTotalAtual = larguraAtual * (wi + wfatual) / 2;
                            int posicaoMediaAtual = xi + larguraAtual * (wi / 3 + 2 * wfatual / 3) / (wi + wfatual);

                            vatual -= forcaTotalAtual;
                        }

                        if (xatual >= xf) {
                            vatual -= forcas.get(k).getForcaResultanteForcaDistribuidaRetangular();
                        }
                    }
                }
                secoesCortantes.get(i).setValuseInsidePosition(j, vatual);
            }
        }
        return secoesCortantes;
    }
}
