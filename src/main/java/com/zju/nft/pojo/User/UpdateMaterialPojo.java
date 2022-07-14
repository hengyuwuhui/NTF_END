package com.zju.nft.pojo.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMaterialPojo {
    private String mail;
    private String tel;
    private String addr;
}