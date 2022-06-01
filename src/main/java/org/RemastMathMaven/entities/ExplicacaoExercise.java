package org.RemastMathMaven.entities;

import org.springframework.stereotype.Component;

@Component
public class ExplicacaoExercise {

    private String explicacaoExercise;

    public ExplicacaoExercise() {
        this.explicacaoExercise = "";
    }

    public void setCabecalhoEsforçoCortante() {
        this.explicacaoExercise.concat("<h1> Cálculo do Esforço Cortante </h1>Para encontrar a equação do esforço cortante, é necessário fazer o" +
                " balanço de forças verticais em cada seção (que vão de 0 até X metros), ou seja: $$ " +
                "\\sum F_y + V(x) = 0 $$ Em que V(x) é o valor do esforço cortante na posição x.");
    }

    public String getExplicacaoExercise() {
        return explicacaoExercise;
    }
}
