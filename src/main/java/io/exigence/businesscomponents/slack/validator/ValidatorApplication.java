package io.exigence.businesscomponents.slack.validator;

import io.exigence.businesscomponents.slack.validator.client.SlackClient;
import io.exigence.businesscomponents.slack.validator.flow.FlowExecutor;
import io.exigence.businesscomponents.slack.validator.flow.step.ArchiveChannelStep;
import io.exigence.businesscomponents.slack.validator.flow.step.ChannelCreateStep;
import io.exigence.businesscomponents.slack.validator.flow.step.PostMessageStep;
import io.exigence.businesscomponents.slack.validator.flow.step.Step;
import io.exigence.businesscomponents.slack.validator.web.WebClient;

import java.util.ArrayList;
import java.util.List;

public class ValidatorApplication {

    public static void main(String[] args) {
        SlackClient slackClient = new SlackClient(new WebClient());
        FlowExecutor flowExecutor = new FlowExecutor();

        List<Step> steps = new ArrayList<>();
        steps.add(new ChannelCreateStep(slackClient, "channeltest"));
        steps.add(new PostMessageStep(slackClient));
        steps.add(new ArchiveChannelStep(slackClient));

        flowExecutor.execute(steps);
    }
}
