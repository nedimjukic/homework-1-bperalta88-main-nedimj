package dao;

import dto.MessageDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO fill this out
public class MessageDao implements BaseDao<MessageDto> {
  private static MessageDao instance = null;

  // user in memory storage just for purposes of this task, otherwise database should be used
  private Map<String, MessageDto> inMemoryStorage;

  // instantiate or just return the singleton (design pattern)
  public static MessageDao getInstance() {
    if(instance == null){
      instance = new MessageDao();
    }

    return instance;
  }

  // make constructor private, so it's not able to create object of this class
  private MessageDao(){
    inMemoryStorage = new HashMap<>();
  }


  // TODO fill this out
  @Override
  public void put(MessageDto messageDto) {
    inMemoryStorage.put(messageDto.getUniqueId(), messageDto);
  }

  // TODO fill this out
  @Override
  public MessageDto get(String uniqueId) {
    return inMemoryStorage.get(uniqueId);
  }

  // TODO fill this out
  @Override
  public List<MessageDto> getAll() {
    return List.copyOf(inMemoryStorage.values());
  }

  // because we need to clear the storage after every test
  public void clear(){
    inMemoryStorage.clear();
  }
}
