package gruppe7.turistguide_del3.Controller;

import gruppe7.turistguide_del3.Model.Attraction;
import gruppe7.turistguide_del3.Model.City;
import gruppe7.turistguide_del3.Model.Tag;
import gruppe7.turistguide_del3.Service.AttractionService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AttractionControllerTest {

    @Mock
    private AttractionService attractionService;

    //creating an instance of the attractionController and injects the mocked attractionService
    @InjectMocks
    private AttractionController attractionController;

    private AutoCloseable closeable;

    //initializing mocks before each test
    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }
    //closing mocks after each test
    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void getAllAttractions() {
        //Arrange
        List<Attraction> attractions = new ArrayList<>();
        attractions.add(new Attraction());
        when(attractionService.getAllAttractions()).thenReturn(attractions);

        Model model = mock(Model.class);

        //act
        String viewName = attractionController.getAllAttractions(model);

        //assert
        assertEquals("attractionList", viewName);
        verify(model).addAttribute("attractions", attractions);
    }

    @Test
    void getAttraction() {
        //arrange
        String attractionName = "Eiffel Tower";
        Attraction attraction = new Attraction();
        attraction.setName(attractionName);
        when(attractionService.getAttractionByName(attractionName)).thenReturn(attraction);

        Model model = mock(Model.class);

        //act
        String viewName = attractionController.getAttraction(attractionName, model);

        //assert
        assertEquals("attractionNames", viewName);
        verify(model).addAttribute("attraction", attraction);
    }

    @Test
    void getTag() {
        //arrange
        String attractionName = "Eiffel Tower";
        List<String> tags = List.of("Museum", "Tårn");
        when(attractionService.getTagsByName(attractionName)).thenReturn(tags);

        Model model = mock(Model.class);

        //act
        String viewName = attractionController.getTag(attractionName, model);

        //assert
        assertEquals("tags", viewName);
        verify(model).addAttribute("tags", tags);
    }

    @Test
    void addAttraction() {
        //arrange
        Model model = mock(Model.class);
        List<Tag> tags = List.of(new Tag(), new Tag());
        List<City> towns = List.of(new City(), new City());

        when(attractionService.getTagsList()).thenReturn(tags);
        when(attractionService.getTownList()).thenReturn(towns);

        //act
        String viewName = attractionController.addAttraction(model);

        //assert
        assertEquals("addAttraction", viewName);
        verify(model).addAttribute("attractionTags", tags);
        verify(model).addAttribute("towns", towns);
        verify(model).addAttribute("attraction", any(Attraction.class));
    }

    /*
    @Test
    void saveAttraction() {
        Attraction attraction = new Attraction();
        attraction.setCityID(1);
        List<Integer> tagIds = List.of(1,2);
        List<Tag> tags = List.of(new Tag(), new Tag());

        // Configure the mock to do nothing when addAttraction is called - for testing without dependencies
        doNothing().when(attractionService).addAttraction(attraction, tags);

        //act
        String viewName = attractionController.saveAttraction(attraction,tagIds);

        //assert
        assertEquals("redirect:/attractions", viewName);
        verify(attractionService).addAttraction(attraction, tags);
    }
     */
    @Test
    void saveAttraction() {
        Attraction attraction = new Attraction();
        List<Integer> tagIds = List.of(1, 2, 3);

        // Stub attractionService to accept any Attraction and any List of Tags
        doNothing().when(attractionService).addAttraction(eq(attraction), anyList());

        // Act
        String viewName = attractionController.saveAttraction(attraction, tagIds);

        // Assert
        assertEquals("redirect:/attractions", viewName);
        verify(attractionService).addAttraction(eq(attraction), anyList());
    }

    @Test
    void deleteAttraction() {
        //arrange
        String attractionName = "Eiffel Tower";
        when(attractionService.deleteAttractionByName(attractionName)).thenReturn(1);

        //act
        String viewName = attractionController.deleteAttraction(attractionName);

        //assert
        assertEquals("redirect:/attractions", viewName);
    }

    @Test
    void showUpdateForm() {
        //arrange
        String attractionName = "Eiffel Tower";
        Attraction attraction = new Attraction();
        when(attractionService.getAttractionByName(attractionName)).thenReturn(attraction);
        List<Tag> tags = List.of(new Tag(), new Tag());
        List<City> towns = List.of(new City(), new City());
        when(attractionService.getTagsList()).thenReturn(tags);
        when(attractionService.getTownList()).thenReturn(towns);

        Model model = mock(Model.class);

        //act
        String viewName = attractionController.showUpdateForm(attractionName, model);

        //assert
        assertEquals("updateAttractions", viewName);
        verify(model).addAttribute("attraction", attraction);
        verify(model).addAttribute("tags", tags);
        verify(model).addAttribute("towns", towns);
    }

    @Test
    void updateAttraction() {
        //arrange
        Attraction attraction = new Attraction();
        attraction.setCityID(1);
        List<Integer> tagIds = List.of(1,2);

        //act
        String viewName = attractionController.updateAttraction(attraction, tagIds);

        //assert
        assertEquals("redirect:/attractions", viewName);
        verify(attractionService).updateAttraction(any(Attraction.class), anyList());
    }
}
