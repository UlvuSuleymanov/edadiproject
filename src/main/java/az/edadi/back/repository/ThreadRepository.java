package az.edadi.back.repository;

import az.edadi.back.entity.message.Thread;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ThreadRepository extends JpaRepository<Thread, Long> {
    Optional<Thread> findByUserIdAndConversationId(Long userId, Long conversationId);
}
