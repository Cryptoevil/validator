package io.exigence.businesscomponents.slack.validator.flow;

import io.exigence.businesscomponents.slack.validator.flow.step.Step;

import java.util.List;

public class FlowExecutor {

    public void execute(List<Step> steps) {
        steps.forEach(Step::invoke);
    }
}
