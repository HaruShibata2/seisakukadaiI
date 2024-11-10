package jp.ac.teami.seisakukadaiI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.ac.teami.seisakukadaiI.model.PostModel;

public interface PostRepository extends JpaRepository<PostModel, Integer> {
    // 必要に応じてカスタムクエリなどを追加
}
