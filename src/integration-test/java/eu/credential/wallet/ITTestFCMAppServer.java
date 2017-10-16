package eu.credential.wallet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import eu.credential.wallet.fcm_app_server.api.PersonalizationProperties;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.glassfish.jersey.client.ClientConfig;
import org.junit.Assert;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

public class ITTestFCMAppServer {

    protected static final Logger logger = LoggerFactory.getLogger(ITTestFCMAppServer.class);

    protected static String messageId;

    @Test(timeout = 10000)
    public void shouldHaveSendANewMessageToDevice() throws IOException {

    	ClientConfig cfg = new ClientConfig();
		cfg.register(JacksonJsonProvider.class);
		Client client = ClientBuilder.newClient(cfg);

        String fcmhost = PersonalizationProperties.getInstance()
                .getValue("fcm.host");
        String appId = PersonalizationProperties.getInstance()
                .getValue("appId");
        String authId = PersonalizationProperties.getInstance()
                .getValue("authId");

        WebTarget target = client.target("https://" + fcmhost )
				.path("fcm").path("send");
		Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON).header("Authorization", authId);

        String newMessage = "{\"notification\":{\"title\":\"Hans August\",\"body\":\"Access to document\"},\"to\":\"" + appId + "\"}";

        String response = invocation.post(Entity.entity(newMessage, MediaType.APPLICATION_JSON),
				String.class);

        logger.info("Response is " + response);

        ObjectMapper mapper = new ObjectMapper();
        TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() {
        };

        HashMap<String, Object> o = mapper.readValue(response, typeRef);

        List<Map<String, Object>> results = (List<Map<String, Object>>) o.get("results");

        for (Map<String, Object> message : results) {
            messageId = (String) message.get("message_id");
        }

        logger.info("The message id is " + messageId);

    }
}
