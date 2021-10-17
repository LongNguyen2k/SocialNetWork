/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhn.service;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Windows 10
 */
public interface StatsService {
    List<Object[]> categoryPostStats();
    List<Object[]> postStats(String kw,Date fromDate, Date toDate);
    List<Object[]> likeStats();
    List<Object[]> commentsStats();
    List<Object[]> commentDayMonthStat(String kw, Date fromDate, Date toDate);
    List<Object[]> reportPostStats();
    List<Object[]> reportCommentStats();
}
