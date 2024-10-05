package ar.edu.utn.frc.tup.lciii.controllers;

import ar.edu.utn.frc.tup.lciii.dtos.common.CountryDTO;
import ar.edu.utn.frc.tup.lciii.service.CountryServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class TestController {
    @Mock
    private MockMvc mockMvc;

    @Mock
    private CountryServiceImpl countryService;

    @InjectMocks
    private CountryController countryController;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testGetCountries() throws Exception {

        CountryDTO country1 = new CountryDTO("Argentina", "ARG");
        CountryDTO country2 = new CountryDTO("Chile", "CHL");
        List<CountryDTO> countries = Arrays.asList(country1, country2);


        when(countryService.getCountries(null, null)).thenReturn(countries);

        MockHttpServletResponse response = mockMvc.perform(get("/api/countries")
                .contentType(MediaType.APPLICATION_JSON)).andReturn()

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}
