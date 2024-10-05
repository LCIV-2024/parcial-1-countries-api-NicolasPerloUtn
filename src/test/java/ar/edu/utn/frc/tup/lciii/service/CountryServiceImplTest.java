package ar.edu.utn.frc.tup.lciii.service;

import ar.edu.utn.frc.tup.lciii.dtos.common.CountryDTO;
import ar.edu.utn.frc.tup.lciii.model.Country;
import ar.edu.utn.frc.tup.lciii.repository.CountryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class CountryServiceImplTest {
    @InjectMocks
    private CountryServiceImpl countryService;

    @Autowired
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testGetCountriesByRegion() {
//
//        Country country1 = new Country();
//        country1.setName("España");
//        country1.setCode("ES");
//        country1.setRegion("Europe");
//
//        Country country2 = new Country();
//        country2.setName("Francia");
//        country2.setCode("FR");
//        country2.setRegion("Europe");
//
//        Country country3 = new Country();
//        country3.setName("Japan");
//        country3.setCode("JP");
//        country3.setRegion("Asia");
//
//        List<Country> allCountries = Arrays.asList(country1, country2, country3);
//
//        when(countryService.getCountries("esp",null)).thenReturn(allCountries);
//
//        List<CountryDTO> result = countryService.getCountriesByRegion("Europe");
//
//        assertEquals(2, result.size());
//        assertTrue(result.stream().anyMatch(c -> c.getName().equals("España") && c.getCode().equals("ES")));
//        assertTrue(result.stream().anyMatch(c -> c.getName().equals("Francia") && c.getCode().equals("FR")));
//        assertFalse(result.stream().anyMatch(c -> c.getName().equals("Japan")));

    }
}