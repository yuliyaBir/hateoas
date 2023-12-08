package be.vdab.hateoas;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class ConsoleService {
    private final ConsoleRepository consoleRepository;

    public ConsoleService(ConsoleRepository consoleRepository) {
        this.consoleRepository = consoleRepository;
    }
    Optional<Console>findById(long id){
        return consoleRepository.findById(id);
    }
    @Transactional
    Console create (NewConsole newConsole){
        var console = new Console(newConsole.naam(), newConsole.gewicht());
        consoleRepository.save(console);
        return console;
    }
}
