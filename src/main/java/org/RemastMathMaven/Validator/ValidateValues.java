package org.RemastMathMaven.Validator;

import org.RemastMathMaven.entities.Apoios;
import org.RemastMathMaven.entities.EnumForcaTipo;
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
    private static final String TAMANHO_DA_CARGA_DISTRIBUIDA_INVERTIDO = " - Posição final da carga distribuida e maior que a posição inicial\n";
    private static final String APOIOS_ESTA_COM_O_VALOR_INCORRETO = " Valide a posicao do apoio";
    private static final String QUANTIDADE_DE_APOIOS_INSUFICIENTE = " Apoios Insuficientes";


    @Autowired
    private ForcasService forcasService;

    private int vigaTamanho;

    public ValidateValues(ForcasService forcasService){
        this.forcasService = forcasService;
    }

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
        List<Apoios> listaApoios = viga.getListOfApoios();

        if(listaApoios.size() < 2){
            ResponseEntity.badRequest().body(QUANTIDADE_DE_APOIOS_INSUFICIENTE);
        }

        for (Apoios apoio : listaApoios){
            if (apoio.getPosicao() < 0){
                ResponseEntity.badRequest().body(APOIOS_ESTA_COM_O_VALOR_INCORRETO);
            }
        }
    }
}
