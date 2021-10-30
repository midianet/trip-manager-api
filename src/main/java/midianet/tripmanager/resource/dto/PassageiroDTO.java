package midianet.tripmanager.resource.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PassageiroDTO {

    @NotBlank
    @Size(max = 80)
    private String nome;

    @Past
    private LocalDate nascimento;

    @Size(max = 10)
    private String rg;

    private Integer familiaID;
}
