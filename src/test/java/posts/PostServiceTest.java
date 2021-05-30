package posts;

import models.posts.Post;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.PostsService;
import util.YamlUtil;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PostServiceTest {

    private final PostsService postsService = new PostsService();
    private final YamlUtil<Post> yamlParser = new YamlUtil<>();

    @Test
    @DisplayName("Verify that post can be created")
    public void verifyPostCreating() {
        String newPostYaml = "new_post.yml";
        Post newPost = yamlParser.parseYamlFile(newPostYaml, Post.class);
        postsService.createPost(newPost);
        Post retrievedPost = postsService.getPost(newPost.getId());

        assertThat(newPost).isEqualTo(retrievedPost);
    }

    @Test
    @DisplayName("Verify that post has been successfully updated")
    public void verifyPostUpdating() {
        String updatedPostYaml = "updated_post.yml";
        Post updatedPost = yamlParser.parseYamlFile(updatedPostYaml, Post.class);
        postsService.updatePost(updatedPost.getId(), updatedPost);
        Post retrievedPost = postsService.getPost(updatedPost.getId());

        assertThat(retrievedPost).isEqualTo(updatedPost);
    }

    @Test
    @DisplayName("Verify that post can be retrieved")
    public void verifyPostRetrieving() {
        String existingPostYaml = "existing_post.yml";
        Post existingPost = yamlParser.parseYamlFile(existingPostYaml, Post.class);
        Post retrievedPost = postsService.getPost(existingPost.getId());

        assertThat(retrievedPost).isEqualTo(existingPost);
    }

    @Test
    @DisplayName("Verify that post can be removed")
    public void verifyPostRemoving() {
        String postToBeRemovedYaml = "removed_post.yml";
        Post newPost = yamlParser.parseYamlFile(postToBeRemovedYaml, Post.class);
        postsService.createPost(newPost);
        postsService.deletePost(newPost.getId());
        List<Post> allPosts = postsService.getPosts();

        //this test will always failed, because it's not possible to actually delete posts for this service
        assertThat(allPosts)
                .isNotEmpty()
                .doesNotContain(newPost);
    }
}
