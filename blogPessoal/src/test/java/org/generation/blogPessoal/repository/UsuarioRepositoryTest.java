package org.generation.blogPessoal.repository;

import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.generation.blogPessoal.model.Usuario;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) //o Spring irá atribuir uma outra porta automaticamente.
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@BeforeAll
	void start() {
		
		usuarioRepository.deleteAll();
		
		usuarioRepository.save(new Usuario(0L, "João da Silva", "joao@email.com.br", "13578966", "https://i.imgur.com/JR7kUFU.jpg"));
		
		usuarioRepository.save(new Usuario(0L, "Victor Hugo", "victor@email.com.br", "78988745", "https://i.imgur.com/JR7kUFU.jpg"));
		
		usuarioRepository.save(new Usuario(0L, "Eloise Silva", "EloSilva@email.com.br", "11223344", "https://i.imgur.com/JR7kUFU.jpg"));
		
		usuarioRepository.save(new Usuario(0L, "Maria Luiza Silva", "Malu@email.com.br", "55577784", "https://i.imgur.com/JR7kUFU.jpg"));
	}
	
	@Test
	@DisplayName("Retornar 1 usuário")
	public void deveRetornarUmUsuario() {
		
		Optional<Usuario> usuario = usuarioRepository.findByUsuario("joao@email.com.br");
		assertTrue(usuario.get().getUsuario().equals("joao@email.com.br"));
	}
	
	@Test
	@DisplayName("Retornar 3 usuários")
	public void deveRetornarTresUsuarios() {
		
		List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Silva");
		assertEquals(3, listaDeUsuarios.size());
		assertTrue(listaDeUsuarios.get(0).getNome().equals("João da Silva"));
		assertTrue(listaDeUsuarios.get(1).getNome().equals("Eloise Silva"));
		assertTrue(listaDeUsuarios.get(2).getNome().equals("Maria Luiza Silva"));
	}
	
	@AfterAll
	public void end() {
		usuarioRepository.deleteAll();
	}

	
	
}
