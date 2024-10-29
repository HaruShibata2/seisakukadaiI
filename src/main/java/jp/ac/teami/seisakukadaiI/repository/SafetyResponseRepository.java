package jp.ac.teami.seisakukadaiI.repository;

import jp.ac.teami.seisakukadaiI.model.SafetyResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SafetyResponseRepository extends JpaRepository<SafetyResponse, Long> {
    // 必要に応じてカスタムクエリを追加できます
}
