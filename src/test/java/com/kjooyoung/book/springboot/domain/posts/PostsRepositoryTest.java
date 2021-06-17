package com.kjooyoung.book.springboot.domain.posts;

import com.kjooyoung.book.springboot.web.dto.PostsUpdateRequestDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;

    @Before
    public void addPosts() {

    }

    @After
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글_저장_불러오기(){
        String title = "title";
        String content = "content";

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("author")
                .build());

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void 게시글_조회(){
        String title = "title";
        String content = "content";

        Long id = postsRepository.save(Posts.builder()
                    .title(title)
                    .content(content)
                    .author("author")
                    .build()).getId();
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found"));

        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록() {
        LocalDateTime now = LocalDateTime.of(2019,6,4,0,0,0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>> createDate="+posts.getCreatedDate()+", modifiedDate="+posts.getModifiedDate());
        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}
