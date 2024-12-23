package com.devsuperior.demo.service;

import com.devsuperior.demo.dto.EventDTO;
import com.devsuperior.demo.entities.City;
import com.devsuperior.demo.entities.Event;
import com.devsuperior.demo.repositories.CityRepository;
import com.devsuperior.demo.repositories.EventRepository;
import com.devsuperior.demo.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventService {


    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CityRepository cityRepository;


    @Transactional
    public EventDTO update(Long id, EventDTO dto) {

            Event event = eventRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Recurso nao encontrado"));

            event.setName(dto.getName());
            event.setDate(dto.getDate());
            event.setUrl(dto.getUrl());

            City city = cityRepository.findById(dto.getCityId()).orElseThrow(
                    () -> new ResourceNotFoundException("Recurso nao encontrado.")
            );

            event.setCity(city);

            return new EventDTO(event);
    }
}
