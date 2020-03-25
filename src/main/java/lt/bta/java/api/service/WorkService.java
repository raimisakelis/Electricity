package lt.bta.java.api.service;

import lt.bta.java.entities.Work;

import java.util.List;

public interface WorkService {

   Work get(int id);

   void save(Work work);

   void delete(int id);

   void update(int id, Work work);

   List<Work> workList(int size, int skip);

}
