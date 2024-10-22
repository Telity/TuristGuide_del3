package gruppe7.turistguide_del3.Controller;


import gruppe7.turistguide_del3.Model.Attraction;
import gruppe7.turistguide_del3.Model.City;
import gruppe7.turistguide_del3.Model.Tag;
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
        Attraction attraction = touristService.getAttractionByName(name);

        if (attraction == null) {
            // Hvis attraktionen ikke findes, omdiriger eller vis en fejlmeddelelse
            return "redirect:/attractions"; // Omdiriger til listen over attraktioner
        }

        model.addAttribute("attraction", attraction);
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
        List<Tag> attractionTags = touristService.getTagsList();
        List<City> towns = touristService.getTownList();

        System.out.println("Towns: " + towns);

        //makes a new tourist that will get values from the html
        Attraction attraction = new Attraction();

        model.addAttribute("attractionTags", attractionTags);
        model.addAttribute("towns", towns);
        model.addAttribute("attraction", attraction);
        return "addAttraction";
    }

    @PostMapping("/save")
    public String saveAttraction(@ModelAttribute Attraction attraction, @RequestParam List<Integer> tagIds){

        // hvis brugeren har valgt tag-id'erne 1, 2, og 3, sendes disse som en liste af værdier med navnet tagIds.
        // RequestParam List<Integer> tagIds beder Spring om at binde alle værdier med dette navn til en liste af heltal.
        List<Tag> tags = tagIds.stream()
                .map(tag_id -> {
                    Tag tag = new Tag(); // Antag, at du har en standard konstruktør
                    tag.setTag_id(tag_id); // Sæt id for tag
                    return tag;
                })
                .toList();

        touristService.addAttraction(attraction, tags);
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
        Attraction attraction = touristService.getAttractionByName(name);
        List<Tag> tags = touristService.getTagsList();
        List<City> towns = touristService.getTownList();

        model.addAttribute("attraction", attraction);
        model.addAttribute("tags",tags);
        model.addAttribute("towns", towns);
        return "updateAttractions"; // navnet på din HTML-skabelon
    }

    // Håndterer formularindsendelse for opdatering
    @PostMapping("/update")
    public String updateAttraction(@ModelAttribute Attraction attraction, @RequestParam List<Integer> tagIds){

        List<Tag> tags = tagIds.stream()
                .map(tag_id -> {
                    Tag tag = new Tag(); // Antag, at du har en standard konstruktør
                    tag.setTag_id(tag_id); // Sæt id for tag
                    return tag;
                })
                .toList();

        touristService.updateAttraction(attraction, tags);

        return "redirect:/attractions"; // Omdiriger til listen over attraktioner
    }

}