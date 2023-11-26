package sample.quarkus.extension;

import java.util.Set;

import jakarta.enterprise.inject.Instance;
import jakarta.enterprise.inject.build.compatible.spi.Parameters;
import jakarta.enterprise.inject.build.compatible.spi.SyntheticBeanCreator;

import sample.quarkus.processor.ImportantClassCheckerImpl;

public class ImportantClassCheckerCreator implements SyntheticBeanCreator<ImportantClassCheckerImpl> {
    @Override
    public ImportantClassCheckerImpl create(Instance<Object> lookup, Parameters params) {
        String[] importantProcessors = params.get("importantProcessors", String[].class);
        return new ImportantClassCheckerImpl(Set.of(importantProcessors));
    }
}
