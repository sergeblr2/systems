package by.bhl;

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

/*    @PostMapping
    public String addPersonalComputer(@RequestBody PersonalComputer personalComputer) {
        // save
        System.out.println("PC saved: " + personalComputer.toString());
        return "Object saved: " + personalComputer.toString();
    }

    @DeleteMapping
    public void deletePersonalComputer(@RequestParam Long id) {
        // delete id
        System.out.println("PC with id " + id + " deleted");

    }

    @PutMapping
    public String updatePersonalComputer(@RequestBody PersonalComputer personalComputer) {
        System.out.println("PC updated: " + personalComputer.toString());
        return "Object updated: " + personalComputer.toString();
    }*/

}
