package jp.ac.teami.seisakukadaiI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.ac.teami.seisakukadaiI.model.PostModel;

public interface PostRepository extends JpaRepository<PostModel, Integer> {
    // 作成日時 (createdAt) で降順に並べ替えて投稿を取得
    List<PostModel> findAllByOrderByCreatedAtDesc();
}


