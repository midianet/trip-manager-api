package midianet.tripmanager.resource.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FamiliaDTO {

    @NotBlank
    @Size(max = 20)
    private String nome;

}
