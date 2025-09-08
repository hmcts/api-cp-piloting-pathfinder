package uk.gov.hmcts.cp.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static uk.gov.hmcts.cp.filter.RequestFilter.JWT_TOKEN_HEADER;

@ExtendWith(MockitoExtension.class)
class RequestFilterTest {
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    FilterChain filterChain;

    @InjectMocks
    RequestFilter requestFilter;

    @Test
    void shouldErrorIfNoJwtInHeader() throws ServletException, IOException {
        assertThrows(ResponseStatusException.class, () -> requestFilter.doFilterInternal(request, response, filterChain));
    }

    @Test
    void shouldPassThroughIfPassedJwt() throws ServletException, IOException {
        when(request.getHeader(JWT_TOKEN_HEADER)).thenReturn("dummy-token");
        requestFilter.doFilterInternal(request, response, filterChain);
        verify(filterChain).doFilter(request, response);
    }
}