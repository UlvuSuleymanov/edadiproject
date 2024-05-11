package az.edadi.back.repository;

import az.edadi.back.entity.message.Conversation;
import az.edadi.back.entity.message.Thread;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {

    @Query("SELECT t.conversation.id FROM Thread t  where t.user.id in :ids group by t.conversation.id having count(*)=2")
    Optional<Long> getConversation(List<Long> ids);

    @Query("SELECT c FROM Conversation c join Thread t on t.conversation.id=c.id  where t.user.id = :userId order by c.dateModified desc")
    List<Conversation> getConversationList(Long userId, Pageable pageable);
}
