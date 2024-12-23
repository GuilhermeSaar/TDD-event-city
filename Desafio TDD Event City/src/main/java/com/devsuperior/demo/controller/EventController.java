package com.devsuperior.demo.controller;


import com.devsuperior.demo.dto.EventDTO;
import com.devsuperior.demo.service.EventService;
import com.devsuperior.demo.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/events")
public class EventController {


    @Autowired
    private EventService eventService;


    @PutMapping(value = "{id}")
    public ResponseEntity<EventDTO> update(@PathVariable Long id, @RequestBody EventDTO dto) {

        try {

            dto = eventService.update(id, dto);

            return ResponseEntity.ok().body(dto);

        }

        catch (ResourceNotFoundException e) {

            return ResponseEntity.notFound().build();

        }
    }
}

