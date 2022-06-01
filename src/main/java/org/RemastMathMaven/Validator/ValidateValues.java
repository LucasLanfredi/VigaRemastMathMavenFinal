package org.RemastMathMaven.Validator;

import org.RemastMathMaven.entities.Forcas;
import org.RemastMathMaven.entities.Viga;
import org.RemastMathMaven.services.ForcasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValidateValues {

    private static final String FORCAS_IS_EMPTY = "Você não adicionou forças";
    private static final String POSICAO_FORCAS_INVALIDAS = "Você colocou valores invalidos na posição das forças";
    private static final String TAMANHO_DA_VIGA_INVALIDO_ZERO = "Tamanho da viga inválido, pois é 0";
    private static final String TAMANHO_DA_VIGA_INVALIDO_NEGATIVO = "Tamanho da viga inválido, pois está negativo";

    @Autowired
    private ForcasService forcasService;

    private int vigaTamanho;

    public void verficarVigaisValid(Viga viga) {

        this.vigaTamanho = viga.getTamanhodaViga();

        if (this.vigaTamanho == 0) {
            ResponseEntity.badRequest().body(TAMANHO_DA_VIGA_INVALIDO_ZERO);
        }
        if (this.vigaTamanho < 0) {
            ResponseEntity.badRequest().body(TAMANHO_DA_VIGA_INVALIDO_NEGATIVO);
        }

    }

    public void verificarForcasisValid(Viga viga) {
        List<Forcas> listaForcas = forcasService.getAllListaForcasWithReacao(viga, List.of(viga.getApoioFinal(),viga.getApoioInicial()));
        if (listaForcas.isEmpty()) {
            ResponseEntity.badRequest().body(FORCAS_IS_EMPTY);
        }

        for (Forcas forca : listaForcas) {
            if (forca.getPosition() <= 0 & vigaTamanho < forca.getPosition()) {
                ResponseEntity.badRequest().body(POSICAO_FORCAS_INVALIDAS);
            }
        }
    }
}
