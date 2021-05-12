import models.posts.Post;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.PostsService;
import util.TestRunner;
import util.YamlUtil;

import static org.assertj.core.api.Assertions.assertThat;

public class PostServiceTest extends TestRunner {

    private final PostsService postsService = new PostsService();
    private final YamlUtil<Post> yamlParser = new YamlUtil<>();

    @Test
    @DisplayName("Verify that post can be created")
    public void verifyPostCreating() {
        Post newPost = yamlParser.parseYamlFile("new_post.yml", Post.class);
        postsService.createPost(newPost);
        Post retrievedPost = postsService.getPost(newPost.getId());
        assertThat(newPost).isEqualTo(retrievedPost);
    }

    @Test
    @DisplayName("Verify that post has been successfully updated")
    public void verifyPostUpdating() {
        Post updatedPost = yamlParser.parseYamlFile("updated_post.yml", Post.class);
        postsService.updatePost(updatedPost.getId(), updatedPost);
        Post retrievedPost = postsService.getPost(updatedPost.getId());

        assertThat(retrievedPost).isEqualTo(updatedPost);
    }

    @Test
    @DisplayName("Verify that post can be retrieved")
    public void verifyPostRetrieving() {
        Post existingPost = yamlParser.parseYamlFile("existing_post.yml", Post.class);
        Post retrievedPost = postsService.getPost(existingPost.getId());

        assertThat(retrievedPost).isEqualTo(existingPost);
    }
}
