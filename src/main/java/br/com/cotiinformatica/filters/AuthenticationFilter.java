package br.com.cotiinformatica.filters;

import java.io.IOException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.servlet.support.RequestContextUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Slf4j
public class AuthenticationFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		final HttpServletRequest request = (HttpServletRequest) servletRequest;
		final HttpServletResponse response = (HttpServletResponse) servletResponse;
		final String authHeader = request.getHeader("Authorization");

		if ("OPTIONS".equals(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
			filterChain.doFilter(request, response);
		} else {
			if (authHeader == null || !authHeader.startsWith("Bearer ")) {
				log.warn("Tentativa de acesso sem token ou com token inválido");
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Acesso não autorizado.");
			} else {
				Environment env = RequestContextUtils.findWebApplicationContext(request).getEnvironment();
				String jwtSecret = env.getProperty("jwt.secret");

				final String token = authHeader.substring(7);
				Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
				request.setAttribute("claims", claims);
				filterChain.doFilter(request, response);
			}
		}
	}
}
