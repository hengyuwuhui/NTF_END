package com.zju.nft.pojo.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePWPojo {
    private String oldpassword;
    private String newpassword;
}