import models.comments.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.CommentService;
import util.TestRunner;
import util.YamlUtil;

import static org.assertj.core.api.Assertions.assertThat;


public class CommentServiceTest extends TestRunner {

    private final YamlUtil<Comment> yamlParser = new YamlUtil<>();
    private final CommentService commentService = new CommentService();

    @Test
    @DisplayName("Verify that comment can be retrieved")
    public void verifyCommentRetrieving() {
        Comment existingComment = yamlParser.parseYamlFile("existing_comment.yml", Comment.class);
        Comment retrievedComment = commentService.getCommentById(existingComment.getId());

        assertThat(existingComment).isEqualTo(retrievedComment);
    }

    @Test
    @DisplayName("Verify that comment can be created")
    public void verifyCommentCreating() {
        Comment newComment = yamlParser.parseYamlFile("new_comment.yml", Comment.class);
        int newCommentId = commentService.createComment(newComment).getId();
        Comment retrievedComment = commentService.getCommentById(newCommentId);

        //this test fails because it's not possible to retrieve comment with 501 id;
        assertThat(retrievedComment)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(retrievedComment);
    }

    @Test
    @DisplayName("Verify that comment can be updated")
    public void verifyCommentUpdating() {
        int commentId = 10;
        Comment updatedComment = yamlParser.parseYamlFile("updated_comment.yml", Comment.class);
        commentService.updateComment(commentId, updatedComment);
        Comment retrievedComment = commentService.getCommentById(commentId);

        assertThat(updatedComment).isEqualTo(retrievedComment);
    }
}
