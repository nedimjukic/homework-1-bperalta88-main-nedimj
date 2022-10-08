package handler;

import com.google.gson.reflect.TypeToken;
import dao.MessageDao;
import dao.UserDao;
import dto.MessageDto;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import response.RestApiAppResponse;
import server.Server;

public class GetMessagesHandlerTest {

  @Test
  public void getMessagesTest1() {
    String fromUserId = String.valueOf(Math.random());
    String toUserId = String.valueOf(Math.random());

    String toUserId2 = String.valueOf(Math.random());

    String messageId1 = String.valueOf(Math.random());
    MessageDto messageDto1 = new MessageDto(messageId1);
    messageDto1.setToId(toUserId);
    messageDto1.setFromId(fromUserId);
    messageDto1.setMessage(String.valueOf(Math.random()));

    String messageId2 = String.valueOf(Math.random());
    MessageDto messageDto2 = new MessageDto(messageId2);
    messageDto2.setToId(toUserId2);
    messageDto2.setFromId(fromUserId);
    MessageDao messageDao = MessageDao.getInstance();

    messageDao.put(messageDto1);
    messageDao.put(messageDto2);

    String test1 = "GET /getMessages?fromId=" + fromUserId + "&toId=" + toUserId + " HTTP/1.1\n"
        + "Host: test\n"
        + "Connection: Keep-Alive\n"
        + "\n";
    System.out.println(test1);
    String response = Server.processRequest(test1);
    String[] responseParts = response.split("\n");
    Assert.assertEquals(responseParts[0], "HTTP/1.1 200 OK");
    RestApiAppResponse<MessageDto> messages = GsonTool.gson.fromJson(responseParts[3],
        new TypeToken<RestApiAppResponse<MessageDto>>() {
        }.getType());
    Assert.assertEquals(messages.data.size(), 1);
    Assert.assertEquals(messages.data.get(0).getUniqueId(), messageDto1.getUniqueId());
    Assert.assertEquals(messages.data.get(0).getToId(), messageDto1.getToId());
    Assert.assertEquals(messages.data.get(0).getFromId(), messageDto1.getFromId());
    Assert.assertEquals(messages.data.get(0).getMessage(), messageDto1.getMessage());
  }

  @Test
  public void getMessagesTest2() {
    String fromUserId = String.valueOf(Math.random());
    String toUserId = String.valueOf(Math.random());

    String toUserId2 = String.valueOf(Math.random());

    String messageId1 = String.valueOf(Math.random());
    MessageDto messageDto1 = new MessageDto(messageId1);
    messageDto1.setToId(toUserId);
    messageDto1.setFromId(fromUserId);
    messageDto1.setMessage(String.valueOf(Math.random()));

    String messageId2 = String.valueOf(Math.random());
    MessageDto messageDto2 = new MessageDto(messageId2);
    messageDto2.setToId(toUserId2);
    messageDto2.setFromId(fromUserId);
    MessageDao messageDao = MessageDao.getInstance();

    messageDao.put(messageDto1);
    messageDao.put(messageDto2);

    String test1 = "GET /getMessages?fromId=" + String.valueOf(Math.random()) + "&toId=" + toUserId + " HTTP/1.1\n"
        + "Host: test\n"
        + "Connection: Keep-Alive\n"
        + "\n";
    System.out.println(test1);
    String response = Server.processRequest(test1);
    String[] responseParts = response.split("\n");
    Assert.assertEquals(responseParts[0], "HTTP/1.1 200 OK");

    // I changed this line of code from responseParts[2], to responseParts[3], as the body of the response should
    // be on 3rd(actually 4th if indexing starts from 1) place in this String array
    RestApiAppResponse<MessageDto> messages = GsonTool.gson.fromJson(responseParts[3],
        new TypeToken<RestApiAppResponse<MessageDto>>() {
        }.getType());
    Assert.assertEquals(messages.data.size(), 0);
  }


  // we need to clear the state of our storage after every test, so tests are not fake
  @AfterMethod
  public void clearState(){
    MessageDao.getInstance().clear();
    UserDao.getInstance().clear();
  }
}
