package cn.episooo.datastructurewebapplication.config;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/1/26 0:34
 * @Description：
 */
public class PageHandler {
    private int pagenum;
    private int pagesize;

    public int getPagenum() {
        return (pagenum-1) * pagesize;
    }

    public PageHandler setPagenum(int pagenum) {
        this.pagenum = pagenum;
        return this;
    }

    public int getPagesize() {
        return pagesize;
    }

    public PageHandler setPagesize(int pagesize) {
        this.pagesize = pagesize;
        return this;
    }
}
