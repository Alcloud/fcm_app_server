package eu.credential.config;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import eu.credential.wallet.notificationservice.ClientProperties;

import org.glassfish.jersey.client.ClientConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class FCMClient {

    protected static final Logger logger = LoggerFactory.getLogger(FCMClient.class);
    protected String fcmHost;
    protected String authId;

    public FCMClient() {
        this.fcmHost = ClientProperties.getInstance().getValue("fcm.host");
    }

    public FCMClient(String host, String authId) {
        this.fcmHost = host;
        this.authId = authId;
    }

    /*public SendNotificationResponse sendNotification(SendNotificationRequest body) throws NotFoundException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://" + this.fcmHost).path("fcm").path("send");
        Builder invocation = target.request(new String[]{"application/json"}).header("Authorization", authId);
        SendNotificationResponse response = (SendNotificationResponse)invocation.post(Entity.entity(body, "application/json"), SendNotificationResponse.class);
        return response;
    }*/

    public void sendNotification(String newMessage){
    	ClientConfig cfg = new ClientConfig();
		cfg.register(JacksonJsonProvider.class);
		Client client = ClientBuilder.newClient(cfg);

		WebTarget target = client.target("https://" + this.fcmHost)
				.path("fcm").path("send");
		Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON).header("Authorization", authId);

		String response = invocation.post(Entity.entity(newMessage, MediaType.APPLICATION_JSON),
				String.class);
		
        logger.info("Response is " + response);
    }
}
