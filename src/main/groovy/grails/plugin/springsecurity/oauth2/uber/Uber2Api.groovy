package grails.plugin.springsecurity.oauth2.uber

import com.github.scribejava.core.builder.api.DefaultApi20

class Uber2Api extends DefaultApi20 {

    private static final String AUTHORIZATION_BASE_URL = "https://login.uber.com/oauth/v2/authorize"

    protected Uber2Api() {
    }

    private static class InstanceHolder {

        private static final Uber2Api INSTANCE = new Uber2Api();
    }

    public static Uber2Api instance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    String getAccessTokenEndpoint() {
        return "https://login.uber.com/oauth/v2/token";
    }

    @Override
    protected String getAuthorizationBaseUrl() {
        return AUTHORIZATION_BASE_URL
    }
}
