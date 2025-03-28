package Project.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Project.springboot.models.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    

    
}
