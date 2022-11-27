package com.project.draw.domain.chat.service;

import com.project.draw.domain.chat.domain.Chat;
import com.project.draw.domain.chat.domain.Room;
import com.project.draw.domain.chat.domain.RoomUser;
import com.project.draw.domain.chat.domain.repository.ChatRepository;
import com.project.draw.domain.chat.facade.RoomUserFacade;
import com.project.draw.domain.chat.presentation.dto.response.QueryChatListResponse;
import com.project.draw.domain.user.domain.User;
import com.project.draw.domain.user.facade.UserFacade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.util.ReflectionTestUtils.setField;

@ExtendWith(MockitoExtension.class)
class QueryChatListServiceTest {

    @Mock
    private ChatRepository chatRepository;

    @Mock
    private RoomUserFacade roomUserFacade;

    @Mock
    private UserFacade userFacade;

    @InjectMocks
    private QueryChatListService service;

    @Test
    public void 채팅_리스트_조회_성공() {
        //given
        Long roomId = 1L;
        List<Chat> arrayList = Collections.emptyList();

        User user = User.builder().build();
        setField(user, "id", 1L);
        Room room = Room.builder().build();
        RoomUser roomUser = RoomUser.builder().user(user).room(room).build();
        LocalDateTime localDateTime = LocalDateTime.of(2022, 1, 31, 00, 00 ,00);

        given(userFacade.getCurrentUser()).willReturn(user);
        given(roomUserFacade.getById(any(), any())).willReturn(roomUser);
        given(chatRepository.findTop100ByRoomAndCreatedAtBeforeOrderByIdAsc(room, localDateTime)).willReturn(arrayList);

        //when
        QueryChatListResponse response = service.execute(roomId, localDateTime);
    }

}