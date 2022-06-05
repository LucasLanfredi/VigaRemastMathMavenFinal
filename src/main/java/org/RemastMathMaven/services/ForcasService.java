package org.RemastMathMaven.services;

import org.RemastMathMaven.entities.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public int sumAllForcasWithoutMomento(List<Forcas> listForcas){
        int sumForcas = 0;
        for(Forcas forca : listForcas){
            if(forca.tipo == EnumForcaTipo.FORCA_PONTUAL || forca.tipo == EnumForcaTipo.FORCA_REACAO){
                sumForcas += forca.getForcaAplicada();
            }
            if(forca.tipo == EnumForcaTipo.FORCA_DISTRIBUIDA) {
                sumForcas += forca.getForcaResultante();
            }
        }
        return sumForcas;
    }

    public int sumAllForcas(List<Forcas> listForcas){
        int sumForcas = 0;
        for(Forcas forca : listForcas){
            if(forca.tipo == EnumForcaTipo.FORCA_PONTUAL || forca.tipo == EnumForcaTipo.FORCA_REACAO){
                sumForcas += forca.getForcaAplicada();
            }
            if(forca.tipo == EnumForcaTipo.FORCA_MOMENTO){
                sumForcas -= forca.getForcaAplicada();
            }
            if(forca.tipo == EnumForcaTipo.FORCA_DISTRIBUIDA) {
                sumForcas += forca.getForcaResultante();
            }
        }
        return sumForcas;
    }

    public List<Forcas> getAllListaForcasWithReacao(Viga viga, List<Apoios> apoios) {
        List<Forcas> listaDeForcas = new ArrayList<>();
        List<ForcaPontual> listaForcasPontual = viga.listarForcasPontuais();
        List<ForcaDistribuida> listaForcasDistribuida = viga.listarForcasDistribuida();
        List<ForcaMomento> listaForcasMomento = viga.listarForcasMomento();

        if (!listaForcasPontual.isEmpty()) {
            for (ForcaPontual forcaPontual : listaForcasPontual) {
                Forcas forcatemp = new Forcas(EnumForcaTipo.FORCA_PONTUAL,forcaPontual.getPosition(), forcaPontual.getForcaAplicada());
                listaDeForcas.add(forcatemp);
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
                Forcas forca = new Forcas(EnumForcaTipo.FORCA_REACAO, apoio.getPosicao(), apoio.getForcaReacaoDoApoio());
                listaDeForcas.add(forca);
            }
        }

        return listaDeForcas;
    }

}
