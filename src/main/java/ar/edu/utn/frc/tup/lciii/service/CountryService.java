package ar.edu.utn.frc.tup.lciii.service;

import ar.edu.utn.frc.tup.lciii.dtos.common.CountryDTO;
import ar.edu.utn.frc.tup.lciii.model.CountryEntity;

import java.util.List;

public interface CountryService {
    List<CountryDTO> getCountries(String name, String code);
    List<CountryDTO> getCountriesByRegion(String region);
    List<CountryDTO> getCountriesByLanguage(String language);
    CountryDTO getCountryWithMostBorders();
    List<CountryEntity> postCountries(int cantidad);
}
