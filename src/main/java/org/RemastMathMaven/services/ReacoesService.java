package org.RemastMathMaven.services;

import org.RemastMathMaven.entities.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReacoesService extends ForcasService {

    public void setApoioValues(Viga viga, ExplicacaoExercise resposta) {
        int sumForcasPontuais = sumForcasPontuais(viga);
        int forcaDoApoioFinal = sumForcasMomentoParaDescobrirForcaNoApoio(viga, viga.getApoioInicial().getPosition(),
                viga.getApoioFinal().getPosition());
        int forcaDoApoioInicial = forcaDoApoioInicial(forcaDoApoioFinal, sumForcasPontuais);
        viga.getApoioInicial().setForcaReacaoDoApoio(forcaDoApoioInicial);
        viga.getApoioFinal().setForcaReacaoDoApoio(forcaDoApoioFinal);

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

        if (!forcaPontualList.isEmpty()) { sumPontual(sumForcasReacoes, forcaPontualList, referencePoint); }
        if (!forcaMomentoList.isEmpty()) { sumMomentoList(sumForcasReacoes, forcaMomentoList); }
        if (!forcaDistribuidaList.isEmpty()) { sumDistribuida(sumForcasReacoes, forcaDistribuidaList, referencePoint); }

        viga.getForcasInternas().setValueOnForcasPontual(referencePoint, sumForcasReacoes);
        int forcaDeReacaoDoApoio2 = sumForcasReacoes / (positionApoioFinal - referencePoint );
        viga.getForcasInternas().setValueOnForcasPontual(referencePoint, forcaDeReacaoDoApoio2);

        return forcaDeReacaoDoApoio2;

    }

    public int forcaDoApoioInicial(int forcaDoApoioFinal, int sumForcasPontuais) {
        return forcaDoApoioFinal - sumForcasPontuais;
    }

    private int sumPontual(int sumForcasMomento, List<ForcaPontual> forcaPontualList, int referencePoint) {
        for (ForcaPontual forcaPontual : forcaPontualList) {
            sumForcasMomento += (forcaPontual.getForcaAplicada() * (forcaPontual.getPosition() - referencePoint));
        }
        return sumForcasMomento;
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
