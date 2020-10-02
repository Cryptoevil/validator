package io.exigence.businesscomponents.slack.validator.response;

import io.exigence.businesscomponents.slack.validator.model.Message;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SlackMessageResponse {
    private boolean ok;
    private String channel;
    private Message message;
    private String error;
}
