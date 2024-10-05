package ar.edu.utn.frc.tup.lciii.service;

import ar.edu.utn.frc.tup.lciii.dtos.common.CountryDTO;
import ar.edu.utn.frc.tup.lciii.model.Country;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
public class ServiceTest {
    @Mock
    private MockMvc mockMvc;

    @Mock
    private CountryServiceImpl countryService;

    private Country[] mockCountries;

    private CountryDTO[] mockCountriesDto;

    private Country createCountry(String name) {
        Country country = new Country();
        country.setName(name);

        return country;
    }

    private CountryDTO createCountryDto(String name) {
        CountryDTO country = new CountryDTO();
        country.setName(name);

        return country;
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockCountries = new Country[] {
                createCountry("pais1"),
                createCountry("pais2")
        };
        mockCountriesDto = new CountryDTO[] {
                createCountryDto("Argentina"),
                createCountryDto("pais2")
        };
    }

    @Test
    public void testGetCountries() {

        CountryDTO country1 = new CountryDTO("Argentina", "ARG");
        CountryDTO country2 = new CountryDTO("Chile", "CHL");
        List<CountryDTO> countries = Arrays.asList(country1, country2);


        when(countryService.getCountries(null, null)).thenReturn(countries);

//        MockHttpServletResponse response = mockMvc.perform(get("/api/countries")
//                .contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();
//
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void testGetCountriesRegion() {

        CountryDTO country1 = new CountryDTO("Argentina", "ARG");
        CountryDTO country2 = new CountryDTO("Chile", "CHL");
        List<CountryDTO> countries = Arrays.asList(country1, country2);


        when(countryService.getCountriesByRegion("Americas")).thenReturn(countries);

    }

    @Test
    public void getCountries() {

        List<Country> countries = Arrays.asList(mockCountries[0], mockCountries[1]);

        when(countryService.getAllCountries()).thenReturn(countries);

        assertEquals(2, countries.size());
    }

    @Test
    public void getCountriesByName() {

        List<CountryDTO> countries = Arrays.asList(mockCountriesDto[0], mockCountriesDto[1]);

        when(countryService.getCountries(null, "Argentina")).thenReturn(countries);

        assertEquals(2, countries.size());
        assertEquals("Argentina", countries.get(0).getName());
    }


    @Test
    void testGetCountriesByLanguage() {

        Country country1 = new Country();
        country1.setName("Spain");
        Map<String, String> languages1 = new HashMap<>();
        languages1.put("es", "Spanish");
        country1.setLanguages(languages1);

        Country country2 = new Country();
        country2.setName("France");
        Map<String, String> languages2 = new HashMap<>();
        languages2.put("fr", "French");
        country2.setLanguages(languages2);

        Country country3 = new Country();
        country3.setName("Belgium");
        Map<String, String> languages3 = new HashMap<>();
        languages3.put("fr", "French");
        languages3.put("nl", "Dutch");
        country3.setLanguages(languages3);

        List<Country> allCountries = Arrays.asList(country1, country2, country3);

        when(countryService.getAllCountries()).thenReturn(allCountries);


        List<CountryDTO> result = countryService.getCountriesByLanguage("French");

        when( countryService.getCountriesByLanguage("French")).thenReturn(allCountries);
        assertEquals(result.size(), allCountries.size());

    }
}
