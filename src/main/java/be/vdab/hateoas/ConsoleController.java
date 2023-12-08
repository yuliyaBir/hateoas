package be.vdab.hateoas;

import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.hateoas.server.TypedEntityLinks;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("consoles")
@ExposesResourceFor(Console.class)
public class ConsoleController {
    private final ConsoleService consoleService;
    private final TypedEntityLinks.ExtendedTypedEntityLinks<Console> links;
    ConsoleController(ConsoleService consoleService, EntityLinks links) {
        this.consoleService = consoleService;
        this.links = links.forType(Console.class, Console::getId);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    HttpHeaders create(@RequestBody @Valid NewConsole newConsole){
        var console = consoleService.create(newConsole);
        var headers = new HttpHeaders();
        headers.setLocation(links.linkToItemResource(console).toUri());
        return headers;
    }
    @GetMapping("{id}")
    EntityModel<Console> findById(@PathVariable long id){
        return consoleService.findById(id)
                .map(console -> EntityModel.of(console)
                        .add(links.linkToItemResource(console))
                        .add(links.linkForItemResource(console)
                                .slash("weightInPounds").withRel("weightInPounds")))
                .orElseThrow(ConsoleNotFoundException::new);
    }
    private static final BigDecimal KILO_IN_A_POUND = BigDecimal.valueOf(2.2);
    @GetMapping("{id}/weightInPounds")
    BigDecimal findWeightInPounds(@PathVariable long id){
        return consoleService.findById(id)
                .map(console -> console.getGewicht().multiply(KILO_IN_A_POUND))
                .orElseThrow(ConsoleNotFoundException::new);
    }
}
