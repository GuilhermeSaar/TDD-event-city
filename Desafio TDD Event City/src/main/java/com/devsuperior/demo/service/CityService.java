package com.devsuperior.demo.service;

import com.devsuperior.demo.dto.CityDTO;
import com.devsuperior.demo.entities.City;
import com.devsuperior.demo.repositories.CityRepository;
import com.devsuperior.demo.repositories.EventRepository;
import com.devsuperior.demo.service.exception.DependentEntityException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class CityService {


    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private EventRepository eventRepository;


    @Transactional(readOnly = true)
    public List<CityDTO> findAll() {

        List<City> list = cityRepository.findAll();

        List<City> orderedList = new ArrayList<>(list);
        orderedList.sort(Comparator.comparing(City::getName));

        return orderedList.stream().map(CityDTO::new).toList();

    }


    @Transactional
    public CityDTO insert(CityDTO dto) {

        City city = new City();

        city.setName(dto.getName());

        city = cityRepository.save(city);

        return new CityDTO(city);
    }


    public void delete(Long id) {

        if (!cityRepository.existsById(id)) {
            throw new EntityNotFoundException("Recurso nao encontrado.");
        }

        if (hasDependecies(id)) {

            throw new DependentEntityException("A entidade nao pode ser excluida.");
        }

        cityRepository.deleteById(id);

    }



    private boolean hasDependecies(Long id) {

        return eventRepository.existsByCityId(id);
    }














}