package mas.repository;

import mas.model.Individual;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndividualRepository extends CrudRepository<Individual, Long> {
}
