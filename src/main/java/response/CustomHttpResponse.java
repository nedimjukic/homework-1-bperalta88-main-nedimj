package response;

import handler.GsonTool;

import java.util.Map;
import java.util.Map.Entry;

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
  // just fill the response as it's written in the article from the link
  public String toString(){
    StringBuilder stringBuilder = new StringBuilder();

    // append version and status
    stringBuilder.append(version).append(" ").append(status).append("\n");

    // append all the headers
    for(Entry<String, String> entry: headers.entrySet()){
      String key = entry.getKey();
      String value = entry.getValue();

      String header = key + ": " + value;

      stringBuilder.append(header).append("\n");
    }

    // add blank line
    stringBuilder.append("\n");

    // at the end add the body
    stringBuilder.append(GsonTool.gson.toJson(body));

    return stringBuilder.toString();
  }
}
