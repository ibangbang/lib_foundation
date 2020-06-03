package lib.foundation.data.pagination;

import java.util.List;

public class Pagination<T> {

    public Pagination(int page, List<T> data) {
        this.page = page;
        setData(data);
    }

    public int page = 1;
    private int pageSize = 20;
    private boolean more = true;
    public int totalPage;
    public List<T> data;

    public static <T> Pagination<T> newInstance() {
        return newInstance(1, null);
    }

    public static <T> Pagination<T> newInstance(int page, List<T> data) {
        return new Pagination<>(page, data);
    }

    public static <T> Pagination<T> newDefaultInstance() {
        return new Pagination<>(1, null);
    }

    public boolean hasMore() {
        return more;
    }

    public boolean isFirstPage() {
        return page == 1;
    }

    public int currentPage() {
        return page;
    }

    public int nextPage() {
        return page + 1;
    }

    public void setData(List<T> dl) {
        this.data = dl;
        if (dl != null && dl.size() >= pageSize) {
            more = true;
        } else {
            more = false;
        }
    }

    public boolean hasData() {
        return data != null && data.size() > 0;
    }

    public void pagePlus() {
        page++;
    }

    public void reset() {
        page = 1;
        data = null;
        more = true;
    }

}
