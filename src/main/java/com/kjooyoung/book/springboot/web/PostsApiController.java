package com.kjooyoung.book.springboot.web;

import com.kjooyoung.book.springboot.service.posts.PostsService;
import com.kjooyoung.book.springboot.web.dto.PostsListResponseDto;
import com.kjooyoung.book.springboot.web.dto.PostsResponseDto;
import com.kjooyoung.book.springboot.web.dto.PostsSaveRequestDto;
import com.kjooyoung.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id, requestDto);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id){
        postsService.delete(id);
        return id;
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        return postsService.findById(id);
    }

    @GetMapping("/api/getPostsAll")
    public List<PostsListResponseDto> findAllDesc(){
        return postsService.findAllDesc();
    }
}
