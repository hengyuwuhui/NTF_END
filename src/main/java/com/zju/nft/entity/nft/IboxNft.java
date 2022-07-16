package com.zju.nft.entity.nft;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IboxNft {
    private Integer id;
    private String name;
    private Date create_time;
    private String author_id;
    private String author;
    private String mark;
    private String address;
    private float price;
    private Integer total_number;
    private Integer risk_level;
    private String url;

}
