package grails.plugin.springsecurity.oauth2.uber

import com.github.scribejava.core.builder.api.DefaultApi20

class Uber2Api extends DefaultApi20 {

    private static final String AUTHORIZATION_BASE_URL = "https://www.linkedin.com/oauth/v2/authorization"

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
        return "https://www.linkedin.com/oauth/v2/accessToken";
    }

    @Override
    protected String getAuthorizationBaseUrl() {
        return AUTHORIZATION_BASE_URL
    }
}
