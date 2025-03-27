package Project.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Project.springboot.models.Authority;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority,Long> {
    
}
