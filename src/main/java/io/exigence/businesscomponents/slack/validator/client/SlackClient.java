package io.exigence.businesscomponents.slack.validator.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.exigence.businesscomponents.slack.validator.response.SlackChannelResponse;
import io.exigence.businesscomponents.slack.validator.response.SlackMessageResponse;
import io.exigence.businesscomponents.slack.validator.web.WebClient;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class SlackClient {

    private static final String BASE_URL = "https://slack.com/api/";
    private static final String TOKEN = "xoxb-1414096600673-1407714141028-0DIa4PToa9dfS0PqR6guumhj";

    private WebClient webClient;
    private ObjectMapper objectMapper;

    public SlackClient(WebClient webClient) {
        this.webClient = webClient;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public SlackChannelResponse createChannel(String name, boolean isPrivate) {
        String response = this.webClient.get(BASE_URL.concat("conversations.create"), Map.of(
           "token", TOKEN,
           "name", name,
           "is_private", String.valueOf(isPrivate)
        ));

        SlackChannelResponse channelResponse = null;
        try {
            channelResponse = this.objectMapper.readValue(response, SlackChannelResponse.class);
        } catch (JsonProcessingException e) {
            log.warn("createChannel -> Json parse error: ", e);
        }
        return channelResponse;
    }

    public SlackMessageResponse postMessage(String channel, String text) {
        String response = this.webClient.get(BASE_URL.concat("chat.postMessage"), Map.of(
                "token", TOKEN,
                "channel", channel,
                "text", text));

        SlackMessageResponse messageResponse = null;
        try {
            messageResponse = this.objectMapper.readValue(response, SlackMessageResponse.class);
        } catch (JsonProcessingException e) {
            log.warn("postMessage -> Json parse error: ", e);
        }
        return messageResponse;
    }

    public SlackChannelResponse archiveChannel(String channel) {
        String response = this.webClient.get(BASE_URL.concat("conversations.archive"), Map.of(
                "token", TOKEN,
                "channel", channel
        ));

        SlackChannelResponse channelResponse = null;
        try {
            channelResponse = this.objectMapper.readValue(response, SlackChannelResponse.class);
        } catch (JsonProcessingException e) {
            log.warn("archiveChannel -> Json parse error: ", e);
        }
        return channelResponse;
    }
}
