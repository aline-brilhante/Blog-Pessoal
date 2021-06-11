package com.blogpessoal.blogPessoal.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.blogpessoal.blogPessoal.model.Usuario;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository repository;
	
	@BeforeAll
	void start() {
		Usuario usuario = new Usuario("VagnerBoaz", "123872");
		if(repository.findByUsuario(usuario.getUsuario())!=null)
			repository.save(usuario);
		
		usuario = new Usuario("LucasBoaz", "123872");
		if(repository.findByUsuario(usuario.getUsuario())!=null)
			repository.save(usuario);
		
		usuario = new Usuario("MarceloBoaz", "123872");
		if(repository.findByUsuario(usuario.getUsuario())!=null)
			repository.save(usuario);	
		}
	
	@Test
	public void findByUsuarioRetornaUsuario() throws Exception {

		Usuario usuario = repository.findByUsuario("VagnerBoaz").get();
		assertTrue(usuario.getUsuario().equals("VagnerBoaz"));
	}
	
	@Test
	public void findAllByUsuarioContainingIgnoreCaseRetornaTresContato() {

		List<Usuario> listaDeUsuarios = repository.findAllByUsuarioContainingIgnoreCase("Boaz");
		assertEquals(3, listaDeUsuarios.size());
	}
	
	@AfterAll
	public void end() {
		repository.deleteAll();
	}

}
