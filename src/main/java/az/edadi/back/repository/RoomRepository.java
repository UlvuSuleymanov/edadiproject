package az.edadi.back.repository;

import az.edadi.back.entity.message.Room;
import az.edadi.back.entity.message.Thread;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

//    Optional<UserThread> findByUserIdAndThreadId(Long userId, Long threadId);
//
//    List<UserThread> findByThreadId(Long threadId);
//
//    List<UserThread> findByUserId(Long userId, Pageable pageable);
//
//    @Query(value = "SELECT r FROM Room r LEFT JOIN Thread t on t.user.id in :userIds")
//    Optional<Thread> getThreadsBWithUserIds(List<Long> userIds);

    @Query("SELECT  t.room FROM Thread t where t.user.id in :ids and t.room.id in" +
            " (SELECT t2.room.id from Thread t2 group by t2.room.id having count(t2)=2 )")
    Optional<Room> getCommonRoom(List<Long> ids);
}
