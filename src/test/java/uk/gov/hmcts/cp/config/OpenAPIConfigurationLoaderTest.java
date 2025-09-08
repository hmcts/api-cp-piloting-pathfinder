package uk.gov.hmcts.cp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


@Slf4j
class OpenAPIConfigurationLoaderTest {

    @Test
    void openAPI_bean_should_have_expected_properties() {
        OpenAPIConfigurationLoader config = new OpenAPIConfigurationLoader();
        OpenAPI openAPI = config.openAPI();
        assertNotNull(openAPI);

        Info info = openAPI.getInfo();
        assertNotNull(info);
        assertEquals("Piloting API", info.getTitle());
        assertEquals("A simple API for the experiments that will help shape the API strategy.", info.getDescription());

        String apiGitHubRepository = "api-cp-piloting-pathfinder";
        String expectedVersion = System.getProperty("API_SPEC_VERSION", "0.0.0");
        log.info("API version set to: {}", expectedVersion);

        assertEquals(expectedVersion, info.getVersion());

        License license = info.getLicense();
        assertNotNull(license);
        assertEquals("MIT", license.getName());
        assertEquals("https://opensource.org/licenses/MIT", license.getUrl());

        assertNotNull(info.getContact());
        assertEquals("no-reply@hmcts.com", info.getContact().getEmail());

        assertNotNull(openAPI.getServers());
        assertFalse(openAPI.getServers().isEmpty());
        assertEquals("https://virtserver.swaggerhub.com/HMCTS-DTS/" + apiGitHubRepository + "/" + expectedVersion,
                openAPI.getServers().get(0).getUrl());
    }

    @Test
    void loadOpenApiFromClasspath_should_throw_for_missing_resource() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                OpenAPIConfigurationLoader.loadOpenApiFromClasspath("nonexistent-file.yaml")
        );
        assertTrue(exception.getMessage().contains("Missing resource"));
    }

    @Test
    void loadOpenApiFromClasspath_should_throw_for_blank_path() {
        try {
            OpenAPIConfigurationLoader.loadOpenApiFromClasspath(" ");
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
    }

    @Test
    void loadOpenApiFromClasspath_should_throw_for_null_path() {
        try {
            OpenAPIConfigurationLoader.loadOpenApiFromClasspath(null);
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
    }
}