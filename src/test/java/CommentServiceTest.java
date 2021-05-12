import models.comments.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.CommentService;
import util.TestRunner;
import util.YamlUtil;

import static org.assertj.core.api.Assertions.assertThat;


public class CommentServiceTest extends TestRunner {

    private final YamlUtil<Comment> yamlParser = new YamlUtil<>();
    private final CommentService service = new CommentService();

    @Test
    @DisplayName("Verify that comment can be retrieved")
    public void verifyCommentRetrieving() {
        Comment existingComment = yamlParser.parseYamlFile("existing_comment.yml", Comment.class);
        Comment retrievedComment = service.getCommentById(existingComment.getId());

        assertThat(existingComment).isEqualTo(retrievedComment);
    }
}
