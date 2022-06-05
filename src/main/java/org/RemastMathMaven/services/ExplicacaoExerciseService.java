package org.RemastMathMaven.services;

import org.RemastMathMaven.entities.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExplicacaoExerciseService {

    private String explicacaoExercise;

    public ExplicacaoExerciseService() {
        this.explicacaoExercise = "";
    }

    public void setExplicacaoReacoes(List<Forcas> cargasExternas, List<Apoios> apoios) {
        StringBuilder explicacaoExercise = new StringBuilder();

        explicacaoExercise.append("<h2> Cálculo das Reações </h2> Para encontrarmos as reações nos apoios, é necessário verificar " +
                "o equilíbrio de forças na vertical, para garantir que a viga não vai se mover nem " +
                "para cima nem para baixo, e o equilíbrio de momentos, para garantir que a viga " +
                "não irá girar. " +
                "Portanto, fazendo o equilíbrio de forças na vertical, encontra-se: $$ \\sum F_y = 0 \\rightarrow ");

			/*
			Esta função basicamente resolve o
			sistema A*R = B em que A é a matriz dos
			coeficientes, R é matriz de reações e B
			é a matriz dos termos independentes
			*/
        boolean omiteMais = true;

            // CONTA O NÚMERO DE CADA TIPO DE CARREGAMENTO
            var numForcaPontual = 0;
            var numForcaDistribuida = 0;
            int numMomento = 0;
            for (int i = 0; i < cargasExternas.size(); i++) {
                switch (cargasExternas.get(i).tipo) {
                    case FORCA_PONTUAL:
                        numForcaPontual++;
                        if (omiteMais) {
                            explicacaoExercise.append("F_{" + (i + 1) + "}");
                            omiteMais = false;
                        } else {
                            explicacaoExercise.append("+F_{" + (i + 1) + "}");
                        }
                        break;
                    case FORCA_MOMENTO:
                        numMomento++;
                        break;
                    case FORCA_DISTRIBUIDA:
                        numForcaDistribuida++;
                        if (omiteMais) {
                            explicacaoExercise.append("W_{").append(i + 1).append("}");
                            omiteMais = false;
                        } else {
                            explicacaoExercise.append("+W_{").append(i + 1).append("}");
                        }
                        break;
                }
            }


            List<Forcas> forcasDeReacao = new ArrayList<>();
            for(Forcas forca : cargasExternas){
                if(forca.tipo == EnumForcaTipo.FORCA_REACAO){
                    forcasDeReacao.add(forca);
                }
            }

            for (int i = 0; i < forcasDeReacao.size(); i++) {
                explicacaoExercise.append("-R_{").append(i + 1).append("}");
            }
            explicacaoExercise.append(" = 0 $$ Em que: R representa as reações");
            if (numForcaPontual > 0) {
                explicacaoExercise.append("; F representa forças pontuais");
            }
            if (numForcaDistribuida > 0) {
                explicacaoExercise.append("; W representa a força total causada por uma força distribuida. Para calcular a esta força total se calcula a área abaixo da carga distribuida, portanto:");
                for (int i = 0; i < cargasExternas.size(); i++) {
                    if (cargasExternas.get(i).tipo == EnumForcaTipo.FORCA_DISTRIBUIDA) {
                        var xi = cargasExternas.get(i).getPosition();
                        var xf = cargasExternas.get(i).getPositionFinal();
                        var wi = cargasExternas.get(i).getForcaAplicada();
                        var wf = cargasExternas.get(i).getForcaAplicadaFinal();
                        if (wi == wf) { // Retângulo, carga constante
                            explicacaoExercise.append("$$ \\text{" + ("Carga") + " ").append(i + 1).append(", ").append("retangular").append(": }W_{").append(i + 1).append("} = w(x_f-x_i) = ").append(wi).append("[(").append(xf).append(") - (").append(xi).append(")] = ").append(cargasExternas.get(i).getForcaResultante()).append("\\text{N}$$");
                        } else if (wi != 0 && wf != 0) { // Trapézio
                            explicacaoExercise.append("$$ \\text{" + ("Carga") + " ").append(i + 1).append(", trapezoidal: } W_{").append(i + 1).append("} = \\frac{w_i + w_f}{2}(x_f-x_i) = \\frac{(").append(wi).append(") + (").append(wf).append(")}{2}[(").append(xf).append(") - (").append(xi).append(")] = ").append(cargasExternas.get(i).getForcaResultante()).append("\\text{N}$$");
                        } else if (wi == 0) { // Triângulo crescente
                            explicacaoExercise.append("$$ \\text{" + ("Carga") + " ").append(i + 1).append(", ").append("triangular crescente").append(": } W_{").append(i + 1).append("} = \\frac{w_f}{2}(x_f-x_i) = \\frac{").append(wf).append("}{2}[(").append(xf).append(") - (").append(xi).append(")] = ").append(cargasExternas.get(i).getForcaResultante()).append("\\text{N}$$");
                        } else if (wf == 0) { // Triângulo decrescente
                            explicacaoExercise.append("$$ \\text{" + ("Carga") + " ").append(i + 1).append(", ").append("triangular decrescente").append(": } W_{").append(i + 1).append("} = \\frac{w_i}{2}(x_f-x_i) = \\frac{").append(wi).append("}{2}[(").append(xf).append(") - (").append(xi).append(")] = ").append(cargasExternas.get(i).getForcaResultante()).append("\\text{N}$$");
                        }
                        explicacaoExercise.append("Em que" + xi + " e " + xf + " representam a posição inicial e final de aplicação da carga, respectivamente, e" + xi + " e " + xf + ", os valores, em N/m, iniciais e finais da carga distribuida");
                    }
                }
            }

            explicacaoExercise.append(".\nPortanto, substituindo os valores numéricos, encontra-se: $$");
            for (int i = 0; i < forcasDeReacao.size(); i++) {
                if (omiteMais) {
                    explicacaoExercise.append("R_{").append(i + 1).append("}");
                    omiteMais = false;
                } else {
                    explicacaoExercise.append("+R_{").append(i + 1).append("}");
                }
            }
            omiteMais = true;
            explicacaoExercise.append(" = ");
            var somaTemp = 0;
        for (Forcas cargasExterna : cargasExternas) {
            if (cargasExterna.tipo == EnumForcaTipo.FORCA_PONTUAL) {
                somaTemp += cargasExterna.getForcaAplicada();
            }
            if (cargasExterna.tipo == EnumForcaTipo.FORCA_DISTRIBUIDA) {
                somaTemp += cargasExterna.getForcaResultante();
            }
        }
            explicacaoExercise.append(somaTemp).append("\\text{N}$$");
            explicacaoExercise.append("Fazendo o equilíbrio dos momentos no primeiro apoio, encontra-se: $$ \\sum M = 0 \\rightarrow ");
            for (int i = 0; i < forcasDeReacao.size(); i++) {
                if (i != 0) {
                    if (omiteMais) {
                        explicacaoExercise.append("R_{").append(i + 1).append("}(x_{\\text{").append("apoio").append(" } ")
                                .append(i + 1).append("} - x_{\\text{").append("apoio").append(" 1}})");
                        omiteMais = false;
                    } else {
                        explicacaoExercise.append("+R_{").append(i + 1).append("}(x_{\\text{").append("apoio").append(" } ")
                                .append(i + 1).append("} - x_{\\text{").append("apoio").append(" 1}})");
                    }
                }
            }
            omiteMais = true;
            for (int i = 0; i < cargasExternas.size(); i++) {
                if (cargasExternas.get(i).tipo == EnumForcaTipo.FORCA_PONTUAL) {
                    explicacaoExercise.append("-F_{").append(i + 1).append("}(x_{\\text{").append("força").append(" } ").append(i + 1).append("} - x_{\\text{").append("apoio").append(" 1}})");
                }
                if (cargasExternas.get(i).tipo == EnumForcaTipo.FORCA_DISTRIBUIDA) {
                    explicacaoExercise.append("-W_{").append(i + 1).append("}(\\bar{x}_{\\text{").append("força").append(" } ").append(i + 1).append("} - x_{\\text{").append("apoio").append(" 1}})");
                }
                if (cargasExternas.get(i).tipo == EnumForcaTipo.FORCA_MOMENTO) {
                    explicacaoExercise.append("+M_{").append(i + 1).append("}");
                }
            }
            explicacaoExercise.append(" = 0 $$");

            if (numForcaDistribuida > 0) {
                explicacaoExercise.append("Em que $\\bar{x}$ representa a posição de aplicação equivalente da carga distribuida, que é o centroide da geometria, calculado como:");
                for (int i = 0; i < cargasExternas.size(); i++) {
                    if (cargasExternas.get(i).tipo == EnumForcaTipo.FORCA_DISTRIBUIDA) {
                        var xi = cargasExternas.get(i).getPosition();
                        var xf = cargasExternas.get(i).getPositionFinal();
                        var wi = cargasExternas.get(i).getForcaAplicada();
                        var wf = cargasExternas.get(i).getForcaAplicadaFinal();

                        if (wi == wf) { // Retângulo, carga constante
                            explicacaoExercise.append("$$ \\text{" + ("Carga") + " ").append(i + 1).append(", ").append("retangular").append(": } \\bar{x} = \\frac{x_i + x_f}{2} = \\frac{").append(xi).append("+").append(xf).append("}{2} = ").append((xi + xf) / 2).append("\\text{m}$$");
                        } else if (wi != 0 && wf != 0) { // Trapézio
                            explicacaoExercise.append("$$ \\text{" + ("Carga") + " ").append(i + 1).append(", trapezoidal: } \\bar{x} = x_i + (x_f - x_i)\\frac{w_i/3 + 2w_f/3}{w_i + w_f} = ").append(xi).append(" + ( ").append(xf).append(" - ").append(xi).append(" ) \\frac{ (").append(wi).append(")/3 + 2(").append(wf).append(")/3}{ (").append(wi).append(") + (").append(wf).append(") } = ").append(cargasExternas.get(i).getPositionMedia()).append(" \\text{m}$$");
                        } else if (wi == 0) { // Triângulo crescente
                            explicacaoExercise.append("$$ \\text{" + ("Carga") + " ").append(i + 1).append(", ").append("triangular crescente").append(": } \\bar{x} = \\frac{x_i + 2x_f}{3} = \\frac{").append(xi).append("+2(").append(xf).append(")}{3} = ").append(cargasExternas.get(i).getPositionMedia()).append("\\text{m}$$");
                        } else if (wf == 0) { // Triângulo decrescente
                            explicacaoExercise.append("$$ \\text{" + ("Carga") + " ").append(i + 1).append(", ").append("triangular decrescente").append(": } \\bar{x} = \\frac{2x_i + x_f}{3} = \\frac{2(").append(xi).append(")+").append(xf).append("}{3} = ").append(cargasExternas.get(i).getPositionMedia()).append("\\text{m}$$");
                        }
                    }
                }
            }

            explicacaoExercise.append("Substituindo os valores numéricos, encontra-se $$");
            for (int i = 0; i < forcasDeReacao.size(); i++) {
                if (i != 0) {
                    explicacaoExercise.append("R_{").append(i + 1).append("}(").append(apoios.get(i).getPosicao()).append(" - ").append(apoios.get(0).getPosicao()).append(")");
                }
            }
            explicacaoExercise.append(" = ");
        for (Forcas cargasExterna : cargasExternas) {
            if (cargasExterna.tipo == EnumForcaTipo.FORCA_PONTUAL) {
                explicacaoExercise.append("+(").append(cargasExterna.getForcaAplicada()).append(")(").append(cargasExterna.getPosition()).append(" - ").append(apoios.get(0).getPosicao()).append(")");
            }
            if (cargasExterna.tipo == EnumForcaTipo.FORCA_DISTRIBUIDA) {
                explicacaoExercise.append("+(").append(cargasExterna.getForcaResultante()).append(")(").append(cargasExterna.getPositionMedia()).append(" - ").append(apoios.get(0).getPosicao()).append(")");
            }
            if (cargasExterna.tipo == EnumForcaTipo.FORCA_MOMENTO) {
                explicacaoExercise.append("-(").append(cargasExterna.getForcaAplicada()).append(")");
            }
        }
            explicacaoExercise.append(" \\rightarrow ");

            somaTemp = 0;
        for (Forcas cargasExterna : cargasExternas) {
            if (cargasExterna.tipo == EnumForcaTipo.FORCA_PONTUAL) {
                somaTemp += cargasExterna.getForcaAplicada() * (cargasExterna.getPosition() - apoios.get(0).getPosicao());
            }
            if (cargasExterna.tipo == EnumForcaTipo.FORCA_DISTRIBUIDA) {
                somaTemp += cargasExterna.getForcaResultante() * (cargasExterna.getPositionMedia() - apoios.get(0).getPosicao());
            }
            if (cargasExterna.tipo == EnumForcaTipo.FORCA_MOMENTO) {
                somaTemp -= cargasExterna.getForcaAplicada();
            }
        }

            for (int i = 0; i < forcasDeReacao.size(); i++) {
                if (i != 0) {
                    switch (apoios.get(i).getPosicao() - apoios.get(0).getPosicao()) {
                        case 1:
                            explicacaoExercise.append("R_{").append(i + 1).append("}");
                            break;
                        case -1:
                            explicacaoExercise.append("-R_{").append(i + 1).append("}");
                            break;
                        default:
                            explicacaoExercise.append(apoios.get(i).getPosicao() - apoios.get(0).getPosicao()).append("R_{").append(i + 1).append("}");
                            break;
                    }
                }
            }
            explicacaoExercise.append(" = ");
            explicacaoExercise.append(somaTemp).append("\\text{N}$$ Das duas equações, encontra-se o seguinte sistema: $$");

            for (int i = 0; i < forcasDeReacao.size(); i++) {
                if (omiteMais) {
                    explicacaoExercise.append("R_{").append(i + 1).append("}");
                    omiteMais = false;
                } else {
                    explicacaoExercise.append("+R_{").append(i + 1).append("}");
                }
            }
            omiteMais = true;
            explicacaoExercise.append(" = ");
            somaTemp = 0;
        for (Forcas cargasExterna : cargasExternas) {
            if (cargasExterna.tipo == EnumForcaTipo.FORCA_PONTUAL) {
                somaTemp += cargasExterna.getForcaAplicada();
            }
            if (cargasExterna.tipo == EnumForcaTipo.FORCA_DISTRIBUIDA) {
                somaTemp += cargasExterna.getForcaResultante();
            }
        }
            explicacaoExercise.append(somaTemp).append("\\text{N}$$ $$ ");
            somaTemp = 0;
        for (Forcas cargasExterna : cargasExternas) {
            if (cargasExterna.tipo == EnumForcaTipo.FORCA_PONTUAL) {
                somaTemp += cargasExterna.getForcaAplicada() * (cargasExterna.getPosition() - apoios.get(0).getPosicao());
            }
            if (cargasExterna.tipo == EnumForcaTipo.FORCA_DISTRIBUIDA) {
                somaTemp += cargasExterna.getForcaResultante() * (cargasExterna.getPositionMedia() - apoios.get(0).getPosicao());
            }
            if (cargasExterna.tipo == EnumForcaTipo.FORCA_MOMENTO) {
                somaTemp -= cargasExterna.getForcaAplicada();
            }
        }

            for (int i = 0; i < forcasDeReacao.size(); i++) {
                if (i != 0) {
                    switch (apoios.get(i).getPosicao() - apoios.get(0).getPosicao()) {
                        case 1:
                            explicacaoExercise.append("R_{").append(i + 1).append("}");
                            break;
                        case -1:
                            explicacaoExercise.append("-R_{").append(i + 1).append("}");
                            break;
                        default:
                            explicacaoExercise.append(apoios.get(i).getPosicao() - apoios.get(0).getPosicao()).append("R_{").append(i + 1).append("}");
                            break;
                    }
                }
            }
            explicacaoExercise.append(" = ");
            explicacaoExercise.append(somaTemp).append("\\text{N}$$ Resolvendo o sistema, encontra-se:");
            for (int i = 0; i < forcasDeReacao.size(); i++) {
                explicacaoExercise.append("$$ R_{").append(i + 1).append("} = ").append(forcasDeReacao.get(i)
                        .getForcaAplicada()).append("\\text{N}$$");
            }
            this.explicacaoExercise += explicacaoExercise.toString();
    }

    public void setResultadoEsforcoCortante(List<Forcas> forcas, List<Cortante> cortanteCortantes) {
        var omiteMais = true;
        StringBuilder explicacaoExercise = new StringBuilder();
        explicacaoExercise.append("<h1> Cálculo do Esforço Cortante </h1>");
        explicacaoExercise.append("Para encontrar a equação do esforço cortante, é necessário fazer o balanço de forças verticais " + "em cada seção (que vão de $0$ até $x$ metros), ou seja: $$ \\sum F_y + V(x) = 0 $$ Em que $V(x)$ é o valor do" + " esforço cortante na posição $x$.");

        int n = 0;
        int numCargas;
        while (true) {
            if (forcas.get(n).tipo == EnumForcaTipo.FORCA_REACAO) {
                numCargas = n;
                break;
            }
            n++;
        }

        for (int i = 0; i < cortanteCortantes.size(); i++) {
            var xinicial = cortanteCortantes.get(i).getPositionX()[0];
            var xfinal = cortanteCortantes.get(i).getPositionX()[cortanteCortantes.get(i).getPositionX().length - 1];
            StringBuilder textoTemp = new StringBuilder();
            explicacaoExercise.append("<h2> " + "Seção" + " ").append(i + 1).append(" $ ( ").append(xinicial).append(" \\leq x \\leq ").append(xfinal).append(" ) $</h2> ").append("Resolvendo o balanço de forças na seção:");
            //TODO
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
                                somaTemp -= forca.getForcaResultante();
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

        explicacaoExercise.append("<h2>  Gráfico  </h2> <div id='grafico-cortante' class='grafico'> </div>");

        this.explicacaoExercise += explicacaoExercise.toString();
    }

    public void setResultadoMomentoFletor(List<Forcas> forcas, List<Fletor> listOfFletor) {
        var omiteMais = true;

        StringBuilder explicacaoExercise = new StringBuilder();
        explicacaoExercise.append("<h2> Cálculo do Momento Fletor </h2>");
        explicacaoExercise.append("Para encontrar a equação do momento fletor, é necessário fazer o balanço do momento em cada seção"
                + " (que vão de $0$ até $x$ metros), ou seja: $$ \\sum F_y(x-x_{carga}) + \\sum M + M(x) = 0 $$ Em que $M(x)$ "
                + "é o valor do momento fletor na posição $x$.");

        int n = 0;
        int numCargas;
        while (true) {
            if (forcas.get(n).tipo == EnumForcaTipo.FORCA_REACAO) {
                numCargas = n;
                break;
            }
            n++;
        }

        for (int i = 0; i < listOfFletor.size(); i++) {
            var xinicial = listOfFletor.get(i).getPositionX()[0];
            var xfinal = listOfFletor.get(i).getPositionX()[listOfFletor.get(i).getPositionX().length - 1];
            var textoTemp = "";
            explicacaoExercise.append("<h2> " + "Seção" + " ").append(i + 1).append(" $ ( ").append(xinicial).append(" \\leq x \\leq ")
                    .append(xfinal).append(" ) $</h2> ").append("Resolvendo o balanço de momentos na seção:");

            for (int j = 0; j < forcas.size(); j++) {
                if ((forcas.get(j).tipo == EnumForcaTipo.FORCA_PONTUAL && forcas.get(j).getPosition() < xfinal) ||
                        (forcas.get(j).tipo == EnumForcaTipo.FORCA_DISTRIBUIDA && forcas.get(j).getPosition() < xfinal) ||
                        (forcas.get(j).tipo == EnumForcaTipo.FORCA_REACAO && forcas.get(j).getPosition() < xfinal) ||
                        (forcas.get(j).tipo == EnumForcaTipo.FORCA_MOMENTO && forcas.get(j).getPosition() < xfinal)) {
                    switch (forcas.get(j).tipo) {
                        case FORCA_PONTUAL:
                            if (omiteMais) {
                                explicacaoExercise.append("F_{").append(j + 1).append("}(x - x_{\\text{").append("força")
                                        .append(" }").append(j + 1).append("})");
                                omiteMais = false;
                            } else {
                                explicacaoExercise.append("+F_{").append(j + 1).append("}(x - x_{\\text{").append("força")
                                        .append(" }").append(j + 1).append("})");
                            }
                            break;
                        case FORCA_DISTRIBUIDA:
                            var xi = forcas.get(j).getPosition();
                            var xf = forcas.get(j).getPositionFinal();
                            var wi = forcas.get(j).getForcaAplicada();
                            var wf = forcas.get(j).getForcaAplicadaFinal();
                            if (forcas.get(i).getPositionFinal() < xfinal) {
                                if (omiteMais) {
                                    explicacaoExercise.append("W_{").append(j + 1).append("}(x - \\bar{x}_{\\text{").append("força")
                                            .append(" }").append(j + 1).append("})");
                                    omiteMais = false;
                                } else {
                                    explicacaoExercise.append("+W_{").append(j + 1).append("}(x - \\bar{x}_{\\text{").append("força")
                                            .append(" }").append(j + 1).append("})");
                                }

                            } else {
                                if (omiteMais) {
                                    explicacaoExercise.append("W_{").append(j + 1).append("x}(x - \\bar{x}_{\\text{").append("força")
                                            .append(" }").append(j + 1).append("})");
                                    omiteMais = false;
                                } else {
                                    explicacaoExercise.append("+W_{").append(j + 1).append("x}(x - \\bar{x}_{\\text{")
                                            .append("força").append(" }").append(j + 1).append("})");
                                }
                                textoTemp += "Em que" + " $W_{" + (j + 1) + "x}(x-\\bar{x})$ " + "representa o moment equivalente à carga distribuida aplicada apenas até a posição $x$, e não a carga completa, até $x_f$: ";
                                if (wi == wf) { // Retangular
                                    var c2 = wi / 2;
                                    var c1 = -wi * xi;
                                    var c0 = xi * xi * wi / 2;
                                    textoTemp += "$$ \\text{" + "Carga" + " " + (j + 1) + ", " + "retangular" + ": } W_{" + (j + 1) + "x}(x - \\bar{x}_{\\text{" + "força" + " }" + (j + 1) + "}) =  \\frac{w}{2}(x-x_i)^2 =  " + c2 + "x^2 " + (c1 > 0 ? "+" : "-") + " " + Math.abs(c1) + "x " + (c0 > 0 ? "+" : "-") + " " + Math.abs(c0) + "$$";

                                } else if (wi == 0) { // Triangulo Crescente
                                    var c3 = wf / 6 / (xf - xi);
                                    var c2 = -3 * xi * wf / 6 / (xf - xi);
                                    var c1 = 3 * xi * xi * wf / 6 / (xf - xi);
                                    var c0 = -xi * xi * xi * wf / 6 / (xf - xi);
                                    textoTemp += "$$ \\text{" + "Carga" + " " + (j + 1) + ", " + "triangular crescente" + ": } W_{" + (j + 1) + "x}(x - \\bar{x}_{\\text{" + "força" + " }" + (j + 1) + "}) = \\frac{w_f}{6(x_f-x_i)}(x-x_i)^3 = " + c3 + "x^3 " + (c2 > 0 ? "+" : "-") + "  " + Math.abs(c2) + "x^2 " + (c1 > 0 ? "+" : "-") + " " + (Math.abs(c1)) + "x " + (c0 > 0 ? "+" : "-") + " " + Math.abs(c0) + "$$";

                                } else if (wf == 0) { // Triangulo Decrescente
                                    var c3 = -wi / 6 / (xf - xi);
                                    var c2 = wi * xi / 2 / (xf - xi) + wi / 2;
                                    var c1 = -wi * xi * (1 + xi / 2 / (xf - xi));
                                    var c0 = wi * xi * xi / 2 + wi * xi * xi * xi / 6 / (xf - xi);
                                    textoTemp += "$$ \\text{" + "Carga" + " " + (j + 1) + ", " + "triangular decrescente" + ": } W_{" + (j + 1) + "x}(x - \\bar{x}_{\\text{" + "força" + " }" + (j + 1) + "}) =  \\frac{w_i}{2}(x-x_i)^2 - \\frac{w_i}{6(x_f-x_i)} (x-x_i)^3 = " + c3 + "x^3 " + (c2 > 0 ? "+" : "-") + " " + Math.abs(c2) + "x^2 " + (c1 > 0 ? "+" : "-") + " " + Math.abs(c1) + "x " + (c0 > 0 ? "+" : "-") + " " + Math.abs(c0) + "$$";

                                } else { // Trapezoidal
                                    var c3 = (wf - wi) / 6 / (xf - xi);
                                    var c2 = -3 * xi * (wf - wi) / 6 / (xf - xi) + wi / 2;
                                    var c1 = 3 * xi * xi * (wf - wi) / 6 / (xf - xi) - wi * xi;
                                    var c0 = -xi * xi * xi * (wf - wi) / 6 / (xf - xi) + xi * xi * wi / 2;
                                    textoTemp += "$$ \\text{" + "Carga" + " " + (j + 1) + ", trapezoidal: } W_{" + (j + 1) + "x}(x - \\bar{x}_{\\text{" + "força" + " }" + (j + 1) + "}) =  \\frac{w_i}{2}(x-x_i)^2 + \\frac{w_f-w_i}{6(x_f-x_i)} (x-x_i)^3 = " + c3 + "x^3 " + (c2 > 0 ? "+" : "-") + "  " + Math.abs(c2) + "x^2 " + (c1 > 0 ? "+" : "-") + " " + Math.abs(c1) + "x " + (c0 > 0 ? "+" : "-") + " " + Math.abs(c0) + "$$";

                                }
                            }
                            break;
                        case FORCA_REACAO:
                            explicacaoExercise.append("-R_{").append(j + 1 - numCargas).append("}(x - x_{\\text{").append("apoio").append(" }").append(j + 1 - numCargas).append("})");
                            break;
                        case FORCA_MOMENTO:
                            if (omiteMais) {
                                explicacaoExercise.append("M_{").append(j + 1).append("}");
                                omiteMais = false;
                            } else {
                                explicacaoExercise.append("+M_{").append(j + 1).append("}");
                            }
                            break;
                    }
                    omiteMais = true;

                    explicacaoExercise.append(" + M(x) = 0 $$");
                    explicacaoExercise.append(textoTemp);
                    explicacaoExercise.append("Substituindo os valores numéricos, encontra-se $$" + " M(x) = ");
                }

            }

                var somaTemp = 0;
                var somaTempx = 0;
                var somaTempxx = 0;
                var somaTempxxx = 0;
                for (int j = 0; j < forcas.size(); j++) {
                    if ((forcas.get(j).tipo == EnumForcaTipo.FORCA_PONTUAL && forcas.get(j).getPosition() < xfinal) ||
                            (forcas.get(j).tipo == EnumForcaTipo.FORCA_DISTRIBUIDA && forcas.get(j).getPosition() < xfinal) ||
                            (forcas.get(j).tipo == EnumForcaTipo.FORCA_REACAO && forcas.get(j).getPosition() < xfinal) ||
                            (forcas.get(j).tipo == EnumForcaTipo.FORCA_MOMENTO && forcas.get(j).getPosition() < xfinal)) {
                        switch (forcas.get(j).tipo) {
                            case FORCA_PONTUAL:
                            case FORCA_REACAO:
                                somaTemp += forcas.get(j).getForcaAplicada() * forcas.get(j).getPosition();
                                somaTempx -= forcas.get(j).getForcaAplicada();
                                break;
                            case FORCA_DISTRIBUIDA:
                                var wi = forcas.get(j).getForcaAplicada();
                                var wf = forcas.get(j).getForcaAplicadaFinal();
                                var xi = forcas.get(j).getPosition();
                                var xf = forcas.get(j).getPositionFinal();
                                if (forcas.get(j).getPositionFinal() < xfinal) { // A carga já está toda dentro da seção
                                    somaTempx -= forcas.get(j).getForcaResultante();
                                    somaTemp -= -forcas.get(j).getForcaResultante() * forcas.get(j).getPositionMedia();
                                } else { // Ainda falta carga na seção
                                    var r1 = (wf - wi) / (6 * (xf - xi));
                                    var r2 = wi / 2;
                                    somaTempxxx -= r1;
                                    somaTempxx -= -3 * r1 * xi + r2;
                                    somaTempx -= 3 * r1 * xi * xi - 2 * r2 * xi;
                                    somaTemp -= -r1 * xi * xi * xi + r2 * xi * xi;
                                }
                                break;
                            case FORCA_MOMENTO:
                                somaTemp -= forcas.get(j).getForcaAplicada();
                                break;
                        }
                    }

                    if (somaTempxxx != 0) {
                        explicacaoExercise.append(somaTempxxx).append("x^3");
                    }
                    if (somaTempxx != 0) {
                        if (somaTempxx > 0) {
                            if (omiteMais && somaTempxxx == 0) {
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
                            if (omiteMais && somaTempxx == 0 && somaTempxxx == 0) {
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
                        if (omiteMais && somaTempx == 0 && somaTempxx == 0 && somaTempxxx == 0) {
                            explicacaoExercise.append(somaTemp);
                            omiteMais = false;
                        } else {
                            explicacaoExercise.append("+").append(somaTemp);
                        }
                    } else if (somaTempx == 0 && somaTempxx == 0 && somaTempxxx == 0) {
                        explicacaoExercise.append(somaTemp);
                    }
                    if (somaTemp < 0) {
                        explicacaoExercise.append(somaTemp);
                    }

                    omiteMais = true;

                    explicacaoExercise.append("$$");
                }
        }
        explicacaoExercise.append("<h2> " + ("Gráfico") + " </h2> <div id='grafico-fletor' class='grafico'> </div>");
        this.explicacaoExercise += explicacaoExercise.toString();
    }
    public String getExplicacaoExercise() {
        return explicacaoExercise;
    }

}
