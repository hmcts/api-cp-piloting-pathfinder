package uk.gov.hmcts.cp.config;

import org.junit.jupiter.api.Test;
import uk.gov.hmcts.cp.openapi.model.ErrorResponse;
import uk.gov.hmcts.cp.openapi.model.Judges;

import static org.assertj.core.api.Assertions.assertThat;

public class OpenApiObjectsTest {

    @Test
    void generateOpenApiModels_should_create_Judges() {
        Judges judges = Judges.builder().build();
        assertThat(judges).hasOnlyFields("judiciary");
    }

    @Test
    void generateOpenApiModels_should_create_ErrorResponse() {
        ErrorResponse errorResponse = ErrorResponse.builder().build();
        assertThat(errorResponse).hasOnlyFields("error",
                "details",
                "message",
                "timestamp",
                "traceId");
    }
}
