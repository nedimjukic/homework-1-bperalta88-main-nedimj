package dao;

import dto.UserDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO fill this out
public class UserDao implements BaseDao<UserDto> {
  private static UserDao instance = null;

  // user in memory storage just for purposes of this task, otherwise database should be used
  private Map<String, UserDto> inMemoryStorage;

  // instantiate or just return the singleton (design pattern)
  public static UserDao getInstance() {
    if(instance == null){
      instance = new UserDao();
    }

    return instance;
  }

  // make constructor private, so it's not able to create object of this class
  private UserDao(){
    inMemoryStorage = new HashMap<>();
  }

  // TODO fill this out
  @Override
  public void put(UserDto userDto) {
    inMemoryStorage.put(userDto.getUniqueId(), userDto);
  }

  // TODO fill this out
  @Override
  public UserDto get(String uniqueId) {
    return inMemoryStorage.get(uniqueId);
  }

  // TODO fill this out
  @Override
  public List<UserDto> getAll() {
    return List.copyOf(inMemoryStorage.values());
  }

  // because we need to clear the storage after every test
  public void clear(){
    inMemoryStorage.clear();
  }
}
