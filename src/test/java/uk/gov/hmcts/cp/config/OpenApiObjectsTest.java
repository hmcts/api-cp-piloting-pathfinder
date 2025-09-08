package uk.gov.hmcts.cp.config;

import org.junit.jupiter.api.Test;
import uk.gov.hmcts.cp.openapi.model.Auth;
import uk.gov.hmcts.cp.openapi.model.ErrorResponse;

import static org.assertj.core.api.Assertions.assertThat;

public class OpenApiObjectsTest {

    @Test
    void shouldGenerateAuthObject() {
        Auth auth = Auth.builder().build();
        assertThat(auth).hasOnlyFields("userName");
    }

    @Test
    void shouldGenerateErrorResponseObject() {
        ErrorResponse errorResponse = ErrorResponse.builder().build();
        assertThat(errorResponse).hasOnlyFields("error",
                "details",
                "message",
                "timestamp",
                "traceId");
    }
}
