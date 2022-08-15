package mas.repository;

import mas.model.Book;
import mas.model.CostUtil;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CostUtilRepository extends CrudRepository<CostUtil, Long> {

}
