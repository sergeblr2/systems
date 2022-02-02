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

    @GetMapping(value = "/systems")
    public final String listAllPcs(Model model) {
        System.out.println("Getting all pcs");
        model.addAttribute("personalComputers",
                restTemplate.getForObject(REST_URL + "/pcs", List.class));
        return "systems";
    }

    // TODO: Add POST, PUT, DELETE and GET by id, contains, etc mappings

}
