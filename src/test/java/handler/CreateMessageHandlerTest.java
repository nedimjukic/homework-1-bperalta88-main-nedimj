package handler;

import dao.MessageDao;
import dao.UserDao;
import dto.MessageDto;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import server.Server;

public class CreateMessageHandlerTest {

  @Test
  public void createMessageTest(){
    MessageDto messageDto = new MessageDto();
    messageDto.setMessage(String.valueOf(Math.random()));
    messageDto.setFromId(String.valueOf(Math.random()));
    messageDto.setToId(String.valueOf(Math.random()));

    String test1 = "POST /createMessage HTTP/1.1\n"
        + "Host: test\n"
        + "Connection: Keep-Alive\n"
        + "\n"
        + GsonTool.gson.toJson(messageDto);
    String response = Server.processRequest(test1);
    String[] responseParts = response.split("\n");
    Assert.assertEquals(responseParts[0], "HTTP/1.1 200 OK");
    List<MessageDto> messageDtos = MessageDao.getInstance().getAll();
    Assert.assertEquals(messageDtos.size(), 1);
    Assert.assertNotNull(messageDtos.get(0).getUniqueId());
    Assert.assertEquals(messageDtos.get(0).getMessage(), messageDto.getMessage());
  }

  // we need to clear the state of our storage after every test, so tests are not fake
  @AfterMethod
  public void clearState(){
    MessageDao.getInstance().clear();
    UserDao.getInstance().clear();
  }
}
