import models.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.CommentService;
import util.TestRunner;
import util.YamlUtil;

import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;


public class CommentServiceTest extends TestRunner {

    @Test
    @DisplayName("Verify that that comment for the post can be created")
    public void verifyContentOfTheCreatedComment() {
//        InputStream commentFile = FileUtil.readFile("/home/sovchenko/IdeaProjects/api_testing/src/main/resources/test_data/comment.yaml");
        InputStream commentFile = getClass().getResourceAsStream("config.properties");
        YamlUtil<Comment> commentParser = new YamlUtil<>();
        Comment initiallyCreatedComment = commentParser.parseYaml(commentFile, Comment.class);

        CommentService service = new CommentService();
        Comment retrievedComment = service.getCommentById(1);

        assertThat(initiallyCreatedComment).isEqualTo(retrievedComment);
    }
}
