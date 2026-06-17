package br.com.datatech.DataAgenda.security;

import br.com.datatech.DataAgenda.repository.TecnicoRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final TecnicoRepository tecnicoRepository;

    public SecurityFilter(TokenService tokenService, TecnicoRepository tecnicoRepository) {
        this.tokenService = tokenService;
        this.tecnicoRepository = tecnicoRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws IOException, ServletException {

        String token = this.getToken(request);
        if (token != null) {
            String email = tokenService.validarToken(token);
            UserDetails tecnico = tecnicoRepository.findByEmail(email);
            var authentication = new UsernamePasswordAuthenticationToken(tecnico, null, tecnico.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }


    private String getToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null ) return null;
        return authHeader.replace("Bearer ", "");
    }
}
