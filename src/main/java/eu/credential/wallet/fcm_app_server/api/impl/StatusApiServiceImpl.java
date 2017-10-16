package eu.credential.wallet.fcm_app_server.api.impl;

import eu.credential.wallet.fcm_app_server.api.ApiResponseMessage;
import eu.credential.wallet.fcm_app_server.api.NotFoundException;
import eu.credential.wallet.fcm_app_server.api.StatusApiService;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-05-29T16:44:14.895+02:00")
public class StatusApiServiceImpl extends StatusApiService {
    @Override
    public Response statusGet(SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
}
