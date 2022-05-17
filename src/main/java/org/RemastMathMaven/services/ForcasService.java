package org.RemastMathMaven.services;

import org.RemastMathMaven.entities.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForcasService {

    public List<Forcas> getAllListaForcas(ForcasInternas forcasInternas) {
        List<Forcas> listaDeForcas = Forcas.generateList();
        List<ForcaPontual> listaForcasPontual = listarForcasPontuais(forcasInternas);
        List<ForcaDistribuida> listaForcasDistribuida = listarForcasDistribuida(forcasInternas);
        List<ForcaMomento> listaForcasMomento = listarForcasMomento(forcasInternas);

        if (!listaForcasPontual.isEmpty()) {
            for (ForcaPontual forcaPontual : listaForcasPontual) {
                Forcas forca = new Forcas(forcaPontual.getPosition(), forcaPontual.getForcaAplicada());
                listaDeForcas.add(forca);
            }
        }
        if (!listaForcasDistribuida.isEmpty()) {
            for (ForcaDistribuida forcaDistribuida : listaForcasDistribuida) {
                Forcas forca = new Forcas(forcaDistribuida.getPositionInit(), forcaDistribuida.getPositionEnd(), forcaDistribuida.getForcaAplicadaInit(),
                        forcaDistribuida.getForcaAplicadaEnd());
                listaDeForcas.add(forca);
            }
        }

        if (!listaForcasMomento.isEmpty()) {
            for (ForcaMomento forcaMomento : listaForcasMomento) {
                Forcas forca = new Forcas(forcaMomento.getPosition(), forcaMomento.getForcaRotacaoAplicada());
                listaDeForcas.add(forca);
            }
        }
        return listaDeForcas;
    }

    public List<ForcaPontual> listarForcasPontuais(ForcasInternas forcasInternas) {
        return forcasInternas.getPontual().get();
    }

    public List<ForcaDistribuida> listarForcasDistribuida(ForcasInternas forcasInternas) {
        return forcasInternas.getDistribuida().get();
    }

    public List<ForcaMomento> listarForcasMomento(ForcasInternas forcasInternas) {
        return forcasInternas.getMomento().get();
    }

}
