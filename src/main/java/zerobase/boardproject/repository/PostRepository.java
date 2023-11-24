package zerobase.boardproject.repository;

import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zerobase.boardproject.domain.Posts;

@Repository
public interface PostRepository extends JpaRepository<Posts, BigInteger> {

}
