package response;

import dto.MessageDto;
import handler.GsonTool;
import java.util.List;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CustomHttpResponseTest {

  @Test
  public void testHttpRes(){
    String randKey = String.valueOf(Math.random());
    String rand = String.valueOf(Math.random());
    MessageDto messageDto =  new MessageDto(String.valueOf(Math.random()));
    messageDto.setMessage(String.valueOf(Math.random()));
    RestApiAppResponse response = new RestApiAppResponse(true,
        List.of(messageDto), String.valueOf(Math.random()));
    CustomHttpResponse res = new ResponseBuilder()
        .setStatus("200 Ok").setVersion("HTTP/1.1")
        .setBody(response)
        .setHeaders(Map.of(randKey, rand))
        .build();
    Assert.assertEquals(res.toString(), "HTTP/1.1 200 Ok\n"
        + randKey + ": " + rand +"\n\n" + GsonTool.gson.toJson(response));
  }

}
