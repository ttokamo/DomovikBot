package by.overone.it.repository;

import by.overone.it.entity.BotStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface BotStatusRepository extends JpaRepository<BotStatus, String> {

    @Query("from BotStatus where username =:username")
    String getBotStatusByUsername(@Param("username") String username);

    @Modifying
    @Query("delete from BotStatus where username =:username")
    void deleteBotStatusByUsername(@Param("username") String username);

    @Modifying
    @Query("update BotStatus set status =:status where username =:username")
    void updateBotStatusByUsername(@Param("username") String username, @Param("status") String status);
}
