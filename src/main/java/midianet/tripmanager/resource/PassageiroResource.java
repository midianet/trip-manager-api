package midianet.tripmanager.resource;

import lombok.RequiredArgsConstructor;
import midianet.tripmanager.model.Passageiro;
import midianet.tripmanager.resource.dto.PassageiroDTO;
import midianet.tripmanager.service.PassageiroService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/passageiros")
public class PassageiroResource {

    private final PassageiroService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody final PassageiroDTO dto, HttpServletResponse response) {
        final var passageiro = new ModelMapper().map(dto,Passageiro.class);
        service.create(passageiro);
        response.addHeader(HttpHeaders.LOCATION, ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(passageiro.getId()).toUriString());
    }

    @PutMapping("/{id}")
    public void update(@PathVariable final Integer id , @RequestBody @Valid final PassageiroDTO dto) {
        service.update(id, new ModelMapper().map(dto,Passageiro.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable final Integer id) {
        service.deleteById(id);
    }

    @GetMapping(path = "/{id}")
    public Passageiro get(@PathVariable final Integer id) {
        return service.findById(id);
    }

    @GetMapping
    public List<Passageiro> list() {
        return service.list();
    }

}
