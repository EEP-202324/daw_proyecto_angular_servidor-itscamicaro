package com.example.formacion;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.annotation.DirtiesContext.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
class FormacionApplicationTests {
	@Autowired
	TestRestTemplate restTemplate;

	@Test
	void shouldReturnAFormacionWhenDataIsSaved() {
		ResponseEntity<String> response = restTemplate.getForEntity("/formaciones/1", String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

		DocumentContext documentContext = JsonPath.parse(response.getBody());
		Number id = documentContext.read("$.id");
		assertThat(id).isEqualTo(1);

		String nombre = documentContext.read("$.nombre");
		assertThat(nombre).isEqualTo("Administración y finanzas");

		String precio = documentContext.read("$.precio");
		assertThat(precio).isEqualTo("1.500€");

		String centro = documentContext.read("$.centro");
		assertThat(centro).isEqualTo("Formación Azuqueca de Henares");

	}

	@Test
	void shouldNotReturnAFormacionWithAnUnknownId() {
		ResponseEntity<String> response = restTemplate.getForEntity("/formaciones/1000", String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(response.getBody()).isBlank();
	}
	
	
	@Test
	@DirtiesContext
	void shouldCreateNewFormacion() {
		Formacion newFormacion = new Formacion(2L, "Comercio internacional", "1.600€", "Formación Alcalá de Henares");
		ResponseEntity<Void> createResponse = restTemplate.postForEntity("/formaciones", newFormacion, Void.class);
		assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

		URI locationOfNewFormacion = createResponse.getHeaders().getLocation();
		ResponseEntity<String> getResponse = restTemplate.getForEntity(locationOfNewFormacion, String.class);
		assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	void shouldReturnAllFormacionesWhenListIsRequested() {
		ResponseEntity<String> response = restTemplate.getForEntity("/formaciones", String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

		DocumentContext documentContext = JsonPath.parse(response.getBody());
		int formacionCount = documentContext.read("$.length()");
		assertThat(formacionCount).isEqualTo(8);

		JSONArray ids = documentContext.read("$..id");
		assertThat(ids).containsExactlyInAnyOrder(1, 2, 3, 4, 5, 6, 7, 8);

		JSONArray nombres = documentContext.read("$..nombre");
		assertThat(nombres).containsExactlyInAnyOrder("Administración y finanzas", "Comercio internacional",
				"Desarrollo de aplicaciones web", "Desarrollo de aplicaciones multiplataforma",
				"Marketing y publicidad", "Higiene sanitaria", "Nutrición y diétetica", "Integración social");

		JSONArray precios = documentContext.read("$..precio");
		assertThat(precios).containsExactlyInAnyOrder("1.500€", "1.600€", "2.500€", "2.500€", "1.800€", "3.100€",
				"2.300€", "800€");

		JSONArray centros = documentContext.read("$..centro");
		assertThat(centros).containsExactlyInAnyOrder("Formación Azuqueca de Henares", "Formación Alcalá de Henares",
				"Formación Majadahonda", "Formación Majadahonda", "Formación Azuqueca de Henares",
				"Formación Alcalá de Henares", "Formación Alcalá de Henares", "Formación Majadahonda");

	}
	
	@Test
	void shouldReturnAPageOfFormaciones() {
	    ResponseEntity<String> response = restTemplate.getForEntity("/formaciones?page=0&size=1", String.class);
	    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

	    DocumentContext documentContext = JsonPath.parse(response.getBody());
	    JSONArray page = documentContext.read("$[*]");
	    assertThat(page.size()).isEqualTo(1);
	}
	
	@Test
	void shouldReturnASortedPageOfFormaciones() {
	    ResponseEntity<String> response = restTemplate.getForEntity("/formaciones?page=0&size=1&sort=nombre,desc", String.class);
	    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

	    DocumentContext documentContext = JsonPath.parse(response.getBody());
	    JSONArray read = documentContext.read("$[*]");
	    assertThat(read.size()).isEqualTo(1);

	    String nombre = documentContext.read("$[0].nombre");
	    assertThat(nombre).isEqualTo("Nutrición y diétetica");
	}
	
	@Test
	void shouldReturnASortedPageOfFormacionesWithNoParametersAndUseDefaultValues() {
	    ResponseEntity<String> response = restTemplate.getForEntity("/formaciones", String.class);
	    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

	    DocumentContext documentContext = JsonPath.parse(response.getBody());
	    JSONArray page = documentContext.read("$[*]");
	    assertThat(page.size()).isEqualTo(8);

	    JSONArray nombres = documentContext.read("$..nombre");
	    assertThat(nombres).containsExactly("Administración y finanzas", "Comercio internacional", "Desarrollo de aplicaciones multiplataforma",
	    		"Desarrollo de aplicaciones web", "Higiene sanitaria", "Integración social", "Marketing y publicidad", "Nutrición y diétetica" );
	    
	}
	
	@Test
	@DirtiesContext
	void shouldUpdateAnExistingFormacion() {
	    Formacion formacionUpdate = new Formacion(6L, "Higiene sanitaria", "3.100€", "Formación Alcalá de Henares");
	    HttpEntity<Formacion> request = new HttpEntity<>(formacionUpdate);
	    ResponseEntity<Void> response = restTemplate
	            .exchange("/formaciones/6", HttpMethod.PUT, request, Void.class);
	    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
	    ResponseEntity<String> getResponse = restTemplate
	            .getForEntity("/formaciones/6", String.class);
	    assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
	    DocumentContext documentContext = JsonPath.parse(getResponse.getBody());
	    Number id = documentContext.read("$.id");
	    String nombre = documentContext.read("$.nombre");
	    String precio = documentContext.read("$.precio");
	    String centro = documentContext.read("$.centro");
	    assertThat(id).isEqualTo(6);
	    assertThat(nombre).isEqualTo("Higiene sanitaria");
	    assertThat(precio).isEqualTo("3.100€");
	    assertThat(centro).isEqualTo("Formación Alcalá de Henares");
	    
	}
	
	@Test
	void shouldNotUpdateAFormacionThatDoesNotExist() {
	    Formacion unknownFormacion = new Formacion(null, "Educación infantil", null, null);
	    HttpEntity<Formacion> request = new HttpEntity<>(unknownFormacion);
	    ResponseEntity<Void> response = restTemplate
	            .exchange("/formaciones/99999", HttpMethod.PUT, request, Void.class);
	    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}
	
	@Test
	@DirtiesContext
	void shouldDeleteAnExistingFormacion() {
	    ResponseEntity<Void> response = restTemplate
	            .exchange("/formaciones/8", HttpMethod.DELETE, null, Void.class);
	    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
	    ResponseEntity<String> getResponse = restTemplate
	            .getForEntity("/formaciones/8", String.class);
	    assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}
	

	


}
