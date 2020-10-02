package io.exigence.businesscomponents.slack.validator.flow;

import io.exigence.businesscomponents.slack.validator.flow.step.ArchiveChannelStep;
import io.exigence.businesscomponents.slack.validator.flow.step.ChannelCreateStep;
import io.exigence.businesscomponents.slack.validator.flow.step.PostMessageStep;
import io.exigence.businesscomponents.slack.validator.flow.step.Step;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class FlowExecutorTest {

    private FlowExecutor flowExecutor;
    private ChannelCreateStep channelCreateStep;
    private PostMessageStep postMessageStep;
    private ArchiveChannelStep archiveChannelStep;

    @Before
    public void setUp() {
        this.flowExecutor = new FlowExecutor();
        this.channelCreateStep = mock(ChannelCreateStep.class);
        this.postMessageStep = mock(PostMessageStep.class);
        this.archiveChannelStep = mock(ArchiveChannelStep.class);
    }

    @Test
    public void stepsShouldBeInvokedTest() {
        List<Step> steps = new ArrayList<>();
        steps.add(this.channelCreateStep);
        steps.add(this.postMessageStep);
        steps.add(this.archiveChannelStep);

        this.flowExecutor.execute(steps);

        verify(this.channelCreateStep, times(1)).invoke();
        verify(this.postMessageStep, times(1)).invoke();
        verify(this.archiveChannelStep, times(1)).invoke();
    }
}
