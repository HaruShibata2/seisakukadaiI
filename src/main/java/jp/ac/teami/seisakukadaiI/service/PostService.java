package jp.ac.teami.seisakukadaiI.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;
import jp.ac.teami.seisakukadaiI.model.PostModel;
import jp.ac.teami.seisakukadaiI.repository.PostRepository;

@Service
@Transactional
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
//	private Subjectrepository repository;

	/**
	 * アドレス帳一覧の取得
	 * @return List<oharabank>
	 */
	public List<PostModel> getPostList() {
	    List<PostModel> entityList = this.postRepository.findAll();
	    return entityList;
	}

	/**
	 * 詳細データの取得
	 * @param @NonNull Long index
	 * @return  AddressBook
	 */
	public PostModel get(@NonNull Integer index) {
		PostModel post = this.postRepository.findById(index).orElse(new PostModel());
		return post;
	}
	/**
	 * アドレス帳一覧の取得
	 * @param AddressBook addressBook
	 */
	public void save(@Nonnull PostModel postmodel) {
		this.postRepository.save(postmodel);
		System.out.println(postmodel);
	}
	/**
	 * アドレス帳データの削除
	 * @param @NonNull Long index
	 */
	public void delete(@NonNull Integer id) {
		this.postRepository.deleteById(id);
	}
	public PostModel getOneBook(Integer id) {
        // idを指定して本の情報を取得する
		PostModel subject = this.postRepository.findById(id).orElseThrow();
        // 画面返却用のFormに値を設定する
        return subject;
    }

    // 本を更新する
    public void update(PostModel editPost) {
        // データベースに登録する値を保持するインスタンスの作成
        System.out.println(editPost);

        // データベースを更新する
        this.postRepository.save(editPost);
        System.out.println("aaa");
    }
//    public String getsubjectcd(String name) {
//    	PostModel subject = this.postRepository.findByUsername(name);
//    	return subject.getSchoolCd();
//    }
    public PostModel getPost(Integer id) {
    	PostModel post = this.postRepository.findById(id).orElse(null);
    	return post;
    	}
    }
