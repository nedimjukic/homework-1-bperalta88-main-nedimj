package dao;

import dto.MessageDto;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class DaoTests {

  @Test
  public void testDao(){
    MessageDao messageDao = MessageDao.getInstance();
    Assert.assertEquals(messageDao, MessageDao.getInstance());

    List<Constructor> constructors = Arrays.asList(MessageDao.class.getConstructors());
    Assert.assertEquals(constructors.size(), 0);
    constructors = Arrays.asList(MessageDao.class.getDeclaredConstructors());
    Assert.assertEquals(constructors.size(), 1);
    Assert.assertTrue(Modifier.isPrivate(constructors.get(0).getModifiers()));
  }

  @Test
  public void testStore(){
    MessageDao messageDao = MessageDao.getInstance();
    String id = String.valueOf(Math.random());
    MessageDto messageDto = new MessageDto(id);
    messageDao.put(messageDto);
    Assert.assertEquals(messageDao.get(id), messageDto);
  }

  // we need to clear the state of our storage after every test, so tests are not fake
  @AfterMethod
  public void clearState(){
    MessageDao.getInstance().clear();
    UserDao.getInstance().clear();
  }
}
