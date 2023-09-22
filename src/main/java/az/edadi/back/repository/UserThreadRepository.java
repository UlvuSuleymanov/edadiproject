package az.edadi.back.repository;

import az.edadi.back.entity.message.UserThread;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserThreadRepository extends JpaRepository<UserThread, Long> {

    Optional<UserThread> findByUserIdAndThreadId(Long userId, Long threadId);

    List<UserThread> findByThreadId(Long threadId);

    List<UserThread> findByUserId(Long userId, Pageable pageable);

    @Query(value = "SELECT ut FROM UserThread ut JOIN UserThread utt on ut.user.id=:userId and utt.user.id=:userId2 and utt.thread.id=ut.thread.id")
    Optional<List<UserThread>> getThreadsBWithUserIds(Long userId, Long userId2);

}
