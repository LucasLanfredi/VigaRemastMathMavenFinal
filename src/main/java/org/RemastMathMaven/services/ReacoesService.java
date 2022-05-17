package org.RemastMathMaven.services;

import org.RemastMathMaven.entities.ForcaMomento;
import org.RemastMathMaven.entities.ForcaPontual;
import org.RemastMathMaven.entities.ForcasInternas;
import org.RemastMathMaven.entities.Viga;
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
        int sumForcasMomento = 0;
        List<ForcaPontual> forcaPontualList = listarForcasPontuais(forcasInternas);
        List<ForcaMomento> forcaMomentoList = listarForcasMomento(forcasInternas);
        if (!forcaPontualList.isEmpty()) {
            for (ForcaPontual forcaPontual : forcaPontualList) {
                sumForcasMomento += (forcaPontual.getForcaAplicada() * (forcaPontual.getPosition() - referencePoint));
            }
        }
        if (!forcaMomentoList.isEmpty()) {
            for (ForcaMomento forcaMomento : forcaMomentoList) {
                sumForcasMomento -= forcaMomento.getForcaRotacaoAplicada();
            }
        }
        sumForcasMomento = sumForcasMomento / (referencePoint - positionApoioFinal);
        return sumForcasMomento;
    }

    public int forcaDoApoioInicial(int forcaDoApoioFinal, int sumForcasPontuais) {
        return forcaDoApoioFinal - sumForcasPontuais;
    }
}
