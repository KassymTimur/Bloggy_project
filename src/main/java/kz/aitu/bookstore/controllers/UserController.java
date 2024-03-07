package kz.aitu.bookstore.controllers;
import kz.aitu.bookstore.models.User;
import kz.aitu.bookstore.services.interfaces.UserServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("users")
public class UserController {
    private final UserServiceInterface service;
    public UserController(UserServiceInterface service){
        this.service = service;}
    @GetMapping("/")
    public List<User> getAllUsers(){
        return service.getAllUsers();
    }
    @GetMapping("{user_id}")
    public ResponseEntity<User> getById(@PathVariable("user_id") int user_id){
        User user = service.getById(user_id);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @PostMapping("/")
    public ResponseEntity<User> create(@RequestBody User user){
        User createdUser = service.create(user);
        if (createdUser==null){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/surname/{user_surname}")
    public List<User> getAllBySurname(@PathVariable("user_surname") String surname){
        return service.getBySurname(surname);
    }
    @GetMapping("/name/{user_name}")
    public List<User> getAllByName(@PathVariable("user_name") String name){
        return service.getByName(name);
    }
    @DeleteMapping("/{user_id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("user_id") int user_id){
        service.deleteUser(user_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

