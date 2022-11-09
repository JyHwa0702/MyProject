package project.first.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.first.domain.User;

public interface UserRepository extends JpaRepository<User,Long> {
    public User findByUsername(String username);
}
