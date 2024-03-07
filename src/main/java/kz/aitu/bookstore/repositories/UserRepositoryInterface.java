package kz.aitu.bookstore.repositories;

import kz.aitu.bookstore.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepositoryInterface extends JpaRepository<User, Integer> {
    List<User> findByName(String name);
    List<User> findBySurname(String surname);
}
