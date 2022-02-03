package by.bhl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class SystemsWebController {

    private RestTemplate restTemplate = new RestTemplate();
    private static String REST_URL = "http://localhost:8080";

    // Controller for loading page with ALL SYSTEMS
    @GetMapping(value = "/systems")
    public final String listAllPcs(Model model) {
        System.out.println("Getting all pcs");
        model.addAttribute("personalComputers",
                restTemplate.getForObject(REST_URL + "/pcs", List.class));
        return "systems";
    }

    // Controller for loading page to ADD SYSTEM
    @GetMapping(value = "/system")
    public final String gotoAddPCPage(Model model) {
        System.out.println("Loading PC adding page");
        PersonalComputer personalComputer = new PersonalComputer();
        model.addAttribute("personalComputer", personalComputer);
        return "system";
    }

    // Controller for calling REST when "CREATE PC" button clicked
    @PostMapping(value = "/system")
    public final String addSystem(PersonalComputer personalComputer) {
        System.out.println("Adding pc: " + personalComputer.toString());
        restTemplate.postForEntity(REST_URL + "/pcs", personalComputer, PersonalComputer.class);
        return "redirect:/systems";
    }

    // TODO: Add PUT, DELETE and GET by id, contains, etc mappings

}
