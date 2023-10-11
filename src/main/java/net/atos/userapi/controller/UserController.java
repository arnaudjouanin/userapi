package net.atos.userapi.controller;

import net.atos.userapi.entity.User;
import net.atos.userapi.repository.UserRepository;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static net.atos.userapi.util.UserUtil.getAge;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            boolean isAnAdult = getAge(user.getBirthdate()) >= 18;
            boolean livesInFrance = user.getCountryOfResidence() != null
                    && user.getCountryOfResidence().equals("France");
            boolean canRegister = isAnAdult && livesInFrance;

            if(!canRegister){
                return ResponseEntity.badRequest().body("User cannot have an account");
            }

            if (userRepository.findByUsername(user.getUsername()).isPresent()){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
            }

            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error registering user");
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getUserDetails(@PathVariable String username) {
        Optional<User> user = userRepository.findByUsername(username);

        if (!user.isPresent()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user);
    }

}
