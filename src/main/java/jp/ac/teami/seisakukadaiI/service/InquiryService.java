package jp.ac.teami.seisakukadaiI.service;

import jp.ac.teami.seisakukadaiI.model.InquiryModel;
import jp.ac.teami.seisakukadaiI.repository.InquiryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class InquiryService {

    @Autowired
    private InquiryRepository inquiryRepository;

    public List<InquiryModel> getAllInquiries() {
        return inquiryRepository.findAll();
    }

    public InquiryModel getInquiryById(Integer id) {
        return inquiryRepository.findById(id).orElse(null);
    }

    public InquiryModel saveInquiry(InquiryModel inquiry) {
        return inquiryRepository.save(inquiry);
    }
}
