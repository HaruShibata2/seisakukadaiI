package jp.ac.teami.seisakukadaiI.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.ac.teami.seisakukadaiI.model.StockpileModel;



public interface StockpileRepository extends JpaRepository<StockpileModel, Long> {
    List<StockpileModel> findAllByOrderByCreatedAtDesc();
}

