package com.blogpessoal.blogPessoal.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.blogpessoal.blogPessoal.model.Usuario;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UsuarioControllerTest {
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	private Usuario usuario;
	private Usuario usuarioAlterar;
	
	@BeforeAll
	public void start() {
		usuario = new Usuario("RafaBoaz", "1234567");
		usuarioAlterar = new Usuario("RafaBoaz", "432167");
	}
	
	@Disabled
	@Test
	void deveSalvarUsuarioRetornaStatus201() {
		HttpEntity<Usuario> request = new HttpEntity<Usuario>(usuario);
		ResponseEntity<Usuario> resp = testRestTemplate.exchange("/usuario/logar", HttpMethod.POST, request, Usuario.class);
		assertEquals(HttpStatus.CREATED, resp.getStatusCode());
	}

	@Disabled
	@Test
	void deveRetornarListadeUsuarioRetornaStatus200() {
		ResponseEntity<String> resp = testRestTemplate.withBasicAuth("RafaBoaz", "1234567")
				.exchange("usuarios", HttpMethod.GET, null, String.class);
		assertEquals(HttpStatus.OK, resp.getStatusCode());
	}
	
	@Test
	void deveAtualizarSenhaUsuarioRetornaStatus201() {
		HttpEntity<Usuario> request = new HttpEntity<Usuario>(usuarioAlterar);
		ResponseEntity<Usuario> resp = testRestTemplate.withBasicAuth("RafaBoaz", "1234567")
				.exchange("usuario/alterar", HttpMethod.PUT, request, Usuario.class);
		assertEquals(HttpStatus.CREATED, resp.getStatusCode());
	}
	

}
