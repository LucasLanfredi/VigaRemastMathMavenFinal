package org.RemastMathMaven.services;

import org.RemastMathMaven.entities.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForcasService {

    public List<Forcas> getAllListaForcas(Viga viga) {
        List<Forcas> listaDeForcas = Forcas.generateList();
        List<ForcaPontual> listaForcasPontual = viga.listarForcasPontuais();
        List<ForcaDistribuida> listaForcasDistribuida = viga.listarForcasDistribuida();
        List<ForcaMomento> listaForcasMomento = viga.listarForcasMomento();

        if (!listaForcasPontual.isEmpty()) {
            for (ForcaPontual forcaPontual : listaForcasPontual) {
                Forcas forca = new Forcas(EnumForcaTipo.FORCA_PONTUAL,forcaPontual.getPosition(), forcaPontual.getForcaAplicada());
                listaDeForcas.add(forca);
            }
        }
        if (!listaForcasDistribuida.isEmpty()) {
            for (ForcaDistribuida forcaDistribuida : listaForcasDistribuida) {
                Forcas forca = new Forcas(EnumForcaTipo.FORCA_DISTRIBUIDA,forcaDistribuida.getPositionEquivalente(),forcaDistribuida.getForcaResultante(),forcaDistribuida.getPositionInit(), forcaDistribuida.getPositionEnd(), forcaDistribuida.getForcaAplicadaInit(),
                        forcaDistribuida.getForcaAplicadaEnd());
                listaDeForcas.add(forca);
            }
        }

        if (!listaForcasMomento.isEmpty()) {
            for (ForcaMomento forcaMomento : listaForcasMomento) {
                Forcas forca = new Forcas(EnumForcaTipo.FORCA_MOMENTO, forcaMomento.getPosition(), forcaMomento.getForcaRotacaoAplicada());
                listaDeForcas.add(forca);
            }
        }
        return listaDeForcas;
    }

    public List<Forcas> getAllListaForcasWithReacao(Viga viga, List<Apoios> apoios) {
        List<Forcas> listaDeForcas = Forcas.generateList();
        List<ForcaPontual> listaForcasPontual = viga.listarForcasPontuais();
        List<ForcaDistribuida> listaForcasDistribuida = viga.listarForcasDistribuida();
        List<ForcaMomento> listaForcasMomento = viga.listarForcasMomento();

        if (!listaForcasPontual.isEmpty()) {
            for (ForcaPontual forcaPontual : listaForcasPontual) {
                Forcas forca = new Forcas(EnumForcaTipo.FORCA_PONTUAL,forcaPontual.getPosition(), forcaPontual.getForcaAplicada());
                listaDeForcas.add(forca);
            }
        }
        if (!listaForcasDistribuida.isEmpty()) {
            for (ForcaDistribuida forcaDistribuida : listaForcasDistribuida) {
                Forcas forca = new Forcas(EnumForcaTipo.FORCA_DISTRIBUIDA,forcaDistribuida.getPositionEquivalente(),forcaDistribuida.getForcaResultante(),forcaDistribuida.getPositionInit(), forcaDistribuida.getPositionEnd(), forcaDistribuida.getForcaAplicadaInit(),
                        forcaDistribuida.getForcaAplicadaEnd());
                listaDeForcas.add(forca);
            }
        }

        if (!listaForcasMomento.isEmpty()) {
            for (ForcaMomento forcaMomento : listaForcasMomento) {
                Forcas forca = new Forcas(EnumForcaTipo.FORCA_MOMENTO, forcaMomento.getPosition(), forcaMomento.getForcaRotacaoAplicada());
                listaDeForcas.add(forca);
            }
        }

        if (!apoios.isEmpty()) {
            for (Apoios apoio : apoios) {
                Forcas forca = new Forcas(EnumForcaTipo.FORCA_REACAO, apoio.getPosition(), apoio.getForcaReacaoDoApoio());
                listaDeForcas.add(forca);
            }
        }

        return listaDeForcas;
    }

}
