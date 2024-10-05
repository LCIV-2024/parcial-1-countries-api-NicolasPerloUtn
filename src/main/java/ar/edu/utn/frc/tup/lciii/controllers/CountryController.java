package ar.edu.utn.frc.tup.lciii.controllers;
import ar.edu.utn.frc.tup.lciii.dtos.common.CountryDTO;
import ar.edu.utn.frc.tup.lciii.dtos.common.CountryRequestDto;
import ar.edu.utn.frc.tup.lciii.model.CountryEntity;
import ar.edu.utn.frc.tup.lciii.service.CountryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CountryController {

    @Autowired
    private final CountryServiceImpl countryService;

    @GetMapping("/api/countries")
    public List<CountryDTO> getCountries(@RequestParam (required = false) String name, @RequestParam (required = false) String code) {
        return countryService.getCountries(name, code);
    }

    @GetMapping("/api/countries/{continent}/continent")
    public List<CountryDTO> getCountriesByRegion(@PathVariable (required = false) String continent) {
        return countryService.getCountriesByRegion(continent);
    }

    @GetMapping("/api/countries/{language}/language")
    public List<CountryDTO> getCountriesByLanguage(@PathVariable (required = false) String language) {
        return countryService.getCountriesByLanguage(language);
    }

    @GetMapping("/api/countries/most-borders")
    public CountryDTO getCountryWithMoreBorders() {
        return countryService.getCountryWithMostBorders();
    }

    @PostMapping("/api/countries")
    public List<CountryEntity> postCountries(@RequestBody CountryRequestDto request) {
        return countryService.postCountries(request.getAmountOfCountryToSave());
    }
}