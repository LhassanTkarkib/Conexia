package com.simplon.NotificationService;

import java.util.List;
import java.util.Optional;

public interface NotificationService {
    NotificationDTO createNotification(NotificationDTO notificationDTO);
    NotificationDTO getNotificationById(Long id);
    List<NotificationDTO> getAllNotifications();
    void markNotificationAsRead(Long id);
    void deleteNotificationById(Long id);
    List<NotificationDTO> getNotificationsByRecipientId(Long recipientId);
    void deleteAllNotifications();
    List<NotificationDTO> getUnreadNotifications();


}
