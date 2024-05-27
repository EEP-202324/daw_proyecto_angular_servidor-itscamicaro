package com.example.formacion;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest

public class FormacionJsonTest {

	@Autowired
	private JacksonTester<Formacion[]> jsonList;

	@Autowired
	private JacksonTester<Formacion> json;

	private Formacion[] formaciones;

	@BeforeEach
	void setUp() {
		formaciones: Arrays.array(
				new Formacion(9L, "Administración y finanzas","1.500€", "Formación Azuqueca de Henares"),
				new Formacion (2L, "Comercio internacional", "1.600€", "Formación Alcalá de Henares"),
				new Formacion (3L, "Desarrollo de aplicaciones web", "2.500€", "Formación Majadahonda"),
				new Formacion (4L, "Desarrollo de aplicaciones multiplataforma", "2.500€", "Formación Majadahonda"),
				new Formacion (5L, "Marketing y publicidad", "1.800€", "Formación Azuqueca de Henares"),
				new Formacion (6L, "Higiene bucodental", "3.100€", "Formación Alcalá de Henares"),
				new Formacion (7L, "Nutrición y diétetica", "2.300€", "Formación Alcalá de Henares"),
				new Formacion (8L, "Integración social", "800€", "Formación Majadahonda")
				);
	}

	@Test
	void formacionSerializationTest() throws IOException {
		Formacion formacion = new Formacion(99L, "Administración y finanzas", "1.500€",
				"Formación Azuqueca de Henares");
		assertThat(json.write(formacion)).isStrictlyEqualToJson("single.json");
		assertThat(json.write(formacion)).hasJsonPathNumberValue("@.id");
		assertThat(json.write(formacion)).extractingJsonPathNumberValue("@.id").isEqualTo(99);
		assertThat(json.write(formacion)).hasJsonPathStringValue("@.nombre");
		assertThat(json.write(formacion)).extractingJsonPathStringValue("@.nombre")
				.isEqualTo("Administración y finanzas");
		assertThat(json.write(formacion)).hasJsonPathStringValue("@.precio");
		assertThat(json.write(formacion)).extractingJsonPathStringValue("@.precio").isEqualTo("1.500€");
		assertThat(json.write(formacion)).hasJsonPathStringValue("@.centro");
		assertThat(json.write(formacion)).extractingJsonPathStringValue("@.centro")
				.isEqualTo("Formación Azuqueca de Henares");

	}

	@Test
	void formacionDeserializationTest() throws IOException {
		String expected = """
				{
				    "id": 99,
				    "nombre": "Administración y finanzas",
				    "precio": "1.500€",
				    "centro": "Formación Azuqueca de Henares"
				}
				""";
		assertThat(json.parse(expected))
				.isEqualTo(new Formacion(99L, "Administración y finanzas", "1.500€", "Formación Azuqueca de Henares"));
		assertThat(json.parseObject(expected).getId()).isEqualTo(99);
		assertThat(json.parseObject(expected).getNombre()).isEqualTo("Administración y finanzas");
		assertThat(json.parseObject(expected).getPrecio()).isEqualTo("1.500€");
		assertThat(json.parseObject(expected).getCentro()).isEqualTo("Formación Azuqueca de Henares");

	}

}
