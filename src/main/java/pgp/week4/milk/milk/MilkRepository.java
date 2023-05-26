package pgp.week4.milk.milk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.util.Streamable;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
public class MilkRepository {
    Logger log = Logger.getLogger(MilkRepository.class.getName());

    @Autowired
    JpaMilkRepository repo;


    public MilkRepository(JpaMilkRepository repo) {
        this.repo = repo;
    }

    public MilkRepository() {
    }

    public List<Milk> listMilks() {
        return Streamable.of(repo.findAll()).toList();
    }

    public Milk getById(String id) {
        Optional<Milk> byId = repo.findById(id);
        return byId.orElse(null);
    }

    public Milk saveMilk(Milk newMilk) {
        if(newMilk != null) {
            return repo.save(newMilk);
        }
        return null;
    }

    public void deleteMilk(String id) {

        try{
            if(id != null) {
                repo.deleteById(id);
            }
        } catch (EmptyResultDataAccessException ex) {
            log.info("Attempt to delete Milk %d that doesn't exist".formatted(id));
        }

    }

}
