package org.RemastMathMaven.entities;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExplicacaoExercise {

    private String explicacaoExercise;

    public ExplicacaoExercise() {
        this.explicacaoExercise = "";
    }

    public void setResultadoEsforcoCortante(List<Forcas> forcas, List<Secoes> secoesCortantes ) {
        var omiteMais = true;
        StringBuilder explicacaoExercise = new StringBuilder();
        explicacaoExercise.append("<h1> Cálculo do Esforço Cortante </h1>");
        explicacaoExercise.append("Para encontrar a equação do esforço cortante, é necessário fazer o balanço de forças verticais " + "em cada seção (que vão de $0$ até $x$ metros), ou seja: $$ \\sum F_y + V(x) = 0 $$ Em que $V(x)$ é o valor do" + " esforço cortante na posição $x$.");

        int n = 0;
        int numCargas ;
        while (true) {
            if (forcas.get(n).tipo == EnumForcaTipo.FORCA_REACAO) {
                numCargas = n;
                break;
            }
            n++;
        }

        for (int i = 0; i < secoesCortantes.size(); i++) {
            var xinicial = secoesCortantes.get(i).findIndexOfPositionX(0);
            var xfinal = secoesCortantes.get(i).findIndexOfPositionX(secoesCortantes.get(i).positionX.length - 1);
            StringBuilder textoTemp = new StringBuilder();
            explicacaoExercise.append("<h2> " + "Seção" + " ").append(i + 1).append(" $ ( ").append(xinicial).append(" \\leq x \\leq ").append(xfinal).append(" ) $</h2> ").append("Resolvendo o balanço de forças na seção").append(":  <canvas id='cortante-viga-").append(i + 1).append("' style='width: 100%'></canvas> $$");

            for (int j = 0; j < forcas.size(); j++) {
                if ((forcas.get(j).tipo == EnumForcaTipo.FORCA_PONTUAL && forcas.get(j).getPosition() < xfinal) ||
                        (forcas.get(j).tipo == EnumForcaTipo.FORCA_DISTRIBUIDA && forcas.get(j).getPosition() < xfinal) ||
                        (forcas.get(j).tipo == EnumForcaTipo.FORCA_REACAO && forcas.get(j).getPositionFinal() < xfinal)) {
                    switch (forcas.get(j).tipo) {
                        case FORCA_PONTUAL:
                            if (omiteMais) {
                                explicacaoExercise.append("F_{").append(j + 1).append("}");
                                omiteMais = false;
                            } else {
                                explicacaoExercise.append("+F_{").append(j + 1).append("}");
                            }
                            break;
                        case FORCA_DISTRIBUIDA:
                            var wi = forcas.get(j).getForcaAplicada();
                            var wf = forcas.get(j).getForcaAplicadaFinal();
                            var xi = forcas.get(j).getPosition();
                            var xf = forcas.get(j).getPositionFinal();
                            if (forcas.get(j).getPositionFinal() < xfinal) {
                                if (omiteMais) {
                                    explicacaoExercise.append("W_{").append(j + 1).append("}");
                                    omiteMais = false;
                                } else {
                                    explicacaoExercise.append("+W_{").append(j + 1).append("}");
                                }
                            } else {
                                if (omiteMais) {
                                    explicacaoExercise.append("W_{").append(j + 1).append("x}");
                                    omiteMais = false;
                                } else {
                                    explicacaoExercise.append("+W_{").append(j + 1).append("x}");
                                }
                                textoTemp.append("Em que" + " $W_{").append(j + 1).append("x}$ ").append("representa a carga distribuida aplicada apenas ").append("até a posição $x$, e não a carga completa, até $x_f$, calculada como:");
                                if (wi == wf) {
                                    var c0 = -wi * xi;
                                    textoTemp.append("$$ \\text{" + "Carga" + " ").append(j + 1).append(", ").append("retangular").append(": } W_{").append(j + 1).append("x} =  w(x-x_i) = ").append(wi).append("x ").append(c0 > 0 ? "+" : "-").append(" ").append(Math.abs(c0)).append("$$");
                                } else if (wi == 0) {
                                    var c2 = wf / 2 / (xf - xi);
                                    var c1 = -wf * xi / (xf - xi);
                                    var c0 = xi * xi * wf / 2 / (xf - xi);
                                    textoTemp.append("$$ \\text{" + "Carga" + " ").append(j + 1).append(", ").append("triangular crescente").append(": } W_{").append(j + 1).append("x} =  \\frac{w_f}{2(x_f-x_i)}(x-x_i)^2 = ").append(c2).append("x^2 ").append(c1 > 0 ? "+" : "-").append(" ").append(Math.abs(c1)).append("x ").append(c0 > 0 ? "+" : "-").append(" ").append(Math.abs(c0)).append("$$");
                                } else if (wf == 0) {
                                    var c2 = -wi / 2 / (xf - xi);
                                    var c1 = wi + wi * xi / (xf - xi);
                                    var c0 = -wi * xi - xi * xi * wi / 2 / (xf - xi);
                                    textoTemp.append("$$ \\text{" + "Carga" + " ").append(j + 1).append(", ").append("triangular decrescente").append(": } W_{").append(j + 1).append("x} =  w_i(x-x_i) - \\frac{w_i}{2(x_f-x_i)}(x-x_i)^2= ").append(c2).append("x^2 ").append(c1 > 0 ? "+" : "-").append(" ").append(Math.abs(c1)).append("x ").append(c0 > 0 ? "+" : "-").append(" ").append(Math.abs(c0)).append("$$");
                                } else { // Trapezoidal
                                    var c2 = (wf - wi) / 2 / (xf - xi);
                                    var c1 = -(wf - wi) * xi / (xf - xi) + wi;
                                    var c0 = xi * xi * (wf - wi) / 2 / (xf - xi) - wi * xi;
                                    textoTemp.append("$$ \\text{" + "Carga" + " ").append(j + 1).append(", trapezoidal: } W_{").append(j + 1).append("x} =  w_i(x-x_i)").append(" + \\frac{w_f-w_i}{2(x_f-x_i)}(x-x_i)^2= ").append(c2).append("x^2 ").append(c1 > 0 ? "+" : "-").append(" ").append(Math.abs(c1)).append("x ").append(c0 > 0 ? "+" : "-").append(" ").append(Math.abs(c0)).append("$$");
                                }

                            }
                            break;
                        case FORCA_REACAO:
                            explicacaoExercise.append("-R_{").append(j + 1 - numCargas).append("}");
                            break;
                    }
                }
            }
            omiteMais = true;

            explicacaoExercise.append(" + V(x) = 0 $$");
            explicacaoExercise.append(textoTemp);
            explicacaoExercise.append("Substituindo os valores numéricos, encontra-se $$" + "V(x) = ");



            var somaTemp = 0;
            var somaTempx = 0;
            var somaTempxx = 0;
            for (Forcas forca : forcas) {
                if ((forca.tipo == EnumForcaTipo.FORCA_PONTUAL && forca.getPosition() < xfinal) ||
                        (forca.tipo == EnumForcaTipo.FORCA_DISTRIBUIDA && forca.getPositionFinal() < xfinal) ||
                        (forca.tipo == EnumForcaTipo.FORCA_REACAO && forca.getPosition() < xfinal)) {
                    switch (forca.tipo) {
                        case FORCA_PONTUAL:
                        case FORCA_REACAO:
                            somaTemp -= forca.getForcaAplicada();
                            break;
                        case FORCA_DISTRIBUIDA:
                            var wi = forca.getForcaAplicada();
                            var wf = forca.getForcaAplicadaFinal();
                            var xi = forca.getPosition();
                            var xf = forca.getPositionFinal();
                            if (forca.getPositionFinal() < xfinal) { // A carga já está toda dentro da seção
                                somaTemp -= forca.getForcaResultanteForcaDistribuidaRetangular();
                            } else { // Ainda falta carga na seção
                                var razao = (wf - wi) / (xf - xi);
                                somaTempxx -= razao / 2;
                                somaTempx -= wi - razao * xi;
                                somaTemp -= xi * (razao / 2 * xi - wi);
                            }
                            break;
                    }
                }
            }

            if (somaTempxx != 0) {
                if (somaTempxx > 0) {
                    if (omiteMais) {
                        explicacaoExercise.append(somaTempxx).append("x^2");
                        omiteMais = false;
                    } else {
                        explicacaoExercise.append("+").append(somaTempxx).append("x^2");
                    }
                } else {
                    explicacaoExercise.append(somaTempxx).append("x^2");
                }
            }
            if (somaTempx != 0) {
                if (somaTempx > 0) {
                    if (omiteMais && somaTempxx == 0) {
                        explicacaoExercise.append(somaTempx).append("x");
                        omiteMais = false;
                    } else {
                        explicacaoExercise.append("+").append(somaTempx).append("x");
                    }
                } else {
                    explicacaoExercise.append(somaTempx).append("x");
                }
            }
            if (somaTemp > 0) {
                if (omiteMais && somaTempx == 0 && somaTempxx == 0) {
                    explicacaoExercise.append(somaTemp);
                    omiteMais = false;
                } else {
                    explicacaoExercise.append("+").append(somaTemp);
                }
            } else if (somaTemp != 0) {
                explicacaoExercise.append(somaTemp);
            }
            if (somaTemp == 0 && somaTempx == 0 && somaTempxx == 0) {
                explicacaoExercise.append("0");
            }
            omiteMais = true;
            explicacaoExercise.append("$$");

        }

        explicacaoExercise.append("<h2> Gráfico </h2> <div id='grafico-cortante' class='grafico'> </div>");

        this.explicacaoExercise = explicacaoExercise.toString();
    }

    public void setResultadoMomentoFletor(List<Forcas> forcas, List<Secoes> secoesCortantes ) {

    }

    public String getExplicacaoExercise() {
        return explicacaoExercise;
    }
}
