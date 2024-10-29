package jp.ac.teami.seisakukadaiI.repository;

import jp.ac.teami.seisakukadaiI.model.SafetyCheckRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SafetyCheckRequestRepository extends JpaRepository<SafetyCheckRequest, Long> {
    // 必要に応じてカスタムクエリを追加できます
}
