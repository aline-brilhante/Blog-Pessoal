package com.blogpessoal.blogPessoal.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class UsuarioModelTest {
	
	public Usuario usuario;
	
	@Autowired
	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	Validator validator = factory.getValidator();

	@BeforeEach
	public void start() {
		usuario = new Usuario("Boazzz", "12345");
	}
	
	@Test
	void testValidaAtributos() {
		Set<ConstraintViolation<Usuario>> violacao = validator.validate(usuario);
		System.out.println(violacao.toString());
		assertTrue(violacao.isEmpty());
	}

	@Test
	void testValidaAtributosNulos() {
		Usuario usuarioErro = new Usuario();
		usuarioErro.setUsuario("AlineBoaz");
		Set<ConstraintViolation<Usuario>> violacao = validator.validate(usuarioErro);
		System.out.println(violacao.toString());
		assertFalse(violacao.isEmpty());
	}
}
