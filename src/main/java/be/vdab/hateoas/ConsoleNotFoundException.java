package be.vdab.hateoas;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ConsoleNotFoundException extends RuntimeException{
    public ConsoleNotFoundException() {
        super("Console niet gevonden");
    }
}
