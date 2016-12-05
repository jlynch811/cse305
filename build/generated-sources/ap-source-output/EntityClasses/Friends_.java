package EntityClasses;

import EntityClasses.FriendsPK;
import EntityClasses.Users;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-12-05T00:32:16")
@StaticMetamodel(Friends.class)
public class Friends_ { 

    public static volatile SingularAttribute<Friends, Users> users1;
    public static volatile SingularAttribute<Friends, String> requestStatus;
    public static volatile SingularAttribute<Friends, Users> users;
    public static volatile SingularAttribute<Friends, FriendsPK> friendsPK;

}