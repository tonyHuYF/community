package com.tony.community.domain.vo;

import com.tony.community.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionVo {
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String description;
    /**
     * 标签
     */
    private String tag;
    /**
     * 作者
     */
    private String creator;
    /**
     * 浏览数
     */
    private Integer viewCount;
    /**
     * '评论数'
     */
    private Integer commentCount;
    /**
     * '点赞数'
     */
    private Integer likeCount;
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
