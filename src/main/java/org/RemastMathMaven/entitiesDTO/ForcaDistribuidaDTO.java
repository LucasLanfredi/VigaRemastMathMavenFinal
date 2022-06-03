package org.RemastMathMaven.entitiesDTO;

import javax.persistence.Entity;

@Entity
public class ForcaDistribuidaDTO {

    private int positionEquivalente;
    private int forcaResultante;

    private int positionInit;
    private int positionEnd;
    private int forcaAplicadaInit;
    private int forcaAplicadaEnd;

    public ForcaDistribuidaDTO(int positionEquivalente, int forcaResultante, int positionInit, int positionEnd, int forcaAplicadaInit, int forcaAplicadaEnd) {
        this.positionEquivalente = positionEquivalente;
        this.forcaResultante = forcaResultante;
        this.positionInit = positionInit;
        this.positionEnd = positionEnd;
        this.forcaAplicadaInit = forcaAplicadaInit;
        this.forcaAplicadaEnd = forcaAplicadaEnd;
    }

    public int getPositionEquivalente() {
        return positionEquivalente;
    }

    public void setPositionEquivalente(int positionEquivalente){
        this.positionEquivalente = positionEquivalente;
    }

    public int getForcaResultante() {
        return forcaResultante;
    }

    public void setForcaResultante(int forcaResultante) {
        this.forcaResultante = forcaResultante;
    }

    public int getPositionInit() {
        return positionInit;
    }

    public void setPositionInit(int positionInit) {
        this.positionInit = positionInit;
    }

    public int getPositionEnd() {
        return positionEnd;
    }

    public void setPositionEnd(int positionEnd) {
        this.positionEnd = positionEnd;
    }

    public int getForcaAplicadaInit() {
        return forcaAplicadaInit;
    }

    public void setForcaAplicadaInit(int forcaAplicadaInit) {
        this.forcaAplicadaInit = forcaAplicadaInit;
    }

    public int getForcaAplicadaEnd() {
        return forcaAplicadaEnd;
    }

    public void setForcaAplicadaEnd(int forcaAplicadaEnd) {
        this.forcaAplicadaEnd = forcaAplicadaEnd;
    }
}

