package com.zju.nft.pojo.User;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterPojo {
    private String username;
    private String password;
    private List<String> classification;
}
