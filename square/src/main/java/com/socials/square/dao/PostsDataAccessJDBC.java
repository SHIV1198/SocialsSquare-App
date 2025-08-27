package com.socials.square.dao;

import com.socials.square.models.Post;
import com.socials.square.models.PostsMessageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository("JDBC_Dao_Repository")
public class PostsDataAccessJDBC implements PostsDAO {

    private final JdbcTemplate jdbcTemplate;

    public PostsDataAccessJDBC(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Post> postRowMapper() {
        return (rs, rowNum) -> Post.builder()
                .id(rs.getInt("id"))
                .userId(rs.getString("user_id"))
                .content(rs.getString("content"))
                .build();
    }


    @Override
    public List<Post> findAllPostsByUserId(String userId) {
        log.info("JDBCTemplate - Getting all posts for user: {}", userId);
        String sql = "SELECT * FROM test_posts WHERE user_id = ?";
//        RowMapper mapper = (rs, rowNum) -> {
//            Post post = Post.builder()
//                    .id(rs.getInt("id"))
//                    .userId(rs.getString("user_id"))
//                    .content(rs.getString("content"))
//                    .build();
//            return post;
//        };
        List<Post> userPosts = jdbcTemplate.query(sql, postRowMapper(), userId);
        return userPosts;
    }

    @Override
    public void createNewPost(Post newPost) {
        log.info("JDBCTemplate - Creating new post for user: {}", newPost.getUserId());

        //Since id is auto generated, you get error if persist w/o it in jdbc so get it first'
        Long nextId = jdbcTemplate.queryForObject("SELECT nextval('test_posts_seq')", Long.class);

        var sql = "Insert into test_posts (id, user_id, content) values (?, ?, ?)";
        int update = jdbcTemplate.update(sql, nextId, newPost.getUserId(), newPost.getContent());
        if(update == 0){
            throw new RuntimeException("Failed to create new post");
        }
    }

    @Override
    public void deletePost(PostsMessageDTO post) {
        log.info("JDBCTemplate - Deleting post for user: {}", post.userId());
        var sql = "Delete from test_posts where user_id = ? and content = ?";
        int update = jdbcTemplate.update(sql, post.userId(), post.content());
        if(update == 0){
            throw new RuntimeException("Failed to delete post");
        }
    }
}
