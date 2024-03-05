package kz.aitu.simpleproject.services.interfaces;

import kz.aitu.simpleproject.models.User;

import java.util.List;

public interface UserServiceInterface {
    List<User> getAll();
    User getById(int id);
    User create(User user);
    List<User> getBySurname(String surname);
}
