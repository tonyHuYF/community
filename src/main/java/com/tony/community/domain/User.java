package com.tony.community.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 7307727403708948874L;
    /**
     * id
     */
    @TableId(type = IdType.UUID)
    private String id;
    /**
     * 账户Id
     */
    private String accountId;
    /**
     * 用户名
     */
    private String name;
    /**
     * token令牌
     */
    private String token;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 个人简历
     */
    private String bio;
    /**
     * 头像路径
     */
    private String avatarUrl;

}
