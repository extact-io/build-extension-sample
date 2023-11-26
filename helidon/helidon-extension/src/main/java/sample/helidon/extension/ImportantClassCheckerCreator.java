package sample.helidon.extension;

import jakarta.enterprise.inject.Instance;
import jakarta.enterprise.inject.build.compatible.spi.Parameters;
import jakarta.enterprise.inject.build.compatible.spi.SyntheticBeanCreator;

import sample.helidon.processor.ImportantClassCheckerImpl;

import java.util.Set;

public class ImportantClassCheckerCreator implements SyntheticBeanCreator<ImportantClassCheckerImpl> {
    @Override
    public ImportantClassCheckerImpl create(Instance<Object> lookup, Parameters params) {
        String[] importantProcessors = params.get("importantProcessors", String[].class);
        return new ImportantClassCheckerImpl(Set.of(importantProcessors));
    }
}
