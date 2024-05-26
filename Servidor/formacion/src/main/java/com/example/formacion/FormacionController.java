package com.example.formacion;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/formaciones")

class FormacionController {

	@GetMapping("/{requestedId}")
	private ResponseEntity<Formacion> findById(@PathVariable Long requestedId) {
	    if (requestedId.equals(99L)) {
	        Formacion formacion = new Formacion(99L, "Administración y finanzas", "1.500€", true, "Formación Azuqueca de Henares" );
	        return ResponseEntity.ok(formacion);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}

}
