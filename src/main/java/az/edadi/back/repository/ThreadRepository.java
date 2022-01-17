package az.edadi.back.repository;

import az.edadi.back.entity.message.Thread;
import org.springframework.data.jpa.repository.JpaRepository;
 import org.springframework.stereotype.Repository;

@Repository
public interface ThreadRepository extends JpaRepository<Thread,Long> {

}
