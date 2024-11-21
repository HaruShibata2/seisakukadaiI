package jp.ac.teami.seisakukadaiI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.ac.teami.seisakukadaiI.model.InquiryModel;
import jp.ac.teami.seisakukadaiI.repository.InquiryRepository;

@Service
public class InquiryService {

    @Autowired
    private InquiryRepository inquiryRepository;

    public void saveInquiry(InquiryModel inquiry) {
        inquiryRepository.save(inquiry);  // データベースに問い合わせ情報を保存
    }
}
