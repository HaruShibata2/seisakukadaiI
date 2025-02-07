package jp.ac.teami.seisakukadaiI.service;




import java.util.List;

import org.springframework.stereotype.Service;

import jp.ac.teami.seisakukadaiI.model.StockpileModel;
import jp.ac.teami.seisakukadaiI.repository.StockpileRepository;




@Service
public class StockpileService {
    private final StockpileRepository repository;

    public StockpileService(StockpileRepository repository) {
        this.repository = repository;
    }

    public List<StockpileModel> getAllStockpiles() {
        return repository.findAllByOrderByCreatedAtDesc();
    }

    public StockpileModel saveStockpile(StockpileModel stockpile) {
        return repository.save(stockpile);
    }

    public void deleteStockpile(Long id) {
        repository.deleteById(id);
    }
}

