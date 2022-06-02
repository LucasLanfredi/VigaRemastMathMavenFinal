package org.RemastMathMaven.entitiesDTO;

public class ForcaMomentoDTO {

    private int position;
    private int forcaRotacaoAplicada;

    public ForcaMomentoDTO(int position, int forcaAplicada) {
        this.position = position;
        this.forcaRotacaoAplicada = forcaAplicada;
    }

    public void setForcaRotacaoAplicada(int forcaRotacaoAplicada) {
        this.forcaRotacaoAplicada = forcaRotacaoAplicada;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getForcaRotacaoAplicada() {
        return forcaRotacaoAplicada;
    }

    public int getPosition() {
        return position;
    }
}
