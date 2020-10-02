package io.exigence.businesscomponents.slack.validator.flow.step;

import io.exigence.businesscomponents.slack.validator.client.SlackClient;
import io.exigence.businesscomponents.slack.validator.flow.Properties;
import io.exigence.businesscomponents.slack.validator.model.Channel;
import io.exigence.businesscomponents.slack.validator.response.SlackChannelResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class ChannelCreateStep implements Step {

    private SlackClient slackClient;
    private String channelName;

    @Override
    public void invoke() {
        SlackChannelResponse response = this.slackClient.createChannel(this.channelName, false);
        log.info("invoke -> ChannelCreateStep result: {}", response);

        Channel channel = response.getChannel();
        Properties.putString("channelId", channel.getId());
    }
}
