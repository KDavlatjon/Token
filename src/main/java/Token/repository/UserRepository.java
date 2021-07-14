package Token.repository;

import Token.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

    Users findByPhone(String phone);

    Users findByName(String name);

    boolean existsByPhone(String phone);
}
