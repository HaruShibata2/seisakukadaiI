package jp.ac.teami.seisakukadaiI.repository;

import jp.ac.teami.seisakukadaiI.model.InquiryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InquiryRepository extends JpaRepository<InquiryModel, Integer> {
}
