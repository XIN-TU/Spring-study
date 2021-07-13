import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import study.dao.BlogMapper;
import study.pojo.Blog;
import study.utils.IdUtiles;
import study.utils.MybatisUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MyTest {

    @Test
    public void addBook() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);

        Blog blog = new Blog();
        blog.setId(IdUtiles.getId());
        blog.setTitle("Mybatis如此简单");
        blog.setAuthor("狂神说");
        blog.setCreateTime(new Date());
        blog.setViews(9999);
        mapper.addBook(blog);

        blog.setId(IdUtiles.getId());
        blog.setTitle("Java如此简单");
        mapper.addBook(blog);

        blog.setId(IdUtiles.getId());
        blog.setTitle("Spring如此简单");
        mapper.addBook(blog);

        blog.setId(IdUtiles.getId());
        blog.setTitle("微服务如此简单");
        mapper.addBook(blog);

        sqlSession.close();
    }

    @Test
    public void queryBlogIf() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        HashMap map = new HashMap();
        map.put("title","Java如此简单");
        map.put("author","狂神说");
        List<Blog> blogs = mapper.queryBlogIf(map);
        for (Blog blog : blogs) {
            System.out.println(blog);
        }
        sqlSession.close();
    }

    @Test
    public void queryBlogChoose(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        HashMap map = new HashMap();
        map.put("title","Mybatis如此简单");
        map.put("views",9999);
        List<Blog> blogList = mapper.queryBlogChoose(map);
        for (Blog blog : blogList) {
            System.out.println(blog);
        }
        sqlSession.close();
    }

    @Test
    public void updateBlog(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        HashMap map = new HashMap();
//        map.put("title","Mybatis如此简单2");
        map.put("author","狂神说2");
        map.put("id","45ffb1541efb4918adaf3aea2ea65e2a");
        int i = mapper.updateBlog(map);
        if (i>0){
            System.out.println("update successfully");
        }
        sqlSession.close();

    }

    @Test
    public void queryBlogForeach(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        HashMap map = new HashMap();
        ArrayList<Integer> ids = new ArrayList<Integer>();
        ids.add(1);
        ids.add(2);
        map.put("ids",ids);
        List<Blog> blogs = mapper.queryBlogForeach(map);
        for (Blog blog : blogs) {
            System.out.println(blog);
        }
        sqlSession.close();
    }

}
