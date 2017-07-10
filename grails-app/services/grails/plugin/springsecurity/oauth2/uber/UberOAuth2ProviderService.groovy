package grails.plugin.springsecurity.oauth2.uber

import com.github.scribejava.core.builder.api.DefaultApi20
import com.github.scribejava.core.model.OAuth2AccessToken
import com.github.scribejava.core.model.OAuthConstants
import com.github.scribejava.core.model.OAuthRequest
import com.github.scribejava.core.model.Verb
import grails.converters.JSON
import grails.plugin.springsecurity.oauth2.exception.OAuth2Exception
import grails.plugin.springsecurity.oauth2.service.OAuth2AbstractProviderService
import grails.plugin.springsecurity.oauth2.token.OAuth2SpringToken
import grails.transaction.Transactional

@Transactional
class UberOAuth2ProviderService extends OAuth2AbstractProviderService {

    @Override
    String getProviderID() {
        return 'uber'
    }

    @Override
    Class<? extends DefaultApi20> getApiClass() {
        Uber2Api.class
    }

    @Override
    String getProfileScope() {
        return "https://api.uber.com/v1.2/me"
    }

    @Override
    String getScopes() {
        return "history profile places"
    }

    @Override
    String getScopeSeparator() {
        return " "
    }

    @Override
    OAuth2SpringToken createSpringAuthToken(OAuth2AccessToken accessToken) {
        def user
//        def response = getResponse(accessToken)
        OAuthRequest oAuthRequest = new OAuthRequest(Verb.GET, getProfileScope(), authService)
        oAuthRequest.addHeader(OAuthConstants.HEADER,
                'Bearer '
                        + accessToken.accessToken);
        def response =  oAuthRequest.send()
        try {
            println "JSON response body: " + accessToken.rawResponse
            user = JSON.parse(response.body)
            println "Usuário: $user"
            println "Response: $response.body"
        } catch (Exception exception) {
            log.error("Error parsing response from " + getProviderID() + ". Response:\n" + response.body)
            throw new OAuth2Exception("Error parsing response from " + getProviderID(), exception)
        }
        if (!user?.email) {
            log.error("No user email from " + getProviderID() + ". Response was:\n" + response.body)
            throw new OAuth2Exception("No user email from " + getProviderID())
        }
        new UberOauth2SpringToken(accessToken, user?.email, providerID)
    }
}
