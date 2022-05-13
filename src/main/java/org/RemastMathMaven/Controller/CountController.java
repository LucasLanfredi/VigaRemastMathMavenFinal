package org.RemastMathMaven.Controller;

import org.RemastMathMaven.entities.Apoios;
import org.RemastMathMaven.entities.ForcasInternas;
import org.RemastMathMaven.entities.Viga;
import org.RemastMathMaven.entities.VigaResponseDTO;
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
    private VigaResponseDTO vigaResponseDTO;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private Count count;

    @GetMapping("/com/vigaresmath/entities")
    public ResponseEntity<VigaResponseDTO> save(@RequestBody VigaResponseDTO VigaResponseDTO) {
        Viga viga = modelMapper.map(VigaResponseDTO, Viga.class);
        VigaResponseDTO dto = count.countForcasDaViga(viga);

        return ResponseEntity.ok().body(dto);
    }
}
