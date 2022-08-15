package mas.project.mp5.repository;

import mas.project.mp5.model.PublishingHouse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PublishingHouseRepository extends CrudRepository<PublishingHouse, Long> {
    public List<PublishingHouse> findPublishingHouseByName(String name);

    public List<PublishingHouse> findPublishingHouseByNameAndEmail(String name, String email);

    @Query("from PublishingHouse as ph left join fetch ph.authors where ph.id = :id")
    public Optional<PublishingHouse> findById (@Param("id") long id);
}
