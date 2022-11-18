package com.ios.serviceIMPL;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ios.exception.UserNotFoundException;
import com.ios.model.User;
import com.ios.repository.UserRepository;
import com.ios.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() {		
		List<User> users = userRepository.findAll();
		log.info("Users List:{}",users);
		log.info("getUsers call ended..");
		return users;
	}

	@Override
	public User getUserById(long userId) throws UserNotFoundException {
		return userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User Not Found Exception..!"));
	}

	@Override
	public User updateUser(User user, long userId) throws UserNotFoundException {
		User existingUser = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User Not Found..!"));
		log.info("User Found with UserId:{}",userId);
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());
		log.info("User updated of User Id:{}",userId);
		return userRepository.save(existingUser);
	}

	@Override
	public ResponseEntity<Object> deleteUser(long userId) throws UserNotFoundException {
		
		User foudUser = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User Not Found..!"));
		log.info("User found with UserId:{}",userId);
		userRepository.delete(foudUser);
		log.info("User deleted of UserId:{}",userId);
		return new ResponseEntity<>("User Deleted Successfully...", HttpStatus.NO_CONTENT);
	}
}
