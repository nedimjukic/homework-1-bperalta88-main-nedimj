package handler;

import com.google.gson.Gson;
import dao.UserDao;
import dto.UserDto;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;
import server.Server;

public class CreateUserHandlerTest {

  private static final Gson gson = new Gson();

  @Test
  public void createUserTest(){
    String user1 = String.valueOf(Math.random());
    UserDto user = new UserDto();
    user.setUserName(user1);
    String test1 = "POST /createUser HTTP/1.1\n"
        + "Host: test\n"
        + "Connection: Keep-Alive\n"
        + "\n"
        + gson.toJson(user);
    String response = Server.processRequest(test1);
    String[] responseParts = response.split("\n");
    Assert.assertEquals(responseParts[0], "HTTP/1.1 200 OK");
    List<UserDto> users = UserDao.getInstance().getAll();
    Assert.assertEquals(users.size(), 1);
    Assert.assertNotNull(users.get(0).getUniqueId());
    Assert.assertEquals(users.get(0).getUserName(), user1);
  }

}
