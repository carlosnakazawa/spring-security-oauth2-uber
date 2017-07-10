/**
 * Created by carlo on 03/07/2017.
 */
security {
    oauth2 {
        providers {
            uber {
                successUri = "/oauth2/uber/success"
                failureUri = "/oauth2/uber/failure"
                callback = "/oauth2/uber/callback"
                api_key = "changeme_apikey"
                api_secret = "changeme_apisecret"
            }
        }
    }
}
