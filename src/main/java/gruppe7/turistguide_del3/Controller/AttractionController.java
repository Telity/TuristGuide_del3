package gruppe7.turistguide_del3.Controller;


import gruppe7.turistguide_del3.Model.Attraction;
import gruppe7.turistguide_del3.Service.AttractionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/attractions")
public class AttractionController {

    public final AttractionService touristService;

    public AttractionController(AttractionService touristService) {
        this.touristService = touristService;
    }

    @GetMapping("")
    public String getAllAttractions(Model model) {
        List<Attraction> allAttractions = touristService.getAllAttractions();
        model.addAttribute("attractions", allAttractions);
        return "attractionList";
    }

    @GetMapping("/{name}")
    public String getAttraction(@PathVariable("name") String name, Model model) {
        List<Attraction> attractions = touristService.getAttractionByName(name);
        model.addAttribute("attraction", attractions);
        return "attractionNames";
    }

    @GetMapping("{name}/tags")
    public String getTag(@PathVariable("name") String name, Model model) {
        List<String> tags = touristService.getTagsByName(name);
        model.addAttribute("tags", tags);
        return "tags";
    }

    @GetMapping("/add") // displays form
    public String addAttraction(Model model) {
        //adding list with tags options
        List<String> attractionTags = touristService.getTagsList();
        //makes a new tourist that will get values from the html
        Attraction attraction = new Attraction();
        model.addAttribute("attractionTags", attractionTags);
        model.addAttribute("attraction", attraction);
        return "addAttraction";
    }

    @PostMapping("/save")
    public String saveAttraction(@ModelAttribute Attraction attraction){
        touristService.addAttraction(attraction);
        return "redirect:/attractions";
    }

    /*
    @PostMapping("/save")
    public String saveAttraction(@ModelAttribute Attraction attraction){;
        touristService.AddAttractionsList(attraction);
        return "redirect:/attractions";
    }
     */

    @PostMapping("/{name}/delete")
    public String deleteAttraction(@PathVariable("name") String name) {
        int deletedRows = touristService.deleteAttractionByName(name);

        if(deletedRows > 0) {
            return "redirect:/attractions";
        }else{
            return "redirect:/attractions";
        }
    }

    // Viser formular til at opdatere en attraktion
    @GetMapping("/{name}/edit")
    public String showUpdateForm(@PathVariable("name") String name, Model model) {
        List<Attraction> attraction = touristService.getAttractionByName(name);
        model.addAttribute("attraction", attraction);
        model.addAttribute("tags",touristService.getTagsList());
        model.addAttribute("towns",touristService.getTownList());
        return "updateAttractions"; // navnet på din HTML-skabelon
    }

    // Håndterer formularindsendelse for opdatering
    @PostMapping("/update")
    public String updateAttraction(@ModelAttribute Attraction attraction) {
        touristService.updateAttraction(attraction);
        return "redirect:/attractions"; // Omdiriger til listen over attraktioner
    }

}