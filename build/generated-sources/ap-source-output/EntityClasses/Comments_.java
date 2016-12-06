package EntityClasses;

import EntityClasses.Posts;
import EntityClasses.Users;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-12-05T23:56:04")
@StaticMetamodel(Comments.class)
public class Comments_ { 

    public static volatile SingularAttribute<Comments, Integer> commentId;
    public static volatile SingularAttribute<Comments, Integer> likeCount;
    public static volatile SingularAttribute<Comments, Posts> postId;
    public static volatile SingularAttribute<Comments, Users> authorId;
    public static volatile SingularAttribute<Comments, Date> cmntDate;
    public static volatile SingularAttribute<Comments, String> cmntContent;

}