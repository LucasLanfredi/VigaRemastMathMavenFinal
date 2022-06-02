package org.RemastMathMaven.entitiesDTO;

import java.util.List;
import java.util.stream.IntStream;

public class CortanteDTO {

    int[] positionX;
    int[] positionY;
    List<ForcasDTO> forcasDentroDaSecao;

    public CortanteDTO(int[] positionInit, int[] positionFinal) {
        this.positionX = positionInit;
        this.positionY = positionFinal;
    }

    public int[] getPositionX() {
        return positionX;
    }

    public void setPositionX(int[] positionX) {
        this.positionX = positionX;
    }

    public int[] getPositionY() {
        return positionY;
    }

    public int findIndexOfPositionX(int value) {
        return IntStream.range(0, this.positionY.length)
                .filter(i->this.positionY[i]==value)
                .findFirst()
                .getAsInt();
    }

    public int findIndexOfPositionY(int j) {
        return IntStream.range(0, this.positionY.length)
                .filter(i->this.positionY[i]==j)
                .findFirst()
                .getAsInt();
    }

    public void setPositionY(int[] positionY) {
        this.positionY = positionY;
    }

    public List<ForcasDTO> getForcasDentroDaSecao() {
        return forcasDentroDaSecao;
    }

    public void setForcasDentroDaSecao(List<ForcasDTO> forcasDentroDaSecao) {
        this.forcasDentroDaSecao = forcasDentroDaSecao;
    }

    public void setValuseInsidePosition(int j, int value) {
        this.positionY[j] = value;
    }
}
