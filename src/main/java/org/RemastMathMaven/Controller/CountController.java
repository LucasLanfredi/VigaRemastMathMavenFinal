package org.RemastMathMaven.Controller;

import org.RemastMathMaven.Validator.ValidateValues;
import org.RemastMathMaven.entities.*;
import org.RemastMathMaven.entitiesDTO.VigaDTO;
import org.RemastMathMaven.services.Count;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/count")
@CrossOrigin("*")
@ResponseBody
public class CountController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private Count count;

    @Autowired
    private ValidateValues validateValues;

    private String resposta;

    @PostMapping("")
    public ResponseEntity<String> count(@RequestBody VigaDTO vigaDTO) {
        Viga viga = new Viga(vigaDTO.getTamanhodaViga(), vigaDTO.getForcasExternas(), vigaDTO.getApoioInicial(), vigaDTO.getApoioFinal());
        validateValues.verficarVigaisValid(viga);
        validateValues.verificarForcasisValid(viga);

        final ExplicacaoExerciseService resposta = new ExplicacaoExerciseService();
        final String respostaValue = count.countForcasDaViga(viga, resposta);
        this.resposta = respostaValue;

        return ResponseEntity.ok().body(HttpStatus.OK.toString());
    }

    @GetMapping("/result")
    public String result() {
        return resposta;
    }
}
