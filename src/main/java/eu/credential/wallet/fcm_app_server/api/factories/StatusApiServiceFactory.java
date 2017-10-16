package eu.credential.wallet.fcm_app_server.api.factories;

import eu.credential.wallet.fcm_app_server.api.StatusApiService;
import eu.credential.wallet.fcm_app_server.api.impl.StatusApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-05-29T16:44:14.895+02:00")
public class StatusApiServiceFactory {
    private final static StatusApiService service = new StatusApiServiceImpl();

    public static StatusApiService getStatusApi() {
        return service;
    }
}
