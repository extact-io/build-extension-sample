package sample.quarkus.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sample.quarkus.processor.Important;
import sample.quarkus.processor.Processor;

@Important
public class MyProcessor implements Processor {
    private static final Logger log = LoggerFactory.getLogger(MyProcessor.class);

    @Override
    public void doWork() {
        log.info("Working really hard");
    }
}
