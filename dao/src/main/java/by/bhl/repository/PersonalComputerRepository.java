package by.bhl.repository;

import by.bhl.model.PersonalComputer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonalComputerRepository extends CrudRepository<PersonalComputer, Long> {

    // Custom findBy
    public List<PersonalComputer> findByNameContains(String value);

}
