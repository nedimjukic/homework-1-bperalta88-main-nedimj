package handler;

import com.google.gson.reflect.TypeToken;
import dao.UserDao;
import dto.UserDto;
import org.testng.Assert;
import org.testng.annotations.Test;
import response.RestApiAppResponse;
import server.Server;

public class GetUsersHandlerTest {

  @Test
  public void createUserTest(){
    String user1 = String.valueOf(Math.random());
    String user1Id = String.valueOf(Math.random());
    UserDto user = new UserDto();
    UserDao userDao = UserDao.getInstance();
    user.setUserName(user1);
    user.setUniqueId(user1Id);
    userDao.put(user);
    String test1 = "GET /getUsers HTTP/1.1\n"
        + "Host: test\n"
        + "Connection: Keep-Alive\n"
        + "\n";
    String response = Server.processRequest(test1);
    String[] responseParts = response.split("\n");
    Assert.assertEquals(responseParts[0], "HTTP/1.1 200 OK");
    RestApiAppResponse<UserDto> userRes = GsonTool.gson.fromJson(responseParts[2],
        new TypeToken<RestApiAppResponse<UserDto>>() {}.getType());
    Assert.assertEquals(userRes.data.size(), 1);
    Assert.assertEquals(userRes.data.get(0).getUserName(), user1);
    Assert.assertEquals(userRes.data.get(0).getUniqueId(), user1Id);
  }

}
