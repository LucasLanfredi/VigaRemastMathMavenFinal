package org.VigaRemastMath.entities;

public class Apoios {

    private int position;
    private int typeApoio;

    public Apoios(int position, int typeApoio) {
        this.position = position;
        this.typeApoio = typeApoio;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setTypeApoio(int typeApoio) {
        this.typeApoio = typeApoio;
    }

    public int getPosition() {
        return position;
    }

    public int getTypeApoio() {
        return typeApoio;
    }
}
