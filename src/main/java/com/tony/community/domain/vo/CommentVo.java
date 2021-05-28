package com.tony.community.domain.vo;

import com.tony.community.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentVo implements Serializable {

    private static final long serialVersionUID = 7307727403708948874L;
    /**
     * id
     */
    private String id;
    /**
     * 父类id
     */
    private String parentId;
    /**
     * 类别，0：1级评论，1：2级评论
     */
    private int type;
    /**
     * 评论人id
     */
    private String commentator;
    /**
     * 点赞数
     */
    private Integer likeCount;
    /**
     * 评论
     */
    private String content;
    /**
     * 评论数
     */
    private Integer commentCount;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 关联的User
     */
    private User user;
}
