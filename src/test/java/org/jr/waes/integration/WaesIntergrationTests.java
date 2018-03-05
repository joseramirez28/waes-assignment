package org.jr.waes.integration;

import java.util.HashMap;
import java.util.Map;

import org.jr.waes.WaesRunner;
import org.json.JSONException;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Integration tests for the Microservice to test the addition of binary data
 * and the diff
 * 
 * @author Jose Ramirez
 *
 */
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest(classes = WaesRunner.class, webEnvironment = WebEnvironment.DEFINED_PORT)
public class WaesIntergrationTests {

	@LocalServerPort
	private int port;

	// JSON from Java objects
	public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	// Test RestTemplate to invoke the API.
	private TestRestTemplate restTemplate = new TestRestTemplate();

	/**
	 * Prepare a URL for the Tests with the configured port
	 * 
	 * @param uri
	 *            path for the request
	 * @return Complete URL
	 */
	private String createURL(final String uri) {
		return "http://localhost:" + port + uri;
	}

	@Test
	public void testLeftEndpoint() throws JsonProcessingException, JSONException {

		// Building the Request body data
		Map<String, Object> requestBody = new HashMap<>();
		requestBody.put("id", "100");
		requestBody.put("base64Data", "dGhpcyBpcyBhIHRlc3QNCg==");
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);

		// Creating http entity object with request body and headers
		HttpEntity<String> httpEntity = new HttpEntity<String>(OBJECT_MAPPER.writeValueAsString(requestBody),
				requestHeaders);

		// Invoking the API
		ResponseEntity<String> apiResponse = restTemplate.exchange(createURL("/v1/diff/100/left"), HttpMethod.POST,
				httpEntity, String.class);

		String expected = "{\"status\": \"Left data added correctly\" }";

		JSONAssert.assertEquals(expected, apiResponse.getBody(), false);
	}

	@Test
	public void testRightEndpoint() throws JsonProcessingException, JSONException {
		// Building the Request body data
		Map<String, Object> requestBody = new HashMap<>();
		requestBody.put("id", "100");
		requestBody.put("base64Data", "dGhpcyBpcyBhIHRlc3QNCg==");
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);

		// Creating http entity object with request body and headers
		HttpEntity<String> httpEntity = new HttpEntity<String>(OBJECT_MAPPER.writeValueAsString(requestBody),
				requestHeaders);

		// Invoking the API
		ResponseEntity<String> apiResponse = restTemplate.exchange(createURL("/v1/diff/100/right"), HttpMethod.POST,
				httpEntity, String.class);

		String expected = "{\"status\": \"Right data added correctly\" }";

		JSONAssert.assertEquals(expected, apiResponse.getBody(), false);
	}

	@Test
	public void testZDiffEndpoint() throws JsonProcessingException, JSONException {
		// Building the Request body data
		Map<String, Object> requestBody = new HashMap<>();
		requestBody.put("id", "100");

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);

		// Creating http entity object with request body and headers
		HttpEntity<String> httpEntity = new HttpEntity<String>(OBJECT_MAPPER.writeValueAsString(requestBody),
				requestHeaders);

		// Invoking the API
		ResponseEntity<String> apiResponse = restTemplate.exchange(createURL("/v1/diff/100"), HttpMethod.GET,
				httpEntity, String.class);

		String expected = "{match: true, sizeMismatch: false, mismatchOffsets: null}";

		JSONAssert.assertEquals(expected, apiResponse.getBody(), false);
	}

}
