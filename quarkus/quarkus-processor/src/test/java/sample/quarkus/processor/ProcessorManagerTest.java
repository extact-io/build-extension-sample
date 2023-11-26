package sample.quarkus.processor;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.inject.Inject;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;

@QuarkusTest
@TestProfile(ProcessorManagerTest.ProcessorManagerTestProfile.class)
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

    @ApplicationScoped
    @Alternative
    public static class ImportantClassCheckerStub implements ImportantClassChecker {
        @Override
        public boolean isImportant(Class<?> clazz) {
            return true;
        }
    }

    @ApplicationScoped
    @Alternative
    public static class TestProcessorImpl1 implements Processor {
        @Logged
        @Override
        public void doWork() {
        }
    }

    @ApplicationScoped
    @Alternative
    public static class TestProcessorImpl2 implements Processor {
        @Override
        public void doWork() {
        }
    }

    public static class ProcessorManagerTestProfile implements QuarkusTestProfile {
        @Override
        public Set<Class<?>> getEnabledAlternatives() {
            return Set.of(ImportantClassCheckerStub.class,
                    TestProcessorImpl1.class,
                    TestProcessorImpl2.class);
        }
    }
}
