package org.RemastMathMaven.services;

import org.RemastMathMaven.entities.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReacoesService extends ForcasService {

    public void setApoioValues(Viga viga) {
        int sumForcasPontuais = sumForcasPontuais(viga.getForcasInternas());
        int forcaDoApoioFinal = sumForcasMomentoParaDescobrirForcaNoApoio(viga.getForcasInternas(), viga.getApoioInicial().getPosition(),
                viga.getApoioFinal().getPosition());
        int forcaDoApoioInicial = forcaDoApoioInicial(forcaDoApoioFinal, sumForcasPontuais);
        viga.getApoioInicial().setForcaDoApoio(forcaDoApoioInicial);
        viga.getApoioFinal().setForcaDoApoio(forcaDoApoioFinal);
    }

    public int sumForcasPontuais(ForcasInternas forcasInternas) {
        List<ForcaPontual> forcaPontualList = listarForcasPontuais(forcasInternas);
        int sumForcasPontuais = 0;
        for (ForcaPontual forcaPontual : forcaPontualList) {
            sumForcasPontuais += forcaPontual.getForcaAplicada();
        }
        return sumForcasPontuais;
    }

    public int sumForcasMomentoParaDescobrirForcaNoApoio(ForcasInternas forcasInternas, int referencePoint, int positionApoioFinal) {
        int sumForcasReacoes = 0;
        List<ForcaPontual> forcaPontualList = listarForcasPontuais(forcasInternas);
        List<ForcaMomento> forcaMomentoList = listarForcasMomento(forcasInternas);
        List<ForcaDistribuida> forcaDistribuidaList = listarForcasDistribuida(forcasInternas);

        if (!forcaPontualList.isEmpty()) { sumPontual(sumForcasReacoes, forcaPontualList, referencePoint); }
        if (!forcaMomentoList.isEmpty()) { sumMomentoList(sumForcasReacoes, forcaMomentoList); }
        if (!forcaDistribuidaList.isEmpty()) { sumDistribuida(sumForcasReacoes, forcaDistribuidaList); }

        int forcaDeReacaoDoApoio2 = sumForcasReacoes / (positionApoioFinal - referencePoint );
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

    private int sumDistribuida(int sumForcasMomento, List<ForcaDistribuida> forcaDistribuidaList) {
        for (ForcaDistribuida forcaDistribuida : forcaDistribuidaList) {
            sumForcasMomento += (((forcaDistribuida.getForcaAplicadaInit() + forcaDistribuida.getForcaAplicadaEnd()) / 2) *
                    (forcaDistribuida.getForcaAplicadaEnd() - forcaDistribuida.getForcaAplicadaEnd()));
        }
        return sumForcasMomento;
    }
}
