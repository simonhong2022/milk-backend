package pgp.week4.milk.milk;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaMilkRepository extends JpaRepository<Milk, String> {
    List<Milk> findByName(String name);
    List<Milk> findByType(String type);

}
