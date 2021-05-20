package models.posts;

import lombok.Data;
import models.AbstractModel;

@Data
public class Post extends AbstractModel {
    int userId;
    int id;
    String title;
    String body;
}
