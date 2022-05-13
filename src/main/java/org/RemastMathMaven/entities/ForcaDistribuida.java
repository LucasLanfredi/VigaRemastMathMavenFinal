package org.RemastMathMaven.entities;

import org.springframework.stereotype.Component;

@Component
public class ForcaDistribuida {
    private int positionInit;
    private int positionEnd;
    private int forcaAplicadaInit;
    private int forcaAplicadaEnd;

    public ForcaDistribuida(int positionInit, int positionEnd, int forcaAplicadaInit, int forcaAplicadaEnd) {
        this.positionInit = positionInit;
        this.positionEnd = positionEnd;
        this.forcaAplicadaInit = forcaAplicadaInit;
        this.forcaAplicadaEnd = forcaAplicadaEnd;
    }

    public int getForcaAplicadaEnd() {
        return forcaAplicadaEnd;
    }

    public int getForcaAplicadaInit() {
        return forcaAplicadaInit;
    }

    public int getPositionEnd() {
        return positionEnd;
    }

    public int getPositionInit() {
        return positionInit;
    }

    public void setForcaAplicadaEnd(int forcaAplicadaEnd) {
        this.forcaAplicadaEnd = forcaAplicadaEnd;
    }

    public void setForcaAplicadaInit(int forcaAplicadaInit) {
        this.forcaAplicadaInit = forcaAplicadaInit;
    }

    public void setPositionEnd(int positionEnd) {
        this.positionEnd = positionEnd;
    }

    public void setPositionInit(int positionInit) {
        this.positionInit = positionInit;
    }
}

