package dao;

import dto.BaseDto;
import java.util.List;

public interface BaseDao<T extends BaseDto> {

  public void put(T t);

  public T get(String uniqueId);

  List<T> getAll();
}
