package by.bhl;

import by.bhl.model.PersonalComputer;
import by.bhl.repository.PersonalComputerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/pcs")
public class SystemsRestController {

    Logger logger = LoggerFactory.getLogger(SystemsRestController.class);

    @Autowired
    PersonalComputerRepository repository;

    @GetMapping
    public List<PersonalComputer> findAllPersonalComputers() {
        return (List<PersonalComputer>) repository.findAll();
    }

    @GetMapping("/version")
    public String getRestApPVersion() {
        return "rest-app-1.14";
    }

    @GetMapping("{id}")
    public Optional<PersonalComputer> findPersonalComputerById(@PathVariable("id") Long id) {
        return repository.findById(id);
    }

    @GetMapping("/name")
    public List<PersonalComputer> findByNameContains(@RequestParam String value) {
        return repository.findByNameContains(value);
    }

    @PostMapping
    public void addPersonalComputer(@RequestBody PersonalComputer personalComputer) {
        logger.info("Saving pc: " + personalComputer.toString());
        repository.save(personalComputer);
        //return "Object saved: " + personalComputer.toString();
    }

    // Try VOID in DELETE method (see that no any string returns)
    @DeleteMapping
    public void deletePersonalComputer(@RequestParam Long id) {
        logger.info("Deleting pc with id " + id);
        repository.deleteById(id);
    }

    @PutMapping
    public void updatePersonalComputer(@RequestBody PersonalComputer personalComputer) {
        logger.info("Updating pc: " + personalComputer.toString());
        if(repository.findById(personalComputer.getId()).isPresent()) {
            repository.save(personalComputer);
        } else {
            logger.warn("next pc is not found in db for update: " + personalComputer.toString());
        }

        //return "Object updated: " + personalComputer.toString();
    }

}
