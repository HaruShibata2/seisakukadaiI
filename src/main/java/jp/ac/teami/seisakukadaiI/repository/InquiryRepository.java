package jp.ac.teami.seisakukadaiI.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import jp.ac.teami.seisakukadaiI.model.InquiryModel;

public interface InquiryRepository extends JpaRepository<InquiryModel, Integer> {
    // 必要に応じてカスタムメソッドを追加可能
}

