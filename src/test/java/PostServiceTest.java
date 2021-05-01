import models.Comment;
import models.Post;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.PostsService;
import util.FileUtil;
import util.TestRunner;
import util.YamlUtil;

import java.io.InputStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PostServiceTest extends TestRunner {

    private final PostsService postsService = new PostsService();

    @Test
    @DisplayName("Verify amount of the retrieved posts")
    public void verifyReceivingAllPosts() {
        List<Post> posts = postsService.getPosts();
        assertThat(posts.size()).isEqualTo(100);
    }

    @Test
    @DisplayName("Verify comments amount for the post")
    public void verifyReceivingPostComments() {
        List<Comment> postComments = postsService.getPostComments(1);
        assertThat(postComments.size()).isEqualTo(5);
    }

    @Test
    @DisplayName("Verify post creating")
    public void verifyPostCreating() {
        InputStream inputStream = FileUtil.readFile("/home/sovchenko/IdeaProjects/api_testing/src/main/resources/test_data/post.yaml");
        YamlUtil<Post> yamlParser = new YamlUtil<>();
        Post newPost = yamlParser.parseYaml(inputStream, Post.class);
        Post createdPost = postsService.createPost(newPost);
        // best practice to create post using api and then retrieve it and compare to originally created
        // unfortunately this service doesn't allow to do that
        assertThat(newPost).isEqualTo(createdPost);
    }

    @Test
    @DisplayName("Verify that post has been successfully updated")
    public void verifyPostUpdating() {
        int postId = 1;
        InputStream inputStream = FileUtil.readFile("/home/sovchenko/IdeaProjects/api_testing/src/main/resources/test_data/posts/updated_post.yaml");
        YamlUtil<Post> yamlParser = new YamlUtil<>();
        Post newPost = yamlParser.parseYaml(inputStream, Post.class);
        Post updatedPost = postsService.createPost(newPost);
        Post retrievedPost = postsService.updatePost(postId, updatedPost);
        // best practice to update post using api and then retrieve with GET it and check it is indeed updated
        // unfortunately this service doesn't allow to do that
        assertThat(retrievedPost).isEqualTo(updatedPost);
    }
}
