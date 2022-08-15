package mas.service;

import lombok.RequiredArgsConstructor;
import mas.model.Author;
import mas.model.Contract;
import mas.repository.AuthorRepository;
import mas.repository.ContractRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PHService implements IPHService{
    private final ContractRepository contractRepository;

    @Override
    public List<Author> findWorkingAuthors(Long id) {
        List<Contract> contracts = contractRepository.findContractsByPublishingHouseId(id);
        List<Author> result = new ArrayList<>();

        for(Contract con : contracts) {
            Date now = new Date(String.valueOf(LocalDateTime.now()));
            if(con.getStartDate().before(now) && con.getEndDate().after(now)){
                result.add(con.getAuthor());
            }
        }

        return result;
    }
}
