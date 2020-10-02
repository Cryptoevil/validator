package io.exigence.businesscomponents.slack.validator.response;

import io.exigence.businesscomponents.slack.validator.model.Channel;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SlackChannelResponse {
    private boolean ok;
    private Channel channel;
    private String error;
}
