package ar.edu.utn.frc.tup.lciii.service;

import ar.edu.utn.frc.tup.lciii.model.Country;
import ar.edu.utn.frc.tup.lciii.dtos.common.CountryDTO;
import ar.edu.utn.frc.tup.lciii.model.CountryEntity;
import ar.edu.utn.frc.tup.lciii.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

        @Autowired
        private CountryRepository countryRepository;

        private final RestTemplate restTemplate;

        public List<Country> getAllCountries() {
                String url = "https://restcountries.com/v3.1/all";
                List<Map<String, Object>> response = restTemplate.getForObject(url, List.class);
                return response.stream().map(this::mapToCountry).collect(Collectors.toList());
        }

        /**
         * Agregar mapeo de campo cca3 (String)
         * Agregar mapeo campos borders ((List<String>))
         */
        public Country mapToCountry(Map<String, Object> countryData) {
                Map<String, Object> nameData = (Map<String, Object>) countryData.get("name");
                return Country.builder()
                        .name((String) nameData.get("common"))
                        .code((String) countryData.get("cca3"))
                        .borders((List<String>) countryData.get("borders"))
                        .population(((Number) countryData.get("population")).longValue())
                        .area(((Number) countryData.get("area")).doubleValue())
                        .region((String) countryData.get("region"))
                        .languages((Map<String, String>) countryData.get("languages"))
                        .build();
        }


        public CountryDTO mapToDTO(Country country) {
                return new CountryDTO(country.getCode(), country.getName());
        }


        @Override
        public List<CountryDTO> getCountries(String name, String code) {
                List<Country> countries = this.getAllCountries();
                List<CountryDTO> countryDTOS = new ArrayList<>();

                for (Country c : countries) {
                        countryDTOS.add(mapToDTO(c));
                }

                if (name == null && code != null) {
                        return countryDTOS.stream().filter(c -> c.getCode().equals(code)).collect(Collectors.toList());
                }
                else if (code == null && name!=null) {
                        return countryDTOS.stream().filter(c -> c.getName().equals(name)).collect(Collectors.toList());
                }

                return countryDTOS;
        }

        @Override
        public List<CountryDTO> getCountriesByRegion(String region) {
                List<Country> countries = this.getAllCountries();
                List<CountryDTO> countryDTOS = new ArrayList<>();

                for (Country c : countries) {
                        if (c.getRegion().equals(region)) {
                                countryDTOS.add(mapToDTO(c));
                        }
                }

                return countryDTOS;
        }

        @Override
        public List<CountryDTO> getCountriesByLanguage(String language) {
                List<Country> countries = this.getAllCountries();
                List<CountryDTO> countryDTOS = new ArrayList<>();

                for (Country c : countries) {
                        if (c.getLanguages() != null && c.getLanguages().containsValue(language)) {
                                countryDTOS.add(mapToDTO(c));
                        }
                }

                return countryDTOS;
        }

        @Override
        public CountryDTO getCountryWithMostBorders() {
                List<Country> countries = this.getAllCountries();
                Country paisConMasBordes = null;
                int maximoBorders = 0;

                for (Country country : countries) {
                        int contador = (country.getBorders() != null) ? country.getBorders().size() : 0;

                        if (contador > maximoBorders) {
                                maximoBorders = contador;
                                paisConMasBordes = country;
                        }
                }

                if (paisConMasBordes != null) {
                        return mapToDTO(paisConMasBordes);
                }

                return null;
        }

        @Override
        public List<CountryEntity> postCountries(int cantidad) {
                List<Country> countries = this.getAllCountries();

                if (cantidad <= 0 || cantidad > 10) {
                        throw new IllegalArgumentException("La cantidad debe estar entre 1 y 10.");
                }

                Collections.shuffle(countries);
                List<Country> selectedCountries = countries.stream()
                        .limit(cantidad)
                        .collect(Collectors.toList());

                List<CountryEntity> guardar = new ArrayList<>();
                for (Country country : selectedCountries) {
                        CountryEntity entity = new CountryEntity();
                        entity.setName(country.getName());
                        entity.setCode(country.getCode());
                        entity.setPopulation(country.getPopulation());
                        entity.setArea(country.getArea());

                        guardar.add(entity);
                }

                countryRepository.saveAll(guardar);

                return countryRepository.findAll();
        }
}