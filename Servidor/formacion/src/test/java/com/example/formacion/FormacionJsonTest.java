package com.example.formacion;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;

public class FormacionJsonTest {

	@Autowired
	private JacksonTester<Formacion> json;

	@Test
	void formacionSerializationTest() throws IOException {
		Formacion formacion = new Formacion(99L, 123.45);
		assertThat(json.write(formacion)).isStrictlyEqualToJson("expected.json");
		assertThat(json.write(formacion)).hasJsonPathNumberValue("@.id");
		assertThat(json.write(formacion)).extractingJsonPathNumberValue("@.id").isEqualTo(99);
		assertThat(json.write(formacion)).hasJsonPathNumberValue("@.amount");
		assertThat(json.write(formacion)).extractingJsonPathNumberValue("@.amount").isEqualTo(123.45);
	}
	
	
	@Test
	void formacionDeserializationTest() throws IOException {
	   String expected = """
	           {
	               "id":99,
	               "amount":123.45
	           }
	           """;
	   assertThat(json.parse(expected))
	           .isEqualTo(new Formacion(99L, 123.45));
	   assertThat(json.parseObject(expected).id()).isEqualTo(1000);
	   assertThat(json.parseObject(expected).amount()).isEqualTo(67.89);
	}

}
