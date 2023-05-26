package pgp.week4.milk.milk;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface JpaMilkRepository extends CrudRepository<Milk, String> {
    List<Milk> findByName(String name);
    List<Milk> findByType(String type);

}
