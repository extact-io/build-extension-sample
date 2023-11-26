package sample.helidon.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sample.helidon.processor.Important;
import sample.helidon.processor.Processor;

@Important
public class MyProcessor implements Processor {
    private static final Logger log = LoggerFactory.getLogger(MyProcessor.class);
    @Override
    public void doWork() {
        log.info("Working really hard");
    }
}
