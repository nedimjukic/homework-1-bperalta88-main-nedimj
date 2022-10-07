package request;

import org.testng.Assert;
import org.testng.annotations.Test;

public class BasicRequestTest {

  @Test
  public void basicTest(){
    String test1 = "GET /hello HTTP/1.1\n"
        + "User-Agent: Mozilla/4.0 (compatible; MSIE5.01; Windows NT)\n"
        + "Accept-Language: en-us\n"
        + "Accept-Encoding: gzip, deflate\n"
        + "Connection: Keep-Alive";
    ParsedRequest request = CustomParser.parse(test1);
    Assert.assertEquals(request.getPath(), "/hello");
    Assert.assertEquals(request.getMethod(), "GET");
  }

  @Test
  public void basicTest2(){
    String random1 = String.valueOf(Math.random());
    String random2 = String.valueOf(Math.random());
    String test1 = random1 + " /" + random2 + " HTTP/1.1\n"
        + "User-Agent: Mozilla/4.0 (compatible; MSIE5.01; Windows NT)\n"
        + "Accept-Language: en-us\n"
        + "Accept-Encoding: gzip, deflate\n"
        + "Connection: Keep-Alive";
    ParsedRequest request = CustomParser.parse(test1);
    Assert.assertEquals(request.getPath(), "/" + random2);
    Assert.assertEquals(request.getMethod(), random1);
  }

}
