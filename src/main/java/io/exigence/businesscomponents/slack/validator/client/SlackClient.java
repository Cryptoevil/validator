package io.exigence.businesscomponents.slack.validator.client;

import io.exigence.businesscomponents.slack.validator.web.WebClient;

public class SlackClient {

    private WebClient webClient;

    public SlackClient(WebClient webClient) {
        this.webClient = webClient;
    }
}
