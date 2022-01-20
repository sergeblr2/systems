package by.bhl;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonalComputerRepository extends CrudRepository<PersonalComputer, Long> {
    //
    public List<PersonalComputer> findByNameContains(String value);

}
