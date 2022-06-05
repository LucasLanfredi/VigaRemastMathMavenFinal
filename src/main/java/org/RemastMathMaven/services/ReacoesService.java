package org.RemastMathMaven.services;

import org.RemastMathMaven.entities.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReacoesService extends ForcasService {

    public void setApoioValues(Viga viga, ExplicacaoExerciseService resposta) {
        List<Forcas> listaForcas = getAllListaForcas(viga);
        int sumForcasdaViga = sumAllForcasWithoutMomento(listaForcas);
        int forcaDoApoioFinal = sumForcasMomentoParaDescobrirForcaNoApoio(viga, viga.getApoioInicial().getPosicao(),
                viga.getApoioFinal().getPosicao());
        int forcaDoApoioInicial = forcaDoApoioInicial(forcaDoApoioFinal, sumForcasdaViga);
        viga.getApoioInicial().setForcaReacaoDoApoio(-forcaDoApoioInicial);
        viga.getApoioFinal().setForcaReacaoDoApoio(-forcaDoApoioFinal);

    }

    public int sumForcasPontuais(Viga viga) {
        List<ForcaPontual> forcaPontualList = viga.listarForcasPontuais();
        int sumForcasPontuais = 0;
        for (ForcaPontual forcaPontual : forcaPontualList) {
            sumForcasPontuais += forcaPontual.getForcaAplicada();
        }
        return sumForcasPontuais;
    }

    public int sumForcasMomentoParaDescobrirForcaNoApoio(Viga viga, int referencePoint, int positionApoioFinal) {

        int sumForcasReacoes = 0;

        List<ForcaPontual> forcaPontualList = viga.listarForcasPontuais();
        List<ForcaMomento> forcaMomentoList = viga.listarForcasMomento();
        List<ForcaDistribuida> forcaDistribuidaList = viga.listarForcasDistribuida();

        if (!forcaPontualList.isEmpty()) { sumForcasReacoes =  sumPontual(sumForcasReacoes, forcaPontualList, referencePoint); }
        if (!forcaMomentoList.isEmpty()) { sumForcasReacoes = sumMomentoList(sumForcasReacoes, forcaMomentoList); }
        //       if (!forcaDistribuidaList.isEmpty()) { sumForcasReacoes += sumDistribuida(sumForcasReacoes, forcaDistribuidaList, referencePoint); }

        int forcaDeReacaoDoApoio2 = sumForcasReacoes / (positionApoioFinal - referencePoint );

        return forcaDeReacaoDoApoio2;

    }

    public int forcaDoApoioInicial(int forcaDoApoioFinal, int sumForcasPontuais) {
        return sumForcasPontuais - forcaDoApoioFinal ;
    }

    private int sumPontual(int sumForcasPontual, List<ForcaPontual> forcaPontualList, int referencePoint) {
        for (ForcaPontual forcaPontual : forcaPontualList) {
            sumForcasPontual += (forcaPontual.getForcaAplicada() * (forcaPontual.getPosition() - referencePoint));
        }
        return sumForcasPontual;
    }

    private int sumMomentoList(int sumForcasMomento, List<ForcaMomento> forcaMomentoList) {
        for (ForcaMomento forcaMomento : forcaMomentoList) {
            sumForcasMomento -= forcaMomento.getForcaRotacaoAplicada();
        }
        return sumForcasMomento;
    }

    private int sumDistribuida(int sumForcasDistribuida, List<ForcaDistribuida> forcaDistribuidaList, int referencePoint) {

        for (ForcaDistribuida forcaDistribuida : forcaDistribuidaList) {
            if(forcaDistribuida.getForcaAplicadaInit() == forcaDistribuida.getForcaAplicadaEnd()){

                forcaDistribuida.setPositionEquivalente((forcaDistribuida.getPositionInit() + forcaDistribuida.getPositionEnd())/2);
                forcaDistribuida.setForcaResultante(forcaDistribuida.getForcaAplicadaInit() *
                        Math.abs(forcaDistribuida.getPositionEnd() - forcaDistribuida.getPositionInit()));

            }
            else {

                forcaDistribuida.setPositionEquivalente(
                        ((forcaDistribuida.getPositionInit() + (forcaDistribuida.getPositionEnd() - forcaDistribuida.getPositionInit()))
                                * (((forcaDistribuida.getForcaAplicadaInit()/3) + ((2*forcaDistribuida.getForcaAplicadaEnd())/3)) /
                        (forcaDistribuida.getPositionInit() + forcaDistribuida.getPositionEnd())))
                );
                forcaDistribuida.setForcaResultante(
                        ((forcaDistribuida.getForcaAplicadaInit() + forcaDistribuida.getForcaAplicadaEnd())/2) *
                                (forcaDistribuida.getPositionEnd() - forcaDistribuida.getPositionInit())
                );


            }

            sumForcasDistribuida += forcaDistribuida.getForcaResultante()*(forcaDistribuida.getPositionEquivalente() - referencePoint);
        }
        return sumForcasDistribuida;
    }


}
