/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhn.service.impl;


import com.hhn.pojos.Auctions;
import com.hhn.pojos.Post;
import com.hhn.pojos.User;
import com.hhn.repository.AuctionRespository;
import com.hhn.repository.PostRepository;
import com.hhn.repository.UserRepository;
import com.hhn.service.AuctionService;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Windows 10
 */
@Service
@Transactional
public class AuctionServiceImpl implements AuctionService{
    @Autowired
    private AuctionRespository auctionRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<Object[]> getBiddingInfoPost(String postid) {
        int postID = Integer.parseInt(postid);
        return this.auctionRepository.getBiddingInfo(postID);
    }

    @Override
    public boolean checkingStartPrice(BigDecimal biddingPrice,String postId) {
        int postID = Integer.parseInt(postId);
        Object[] auctionInfoPost = this.auctionRepository.getBiddingInfo(postID).get(0);
        int result = biddingPrice.compareTo((BigDecimal) auctionInfoPost[9]);
        if(result == 1  )  // giá tiền truyền vào lớn hơn giá tiền giá khởi đầu
            return true;
        else if(result == -1 || result == 0 )
            return false;  // giá trị truyền vào nhỏ hơn giá khởi đầu hoặc giá trị truyền vào = giá tiền khởi đầu 
        return false;
    }
    
    @Override
    public boolean addnewBiddingForPost(String username,String post_id, BigDecimal priceBidding  , Auctions auctions) {
        User user = this.userRepository.getCurrentLoggedInUser(username).get(0);
        Post post = this.postRepository.getPostId(post_id);
        boolean biddingStatus = false;
        Calendar cal = Calendar.getInstance();
        auctions.setBiddingAt(new Timestamp(cal.getTimeInMillis()));
        auctions.setBiddingStatus(biddingStatus);
        auctions.setBiddingPrice(priceBidding);
        auctions.setBiddingUser(user);
        auctions.setBiddingPost(post);
        return this.auctionRepository.addBiddingPrice(auctions);
        
    }

    @Override
    public List<Auctions> getListOfBiddingFromPost(String postId) {
        Post post = this.postRepository.getPostId(postId);
        return this.auctionRepository.getListOfBiddingFromPost(post);
        
    }

    @Override
    public boolean checkBiddingPricePost(BigDecimal priceBidding , String strPostID) {
        // lấy ra đối tượng auctions có giá đấu giá cao nhất . 
        Post post = this.postRepository.getPostId(strPostID);
        BigDecimal maxPrice = this.auctionRepository.getMaxAuctionPrice(post);
        if(maxPrice == null)
        {
            return true;
        }
        else
        {
        if( priceBidding.compareTo(maxPrice) == 1 ) {
            return true;
        }
        else if(priceBidding.compareTo(maxPrice)== -1 || priceBidding.compareTo(maxPrice) == 0 ){
            return false;
        }
        return false;
        }
        
    }

    @Override
    public BigDecimal getMaxBiddingPrice(String postID) {
        Post post = this.postRepository.getPostId(postID);
        return this.auctionRepository.getMaxAuctionPrice(post);
    }

    @Override
    public boolean chooseWinner(String username , String postID , BigDecimal biddingPrice, Timestamp biddingAt) {
         User user = this.userRepository.getCurrentLoggedInUser(username).get(0);
        Post post = this.postRepository.getPostId(postID);
        Auctions auctionObject = this.auctionRepository.findAuctionMethod(user, post, biddingPrice, biddingAt);
        auctionObject.setBiddingStatus(true);
        return this.auctionRepository.chooseWiner(auctionObject);
        
    }

    @Override
    public boolean findWinner(String postID) {
        Post post = this.postRepository.getPostId(postID);
       List<Auctions> auctionObject = this.auctionRepository.findAuctionAlreadyWinner(post);
        if(auctionObject.isEmpty()) // nếu như chưa có ai giành  chiến thắng thì được phép đấu giá và được phép chọn người chiến thắng
            return false;
        else  // nếu như đã có rồi thì ko được phép đấu giá cũng như không được phép chọn người chiến thắng 
            return true;
    }

    @Override
    public boolean checkHaveAuction(Post postHaveCurrentAuction) {
       List<Auctions> auctionChecked = this.auctionRepository.checkHaveAuction(postHaveCurrentAuction);
       if(auctionChecked.isEmpty()) // chưa có ai tham gia đấu giá thì trả ra false
           return false;
       else
           return true;
    }
    
    
}
