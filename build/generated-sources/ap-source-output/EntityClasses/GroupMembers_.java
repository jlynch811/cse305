package EntityClasses;

import EntityClasses.GroupMembersPK;
import EntityClasses.Groups;
import EntityClasses.Users;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-12-04T16:38:58")
@StaticMetamodel(GroupMembers.class)
public class GroupMembers_ { 

    public static volatile SingularAttribute<GroupMembers, GroupMembersPK> groupMembersPK;
    public static volatile SingularAttribute<GroupMembers, Groups> groups;
    public static volatile SingularAttribute<GroupMembers, String> memberType;
    public static volatile SingularAttribute<GroupMembers, Users> users;

}