package EntityClasses;

import EntityClasses.GroupMembers;
import EntityClasses.Pages;
import EntityClasses.Users;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-12-05T00:32:16")
@StaticMetamodel(Groups.class)
public class Groups_ { 

    public static volatile SingularAttribute<Groups, String> groupName;
    public static volatile SingularAttribute<Groups, String> groupType;
    public static volatile SingularAttribute<Groups, Pages> pages;
    public static volatile SingularAttribute<Groups, Integer> groupId;
    public static volatile CollectionAttribute<Groups, GroupMembers> groupMembersCollection;
    public static volatile SingularAttribute<Groups, Users> ownerId;

}