package mas.repository;

import mas.model.Contract;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends CrudRepository<Contract, Long> {
    public List<Contract> findContractsByPublishingHouseId(Long id);
}
