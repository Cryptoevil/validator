package io.exigence.businesscomponents.slack.validator;

import io.exigence.businesscomponents.slack.validator.web.WebClient;
import lombok.SneakyThrows;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.HttpURLConnection;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class WebClientTest {

    private static final String RESPONSE_BODY = "{\"ok\":\"true\"}";

    private WebClient webClient;
    private MockWebServer mockWebServer;

    @Before
    @SneakyThrows
    public void setUp() {
        this.webClient = new WebClient();
        this.mockWebServer = new MockWebServer();
        this.mockWebServer.start(8181);
    }

    @After
    @SneakyThrows
    public void tearDown() {
        this.mockWebServer.shutdown();
    }

    @Test
    @SneakyThrows
    public void getRequestResponseIsNotNullTest() {
        MockResponse mockResponse = new MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody("{\"ok\":\"true\"}");
        this.mockWebServer.url("/test");
        this.mockWebServer.enqueue(mockResponse);

        String response = this.webClient.get("http://127.0.0.1:8181/test");

        assertNotNull(response);
    }

    @Test
    @SneakyThrows
    public void getRequestIsValidResponseTest() {
        MockResponse mockResponse = new MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody("{\"ok\":\"true\"}");
        this.mockWebServer.url("/test");
        this.mockWebServer.enqueue(mockResponse);

        String response = this.webClient.get("http://127.0.0.1:8181/test");

        assertEquals(RESPONSE_BODY, response);
    }

    @Test
    public void getRequestWithQueryParametersResponseIsNotNullTest() {
        MockResponse mockResponse = new MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody("{\"ok\":\"true\"}");
        this.mockWebServer.url("/test?param=true");
        this.mockWebServer.enqueue(mockResponse);

        String response = this.webClient.get("http://127.0.0.1:8181/test", Map.of("param", "true"));

        assertNotNull(response);
    }

    @Test
    public void getRequestValidQueryParametersTest() {
        MockResponse mockResponse = new MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody("{\"ok\":\"true\"}");
        this.mockWebServer.url("/test?param=true");
        this.mockWebServer.enqueue(mockResponse);

        String response = this.webClient.get("http://127.0.0.1:8181/test", Map.of("param", "true"));

        assertEquals(RESPONSE_BODY, response);
    }
}
