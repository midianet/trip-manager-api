package midianet.tripmanager.model;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class Pagamento {

    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    @Column(nullable = false)
    private LocalDate data;

    @NotNull
    @Column(nullable = false)
    private Double valor;

    @NotNull
    @ManyToOne
    @JoinColumn(nullable = false)
    private Passageiro passageiro;

}
