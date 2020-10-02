package io.exigence.businesscomponents.slack.validator.flow.step;

import io.exigence.businesscomponents.slack.validator.client.SlackClient;
import io.exigence.businesscomponents.slack.validator.flow.Properties;
import io.exigence.businesscomponents.slack.validator.response.SlackMessageResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class PostMessageStep implements Step {

    private SlackClient slackClient;

    @Override
    public void invoke() {
        SlackMessageResponse messageResponse = slackClient.postMessage(Properties.getString("channelId"), "Test");
        log.info("invoke -> PostMessageStep result: {}", messageResponse);
    }
}
