package request;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestRequestParserBody {

  @Test
  public void postTest(){
    String test1 = "POST /hello/world HTTP/1.1\n"
        + "Accept-Language: en-us\n"
        + "Accept-Encoding: gzip, deflate\n"
        + "Connection: Keep-Alive";
    ParsedRequest request = CustomParser.parse(test1);
    Assert.assertEquals(request.getPath(), "/hello/world");
    Assert.assertEquals(request.getMethod(), "POST");
  }

  @Test
  public void getBodyTest(){
    String random = String.valueOf(Math.random());
    String test1 = "POST /hello/world HTTP/1.1\n"
        + "Accept-Language: en-us\n"
        + "Accept-Encoding: gzip, deflate\n"
        + "Connection: Keep-Alive\n"
        + "\n"
        + random;
    ParsedRequest request = CustomParser.parse(test1);
    Assert.assertEquals(request.getBody(), random);
    Assert.assertEquals(request.getMethod(), "POST");
  }
}
