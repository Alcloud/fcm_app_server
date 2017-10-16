package eu.credential.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;
import javax.validation.constraints.NotNull;

public class DataMessage {
    @JsonProperty("notificationId")
    private String notificationId = null;
    @JsonProperty("accountId")
    private String accountId = null;

    public DataMessage() {
    }

    public DataMessage notificationId(String notificationId) {
        this.notificationId = notificationId;
        return this;
    }

    @JsonProperty("notificationId")
    @ApiModelProperty(
            required = true,
            value = ""
    )
    @NotNull
    public String getNotificationId() {
        return this.notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    public DataMessage accountId(String accountId) {
        this.accountId = accountId;
        return this;
    }

    @JsonProperty("accountId")
    @ApiModelProperty(
            required = true,
            value = "accountId"
    )
    @NotNull
    public String getAccountId() {
        return this.accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public boolean equals(Object o) {
        if(this == o) {
            return true;
        } else if(o != null && this.getClass() == o.getClass()) {
            DataMessage dataMessage = (DataMessage)o;
            return Objects.equals(this.notificationId, dataMessage.notificationId) && Objects.equals(this.accountId, dataMessage.accountId);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.notificationId, this.accountId});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class KeyValue {\n");
        sb.append("    key: ").append(this.toIndentedString(this.notificationId)).append("\n");
        sb.append("    value: ").append(this.toIndentedString(this.accountId)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(Object o) {
        return o == null?"null":o.toString().replace("\n", "\n    ");
    }
}
