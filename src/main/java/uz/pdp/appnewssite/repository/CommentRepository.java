package uz.pdp.appnewssite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appnewssite.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
