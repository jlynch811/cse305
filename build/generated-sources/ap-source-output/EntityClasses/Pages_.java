package EntityClasses;

import EntityClasses.Groups;
import EntityClasses.Posts;
import EntityClasses.Users;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-29T18:10:36")
@StaticMetamodel(Pages.class)
public class Pages_ { 

    public static volatile SingularAttribute<Pages, String> pageType;
    public static volatile CollectionAttribute<Pages, Posts> postsCollection;
    public static volatile SingularAttribute<Pages, Groups> groupId;
    public static volatile SingularAttribute<Pages, Integer> postCount;
    public static volatile SingularAttribute<Pages, Integer> pageId;
    public static volatile SingularAttribute<Pages, Users> ownerId;

}