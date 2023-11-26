package sample.helidon.processor;

import java.util.Collection;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;

@ApplicationScoped
public class ProcessorManager {
    private final Collection<Processor> processors;

    @Inject
    public ProcessorManager(Instance<Processor> processors) {
        this.processors = processors.stream().toList();
    }

    public Collection<Processor> all() {
        return processors;
    }
}
