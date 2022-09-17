package com.project.draw.domain.chat.service;

import com.project.draw.domain.chat.domain.repository.ChatRepository;
import com.project.draw.domain.chat.presentation.dto.response.ChatResponse;
import com.project.draw.domain.chat.presentation.dto.response.QueryChatListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QueryChatListService {

    private final ChatRepository chatRepository;

    @Transactional(readOnly = true)
    public QueryChatListResponse execute(Long roomId, Pageable pageable) {

        return new QueryChatListResponse(
                chatRepository.findByRoomIdOrderByIdDesc(roomId, pageable)
                        .stream()
                        .map(ChatResponse::of)
                        .collect(Collectors.toList())
        );
    }
}