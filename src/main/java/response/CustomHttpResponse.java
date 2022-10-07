package response;

import java.util.Map;

public class CustomHttpResponse {
  public final Map<String,String> headers;
  public final String status;
  public final String version;
  public final RestApiAppResponse body;

  public CustomHttpResponse(Map<String, String> headers, String status, String version,
      RestApiAppResponse body) {
    this.headers = headers;
    this.status = status;
    this.version = version;
    this.body = body;
  }

  // TODO fill this out
  public String toString(){
    return null;
  }
}
