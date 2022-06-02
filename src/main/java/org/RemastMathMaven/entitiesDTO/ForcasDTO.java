package org.RemastMathMaven.entitiesDTO;

import org.RemastMathMaven.entities.EnumForcaTipo;

import java.util.List;
public class ForcasDTO {

    public EnumForcaTipo tipo;
    private int position;
    private int positionFinal;
    private int forcaAplicada;
    private int forcaAplicadaFinal;

    public ForcasDTO() {
    }

    public ForcasDTO(EnumForcaTipo tipo, int position, int value) {
        this.tipo = tipo;
        this.position = position;
        this.forcaAplicada = value;
    }

    public ForcasDTO(EnumForcaTipo tipo, int positionInitial, int positionFinal, int forcaAplicadaInicial, int forcaAplicadaFinal) {
        this.tipo = tipo;
        this.position = positionInitial;
        this.positionFinal = positionFinal;
        this.forcaAplicada = forcaAplicadaInicial;
        this.forcaAplicadaFinal = forcaAplicadaFinal;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getForcaAplicada() {
        return forcaAplicada;
    }

    public void setForcaAplicada(int forcaAplicada) {
        this.forcaAplicada = forcaAplicada;
    }

    public EnumForcaTipo getTipo() {
        return tipo;
    }

    public void setTipo(EnumForcaTipo tipo) {
        this.tipo = tipo;
    }

    public int getPositionFinal() {
        return positionFinal;
    }

    public void setPositionFinal(int positionFinal) {
        this.positionFinal = positionFinal;
    }

    public int getForcaAplicadaFinal() {
        return forcaAplicadaFinal;
    }

    public void setForcaAplicadaFinal(int forcaAplicadaFinal) {
        this.forcaAplicadaFinal = forcaAplicadaFinal;
    }

    public int getForcaResultanteForcaDistribuidaRetangular() {
        return (forcaAplicada * Math.abs(positionFinal-position));
    }


    public static List<ForcasDTO> generateList() {
        return List.of(new ForcasDTO());
    }

    public int getPositionMedia() {
        return (Math.abs(positionFinal-position)/2);
    }
}
