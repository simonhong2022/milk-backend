package pgp.week4.milk.milk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
@Repository
public class MilkRepository {
    Logger log = Logger.getLogger(MilkRepository.class.getName());

    @Autowired
    JpaMilkRepository jpaMilkRepository;

    public MilkRepository(JpaMilkRepository repo) {
        this.jpaMilkRepository = repo;
    }

    public MilkRepository() {
    }

    public List<Milk> listMilks() {
        return Streamable.of(jpaMilkRepository.findAll()).toList();
    }

    public Milk getById(String id) {
        Optional<Milk> byId = jpaMilkRepository.findById(id);
        return byId.orElse(null);
    }

    public Milk saveMilk(Milk newMilk) {
        if(newMilk != null) {
            return jpaMilkRepository.save(newMilk);
        }
        return null;
    }

    public void saveMilks(List<Milk> milks) {
        jpaMilkRepository.saveAll(milks);
    }

    public void deleteMilk(String id) {

        try{
            if(id != null) {
                jpaMilkRepository.deleteById(id);
            }
        } catch (EmptyResultDataAccessException ex) {
            log.info("Attempt to delete Milk %s that doesn't exist".formatted(id));
        }

    }

}
