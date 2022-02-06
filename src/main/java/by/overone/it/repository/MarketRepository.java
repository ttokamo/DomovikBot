package by.overone.it.repository;

import by.overone.it.entity.Market;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface MarketRepository extends JpaRepository<Market, String> {
}
