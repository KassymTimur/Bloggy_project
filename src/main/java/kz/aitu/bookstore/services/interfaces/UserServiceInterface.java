package kz.aitu.bookstore.services.interfaces;
import kz.aitu.bookstore.models.User;
import java.util.List;
public interface UserServiceInterface {
    List<User> getAllUsers();
    User getById(int user_id);
    User create(User user);
    void deleteUser(int user_id);
    List<User> getByName(String name);
    List<User> getBySurname(String surname);
}
