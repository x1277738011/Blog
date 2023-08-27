package com.blog.mapper;

import com.blog.domain.Blog;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BlogMapper {
    /**
     * 删除博客
     * @param id
     * @return
     */
    @Delete("delete from blog where id=#{id} and uid=#{uid}")
    int deleteBlog(Integer id,Integer uid);

    /**
     * 添加博客
     * @param blog
     * @return
     */
    @Insert("insert into blog(title,content,uid,state) values(#{title},#{content},#{uid},#{state})")
    Integer insertBlog(Blog blog);
    /**
     * 关于文章内容或者标题的修改
     *
     * @param blog
     * @return
     */
    @Update("update blog set title=#{title},content=#{content},updatetime=now() " +
            "where id=#{id} and uid=#{uid}")
    Integer updateBlog(Blog blog);
    /**
     * 对于浏览量的更新
     *
     * @param aid
     * @return
     */
    @Update("update blog set rcount=rcount+1 where id=#{aid} and state=1;")
    Integer updateRcount(Integer aid);
    /**
     * 分页展示
     *
     * @param precount 每页需要展示的博客条数
     * @param start    从第几条博客开始
     * @return
     */
    @Select("select * from blog where state=1 order by rcount desc limit #{start},#{precount}")
    List<Blog> getAllArt(Integer precount, Integer start);

    /**
     * 获取文章总数
     * @return
     */
    @Select("select count(*) from blog where state=1 and uid=#{uid}")
    Integer getNum(Integer uid);

    /**
     * 获取草稿
     * @param uid
     * @return
     */
    @Select("select * from blog where uid=#{uid} and state=0 order by updatetime desc;")
    List<Blog> getAllDraft(Integer uid);

    /**
     * 获取属于用户的草稿总数
     * @param uid
     * @return
     */
    @Select("select count(*) from blog where uid=#{uid} and state=0")
    Integer getMyDraftCountByUid(Integer uid);

    /**
     * 提交草稿
     * @param id
     * @param uid
     * @return
     */
    @Update("update blog set state=1 where id=#{id} and uid=#{uid}")
    Integer updateDraft(Integer id, Integer uid);

    /**
     * 获取博客
     * @param uid
     * @return
     */
    @Select("select * from blog where uid=#{uid} and state=1;")
    List<Blog> getMyList(Integer uid);

    /**
     * 文章详情
     * @param id
     * @return
     */
    @Select("select * from blog where id=#{id}")
    Blog getBlogDetail(Integer id);

    @Select("select count(*) from blog where state=1")
    Integer getAllist();


}
