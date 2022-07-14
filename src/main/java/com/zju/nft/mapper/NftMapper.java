package com.zju.nft.mapper;

import com.zju.nft.entity.nft.IboxNft;
import com.zju.nft.entity.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface NftMapper {

    @Select("SELECT * FROM ${nftName} WHERE id != #{id} LIMIT 50")
    List<IboxNft> findAllNft(String nftName, Integer id);

    @Update("update ${nftName} set risk_level = #{risk_level} WHERE id = #{id}")
    void updateNftRisk(String nftName, Integer risk_level, Integer id);


}
