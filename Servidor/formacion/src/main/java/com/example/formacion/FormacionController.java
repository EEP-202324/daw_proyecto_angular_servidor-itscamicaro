package com.example.formacion;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/formaciones")

class FormacionController {
	
	   private final FormacionRepository formacionRepository;

	   private FormacionController(FormacionRepository formacionRepository) {
	      this.formacionRepository = formacionRepository;
	   }
	
	
	@GetMapping("/{requestedId}")
	private ResponseEntity<Formacion> findById(@PathVariable Long requestedId) {
	    Optional<Formacion> formacionOptional = formacionRepository.findById(requestedId);
	    if (formacionOptional.isPresent()) {
	        return ResponseEntity.ok(formacionOptional.get());
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	@PostMapping
	private ResponseEntity<Void> createFormacion(@RequestBody Formacion newFormacionRequest, UriComponentsBuilder ucb) {
	   Formacion savedFormacion = formacionRepository.save(newFormacionRequest);
	   URI locationOfNewFormacion = ucb
	            .path("formaciones/{id}")
	            .buildAndExpand(savedFormacion.getId())
	            .toUri();
	   return ResponseEntity.created(locationOfNewFormacion).build();
	}
	
	
	@GetMapping
	private ResponseEntity<List<Formacion>> findAll(Pageable pageable) {
	    Page<Formacion> page = formacionRepository.findAll(
	            PageRequest.of(
	                    pageable.getPageNumber(),
	                    pageable.getPageSize(),
	                    pageable.getSortOr(Sort.by(Sort.Direction.ASC, "nombre"))
	            		));
	    return ResponseEntity.ok(page.getContent());
	}

}
