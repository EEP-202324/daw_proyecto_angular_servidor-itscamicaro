package com.example.formacion;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@JsonTest
@ExtendWith(SpringExtension.class)

public class FormacionJsonTest {

	@Autowired
	private JacksonTester<Formacion> json;

	@Test
	void formacionSerializationTest() throws IOException {
		Formacion formacion = new Formacion(99L, "Administración y finanzas", "1.500€", true,
				"Formación Azuqueca de Henares");
		assertThat(json.write(formacion)).isStrictlyEqualToJson("expected.json");
		assertThat(json.write(formacion)).hasJsonPathNumberValue("@.id");
		assertThat(json.write(formacion)).extractingJsonPathNumberValue("@.id").isEqualTo(99);
		assertThat(json.write(formacion)).hasJsonPathStringValue("@.nombre");
		assertThat(json.write(formacion)).extractingJsonPathStringValue("@.nombre")
				.isEqualTo("Administración y finanzas");
		assertThat(json.write(formacion)).hasJsonPathStringValue("@.precio");
		assertThat(json.write(formacion)).extractingJsonPathStringValue("@.precio").isEqualTo("1.500€");
		assertThat(json.write(formacion)).hasJsonPathBooleanValue("@.dual");
		assertThat(json.write(formacion)).extractingJsonPathBooleanValue("@.dual").isEqualTo(true);
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
				    "dual": true,
				    "centro": "Formación Azuqueca de Henares"
				}
				""";
		assertThat(json.parse(expected)).isEqualTo(
				new Formacion(99L, "Administración y finanzas", "1.500€", true, "Formación Azuqueca de Henares"));
		assertThat(json.parseObject(expected).getId()).isEqualTo(99);
		assertThat(json.parseObject(expected).getNombre()).isEqualTo("Administración y finanzas");
		assertThat(json.parseObject(expected).getPrecio()).isEqualTo("1.500€");
		assertThat(json.parseObject(expected).getDual()).isTrue();
		assertThat(json.parseObject(expected).getCentro()).isEqualTo("Formación Azuqueca de Henares");

	}

}
