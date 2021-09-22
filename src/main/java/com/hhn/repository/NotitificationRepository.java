/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhn.repository;

import com.hhn.pojos.Notifications;
import com.hhn.pojos.User;
import java.util.List;

/**
 *
 * @author Windows 10
 */
public interface NotitificationRepository {
    List<Object[]> getListNotificationses(User userReceiVeNotify);
    boolean addNotifications(Notifications notifications);
}
