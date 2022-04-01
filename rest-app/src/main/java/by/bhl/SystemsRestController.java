package by.bhl;

import by.bhl.model.PersonalComputer;
import by.bhl.repository.PersonalComputerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pcs")
public class SystemsRestController {

    @Autowired
    PersonalComputerRepository repository;

    @GetMapping
    public List<PersonalComputer> findAllPersonalComputers() {
        return (List<PersonalComputer>) repository.findAll();
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
        System.out.println("Saving pc: " + personalComputer.toString());
        repository.save(personalComputer);
        //return "Object saved: " + personalComputer.toString();
    }

    // Try VOID in DELETE method (see that no any string returns)
    @DeleteMapping
    public void deletePersonalComputer(@RequestParam Long id) {
        System.out.println("Deleting pc with id " + id);
        repository.deleteById(id);
    }

    @PutMapping
    public void updatePersonalComputer(@RequestBody PersonalComputer personalComputer) {
        System.out.println("Updating pc: " + personalComputer.toString());
        if(repository.findById(personalComputer.getId()).isPresent()) {
            repository.save(personalComputer);
        } else {
            System.out.println("next pc is not found in db for update: " + personalComputer.toString());
        }

        //return "Object updated: " + personalComputer.toString();
    }

}
