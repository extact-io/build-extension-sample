package sample.helidon.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sample.helidon.processor.Processor;

public class AnotherProcessor implements Processor {
    private static final Logger log = LoggerFactory.getLogger(AnotherProcessor.class);
    @Override
    public void doWork() {
        log.info("Working barely enough");
    }
}
