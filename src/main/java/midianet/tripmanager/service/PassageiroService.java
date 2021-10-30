package midianet.tripmanager.service;

import lombok.RequiredArgsConstructor;
import midianet.tripmanager.model.Passageiro;
import midianet.tripmanager.repository.PassageiroRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PassageiroService {

    private final PassageiroRepository repository;

    @Transactional
    public void create(@NonNull final Passageiro passageiro){
        passageiro.setId(null);
        repository.save(passageiro);
    }

    @Transactional
    public void update(@NonNull final Integer id, @NonNull final Passageiro passageiro){
        final var persistent = findById(id);
        BeanUtils.copyProperties(passageiro,persistent,"id");
        repository.save(persistent);
    }

    @Transactional
    public void deleteById(@NonNull final Integer id){
        repository.delete(findById(id));
    }

    public Passageiro findById(@NonNull final Integer id ){
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Passageiro " + id));
    }

    public List<Passageiro> list(){
        return repository.findAll();
    }

}
