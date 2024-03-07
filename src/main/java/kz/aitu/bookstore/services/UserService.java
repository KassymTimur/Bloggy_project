package kz.aitu.bookstore.services;
import jakarta.persistence.EntityNotFoundException;
import kz.aitu.bookstore.models.User;
import kz.aitu.bookstore.services.interfaces.UserServiceInterface;
import kz.aitu.bookstore.repositories.UserRepositoryInterface;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class UserService implements UserServiceInterface{
    private final UserRepositoryInterface repo;
    public UserService(UserRepositoryInterface repo){this.repo = repo;}
    public List<User> getAllUsers() {return repo.findAll();}
    public User getById(int user_id) {
        return repo.findById(user_id).orElseThrow(() -> new EntityNotFoundException("User not found with id " + user_id));}
    @Override
    public User create(User user){
        return repo.save(user);
    }
    @Override
    public void deleteUser(int user_id){
        repo.deleteById(user_id);
    }
    @Override
    public List<User> getByName(String name) {return repo.findByName(name);}
    @Override
    public List<User> getBySurname(String surname){return repo.findBySurname(surname);}

}
