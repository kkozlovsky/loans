package ru.kerporation.controller;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kerporation.entities.User;
import ru.kerporation.service.UserService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@Api(value = "apiusers", description = "User API")
@RequestMapping("apiusers")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Получить список заёмщиков", response = User.class)
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> list() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Получить описание заёмщика по userId", response = User.class)
    @RequestMapping(value = "/users/{userId:.+}", method = RequestMethod.GET)
    public ResponseEntity<User> get(@PathVariable String userId) {
        return new ResponseEntity<>(userService.getOne(userId), HttpStatus.OK);
    }

    @ApiOperation(value = "Создать нового заёмщика", response = User.class)
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<User> create(@RequestBody User user) {
        return new ResponseEntity<>(userService.create(user), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Изменить существующего заёмщика", response = User.class)
    @RequestMapping(value = "/users/{userId:.+}", method = RequestMethod.PUT)
    public ResponseEntity<User> update(@PathVariable String userId, @RequestBody User user) {
        return new ResponseEntity<>(userService.update(userId, user), HttpStatus.OK);
    }

    @ApiOperation(value = "Удалить существующего заёмщика", response = User.class)
    @RequestMapping(value = "/users/{userId:.+}", method = RequestMethod.DELETE)
    public ResponseEntity<User> delete(@PathVariable String userId) {
        return new ResponseEntity<>(userService.delete(userId), HttpStatus.OK);
    }

    @ApiOperation(value = "Получить чёрный список заёмщиков", response = User.class)
    @RequestMapping(value = "/blacklist", method = RequestMethod.GET)
    public ResponseEntity<List<User>> blacklist() {
        return new ResponseEntity<>(userService.getBlacklist(), HttpStatus.OK);
    }

}
