package handler;

import com.google.gson.reflect.TypeToken;
import dao.MessageDao;
import dto.MessageDto;
import org.testng.Assert;
import org.testng.annotations.Test;
import response.RestApiAppResponse;
import server.Server;

public class GetMessagesHandlerTest {

  @Test
  public void createUserTest() {
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
    RestApiAppResponse<MessageDto> messages = GsonTool.gson.fromJson(responseParts[2],
        new TypeToken<RestApiAppResponse<MessageDto>>() {
        }.getType());
    Assert.assertEquals(messages.data.size(), 1);
    Assert.assertEquals(messages.data.get(0).getUniqueId(), messageDto1.getUniqueId());
    Assert.assertEquals(messages.data.get(0).getToId(), messageDto1.getToId());
    Assert.assertEquals(messages.data.get(0).getFromId(), messageDto1.getFromId());
    Assert.assertEquals(messages.data.get(0).getMessage(), messageDto1.getMessage());
  }

  @Test
  public void createUserTest2() {
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
    RestApiAppResponse<MessageDto> messages = GsonTool.gson.fromJson(responseParts[2],
        new TypeToken<RestApiAppResponse<MessageDto>>() {
        }.getType());
    Assert.assertEquals(messages.data.size(), 0);
  }

}
