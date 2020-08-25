package uk.co.datumedge.bpdts.test.system.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.OK;

public class RestSteps {
    private static final String ROOT_URL = "http://localhost:8080";
    private final TestRestTemplate client = new TestRestTemplate();
    private String responseBody;

    @When("I GET $path")
    public void get(String path) {
        ResponseEntity<String> responseEntity = this.client.getForEntity(ROOT_URL + path, String.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(OK);
        responseBody = responseEntity.getBody();
    }

    @Then("the JSON response body is: $expectedResponse")
    public void assertJSONResponseBody(String expectedResponse) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode expectedJson = objectMapper.readTree(expectedResponse);
        JsonNode actualJson = objectMapper.readTree(responseBody);

        assertThat(actualJson).isEqualTo(expectedJson);
    }
}