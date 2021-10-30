package midianet.tripmanager.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Passageiro {

    @Id
    @GeneratedValue
    private Integer id;

    @NotBlank
    @Size(max = 80)
    @Column(nullable = false, length = 80)
    private String nome;

    @Past
    private LocalDate nascimento;

    @Size(max = 10)
    @Column(length = 10)
    private String rg;

    @ManyToOne
    private Familia familia;

}
