package io.exigence.businesscomponents.slack.validator.flow.step;

import io.exigence.businesscomponents.slack.validator.client.SlackClient;
import io.exigence.businesscomponents.slack.validator.flow.Properties;
import io.exigence.businesscomponents.slack.validator.response.SlackChannelResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class ArchiveChannelStep implements Step {

    private SlackClient slackClient;

    @Override
    public void invoke() {
        SlackChannelResponse response = this.slackClient.archiveChannel(Properties.getString("channelId"));
        log.info("invoke -> ArchiveChannelStep result: {}", response);
    }
}
