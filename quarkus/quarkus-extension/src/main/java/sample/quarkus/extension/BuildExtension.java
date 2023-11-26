package sample.quarkus.extension;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.build.compatible.spi.BeanInfo;
import jakarta.enterprise.inject.build.compatible.spi.BuildCompatibleExtension;
import jakarta.enterprise.inject.build.compatible.spi.ClassConfig;
import jakarta.enterprise.inject.build.compatible.spi.Discovery;
import jakarta.enterprise.inject.build.compatible.spi.Enhancement;
import jakarta.enterprise.inject.build.compatible.spi.Messages;
import jakarta.enterprise.inject.build.compatible.spi.Registration;
import jakarta.enterprise.inject.build.compatible.spi.ScannedClasses;
import jakarta.enterprise.inject.build.compatible.spi.Synthesis;
import jakarta.enterprise.inject.build.compatible.spi.SyntheticComponents;
import jakarta.enterprise.inject.build.compatible.spi.Validation;
import jakarta.enterprise.lang.model.declarations.ClassInfo;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sample.quarkus.processor.Important;
import sample.quarkus.processor.ImportantClassChecker;
import sample.quarkus.processor.ImportantClassCheckerImpl;
import sample.quarkus.processor.Logged;
import sample.quarkus.processor.Processor;

public class BuildExtension implements BuildCompatibleExtension {
    
    private static Logger log = LoggerFactory.getLogger(BuildExtension.class);
    private final Set<ClassInfo> processors = new HashSet<>();

    @Discovery
    public void discoverFrameworkClasses(ScannedClasses scan) {
        log.info("*** execute Discovery ***");
        Config config = ConfigProvider.getConfig();
        config.getOptionalValue("sample.app.processer.class", String[].class)
                .ifPresent(values -> Stream.of(values).forEach(scan::add));
    }

    @Enhancement(types = Processor.class, withSubtypes = true)
    public void addInterceptorBindingToProcessors(ClassConfig clazz) {
        log.info("*** execute Enhancement ***");
        clazz.addAnnotation(ApplicationScoped.class);
        clazz.methods()
                .stream()
                .filter(it -> it.info().name().equals("doWork") && it.info().parameters().isEmpty())
                .forEach(it -> it.addAnnotation(Logged.class));
    }

    @Registration(types = Processor.class)
    public void rememberProcessors(BeanInfo bean) {
        log.info("*** execute Registration ***");
        if (bean.isClassBean()) {
            processors.add(bean.declaringClass());
        }
    }

    @Synthesis
    public void registerImportanceImpl(SyntheticComponents synth) {
        log.info("*** execute Synthesis ***");
        String[] importantProcessors = processors.stream()
                .filter(it -> it.hasAnnotation(Important.class))
                .map(ClassInfo::name)
                .toArray(String[]::new);

        synth.addBean(ImportantClassCheckerImpl.class)
                .type(ImportantClassChecker.class)
                .withParam("importantProcessors", importantProcessors)
                .createWith(ImportantClassCheckerCreator.class);
    }

    @Validation
    public void validateProcessors(Messages msg) {
        log.info("*** execute Validation ***");
        if (processors.isEmpty()) {
            msg.error("At least one `Processor` implementation must exist");
        }
    }
}
