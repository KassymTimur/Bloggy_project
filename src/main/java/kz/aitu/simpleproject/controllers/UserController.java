package kz.aitu.simpleproject.controllers;

import kz.aitu.simpleproject.models.User;
import kz.aitu.simpleproject.services.interfaces.UserServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    private final UserServiceInterface service;

    public UserController(UserServiceInterface service) {
        this.service = service;
    }


    @GetMapping("hello")
    public String sayHello(){
        return "Hello world";
    }
    @GetMapping("/")
    public List<User> getAll(){
        return service.getAll();
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<User> getById(@PathVariable("user_id") int id){
        User user = service.getById(id);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); //404
        }
        return new ResponseEntity<>(user, HttpStatus.OK); //200
    }
    @PostMapping("/")
    public ResponseEntity<User> create(@RequestBody User user){
        User createdUser = service.create(user);
        if (createdUser==null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); //501 ish?
            }
        return new ResponseEntity<>(HttpStatus.CREATED); //201
        }
    @GetMapping("/surname/{user_surname}")
    public List<User> getAllBySurname(@PathVariable("user_surname") String surname){
    return service.getBySurname(surname);
    }
}
