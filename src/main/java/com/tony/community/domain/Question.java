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
public class Question implements Serializable {

    private static final long serialVersionUID = 7307727403708948874L;
    /**
     * id
     */
    @TableId(type = IdType.UUID)
    private String id;
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
}

