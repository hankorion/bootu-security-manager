package com.bootu.security.web.session;

import com.bootu.security.core.properties.SecurityProperties;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import java.io.IOException;

public class BootuSessionExpiredSessionStrategy extends AbstractSessionStrategy implements SessionInformationExpiredStrategy {


    /**
     * @param securityPropertie
     */
    public BootuSessionExpiredSessionStrategy(SecurityProperties securityPropertie) {
        super(securityPropertie);
    }

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        onSessionInvalid(event.getRequest(), event.getResponse());
    }

    @Override
    protected boolean isConcurrency() {
        return true;
    }

}
