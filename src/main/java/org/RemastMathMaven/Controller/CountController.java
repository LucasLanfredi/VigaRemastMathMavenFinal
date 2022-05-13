package org.VigaRemastMath.Controller;



import org.VigaRemastMath.entities.Apoios;
import org.VigaRemastMath.entities.ForcasInternas;
import org.VigaRemastMath.entities.Viga;
import org.VigaRemastMath.entities.VigaResponseDTO;
import org.VigaRemastMath.services.Count;

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
