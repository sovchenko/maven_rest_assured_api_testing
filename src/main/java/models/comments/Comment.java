package models.comments;

import lombok.Data;
import models.AbstractModel;

@Data
public class Comment extends AbstractModel {
    int postId;
    String name;
    String email;
    String body;
    int id;
}