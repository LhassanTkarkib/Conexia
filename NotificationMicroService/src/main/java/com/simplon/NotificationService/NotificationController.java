package com.simplon.NotificationService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;
    private static final Logger LOGGER = LogManager.getLogger(NotificationController.class);

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }
    @GetMapping
    public ResponseEntity<List<NotificationDTO>> getAllNotifications() {
        try {
            List<NotificationDTO> notifications = notificationService.getAllNotifications();
            if (!notifications.isEmpty()) {
                return new ResponseEntity<>(notifications, HttpStatus.OK);
            } else {
                LOGGER.warn("No notifications found");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            LOGGER.error("An error occurred while fetching all notifications", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/unread")
    public ResponseEntity<List<NotificationDTO>> getUnreadNotifications() {
        try {
            List<NotificationDTO> notifications = notificationService.getUnreadNotifications();
            if (!notifications.isEmpty()) {
                return new ResponseEntity<>(notifications, HttpStatus.OK);
            } else {
                LOGGER.warn("All notifications are read");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            LOGGER.error("An error occurred while fetching all notifications", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<NotificationDTO> createNotification(@RequestBody NotificationDTO notificationDTO) {
        try {
            NotificationDTO createdNotification = notificationService.createNotification(notificationDTO);
            if (createdNotification != null) {
                return new ResponseEntity<>(createdNotification, HttpStatus.CREATED);
            } else {
                LOGGER.error("Failed to create notification");
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            LOGGER.error("An error occurred while creating the notification", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationDTO> getNotificationById(@PathVariable Long id) {
        try {
            NotificationDTO notificationDTO = notificationService.getNotificationById(id);
            if (notificationDTO != null) {
                return new ResponseEntity<>(notificationDTO, HttpStatus.OK);
            } else {
                LOGGER.warn("Notification with ID {} not found", id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            LOGGER.error("An error occurred while fetching notification with ID " + id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/recipient/{recipientId}")
    public ResponseEntity<List<NotificationDTO>> getNotificationsByRecipientId(@PathVariable Long recipientId) {
        try {
            List<NotificationDTO> notifications = notificationService.getNotificationsByRecipientId(recipientId);
            if (!notifications.isEmpty()) {
                return new ResponseEntity<>(notifications, HttpStatus.OK);
            } else {
                LOGGER.warn("No notifications found for recipient with ID {}", recipientId);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            LOGGER.error("An error occurred while fetching notifications for recipient with ID " + recipientId, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotificationById(@PathVariable Long id) {
        try {
            notificationService.deleteNotificationById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            LOGGER.error("An error occurred while deleting the notification with ID " + id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAllNotifications() {
        try {
            notificationService.deleteAllNotifications();
            LOGGER.info("All notifications deleted successfully.");
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            LOGGER.error("An error occurred while deleting all notifications", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/markAsRead/{id}")
    public ResponseEntity<Void> markNotificationAsRead(@PathVariable Long id) {
        try {
            notificationService.markNotificationAsRead(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            LOGGER.error("An error occurred while marking the notification as read", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
