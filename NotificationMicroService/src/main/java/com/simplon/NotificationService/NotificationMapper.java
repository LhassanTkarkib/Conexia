package com.simplon.NotificationService;

import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface NotificationMapper {
    NotificationDTO toDTO(Notification e);
    Notification toEntity(NotificationDTO o);
}
