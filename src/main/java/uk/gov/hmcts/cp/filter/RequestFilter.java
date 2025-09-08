package uk.gov.hmcts.cp.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
@Slf4j
public class RequestFilter extends OncePerRequestFilter {
    public final static String JWT_TOKEN_HEADER = "jwt";

    @Value("${auth.filter.enabled:true}")
    private boolean authFilterEnabled;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("API RequestFilter enabled:{} called for url:{}", authFilterEnabled, request.getRequestURI());
        // We can pick off jwt token in here and validate it and then set the claims into an AuthContext object
        // For now lets just show that we can inject this filter into our parent service and do a simple check
        if (authFilterEnabled && request.getHeader(JWT_TOKEN_HEADER) == null) {
            log.error("API RequestFilter expected header {} not passed", JWT_TOKEN_HEADER);
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "No jwt token passed");
        }
        // Option1 we could use MDC as a global
        MDC.put("authUserName", "Demo Authorised User");

        // Option2 feels better we could use a request scoped bean

        filterChain.doFilter(request, response);
    }
}