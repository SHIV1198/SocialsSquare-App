package com.socials.square.dao;

import com.socials.square.models.Post;
import com.socials.square.models.PostsMessageDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Service class that provides data access operations for Posts by interacting with
 * the underlying repository layer. This class implements the {@code PostsDAO} interface.
 *
 * The primary responsibility of this class is to delegate data retrieval operations
 * to the {@code PostsRepository} and ensure that business logic, if any, is properly
 * handled before returning the data.
 *
 * In future if you have different implementation for DB operations, just create a new DAO class and mark that as Primary
 */
@Repository
public class PostsDataAccessService implements PostsDAO{

    private final PostsRepository postsRepository;

    public PostsDataAccessService(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    @Override
    public List<Post> findAllPostsByUserId(String userId) {
        List<Post> allByUserId = postsRepository.findAllByUserId(userId);
        return allByUserId;
    }

    @Override
    public void createNewPost(Post newPost) {
        postsRepository.save(newPost);
    }

    @Override
    public void deletePost(PostsMessageDTO post) {
        postsRepository.deleteByUserIdAndContent(post.userId(), post.content());
    }
}
