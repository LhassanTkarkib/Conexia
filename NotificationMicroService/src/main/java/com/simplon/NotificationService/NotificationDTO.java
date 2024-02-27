package com.simplon.NotificationService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class NotificationDTO {
    private Long idNotif;
    private String contentNotif;
    private String typeNotif;
    private Boolean isRead;
    private Long senderId;
    private Long recipientId;
    private Date dateNotif;

}
