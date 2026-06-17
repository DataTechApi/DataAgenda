package br.com.datatech.DataAgenda.security;

import br.com.datatech.DataAgenda.entity.Tecnico;
import br.com.datatech.DataAgenda.entity.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TokenServiceTest {

    @InjectMocks
    private TokenService tokenService;

    @BeforeEach
    void setUp() {
        // Injeta o valor do @Value("${jwt.secret}") manualmente no teste
        ReflectionTestUtils.setField(tokenService, "secret", "minha-chave-secreta-de-teste-123");
    }

    private Tecnico criarTecnicoFake() {
        Tecnico tecnico = new Tecnico();
        tecnico.setEmail("spring@spring");
        tecnico.setRole(Role.ADMIN); // ajuste conforme seu enum
        return tecnico;
    }

    @Test
    void deveGerarTokenComSucesso() {
        Tecnico tecnico = criarTecnicoFake();

        String token = tokenService.gerarToken(tecnico);

        assertNotNull(token);
        assertFalse(token.isEmpty());
    }

    @Test
    void tokenGeradoDeveTerTresParte() {
        // JWT sempre tem formato: header.payload.signature
        Tecnico tecnico = criarTecnicoFake();

        String token = tokenService.gerarToken(tecnico);

        assertEquals(3, token.split("\\.").length);
    }

    @Test
    void deveValidarTokenERetornarEmail() {
        Tecnico tecnico = criarTecnicoFake();
        String token = tokenService.gerarToken(tecnico);

        String emailRetornado = tokenService.validarToken(token);

        assertEquals("jackson@datatech.com", emailRetornado);
    }

    @Test
    void deveRetornarNullParaTokenInvalido() {
        String tokenInvalido = "token.invalido.qualquer";

        String resultado = tokenService.validarToken(tokenInvalido);

        assertNull(resultado);
    }

    @Test
    void deveRetornarNullParaTokenComSecretErrado() {
        // Gera token com secret correto
        Tecnico tecnico = criarTecnicoFake();
        String token = tokenService.gerarToken(tecnico);

        // Troca o secret para simular validação com chave diferente
        ReflectionTestUtils.setField(tokenService, "secret", "secret-diferente-errado");

        String resultado = tokenService.validarToken(token);

        assertNull(resultado);
    }

    @Test
    void deveRetornarNullParaTokenNulo() {
        String resultado = tokenService.validarToken(null);

        assertNull(resultado);
    }

    @Test
    void deveRetornarNullParaTokenVazio() {
        String resultado = tokenService.validarToken("");

        assertNull(resultado);
    }
}