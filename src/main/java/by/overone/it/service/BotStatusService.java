package by.overone.it.service;

import by.overone.it.entity.BotStatus;
import by.overone.it.repository.BotStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BotStatusService {

    @Autowired
    private BotStatusRepository repository;

    public String getBotStatusByUsername(String username) {
        return repository.getBotStatusByUsername(username);
    }

    public void deleteBotStatusByUsername(String username) {
        repository.deleteBotStatusByUsername(username);
    }

    public void saveBotStatus(BotStatus botStatus) {
        repository.save(botStatus);
    }

    public void updateBotStatus(String username, String status) {
        repository.updateBotStatusByUsername(username, status);
    }
}
