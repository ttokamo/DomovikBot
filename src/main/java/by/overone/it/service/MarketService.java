package by.overone.it.service;

import by.overone.it.entity.Market;
import by.overone.it.repository.MarketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketService {

    @Autowired
    private MarketRepository marketRepository;

    private void saveAd(Market market) {
        marketRepository.save(market);
    }

    public void saveAd(String username, String description) {
        Market market = new Market();
        market.setUsername(username);
        market.setDescription(description);
        saveAd(market);
    }

    public List<Market> getAllAd() {
        return marketRepository.findAll();
    }
}
