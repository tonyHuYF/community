package com.tony.community.domain.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationVo<T> {
    private List<T> data;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer page;
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPage;

    public void setPagination(Integer totalPage, Integer page) {
        this.totalPage = totalPage;
        this.page = page;

        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) {
                pages.add(0, page - i);
            }

            if (page + i <= totalPage) {
                pages.add(page + i);
            }
        }

        // 是否展示上一页
        this.showPrevious = page == 1 ? false : true;
        // 是否展示下一页
        this.showNext = page == totalPage ? false : true;
        // 是否展示第一页
        this.showFirstPage = pages.contains(1) ? false : true;
        // 是否展示最后一页
        this.showEndPage = pages.contains(totalPage) ? false : true;
    }
}
