package az.edadi.back.repository;

import az.edadi.back.entity.message.Thread;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ThreadRepository extends JpaRepository<Thread, Long> {

    @Query("SELECT  t FROM Thread t where t.user.id in :ids and t.room.id in" +
            " (SELECT t2.room.id from Thread t2 group by t2.room.id having count(t2)=2 )")
    Optional<Thread> getCommonThreads(List<Long> ids);
}
