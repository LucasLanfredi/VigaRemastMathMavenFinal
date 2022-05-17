package org.RemastMathMaven.entities;

import org.springframework.stereotype.Component;

@Component
public class ForcaDistribuida {

    private final int id;

    private int positionInit;
    private int positionEnd;
    private int forcaAplicadaInit;
    private int forcaAplicadaEnd;

    public ForcaDistribuida(int id, int positionInit, int positionEnd, int forcaAplicadaInit, int forcaAplicadaEnd) {
        this.id = id;
        this.positionInit = positionInit;
        this.positionEnd = positionEnd;
        this.forcaAplicadaInit = forcaAplicadaInit;
        this.forcaAplicadaEnd = forcaAplicadaEnd;
    }

    public int getId() {
        return id;
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

