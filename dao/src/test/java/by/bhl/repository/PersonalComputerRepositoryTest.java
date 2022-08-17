/*

package by.bhl.repository;

import by.bhl.model.PersonalComputer;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

//@RunWith(SpringRunner.class)
@DataJpaTest
//@AutoConfigureTestDatabase
//@SpringBootTest(classes = {by.bhl.RestApp.class})
public class PersonalComputerRepositoryTest
{
    @Autowired
    private PersonalComputerRepository repository;

    @Test
    public void testPersonalComputerSaveGet()
    {
        PersonalComputer pcToSave = new PersonalComputer("MyPC", 25, (byte) 12);
        Long savedId = repository.save(pcToSave).getId();
        Optional<PersonalComputer> pcRetrieved = repository.findById(savedId);
        assertTrue( pcRetrieved.get().getId().equals(savedId));
    }
}

*/
