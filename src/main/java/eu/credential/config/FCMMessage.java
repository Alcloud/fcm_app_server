package eu.credential.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.NotNull;

public class FCMMessage {
    @JsonProperty("data")
    private List<DataMessage> data = new ArrayList<>();
    @JsonProperty("to")
    private String appId = null;

    public FCMMessage() {
    }

    public FCMMessage data(List<DataMessage> strings) {
        this.data = strings;
        return this;
    }

    @JsonProperty("data")
    @ApiModelProperty("")
    public List<DataMessage> getData() {
        return this.data;
    }
    public void setData(List<DataMessage> strings) {
        this.data = strings;
    }

    public FCMMessage appId (String string) {
        this.appId = string;
        return this;
    }

    @JsonProperty("appId")
    @ApiModelProperty("")
    @NotNull
    public String getAppId(){
        return this.appId;
    }
    public void setAppId(String string) {
        this.appId = string;
    }

    public boolean equals(Object o) {
        if(this == o) {
            return true;
        } else if(o != null && this.getClass() == o.getClass()) {
            FCMMessage fcmMessage = (FCMMessage)o;
            return Objects.equals(this.data, fcmMessage.data) && Objects.equals(this.appId, fcmMessage.appId);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.data, this.appId});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Notification {\n");
        sb.append("    data: ").append(this.toIndentedString(this.data)).append("\n");
        sb.append("    appId: ").append(this.toIndentedString(this.appId)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(Object o) {
        return o == null?"null":o.toString().replace("\n", "\n    ");
    }
}
