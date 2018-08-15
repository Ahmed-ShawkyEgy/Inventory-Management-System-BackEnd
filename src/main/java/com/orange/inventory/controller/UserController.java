package com.orange.inventory.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orange.inventory.exception.ResourceNotFoundException;
import com.orange.inventory.model.User;
import com.orange.inventory.repository.UserRepository;
import com.orange.inventory.repository.custom.OwnershipRepositoryCustom;

@RestController
@RequestMapping("/api")
public class UserController {
	
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OwnershipRepositoryCustom ownershipRepositoryCustom;
    
    @GetMapping("/users")
    public List<User> getAllUsers()
    {
    	return userRepository.findAll();
    }
    
    @GetMapping("/users/myitems/{id}")
    public ResponseEntity<?> findMyItems(@PathVariable(value = "id") Long userId)
    {
    	return ResponseEntity.ok().body(userRepository.findById(userId).get().getItems());
    }
    
    // Read
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable(value = "id") Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
    }
    
    // Update
    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable(value = "id") Long userId,
            @Valid @RequestBody User userDetails) {

		User user = userRepository.findById(userId)
		.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		
		user.setEmail(userDetails.getEmail());
		user.setFirst_name(userDetails.getFirst_name());
		user.setLast_name(userDetails.getLast_name());
		user.setPassword(userDetails.getPassword());

		
		User updatedUser = userRepository.save(user);
		return updatedUser;
	}
    
    // Delete
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        
        ownershipRepositoryCustom.discardAllItemsByUser(userId);
        userRepository.delete(user);

        return ResponseEntity.ok().build();
    }
    
    

}
