package org.VigaRemastMath.entities;

public class ForcaPontual {
    private int position;
    private int forcaAplicada;

    public ForcaPontual(int position, int forcaAplicada) {
        this.position = position;
        this.forcaAplicada= forcaAplicada;
    }

    public void setForcaAplicada(int forcaAplicada) {
        this.forcaAplicada = forcaAplicada;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getForcaAplicada() {
        return forcaAplicada;
    }

    public int getPosition() {
        return position;
    }
}
