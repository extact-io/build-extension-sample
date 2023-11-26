package sample.helidon.processor;

import java.util.Set;

public class ImportantClassCheckerImpl implements ImportantClassChecker {
    private final Set<String> importantClasses;

    public ImportantClassCheckerImpl(Set<String> importantClasses) {
        this.importantClasses = importantClasses;
    }

    @Override
    public boolean isImportant(Class<?> clazz) {
        return importantClasses.contains(clazz.getName());
    }
}
