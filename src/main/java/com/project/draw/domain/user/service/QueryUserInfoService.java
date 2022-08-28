package com.project.draw.domain.user.service;

import com.project.draw.domain.user.domain.User;
import com.project.draw.domain.user.facade.UserFacade;
import com.project.draw.domain.user.presentation.dto.response.QueryUserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class QueryUserInfoService {

    private final UserFacade userFacade;

    @Transactional(readOnly = true)
    public QueryUserInfoResponse execute(Long userId) {

        User user = userFacade.getUserById(userId);

        return QueryUserInfoResponse
                .builder()
                .name(user.getName())
                .profileImageUrl(user.getProfileImageUrl())
                .build();
    }
}