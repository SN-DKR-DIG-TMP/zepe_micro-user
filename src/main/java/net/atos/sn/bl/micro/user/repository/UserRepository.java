package net.atos.sn.bl.micro.user.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import net.atos.sn.bl.micro.user.model.User;

public interface UserRepository extends CrudRepository<User, String> {

	Optional<User> findByEmail(String email);
	Optional<User> findByMobile(String mobile);

}
