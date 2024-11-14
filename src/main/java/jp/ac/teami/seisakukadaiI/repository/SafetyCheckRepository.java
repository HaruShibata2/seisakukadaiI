package jp.ac.teami.seisakukadaiI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.ac.teami.seisakukadaiI.model.SafetyCheck;

@Repository
public interface SafetyCheckRepository extends JpaRepository<SafetyCheck, Integer> {

	List<SafetyCheck> findByStatus(String status);


}	