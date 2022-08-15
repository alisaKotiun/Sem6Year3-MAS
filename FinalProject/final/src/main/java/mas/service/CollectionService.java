package mas.service;

import lombok.RequiredArgsConstructor;
import mas.model.Book;
import mas.model.Collection;
import mas.model.CollectionStatus;
import mas.repository.CollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CollectionService implements ICollectionService{

    private final CollectionRepository repository;

    @Override
    public List<Collection> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Book> findAllBooks(Long id) {
        return repository.findBooksById(id);
    }

    @Override
    public void changeStatus(Long id, CollectionStatus status) {
        Optional<Collection> optional = repository.findCollectionById(id);

        if(optional.isPresent()) {
            optional.get().setStatus(status);
        } else{
            throw new IllegalArgumentException("Collection is not found");
        }
    }

    @Override
    public Double calculatePrice(Long id) {
        Optional<Collection> optional = repository.findCollectionById(id);
        Double result = 0.;

        if(optional.isPresent()) {
             for(Book book : findAllBooks(optional.get().getId())){
                 result += book.getCost().getFinalCost();
             }
        }
        return result;
    }
}
