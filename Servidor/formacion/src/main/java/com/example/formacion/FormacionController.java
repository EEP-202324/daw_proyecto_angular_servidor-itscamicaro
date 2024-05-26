package com.example.formacion;

import org.springframework.http.ResponseEntity;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	

}
