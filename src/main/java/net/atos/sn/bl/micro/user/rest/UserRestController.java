package net.atos.sn.bl.micro.user.rest;

import java.io.IOException;
import java.util.List;

import javax.servlet.annotation.MultipartConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import net.atos.sn.bl.micro.user.dto.UserDto;
import net.atos.sn.bl.micro.user.service.UserService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/users")
public class UserRestController {
	@Autowired
	private UserService userService;

	@GetMapping(value = "")
	public List<UserDto> getAllUsers() {
		return this.userService.findAllUsers();
	}

	@PostMapping(value = "uids")
	public List<UserDto> getAllUsers(@RequestBody List<String> uids) {
		return this.userService.findAllUsersById(uids);
	}

	@GetMapping(value = "{uid}")
	public UserDto getUserByUid(@PathVariable("uid") String uid) {
		return this.userService.findUserByUserId(uid);
	}

	@GetMapping(value = "search")
	public UserDto getUserByEmail(@RequestParam(name = "email", defaultValue = "") String email) {
		return this.userService.findUserByEmail(email);
	}
	
	
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> postUser(@RequestBody UserDto userDto
			/*@RequestParam(value = "userId", required = true) String userId,
			@RequestParam(value = "profilePic", required = true) MultipartFile profilePic
			@RequestPart(value = "profilePic", required = false)  MultipartFile profilePic,
			  @RequestPart(value = "cniRecto", required = false)  MultipartFile cniRecto, 
			  @RequestPart(value = "cniVerso", required = false)  MultipartFile cniVerso*/) throws IOException {
			//UserDto entityModel = ;
		/*UserDto userDto = new UserDto();
		userDto.setUserId(userId);
		userDto.setProfilePic(profilePic.getBytes());
		System.out.println(userDto.getProfilePic());*/
		return this.userService.createUser(userDto);
	}

	@PostMapping(value = "addall")
	public ResponseEntity<?> addAll(@RequestBody List<UserDto> userDtos) {
		this.userService.createAll(userDtos);
		return ResponseEntity.ok().build();
	}

	@PutMapping(value = "{uid}")
	public ResponseEntity<?> putUser(@RequestBody UserDto userDto, @PathVariable("uid") String uid) {
		UserDto entityModel = this.userService.updateUser(userDto, uid);
		return ResponseEntity.ok().body(entityModel);
	}

	@PutMapping(value = "reactivate/{uid}")
	public ResponseEntity<?> reactivateUser(@RequestBody UserDto userDto, @PathVariable("uid") String uid) {
		UserDto entityModel = this.userService.reactivateUser(userDto, uid);
		return ResponseEntity.ok().body(entityModel);
	}

	@DeleteMapping(value = "{uid}")
	public ResponseEntity<?> deleteUser(@PathVariable("uid") String uid) {
		this.userService.deleteUserById(uid);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping(value = "block/{uid}")
	public ResponseEntity<?> blockUser(@PathVariable("uid") String uid) {
		this.userService.blockUser(uid);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "search/{uid}")
	public UserDto getUserByUserIdParams(@PathVariable("uid") String uid) {
		return this.userService.findUserByUserId(uid);
	}
}
