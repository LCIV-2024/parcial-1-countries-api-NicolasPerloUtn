package ar.edu.utn.frc.tup.lciii.controllers;

import ar.edu.utn.frc.tup.lciii.dtos.common.CountryDTO;
import ar.edu.utn.frc.tup.lciii.service.CountryServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
class CountryControllerTest {

    @Mock
    private MockMvc mockMvc;

    @Mock
    private CountryServiceImpl countryService;

    @Test
    public void controllerTest() throws Exception {

        CountryDTO country1 = new CountryDTO("Argentina", "ARG");
        CountryDTO country2 = new CountryDTO("Chile", "CHL");
        List<CountryDTO> countries = Arrays.asList(country1, country2);

        when(countryService.getCountries(null, null)).thenReturn(countries);

        MockHttpServletResponse response = this.mockMvc.perform(get("/api/countries").accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}