package org.RemastMathMaven.Controller;

import org.RemastMathMaven.Validator.ValidateValues;
import org.RemastMathMaven.entities.*;
import org.RemastMathMaven.services.Count;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("count")
@ResponseBody
public class CountController {

    @Autowired
    private Viga viga;

    @Autowired
    private int tamanhoViga;

    @Autowired
    private List<ForcasInternas> forcasInternasList;

    @Autowired
    private List<Integer> momento;

    @Autowired
    private List<Apoios> apoiosViga;

    @Autowired
    private VigaDTO vigaResponseDTO;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private Count count;

    @Autowired
    private ValidateValues validateValues;

    @Autowired
    private Forcas forcas;

    @GetMapping("/result")
    public ResponseEntity<String> result(@RequestBody VigaDTO VigaDTO) {
        Viga viga = modelMapper.map(VigaDTO, Viga.class);
        validateValues.verficarVigaisValid(viga);
        validateValues.verificarForcasisValid(viga);

        ExplicacaoExercise resposta = new ExplicacaoExercise();
        String respostaValue = count.countForcasDaViga(viga, resposta);

        return ResponseEntity.ok().body(respostaValue);
    }
}
