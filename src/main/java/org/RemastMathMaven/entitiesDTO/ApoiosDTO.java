package org.RemastMathMaven.entitiesDTO;

public class ApoiosDTO {

    private String tipo;
    private int posicao;

    public ApoiosDTO( String tipo, int posicao) {
        this.posicao = posicao;
        this.tipo = tipo;
    }

    public void setPosition(int posicao) {
        this.posicao = posicao;
    }

    public void setTypeApoio(String tipo) {
        this.tipo = tipo;
    }

    public int getPosition() {
        return posicao;
    }

    public String getTypeApoio() {
        return tipo;
    }

}
