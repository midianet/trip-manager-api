package midianet.tripmanager.resource;

import lombok.RequiredArgsConstructor;
import midianet.tripmanager.model.Familia;
import midianet.tripmanager.resource.dto.FamiliaDTO;
import midianet.tripmanager.service.FamiliaService;
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
@RequestMapping(value = "/familias")
public class FamiliaResource {

    private final FamiliaService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody final FamiliaDTO dto, HttpServletResponse response) {
        final var familia = new ModelMapper   ().map(dto,Familia.class) ;
        service.create(familia);
        response.addHeader(HttpHeaders.LOCATION, ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(familia.getId()).toUriString());
    }

    @PutMapping("/{id}")
    public void update(@PathVariable final Integer id , @RequestBody @Valid final FamiliaDTO dto) {
        service.update(id,new ModelMapper().map(dto,Familia.class) );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable final Integer id) {
        service.deleteById(id);
    }

    @GetMapping(path = "/{id}")
    public Familia get(@PathVariable final Integer id) {
        return service.findById(id);
    }

    @GetMapping
    public List<Familia> list() {
        return service.list();
    }

}
