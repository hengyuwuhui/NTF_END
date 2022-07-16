package com.zju.nft.service;

import com.zju.nft.entity.Response;
import com.zju.nft.entity.nft.IboxNft;
import com.zju.nft.entity.user.User;
import com.zju.nft.mapper.NftMapper;
import com.zju.nft.pojo.ntf.DealNftPojo;
import com.zju.nft.pojo.ntf.FindNftPojo;
import com.zju.nft.utils.Pair;
import com.zju.nft.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class NftService {

    @Resource
    private TokenService tokenService;

    @Autowired
    private NftMapper nftMapper;

    public Response dealNft(DealNftPojo dealNftPojo){
        Pair<User, Response> pair = tokenService.checkToken();
        Response check = pair.getValue();
        if (check != null){
            log.info("token error: " + TokenUtil.getTokenContent());
            log.info("msg: " + check.getMessage());
            return Response.fail(1000,"用户验证不通过",null);
        }
        String[] dir = {"ibox_records", "opensea_records", "NFT_CHINA"};
        String findName = dir[dealNftPojo.getNftId()];
        try {
            nftMapper.updateNftRisk(findName, dealNftPojo.getRisk_level(), dealNftPojo.getId());
        } catch (Exception e){
            return Response.fail(1012,"处理失败，请联系后台管理员",null);
        }

        String successMessage = String.format("nft藏品处理成功");
        log.info(successMessage);
        return Response.success(successMessage, null);
    }

    public Response findNft(FindNftPojo findNftPojo){
        Pair<User, Response> pair = tokenService.checkToken();
        Response check = pair.getValue();
        if (check != null){
            log.info("token error: " + TokenUtil.getTokenContent());
            log.info("msg: " + check.getMessage());
            return Response.fail(1000,"用户验证不通过",null);
        }

        String[] dir = {"ibox_records", "opensea_records", "NFT_CHINA"};
   
        String findName = dir[findNftPojo.getNftId()];
        List<IboxNft> ret = nftMapper.findAllNft(findName, 0);  //0为无意义但必要参数

        String successMessage = String.valueOf(ret.size());

        return Response.success(successMessage, ret);
    }
}
