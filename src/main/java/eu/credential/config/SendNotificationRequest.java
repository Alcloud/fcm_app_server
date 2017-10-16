package eu.credential.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Objects;
import javax.validation.constraints.NotNull;

@ApiModel(
        description = "Request HTTP message"
)
public class SendNotificationRequest {
    @JsonProperty("data")
    private List<DataMessage> data = null;

    @JsonProperty("to")
    private String appId = null;

    public SendNotificationRequest() {
    }

    public SendNotificationRequest request(List<DataMessage> data, String appId) {
        this.data = data;
        this.appId = appId;
        return this;
    }

    @JsonProperty("to")
    @ApiModelProperty(
            required = true,
            value = ""
    )
    @NotNull
    public String getAppId() {
        return this.appId;
    }
    public void setAppId(String appId) {
        this.appId = appId;
    }

    @JsonProperty("to")
    @ApiModelProperty("")
    public List<DataMessage> getData() {
        return this.data;
    }
    public void setData(List<DataMessage> data) {
        this.data = data;
    }

    public boolean equals(Object o) {
        if(this == o) {
            return true;
        } else if(o != null && this.getClass() == o.getClass()) {
            SendNotificationRequest sendNotificationRequest = (SendNotificationRequest)o;
            return Objects.equals(this.appId, sendNotificationRequest.appId)  && Objects.equals(this.data, sendNotificationRequest.data);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.data, this.appId});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SendNotificationRequest {\n");
        sb.append("    data: ").append(this.toIndentedString(this.data)).append("\n");
        sb.append("    appId: ").append(this.toIndentedString(this.appId)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(Object o) {
        return o == null?"null":o.toString().replace("\n", "\n    ");
    }
}
