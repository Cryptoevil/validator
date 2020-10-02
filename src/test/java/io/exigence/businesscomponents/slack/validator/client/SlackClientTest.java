package io.exigence.businesscomponents.slack.validator.client;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.exigence.businesscomponents.slack.validator.model.Channel;
import io.exigence.businesscomponents.slack.validator.model.Message;
import io.exigence.businesscomponents.slack.validator.response.SlackChannelResponse;
import io.exigence.businesscomponents.slack.validator.response.SlackMessageResponse;
import io.exigence.businesscomponents.slack.validator.web.WebClient;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SlackClientTest {

    private static final String BASE_URL = "https://slack.com/api/";
    private static final String TOKEN = "xoxb-1414096600673-1407714141028-0DIa4PToa9dfS0PqR6guumhj";

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void createChannelIsValidResponseTest() {
        WebClient webClient = mock(WebClient.class);
        SlackChannelResponse generatedResponse = generateChannelResponse();
        String generatedResponseJson = null;
        try {
            generatedResponseJson = this.objectMapper.writeValueAsString(generatedResponse);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        when(webClient.get(BASE_URL.concat("conversations.create"), Map.of(
                "token", TOKEN,
                "name", "testName",
                "is_private", "false"
        ))).thenReturn(generatedResponseJson);

        SlackClient slackClient = new SlackClient(webClient);
        SlackChannelResponse slackResponse = slackClient.createChannel("testName", false);

        assertEquals(generatedResponse, slackResponse);
    }

    @Test
    public void createChannelResponseIsNotNullTest() {
        WebClient webClient = mock(WebClient.class);
        SlackChannelResponse generatedResponse = generateChannelResponse();
        String generatedResponseJson = null;
        try {
            generatedResponseJson = this.objectMapper.writeValueAsString(generatedResponse);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        when(webClient.get(BASE_URL.concat("conversations.create"), Map.of(
                "token", TOKEN,
                "name", "testName",
                "is_private", "false"
        ))).thenReturn(generatedResponseJson);

        SlackClient slackClient = new SlackClient(webClient);
        SlackChannelResponse slackResponse = slackClient.createChannel("testName", false);

        assertNotNull(slackResponse);
    }

    @Test
    public void postMessageIsValidResponseTest() {
        WebClient webClient = mock(WebClient.class);
        SlackMessageResponse generatedResponse = generateMessageResponse();
        String generatedResponseJson = null;
        try {
            generatedResponseJson = this.objectMapper.writeValueAsString(generatedResponse);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        when(webClient.get(BASE_URL.concat("chat.postMessage"), Map.of(
                "token", TOKEN,
                "channel", "testChannel",
                "text", "testText"))).thenReturn(generatedResponseJson);

        SlackClient slackClient = new SlackClient(webClient);
        SlackMessageResponse slackResponse = slackClient.postMessage("testChannel", "testText");

        assertEquals(generatedResponse, slackResponse);
    }

    @Test
    public void postMessageResponseIsNotNullTest() {
        WebClient webClient = mock(WebClient.class);
        SlackMessageResponse generatedResponse = generateMessageResponse();
        String generatedResponseJson = null;
        try {
            generatedResponseJson = this.objectMapper.writeValueAsString(generatedResponse);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        when(webClient.get(BASE_URL.concat("chat.postMessage"), Map.of(
                "token", TOKEN,
                "channel", "testChannel",
                "text", "testText"))).thenReturn(generatedResponseJson);

        SlackClient slackClient = new SlackClient(webClient);
        SlackMessageResponse slackResponse = slackClient.postMessage("testChannel", "testText");

        assertNotNull(slackResponse);
    }

    @Test
    public void archiveChannelIsValidResponseTest() {
        WebClient webClient = mock(WebClient.class);
        SlackChannelResponse generatedResponse = generateChannelResponse();
        String generatedResponseJson = null;
        try {
            generatedResponseJson = this.objectMapper.writeValueAsString(generatedResponse);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        when(webClient.get(BASE_URL.concat("conversations.archive"), Map.of(
                "token", TOKEN,
                "channel", "testChannel"
        ))).thenReturn(generatedResponseJson);

        SlackClient slackClient = new SlackClient(webClient);
        SlackChannelResponse slackResponse = slackClient.archiveChannel("testChannel");

        assertEquals(generatedResponse, slackResponse);
    }

    @Test
    public void archiveChannelResponseIsNotNullTest() {
        WebClient webClient = mock(WebClient.class);
        SlackChannelResponse generatedResponse = generateChannelResponse();
        String generatedResponseJson = null;
        try {
            generatedResponseJson = this.objectMapper.writeValueAsString(generatedResponse);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        when(webClient.get(BASE_URL.concat("conversations.archive"), Map.of(
                "token", TOKEN,
                "channel", "testChannel"
        ))).thenReturn(generatedResponseJson);

        SlackClient slackClient = new SlackClient(webClient);
        SlackChannelResponse slackResponse = slackClient.archiveChannel("testChannel");

        assertNotNull(slackResponse);
    }

    private SlackChannelResponse generateChannelResponse() {
        Channel channel = new Channel();
        channel.setId("testId");
        channel.setName("testName");

        SlackChannelResponse response = new SlackChannelResponse();
        response.setOk(true);
        response.setChannel(channel);

        return response;
    }

    private SlackMessageResponse generateMessageResponse() {
        Message message = new Message();
        message.setText("testText");
        message.setUsername("testUsername");

        SlackMessageResponse response = new SlackMessageResponse();
        response.setOk(true);
        response.setChannel("testChannel");
        response.setMessage(message);

        return response;
    }
}
