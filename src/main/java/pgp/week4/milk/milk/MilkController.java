package pgp.week4.milk.milk;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Controller
@CrossOrigin(origins = "http:/localhost:3000")
@RequestMapping("api/milks")
public class MilkController {

    Logger logger = Logger.getLogger(MilkRepository.class.getName());

    private final MilkService milkService;


    public MilkController(MilkService milkService) {
        this.milkService = milkService;
    }

    @GetMapping
    ResponseEntity<List<MilkResponseDTO>> getAllMilks() {
        List<Milk> body = milkService.getAllMilks();
        logger.info(body.get(0).getName());
        return ResponseEntity.ok(body.stream().map(MilkConverter::toResponseDTO).collect(Collectors.toList()));
    }

    @GetMapping(path="{id}")
    ResponseEntity<MilkResponseDTO> getMilk(@PathVariable String id) {
        Milk byId = milkService.findById(id);
        if (byId == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(MilkConverter.toResponseDTO(byId));
    }

    @PatchMapping(path="{id}")
    ResponseEntity<MilkResponseDTO> patchMilk(@RequestBody CreateMilkDTO milkDTO, @PathVariable String id) {
        Milk milk = MilkConverter.fromDTO(milkDTO);
        milk.setId(id);
        Milk updateMilk = milkService.updateMilkData(milk);
        if (updateMilk == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(MilkConverter.toResponseDTO(updateMilk), HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "{id}")
    ResponseEntity<Milk> deleteMilk(@PathVariable String id) {
        milkService.deleteMilk(id);
        return ResponseEntity.noContent().build();
    }


}
