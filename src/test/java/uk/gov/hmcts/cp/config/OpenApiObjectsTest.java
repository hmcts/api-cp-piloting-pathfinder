package uk.gov.hmcts.cp.config;

import org.junit.jupiter.api.Test;
import uk.gov.hmcts.cp.openapi.api.JudgesApi;
import uk.gov.hmcts.cp.openapi.model.ErrorResponse;
import uk.gov.hmcts.cp.openapi.model.Judges;
import uk.gov.hmcts.cp.openapi.model.JudgesJudiciary;

import static org.assertj.core.api.Assertions.assertThat;

public class OpenApiObjectsTest {

    @Test
    void generated_error_response_should_have_expected_fields() {
        assertThat(ErrorResponse.class).hasDeclaredFields("error", "details", "message", "timestamp", "traceId");
    }

    @Test
    void generated_judges_should_have_expected_fields() {
        assertThat(Judges.class).hasDeclaredFields("judiciary");
    }

    @Test
    void generated_judges_judiciary_should_have_expected_fields() {
        assertThat(JudgesJudiciary.class).hasDeclaredFields("johTitle", "johNameSurname", "role");
    }

    @Test
    void generated_api_should_have_expected_methods() {
        assertThat(JudgesApi.PATH_GET_JUDGE_BY_ID).isEqualTo("/example/judges/{judge_id}");
        assertThat(JudgesApi.class).hasDeclaredMethods("getJudgeById");
    }
}
