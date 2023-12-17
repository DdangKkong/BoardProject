package zerobase.boardproject.repository;

import java.math.BigInteger;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zerobase.boardproject.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, BigInteger> {

  Optional<User> findByUserLoginId(String userLoginId);
  boolean existsByUserLoginId(String userLoginId);

}
