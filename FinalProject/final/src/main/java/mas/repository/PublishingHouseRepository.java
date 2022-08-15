package mas.repository;

import mas.model.Author;
import mas.model.PublishingHouse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublishingHouseRepository extends CrudRepository<PublishingHouse, Long> {
}
