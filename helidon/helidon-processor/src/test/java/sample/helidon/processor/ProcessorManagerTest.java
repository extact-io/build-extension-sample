package sample.helidon.processor;

import static org.junit.jupiter.api.Assertions.*;

import jakarta.inject.Inject;

import org.junit.jupiter.api.Test;

import io.helidon.microprofile.testing.junit5.AddBean;
import io.helidon.microprofile.testing.junit5.AddBeans;
import io.helidon.microprofile.testing.junit5.HelidonTest;
import sample.helidon.processor.ProcessorManagerTest.ImportantClassCheckerStub;
import sample.helidon.processor.ProcessorManagerTest.TestProcessorImpl1;
import sample.helidon.processor.ProcessorManagerTest.TestProcessorImpl2;

@HelidonTest
@AddBeans({
        @AddBean(ImportantClassCheckerStub.class),
        @AddBean(TestProcessorImpl1.class),
        @AddBean(TestProcessorImpl2.class) })
class ProcessorManagerTest {

    @Inject
    ProcessorManager target;

    @Test
    void testProcessorSize() {
        assertEquals(2, target.all().size());
    }

    @Test
    void testDoWorkForLoggingAction() {
        target.all().forEach(Processor::doWork);
    }

    public static class ImportantClassCheckerStub implements ImportantClassChecker {
        @Override
        public boolean isImportant(Class<?> clazz) {
            return true;
        }
    }
    
    public static class TestProcessorImpl1 implements Processor {
        @Override
        public void doWork() {
        }
    }

    public static class TestProcessorImpl2 implements Processor {
        @Override
        public void doWork() {
        }
    }
}
