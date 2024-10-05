package ar.edu.utn.frc.tup.lciii.controllers;

import ar.edu.utn.frc.tup.lciii.dtos.common.CountryDTO;
import ar.edu.utn.frc.tup.lciii.model.Country;
import ar.edu.utn.frc.tup.lciii.service.CountryService;
import ar.edu.utn.frc.tup.lciii.service.CountryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CountryControllerTest {

    @Mock
    private MockMvc mockMvc;

    @Mock
    private CountryServiceImpl countryService;

    @InjectMocks
    private CountryController countryController;

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
                createCountryDto("pais1"),
                createCountryDto("pais2")
        };
    }


    @Test
    void testGetCountries() throws Exception {
        CountryDTO country1 = new CountryDTO("España", "ES");
        CountryDTO country2 = new CountryDTO("España", "ES");
        List<CountryDTO> countries = Arrays.asList(country1, country2);

        when(countryService.getCountries(null, "España")).thenReturn(countries);

        MockHttpServletResponse response = mockMvc.perform(get("/api/countries")
                        .contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentLength()).isEqualTo(countries.size());

    }

    @Test
    void testGetCountriesName() {
        CountryDTO country1 = new CountryDTO("España", "ES");
        CountryDTO country2 = new CountryDTO("Francia", "FR");
        List<CountryDTO> countries = Arrays.asList(country1);

        when(countryService.getCountries(null, "España")).thenReturn(countries);

    }

    @Test
    public void getCountries() {

        List<Country> countries = Arrays.asList(mockCountries[0], mockCountries[1]);

        when(countryService.getAllCountries()).thenReturn(countries);

        assertEquals(2, countries.size());
    }


//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Mock
//    private CountryServiceImpl countryService;
//
//    private Country[] mockCountries;
//
//    private CountryDTO[] mockCountriesDto;
//
//    private Country createCountry(String name) {
//        Country country = new Country();
//        country.setName(name);
//
//        return country;
//    }
//
//    private CountryDTO createCountryDto(String name) {
//        CountryDTO country = new CountryDTO();
//        country.setName(name);
//
//        return country;
//    }
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        mockCountries = new Country[] {
//                createCountry("pais1"),
//                createCountry("pais2")
//        };
//        mockCountriesDto = new CountryDTO[] {
//                createCountryDto("pais1"),
//                createCountryDto("pais2")
//        };
//    }
//
//
//    @Test
//    public void getCountries() throws Exception {
//
//        List<Country> countries = Arrays.asList(mockCountries[0], mockCountries[1]);
//
//        when(countryService.getAllCountries()).thenReturn(countries);
//
//        assertEquals(2, countries.size());
//    }
//
//    @Test
//    public void getCountriesByRegion() throws Exception {
//
//        List<CountryDTO> countries = Arrays.asList(mockCountriesDto[0], mockCountriesDto[1]);
//        String region = "Americas";
//
//        when(countryService.getCountriesByRegion(region)).thenReturn(countries);
//
//
//    }

}