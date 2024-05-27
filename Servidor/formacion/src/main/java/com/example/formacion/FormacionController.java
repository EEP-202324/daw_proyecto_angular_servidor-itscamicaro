package com.example.formacion;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@CrossOrigin(origins = "http://localhost:4200")
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
	
	
	@PutMapping("/{requestedId}")
    public ResponseEntity<Void> putEscuela(@PathVariable Long requestedId, @RequestBody Formacion formacionActualizada) {
        Optional<Formacion> optional = formacionRepository.findById(requestedId);
        if (optional.isPresent()) {
            Formacion formacion = optional.get();
            Formacion updateFormacion = new Formacion (
                    formacion.getId(),
                    formacionActualizada.getNombre(),
                    formacionActualizada.getPrecio(),
                    formacionActualizada.getCentro());
        formacionRepository.save(updateFormacion);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
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
	
	@DeleteMapping("/{id}")
	private ResponseEntity<Void> deleteCashCard(@PathVariable Long id) {
	    formacionRepository.deleteById(id); // Add this line
	    return ResponseEntity.noContent().build();
	}
	
}
