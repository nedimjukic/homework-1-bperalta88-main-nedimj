package dto;

import java.time.Instant;

public class MessageDto extends BaseDto{

  private String fromId;
  private String toId;
  private String message;
  private Long timestamp;

  public MessageDto(){
    timestamp = Instant.now().toEpochMilli();
  }

  public MessageDto(String uniqueId) {
    super(uniqueId);
    timestamp = Instant.now().toEpochMilli();
  }

  public String getFromId() {
    return fromId;
  }

  public void setFromId(String fromId) {
    this.fromId = fromId;
  }

  public String getToId() {
    return toId;
  }

  public void setToId(String toId) {
    this.toId = toId;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
