package com.project.draw.domain.chat.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.draw.domain.chat.domain.Chat;
import com.project.draw.domain.user.domain.User;
import com.project.draw.domain.user.presentation.dto.response.UserResponse;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ChatResponse {

    @JsonProperty("room_id")
    private Long roomId;
    private UserResponse user;
    @JsonProperty("sent_at")
    private LocalDateTime sentAt;
    private String message;

    public static ChatResponse of(Chat chat) {

        User user = chat.getUser();

        return ChatResponse
                .builder()
                .roomId(chat.getRoom().getId())
                .user(UserResponse.of(user))
                .message(chat.getMessage())
                .sentAt(chat.getCreatedAt().toLocalDateTime())
                .build();
    }
}