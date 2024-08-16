package dio.java_desenvolvimento_com_ia.service;

import java.util.List;

import dio.java_desenvolvimento_com_ia.domain.model.User;

public interface UserService {
    
    User findById(Long id);

    List<User> findAll();

    User create(User userToCreate);

    User update(Long id, User userToUpdate);

    void delete(Long id);
}
