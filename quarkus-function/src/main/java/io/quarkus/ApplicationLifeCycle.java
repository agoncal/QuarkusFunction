package io.quarkus;

import io.quarkus.runtime.StartupEvent;
import io.quarkus.runtime.configuration.ProfileManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
public class ApplicationLifeCycle {

    private final Logger logger = LoggerFactory.getLogger(ApplicationLifeCycle.class);

    void onStart(@Observes StartupEvent ev) {
        logger.info("The application is starting with profile [{}].", ProfileManager.getActiveProfile());
    }
}
