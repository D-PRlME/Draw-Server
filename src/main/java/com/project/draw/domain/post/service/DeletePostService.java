package com.project.draw.domain.post.service;

import com.project.draw.domain.post.domain.repository.PostRepository;
import com.project.draw.domain.post.facade.PostFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeletePostService {
    private final PostFacade postFacade;
    private final PostRepository postRepository;

    public void execute(Long id) {
        postRepository.delete(postFacade.getPostById(id));
    }
}
