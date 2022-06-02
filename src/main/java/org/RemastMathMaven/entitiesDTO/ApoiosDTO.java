package org.RemastMathMaven.entitiesDTO;

public class ApoiosDTO {

    private int position;
    private int typeApoio;
    private int forcaReacaoDoApoio;

    public ApoiosDTO(int position, int typeApoio) {
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

    public int getForcaReacaoDoApoio() {
        return forcaReacaoDoApoio;
    }

    public void setForcaReacaoDoApoio(int forcaReacaoDoApoio) {
        this.forcaReacaoDoApoio = forcaReacaoDoApoio;
    }
}
