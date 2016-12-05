package EntityClasses;

import EntityClasses.Comments;
import EntityClasses.Pages;
import EntityClasses.Users;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-12-04T16:38:58")
@StaticMetamodel(Posts.class)
public class Posts_ { 

    public static volatile SingularAttribute<Posts, Integer> cmntCount;
    public static volatile SingularAttribute<Posts, String> postContent;
    public static volatile SingularAttribute<Posts, Date> postDate;
    public static volatile SingularAttribute<Posts, Integer> likeCount;
    public static volatile SingularAttribute<Posts, Integer> postId;
    public static volatile CollectionAttribute<Posts, Comments> commentsCollection;
    public static volatile SingularAttribute<Posts, Users> authorId;
    public static volatile SingularAttribute<Posts, Pages> pageId;

}