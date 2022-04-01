package by.bhl.web;

import by.bhl.model.PersonalComputer;
import by.bhl.web.ExcelHelper.ExcelHelper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class SystemsWebController {

    private RestTemplate restTemplate = new RestTemplate();
    private static String REST_URL = "http://localhost:8091";

    // Controller for loading page with ALL SYSTEMS
    @GetMapping(value = "/systems")
    public final String listAllPcs(Model model) {
        System.out.println("Getting all pcs");
        model.addAttribute("personalComputers",
                restTemplate.getForObject(REST_URL + "/pcs", List.class));
        //model.addAttribute("test2", "j");
        return "systems";
    }

    // Controller for loading page to ADD PC
    @GetMapping(value = "/system")
    public final String gotoAddPCPage(Model model) {
        System.out.println("Loading PC adding page");
        PersonalComputer personalComputer = new PersonalComputer();
        model.addAttribute("personalComputer", personalComputer);
        return "system";
    }

    // Controller for calling REST when "ADD PC" button clicked
    @PostMapping(value = "/system")
    public final String addSystem(PersonalComputer personalComputer) {
        System.out.println("Adding pc: " + personalComputer.toString());
        restTemplate.postForEntity(REST_URL + "/pcs", personalComputer, PersonalComputer.class);
        return "redirect:/systems";
    }

    // Controller for loading page to EDIT SYSTEM
    @GetMapping(value = "/systems/{id}")
    public final String gotoEditPCPage(@PathVariable Integer id, Model model) {
        System.out.println("Loading PC editing page");
        PersonalComputer personalComputer = restTemplate.getForEntity(REST_URL + "/pcs/" + id, PersonalComputer.class).getBody();
        model.addAttribute("personalComputer", personalComputer);
        return "system";
    }

    // Controller for calling REST when "SAVE PC" button clicked on EDIT page
    @PostMapping(value = "/systems/{id}")
    public final String editSystem(PersonalComputer personalComputer) {
        System.out.println("Editing pc: " + personalComputer.toString());
        restTemplate.put(REST_URL + "/pcs", personalComputer, PersonalComputer.class);
        return "redirect:/systems";
    }

    // Controller for calling REST when "DELETE" button clicked
    @GetMapping("/systems/{id}/delete")
    public final void deleteSystem(@PathVariable Integer id) {
        //Map<String, String> params = new HashMap<>();
        //params.put("id", id.toString());   <-- if REST deletes via REQUESTPARAM
        System.out.println("Deleting pc with id: " + id);
        restTemplate.delete(REST_URL + "/" + id + "/delete");
    }


    // Excel download
    @GetMapping("/download")
    public ResponseEntity<Resource> getFile() {
        String filename = "systems.xlsx";
        ObjectMapper mapper = new ObjectMapper();

        JsonNode pcsNode = restTemplate.getForObject(REST_URL + "/pcs", JsonNode.class);
        List<PersonalComputer> pcs = mapper.convertValue(pcsNode, new TypeReference<List<PersonalComputer>>(){});
        ByteArrayInputStream in = ExcelHelper.pcsToExcel(pcs);

        InputStreamResource file = new InputStreamResource(in);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
    }

    // test
    @PostMapping(value = "/systemstest")
    public final String testSystem(String test2, Model model) {
        System.out.println("String test22: " + test2);
        return "redirect:/systems";
    }

    // TODO: Add PUT, DELETE and GET by id, contains, etc mappings

}
