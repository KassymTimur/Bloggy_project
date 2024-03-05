package kz.aitu.simpleproject.repositories;

import kz.aitu.simpleproject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoryInterface extends JpaRepository<User, Integer> {
}
