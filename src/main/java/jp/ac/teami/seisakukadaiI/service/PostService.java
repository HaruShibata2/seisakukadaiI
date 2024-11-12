package jp.ac.teami.seisakukadaiI.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.ac.teami.seisakukadaiI.model.PostModel;
import jp.ac.teami.seisakukadaiI.repository.PostRepository;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    // 投稿を作成日時降順で取得
    public List<PostModel> getAllPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }
}
