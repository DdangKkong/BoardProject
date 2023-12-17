package zerobase.boardproject.repository;

import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zerobase.boardproject.domain.Comments;

@Repository
public interface CommentRepository extends JpaRepository<Comments, BigInteger> {

}
