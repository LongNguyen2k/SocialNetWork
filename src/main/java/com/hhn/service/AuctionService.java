/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhn.service;

import com.hhn.pojos.Auctions;
import com.hhn.pojos.Post;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author Windows 10
 */
public interface AuctionService {
    List<Object[]> getBiddingInfoPost(String postid);
    boolean checkingStartPrice(BigDecimal biddingPrice,String postId);
    boolean addnewBiddingForPost(String username,String post_id, BigDecimal priceBidding , Auctions auctions);
    List<Auctions> getListOfBiddingFromPost(String postID);
    boolean checkBiddingPricePost(BigDecimal priceBidding , String strPostID);
    BigDecimal getMaxBiddingPrice(String postID);
    boolean chooseWinner(String username , String postID , BigDecimal biddingPrice, Timestamp biddingAt);
    boolean findWinner(String postID);
}
