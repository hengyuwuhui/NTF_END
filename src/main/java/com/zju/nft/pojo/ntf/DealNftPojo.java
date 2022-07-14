package com.zju.nft.pojo.ntf;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DealNftPojo {
    private Integer id;
    private Integer nftId;
    private Integer risk_level;
}
