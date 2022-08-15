package mas.service;

import mas.model.Author;

import java.util.List;

public interface IPHService {
    public abstract List<Author> findWorkingAuthors(Long id);
}
