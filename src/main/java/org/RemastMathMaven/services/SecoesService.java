package org.RemastMathMaven.services;

import org.RemastMathMaven.entities.EnumForcaTipo;
import org.RemastMathMaven.entities.Forcas;
import org.RemastMathMaven.entities.Cortante;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SecoesService {

    public List<Cortante> criarUmaListaDeSecoesWithForcas(List<Forcas> forcas, List<Integer> pontosImportantes) {

        List<Cortante> cortanteCortantes = new ArrayList<>();
        for (int i = 0; i < (pontosImportantes.size() - 1); i++) {
            cortanteCortantes.add(new Cortante(linspace(pontosImportantes.get(i), pontosImportantes.get(i + 1), 100),
                    linspace(0, 0, 100)));
        }

        for (int i = 0; i < cortanteCortantes.size(); i++) { // Em cada seção

            for (int j = 0; j <= cortanteCortantes.size(); j++) {
                int xmax = cortanteCortantes.get(i).getPositionX()[cortanteCortantes.get(i).getPositionX().length - 1];
                int xmin = cortanteCortantes.get(i).getPositionX()[0];

                for (int k = 0; k < cortanteCortantes.get(i).getPositionX().length; k++) {
                    int xatual = cortanteCortantes.get(i).getPositionX()[cortanteCortantes.get(i).getPositionX().length - 1];
                    int vatual = 0;

                    for (int l = 0; l < forcas.size(); l++) {
                        if (forcas.get(l).tipo == EnumForcaTipo.FORCA_PONTUAL ||
                                forcas.get(l).tipo == EnumForcaTipo.FORCA_REACAO) {
                            if (forcas.get(l).getPosition() <= xatual && forcas.get(l).getPosition() != xmax) {
                                vatual -= forcas.get(l).getForcaAplicada();
                            }
                        }

                        if (forcas.get(l).tipo == EnumForcaTipo.FORCA_DISTRIBUIDA) {

                            int wi = forcas.get(l).getForcaAplicada();
                            int wf = forcas.get(l).getForcaAplicadaFinal();
                            int xi = forcas.get(l).getPosition();
                            int xf = forcas.get(l).getPositionFinal();

                            if (xatual > xi && xatual < xf) {
                                int wfatual = wi + (wf - wi) * (xatual - xi) / (xf - xi);
                                int larguraAtual = xatual - xi;
                                int forcaTotalAtual = larguraAtual * (wi + wfatual) / 2;
                                int posicaoMediaAtual = xi + larguraAtual * (wi / 3 + 2 * wfatual / 3) / (wi + wfatual);

                                vatual -= forcaTotalAtual;
                            }

                            if (xatual >= xf) {
                                vatual -= forcas.get(l).getForcaResultante();
                            }
                        }
                    }
                    cortanteCortantes.get(i).setValuseInsidePosition(k, vatual);
                }
            }
        }
        return cortanteCortantes;
    }

    public static int[] linspace(int min, int max, int points) {
        int[] d = new int[points];
        for (int i = 0; i < points; i++) {
            d[i] = min + i * (max - min) / (points - 1);
        }
        return d;
    }
}