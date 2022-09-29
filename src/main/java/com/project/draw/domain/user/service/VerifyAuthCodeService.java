package com.project.draw.domain.user.service;

import com.project.draw.domain.user.facade.AuthCodeFacade;
import com.project.draw.domain.user.presentation.dto.request.VerifyAuthCodeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class VerifyAuthCodeService {

    private final AuthCodeFacade authCodeFacade;

    @Transactional
    public void execute(VerifyAuthCodeRequest request){

        String authCode = request.getAuthCode();
        String email = request.getEmail();

        authCodeFacade.verifyAuthCode(authCode, email);
    }

}