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

    public void saveAd(Market market) {
        marketRepository.save(market);
    }

    public List<Market> getAllAd() {
        return marketRepository.findAll();
    }

}
