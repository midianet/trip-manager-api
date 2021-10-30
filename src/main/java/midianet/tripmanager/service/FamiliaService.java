package midianet.tripmanager.service;

import lombok.RequiredArgsConstructor;
import midianet.tripmanager.model.Familia;
import midianet.tripmanager.repository.FamiliaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FamiliaService {

    private final FamiliaRepository repository;

    @Transactional
    public void create(@NonNull final Familia familia){
        familia.setId(null);
        repository.save(familia);
    }

    @Transactional
    public void update(@NonNull final Integer id, @NonNull final Familia familia){
        final var persistent = findById(id);
        BeanUtils.copyProperties(familia,persistent,"id");
        repository.save(persistent);
    }

    @Transactional
    public void deleteById(@NonNull final Integer id){
        repository.delete(findById(id));
    }

    public Familia findById(@NonNull final Integer id ){
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Familia " + id));
    }

    public List<Familia> list(){
        return repository.findAll();
    }

}
