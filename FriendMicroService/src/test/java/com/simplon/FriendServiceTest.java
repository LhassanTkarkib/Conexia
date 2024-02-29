package com.simplon;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class FriendServiceTest {

    @Mock
    private FriendShipRepository friendShipRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private FriendShipService friendShipService;

    @Test
    @DisplayName("Test-Get-All-Friend-Requested-Accepted-Rejected")
    void testGetAllFriendRequestedAcceptedRejected() {

        FriendShipEntity friendShip1 = new FriendShipEntity();
        friendShip1.setFriendshipId(1L);
        friendShip1.setUserId(1L);
        friendShip1.setFriendId(5L);
        friendShip1.setDeleted(false);
        friendShip1.setDateAddition(LocalDate.now());
        friendShip1.setStatus(StatusFriendEnum.REQUESTED);


        FriendShipEntity friendShip2 = new FriendShipEntity();
        friendShip2.setFriendshipId(2L);
        friendShip2.setUserId(2L);
        friendShip2.setFriendId(5L);
        friendShip2.setDeleted(false);
        friendShip2.setDateAddition(LocalDate.now());
        friendShip2.setStatus(StatusFriendEnum.ACCEPTED);

        FriendShipEntity friendShip3 = new FriendShipEntity();
        friendShip3.setFriendshipId(3L);
        friendShip3.setUserId(6L);
        friendShip3.setFriendId(2L);
        friendShip3.setDeleted(false);
        friendShip3.setDateAddition(LocalDate.now());
        friendShip3.setStatus(StatusFriendEnum.REJECTED);

        List<FriendShipEntity> mockFriendShip = Arrays.asList(friendShip1, friendShip2,friendShip3);
        when(friendShipRepository.findByDeletedFalse()).thenReturn(mockFriendShip);


        FriendShipDto friendShipDto1 = new FriendShipDto();
        friendShipDto1.setFriendshipId(1L);
        friendShipDto1.setUserId(1L);
        friendShipDto1.setFriendId(5L);
        friendShipDto1.setDeleted(false);
        friendShipDto1.setDateAddition(LocalDate.now());
        friendShipDto1.setStatus(StatusFriendEnum.REQUESTED);


        FriendShipDto friendShipDto2 = new FriendShipDto();
        friendShipDto2.setFriendshipId(2L);
        friendShipDto2.setUserId(2L);
        friendShipDto2.setFriendId(5L);
        friendShipDto2.setDeleted(false);
        friendShipDto2.setDateAddition(LocalDate.now());
        friendShipDto2.setStatus(StatusFriendEnum.ACCEPTED);

        FriendShipDto friendShipDto3 = new FriendShipDto();
        friendShipDto3.setFriendshipId(3L);
        friendShipDto3.setUserId(6L);
        friendShipDto3.setFriendId(2L);
        friendShipDto3.setDeleted(true);
        friendShipDto3.setDateAddition(LocalDate.now());
        friendShipDto3.setStatus(StatusFriendEnum.REJECTED);

        when(modelMapper.map(friendShip1, FriendShipDto.class)).thenReturn(friendShipDto1);
        when(modelMapper.map(friendShip2, FriendShipDto.class)).thenReturn(friendShipDto2);
        when(modelMapper.map(friendShip3, FriendShipDto.class)).thenReturn(friendShipDto3);

        List<FriendShipDto> resultFriendShipDtos = friendShipService.getAllFriendRequestedAcceptedRejected();

        assertEquals(mockFriendShip.size(), resultFriendShipDtos.size());
        assertEquals(mockFriendShip.get(0).getUserId(), resultFriendShipDtos.get(0).getUserId());
        assertEquals(mockFriendShip.get(0).getFriendId(), resultFriendShipDtos.get(0).getFriendId());
        assertEquals(mockFriendShip.get(0).getStatus(), resultFriendShipDtos.get(0).getStatus());
        assertEquals(mockFriendShip.get(0).getDateAddition(), resultFriendShipDtos.get(0).getDateAddition());
        assertEquals(mockFriendShip.get(0).isDeleted(), resultFriendShipDtos.get(0).isDeleted());

        assertEquals(mockFriendShip.size(), resultFriendShipDtos.size());
        assertEquals(mockFriendShip.get(1).getUserId(), resultFriendShipDtos.get(1).getUserId());
        assertEquals(mockFriendShip.get(1).getFriendId(), resultFriendShipDtos.get(1).getFriendId());
        assertEquals(mockFriendShip.get(1).getStatus(), resultFriendShipDtos.get(1).getStatus());
        assertEquals(mockFriendShip.get(1).getDateAddition(), resultFriendShipDtos.get(1).getDateAddition());
        assertEquals(mockFriendShip.get(1).isDeleted(), resultFriendShipDtos.get(1).isDeleted());

        assertEquals(mockFriendShip.size(), resultFriendShipDtos.size());
        assertEquals(mockFriendShip.get(2).getUserId(), resultFriendShipDtos.get(2).getUserId());
        assertEquals(mockFriendShip.get(2).getFriendId(), resultFriendShipDtos.get(2).getFriendId());
        assertEquals(mockFriendShip.get(2).getStatus(), resultFriendShipDtos.get(2).getStatus());
        assertEquals(mockFriendShip.get(2).getDateAddition(), resultFriendShipDtos.get(2).getDateAddition());
        assertEquals(mockFriendShip.get(2).isDeleted(), resultFriendShipDtos.get(2).isDeleted());

        verify(friendShipRepository, times(1)).findByDeletedFalse();
        verify(modelMapper, times(mockFriendShip.size())).map(any(FriendShipEntity.class), eq(FriendShipDto.class));

    }

    @Test
    void testGetAllFriendAccepted() {

    }

    @Test
    void testGetAllFriendRequested() {

    }

    @Test
    void testGetAllFriendRejected() {

    }

    @Test
    void testSaveNewFriend() {

    }

    @Test
    void testUpdateFriendShip() {

    }

    @Test
    void testDeleteFriendByUserIdAndFriendId() {

    }

    @Test
    void testSearchSpecificFriend() {

    }


}
