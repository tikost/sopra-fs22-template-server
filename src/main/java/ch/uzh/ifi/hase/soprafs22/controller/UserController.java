package ch.uzh.ifi.hase.soprafs22.controller;


import ch.uzh.ifi.hase.soprafs22.constant.UserStatus;
import ch.uzh.ifi.hase.soprafs22.entity.User;
import ch.uzh.ifi.hase.soprafs22.rest.dto.UserGetDTO;
import ch.uzh.ifi.hase.soprafs22.rest.dto.UserPostDTO;
import ch.uzh.ifi.hase.soprafs22.rest.mapper.DTOMapper;
import ch.uzh.ifi.hase.soprafs22.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

/**
 * User Controller
 * This class is responsible for handling all REST request that are related to
 * the user.
 * The controller will receive the request and delegate the execution to the
 * UserService and finally return the result.
 */
@RestController
public class UserController {

  private final UserService userService;

  UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/users")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public List<UserGetDTO> getAllUsers() {
    // fetch all users in the internal representation
    List<User> users = userService.getUsers();
    List<UserGetDTO> userGetDTOs = new ArrayList<>();

    // convert each user to the API representation
    for (User user : users) {
      userGetDTOs.add(DTOMapper.INSTANCE.convertEntityToUserGetDTO(user));
    }
    return userGetDTOs;
  }

  @GetMapping("/users/{userId}")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public List<UserGetDTO> getUserById(@PathVariable long userId) {
      // fetch all users in the internal representation
      List<User> users = userService.getUsers();
      List<UserGetDTO> userGetDTOs = new ArrayList<>();

      for (int i = 0; i < users.size(); i++) {
          if (userId == (users.get(i).getId())) {
              userGetDTOs.add(DTOMapper.INSTANCE.convertEntityToUserGetDTO(users.get(i)));
          }
      }
      return userGetDTOs;
  }

  @PutMapping("/logout/{userId}")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public void updateStatus(@PathVariable long userId) {
        userService.setStatusInRepo(userId, UserStatus.OFFLINE);
    }

    @PutMapping("/changeUsernameCheck/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public UserGetDTO uniqueUsername(@RequestBody UserPostDTO userPostDTO, @PathVariable long userId) {
        // convert API user to internal representation
        User userInput = DTOMapper.INSTANCE.convertUserPostDTOtoEntity(userPostDTO);
        System.out.println(userInput);
        // check username
        userService.updateUsername(userInput);

        List<User> users = userService.getUsers();
        for (int i = 0; i < users.size(); i++) {
            if (userId == (users.get(i).getId())) {
                User currentUser = users.get(i);
                currentUser.setUsername(userInput.getUsername());
                //return DTOMapper.INSTANCE.convertEntityToUserGetDTO(currentUser);
            }
        }
        //if (userInput.getBirthday() != null)

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Update unsuccessful, please try later again.");
    }


  @PostMapping("/users")
  @ResponseStatus(HttpStatus.CREATED) // post user
  @ResponseBody
  public UserGetDTO createUser(@RequestBody UserPostDTO userPostDTO) {
    // convert API user to internal representation
    User userInput = DTOMapper.INSTANCE.convertUserPostDTOtoEntity(userPostDTO);

    // create user
    User createdUser = userService.createUser(userInput);

    // convert internal representation of user back to API
    return DTOMapper.INSTANCE.convertEntityToUserGetDTO(createdUser);
  }

    @PostMapping("/login") // check if user trying to login has correct password
    @ResponseStatus(HttpStatus.CREATED) // post user
    @ResponseBody
    public UserGetDTO checkUser(@RequestBody UserPostDTO userPostDTO) {
        // convert API user to internal representation
        User userInput = DTOMapper.INSTANCE.convertUserPostDTOtoEntity(userPostDTO);

        // list of users
        List<User> users = userService.getUsers();

        for (int i = 0; i<users.size(); i++) {
            if (userInput.getUsername().equals(users.get(i).getUsername())) {
                if (userInput.getPassword().equals(users.get(i).getPassword())){ // name is password
                    userService.setStatusInRepo(users.get(i).getId(), UserStatus.ONLINE);
                    return DTOMapper.INSTANCE.convertEntityToUserGetDTO(users.get(i));
                } else {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password is wrong!");
                }
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please register first.");
    }
}
