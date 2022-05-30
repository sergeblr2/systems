package by.bhl.web;

import by.bhl.model.PersonalComputer;
import by.bhl.web.ExcelHelper.ExcelHelper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class SystemsWebController {

    Logger logger = LoggerFactory.getLogger(SystemsWebController.class);

    private RestTemplate restTemplate = new RestTemplate();

    private static String baseUrl = ServletUriComponentsBuilder.fromCurrentServletMapping().build().toString().
            replace(ServletUriComponentsBuilder.fromCurrentContextPath().build().getPath().toString(),"");

    @Value("${rest.url}")
    private String REST_URL;

    // Controller for loading page with ALL SYSTEMS
    @GetMapping(value = "/systems")
    public final String listAllPcs(Model model) {
        logger.info("Getting all pcs");
        model.addAttribute("personalComputers",
                restTemplate.getForObject(baseUrl + REST_URL + "/pcs", List.class));
        //model.addAttribute("test2", "j");
        return "systems";
    }

    // Controller for loading page to ADD PC
    @GetMapping(value = "/system")
    public final String gotoAddPCPage(Model model) {
        logger.info("Loading PC adding page");
        PersonalComputer personalComputer = new PersonalComputer();
        model.addAttribute("personalComputer", personalComputer);
        return "system";
    }

    // Controller for calling REST when "ADD PC" button clicked
    @PostMapping(value = "/system")
    public final String addSystem(PersonalComputer personalComputer) {
        logger.info("Adding pc: " + personalComputer.toString());
        restTemplate.postForEntity(baseUrl + REST_URL + "/pcs", personalComputer, PersonalComputer.class);
        return "redirect:/systems";
    }

    // Controller for loading page to EDIT SYSTEM
    @GetMapping(value = "/systems/{id}")
    public final String gotoEditPCPage(@PathVariable Integer id, Model model) {
        logger.info("Loading PC editing page");
        PersonalComputer personalComputer = restTemplate.getForEntity(baseUrl + REST_URL + "/pcs/" + id, PersonalComputer.class).getBody();
        model.addAttribute("personalComputer", personalComputer);
        return "system";
    }

    // Controller for calling REST when "SAVE PC" button clicked on EDIT page
    @PostMapping(value = "/systems/{id}")
    public final String editSystem(PersonalComputer personalComputer) {
        logger.info("Editing pc: " + personalComputer.toString());
        restTemplate.put(baseUrl + REST_URL + "/pcs", personalComputer, PersonalComputer.class);
        return "redirect:/systems";
    }

    // Controller for calling REST when "DELETE" button clicked
    @GetMapping("/systems/{id}/delete")
    public final void deleteSystem(@PathVariable Integer id) {
        //Map<String, String> params = new HashMap<>();
        //params.put("id", id.toString());   <-- if REST deletes via REQUESTPARAM
        logger.info("Deleting pc with id: " + id);
        restTemplate.delete(baseUrl + REST_URL + "/" + id + "/delete");
    }


    // Excel download
    @GetMapping("/download")
    public ResponseEntity<Resource> getFile() {
        String filename = "systems.xlsx";
        ObjectMapper mapper = new ObjectMapper();

        JsonNode pcsNode = restTemplate.getForObject(baseUrl + REST_URL + "/pcs", JsonNode.class);
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
        logger.info("String test22: " + test2);
        return "redirect:/systems";
    }

    // TODO: Add PUT, DELETE and GET by id, contains, etc mappings

}
