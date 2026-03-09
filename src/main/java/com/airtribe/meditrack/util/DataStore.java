package com.airtribe.meditrack.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DataStore<T> {
  private Map<String,T> storage=new HashMap<>();
  public void save(String id, T object)
  {
    storage.put(id, object);
  }
  public T findById(String id)
  {
    return storage.get(id);
  }
  public Collection<T> findAll()
  {
    return storage.values();
  }
  public void delete(String id)
  {
    storage.remove(id);
  }
}
