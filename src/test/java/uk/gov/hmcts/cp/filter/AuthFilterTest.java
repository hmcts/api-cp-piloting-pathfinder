package uk.gov.hmcts.cp.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static uk.gov.hmcts.cp.filter.AuthFilter.JWT_TOKEN_HEADER;

@ExtendWith(MockitoExtension.class)
class AuthFilterTest {
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    FilterChain filterChain;

    AuthFilter authFilter = new AuthFilter(true);

    @Test
    void authFilter_should_pass_if_disabled() throws ServletException, IOException {
        AuthFilter authFilter = new AuthFilter(false);
        when(request.getRequestURI()).thenReturn("http://localhost");
        authFilter.doFilterInternal(request, response, filterChain);
        verify(filterChain).doFilter(request, response);
    }

    @Test
    void authFilter_should_fail_if_no_jwt_passed() {
        when(request.getRequestURI()).thenReturn("http://localhost");
        HttpClientErrorException e = assertThrows(HttpClientErrorException.class, () -> authFilter.doFilterInternal(request, response, filterChain));
        assertThat(e.getMessage()).isEqualTo("401 No jwt token passed");
    }

    @Test
    void authFilter_should_pass_if_passed_jwt() throws ServletException, IOException {
        when(request.getRequestURI()).thenReturn("http://localhost");
        when(request.getHeader(JWT_TOKEN_HEADER)).thenReturn("dummy-token");
        authFilter.doFilterInternal(request, response, filterChain);
        verify(filterChain).doFilter(request, response);
    }
}