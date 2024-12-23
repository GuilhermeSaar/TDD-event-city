package com.devsuperior.demo.controller;


import com.devsuperior.demo.dto.CityDTO;
import com.devsuperior.demo.service.CityService;
import com.devsuperior.demo.service.exception.DependentEntityException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/cities")
public class CityController {


    @Autowired
    private CityService cityService;


    @GetMapping
    public ResponseEntity<List<CityDTO>> findAll() {

        List<CityDTO> listDTO = cityService.findAll();

        return ResponseEntity.ok().body(listDTO);

    }


    @PostMapping
    public ResponseEntity<CityDTO> insert(@RequestBody CityDTO dto) {

        dto = cityService.insert(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}")
                .buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(uri).body(dto); // retorna 201

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntity(@PathVariable Long id) {

        try {

            cityService.delete(id);
            return ResponseEntity.noContent().build();
        }
        catch (EntityNotFoundException e ) {
            return ResponseEntity.notFound().build();
        }
        catch (DependentEntityException e) {
            return ResponseEntity.badRequest().body(null);

        }
    }
}
