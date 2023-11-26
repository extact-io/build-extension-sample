package sample.helidon.app;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import sample.helidon.processor.Processor;
import sample.helidon.processor.ProcessorManager;

@Path("/processors")
public class ProcessResource {
    private final ProcessorManager processors;
    
    @Inject
    public ProcessResource(ProcessorManager processors) {
        this.processors = processors;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String doProcesses() {
        processors.all().forEach(Processor::doWork);
        return "success";
    }
}