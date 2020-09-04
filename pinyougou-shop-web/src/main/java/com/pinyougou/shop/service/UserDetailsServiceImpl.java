package com.pinyougou.shop.service;

import com.pinyougou.pojo.TbSeller;
import com.pinyougou.sellergoods.service.SellerService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class UserDetailsServiceImpl implements UserDetailsService {

    private SellerService sellerService;

    public SellerService getSellerService() {
        return sellerService;
    }

    public void setSellerService(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /**
         * 用户权限的集合
         */
        List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
        /**
         * 权限是死的，用户是 roll_seller，准确的说是该用户的角色，这个可以按照用户名去查询
         */
        grantedAuths.add(new SimpleGrantedAuthority("ROLE_SELLER"));
        /**
         * 单家的username就是商家的id  sellerId
         */
        TbSeller seller = sellerService.findOne(username);
        if(seller!=null){
            if(seller.getStatus().equals("1")){
                return new User(username,seller.getPassword(),grantedAuths);
            }else{
                return null;
            }
        }else{
            return null;
        }
        /**
         * 返回用户，user，需要的是用户和密码，权限集合
         */
        //return new User(username,"123456", grantedAuths);
    }
}
