package com.example.formacion;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FormacionApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void shouldReturnAFormacionWhenDataIsSaved() {
        ResponseEntity<String> response = restTemplate.getForEntity("/formaciones/99", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number id = documentContext.read("$.id");
        assertThat(id).isEqualTo(99);
        
        String nombre = documentContext.read("$.nombre");
        assertThat(nombre).isEqualTo("Administración y finanzas");
        
        String precio = documentContext.read("$.precio");
        assertThat(precio).isEqualTo("1.500€");
        
        Boolean dual = documentContext.read("$.dual");
        assertThat(dual).isTrue();
        
        String centro = documentContext.read("$.centro");
        assertThat(centro).isEqualTo("Formación Azuqueca de Henares");
        
        
    }
    
    
    @Test
    void shouldNotReturnAFormacionWithAnUnknownId() {
      ResponseEntity<String> response = restTemplate.getForEntity("/formaciones/1000", String.class);

      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
      assertThat(response.getBody()).isBlank();
    }
}
