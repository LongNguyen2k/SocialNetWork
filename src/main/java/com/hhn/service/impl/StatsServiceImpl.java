/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhn.service.impl;

import com.hhn.repository.StatsRepository;
import com.hhn.service.StatsService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Windows 10
 */
@Service
public class StatsServiceImpl implements StatsService{
    @Autowired
    private StatsRepository statsRepository;
    @Override
    public List<Object[]> categoryPostStats() {
        return this.statsRepository.categoryPostStats();
    }

    @Override
    public List<Object[]> postStats(String kw, Date fromDate, Date toDate) {
       return this.statsRepository.postStats(kw, fromDate, toDate);
    }

    @Override
    public List<Object[]> likeStats() {
        return this.statsRepository.likeStats();
    }

    @Override
    public List<Object[]> commentsStats() {
        return this.statsRepository.commentsStats();
    }

    @Override
    public List<Object[]> commentDayMonthStat(String kw, Date fromDate, Date toDate) {
       return this.statsRepository.commentDayMonthStat(kw, fromDate, toDate);
    }

    @Override
    public List<Object[]> reportPostStats() {
       return this.statsRepository.reportPostStats();
    }

    @Override
    public List<Object[]> reportCommentStats() {
        return this.statsRepository.reportCommentStats();
    }
 
}
