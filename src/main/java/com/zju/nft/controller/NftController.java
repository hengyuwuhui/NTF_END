package com.zju.nft.controller;

import com.zju.nft.annotations.UserLoginToken;
import com.zju.nft.entity.Response;
import com.zju.nft.pojo.User.RegisterPojo;
import com.zju.nft.pojo.ntf.DealNftPojo;
import com.zju.nft.pojo.ntf.FindNftPojo;
import com.zju.nft.service.NftService;
import com.zju.nft.service.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@CrossOrigin(originPatterns = "*", allowCredentials = "true", maxAge = 3600)
public class NftController {
    @Resource
    private NftService nftService;

    @PostMapping("/set_risk_level")
    @UserLoginToken
    public Response dealNft(@RequestBody DealNftPojo dealNftPojo){
        return nftService.dealNft(dealNftPojo);
    }

    @PostMapping("/findNft")
    @UserLoginToken
    public Response findNft(@RequestBody FindNftPojo findNftPojo){
        return nftService.findNft(findNftPojo);
    }

}
