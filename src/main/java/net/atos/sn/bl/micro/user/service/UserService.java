package net.atos.sn.bl.micro.user.service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import net.atos.sn.bl.micro.user.dto.response.MessageResponse;
import org.apache.commons.collections4.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import net.atos.sn.bl.micro.user.dto.UserDto;
import net.atos.sn.bl.micro.user.exception.ClientRuntimeException;
import net.atos.sn.bl.micro.user.model.User;
import net.atos.sn.bl.micro.user.repository.UserRepository;
import net.atos.sn.bl.micro.user.utils.Messages;
import net.atos.sn.bl.micro.user.utils.Patterns;
import net.atos.sn.bl.micro.user.utils.UserStatus;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ModelMapper modelMapper;

	public List<UserDto> findAllUsers() {
		List<UserDto> users = StreamSupport.stream(this.userRepository.findAll().spliterator(), false)
				.map(this::convertToDto).collect(Collectors.toList());
		return users;
	}

	public List<UserDto> findAllUsersById(List<String> uids) {
		List<UserDto> users = StreamSupport.stream(this.userRepository.findAllById(uids).spliterator(), false)
				.map(this::convertToDto).collect(Collectors.toList());
		return users;
	}

	public UserDto findUserByUserId(String uid) throws ClientRuntimeException {
		UserDto userDto = this.userRepository.findById(uid).map(this::convertToDto)
				.orElseThrow(() -> new ClientRuntimeException(Messages.USER_NOT_FOUND, uid, HttpStatus.NOT_FOUND));
		return userDto;
	}

	public UserDto findUserByMobile(String mobile) throws ClientRuntimeException {
		UserDto userDto = this.userRepository.findByMobile(mobile).map(this::convertToDto)
				.orElseThrow(() -> new ClientRuntimeException(Messages.USER_NOT_FOUND, mobile, HttpStatus.NOT_FOUND));
		return userDto;
	}

	public UserDto findUserByEmail(String email) throws ClientRuntimeException {
		if (email.equals("")) {
			throw new ClientRuntimeException(Messages.USER_INVALID_SEARCH_CRITERIA, email, HttpStatus.BAD_REQUEST);
		}
		// Define email pattern
		// Then check if the argument is an email
		Pattern pattern = Pattern.compile(Patterns.EMAIL_PATTERN);
		if (!pattern.matcher(email).matches()) {
			throw new ClientRuntimeException(Messages.USER_INVALID_MAIL_PARAMETER, email, HttpStatus.BAD_REQUEST);
		}
		UserDto userDto = this.userRepository.findByEmail(email).map(this::convertToDto)
				.orElseThrow(() -> new ClientRuntimeException(Messages.USER_NOT_FOUND, email, HttpStatus.NOT_FOUND));
		return userDto;
	}

	public ResponseEntity<?> checkMobile(String mobile) {

		return null;
	}
	public ResponseEntity<?> createUser(UserDto userDto){
		// Check if user already exist
		User user;
		try {
			userDto.setStatus(UserStatus.ACTIVATED);
			user = this.userRepository.save(convertToEntity(userDto));
		} catch (ClientRuntimeException ex) {
			return ResponseEntity.badRequest().body(new MessageResponse(ex.getMessage(), "UserId || Mail || Téléphone || CNI"));
		}
		return ResponseEntity.ok(user);
	}

	public void createAll(List<UserDto> userDtos) {
		if (CollectionUtils.isEmpty(userDtos)) {
			return;
		}

		userDtos.forEach(this::createUser);
	}

	public UserDto reactivateUser(UserDto userDto, String uid) throws ClientRuntimeException {
		UserDto userToUpdate = this.findUserByUserId(uid);
		if (userDto.getUserId() == null) {
			userDto.setUserId(userToUpdate.getUserId());
		}
		userDto.setStatus(UserStatus.ACTIVATED);
		User user = this.userRepository.save(convertToEntity(userDto));
		return convertToDto(user);
	}

	public UserDto updateUser(UserDto userDto, String uid) throws ClientRuntimeException {
		UserDto userToUpdate = this.findUserByUserId(uid);
		if (userDto.getUserId() == null) {
			userDto.setUserId(userToUpdate.getUserId());
		}

		User user = this.userRepository.save(convertToEntity(userDto));
		return convertToDto(user);
	}

	public void deleteUserById(String uid) throws ClientRuntimeException {
		UserDto user = findUserByUserId(uid);
		user.setStatus(UserStatus.DELETED);
		this.updateUser(user, uid);
	}
	
	public void blockUser(String uid) throws ClientRuntimeException {
		UserDto user = findUserByUserId(uid);
		user.setStatus(UserStatus.BLOCKED);
		this.updateUser(user, uid);
	}

	private UserDto convertToDto(User user) {
		return modelMapper.map(user, UserDto.class);
	}

	private User convertToEntity(UserDto userDto) {
		return modelMapper.map(userDto, User.class);
	}

}
