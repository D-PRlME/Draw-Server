package com.project.draw.domain.post.service;

import com.project.draw.domain.post.domain.repository.PostRepository;
import com.project.draw.domain.post.presentation.dto.response.PostListResponse;
import com.project.draw.domain.post.presentation.dto.response.PostResponse;
import com.project.draw.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QueryMyPostService {

    private final PostRepository postRepository;
    private final UserFacade userFacade;

    @Transactional
    public PostListResponse execute(Sort sort) {

        List<PostResponse> myPostList = postRepository.findByUser(userFacade.getCurrentUser(), sort)
                .stream()
                .map(PostResponse::of)
                .collect(Collectors.toList());

        return new PostListResponse(myPostList);
    }
}