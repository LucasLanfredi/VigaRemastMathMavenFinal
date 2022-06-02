package org.RemastMathMaven.Controller;

import org.RemastMathMaven.Validator.ValidateValues;
import org.RemastMathMaven.entities.*;
import org.RemastMathMaven.entitiesDTO.VigaDTO;
import org.RemastMathMaven.services.Count;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/count")
@ResponseBody
public class CountController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private Count count;

    @Autowired
    private ValidateValues validateValues;

    @GetMapping("/result")
    public ResponseEntity<String> result(@RequestBody VigaDTO VigaDTO) {
        Viga viga = modelMapper.map(VigaDTO, Viga.class);
        validateValues.verficarVigaisValid(viga);
        validateValues.verificarForcasisValid(viga);

        final ExplicacaoExercise resposta = new ExplicacaoExercise();
        final String respostaValue = count.countForcasDaViga(viga, resposta);

        return ResponseEntity.ok().body(respostaValue);
    }
}
