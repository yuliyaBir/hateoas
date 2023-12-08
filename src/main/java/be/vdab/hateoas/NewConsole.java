package be.vdab.hateoas;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;

record NewConsole(@NotBlank String naam, @NotNull @Positive BigDecimal gewicht){
}
