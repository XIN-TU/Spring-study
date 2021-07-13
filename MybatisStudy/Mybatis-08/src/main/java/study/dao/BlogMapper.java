package study.dao;

import study.pojo.Blog;

import java.util.List;
import java.util.Map;

public interface BlogMapper {

    // 插入数据
    int addBook(Blog blog);

    // 查询博客
    List<Blog> queryBlogIf(Map map);

    List<Blog> queryBlogChoose(Map map);

    // 更新博客
    int updateBlog(Map map);

    List<Blog> queryBlogForeach(Map map);
}

