package EntityClasses;

import EntityClasses.Accounts;
import EntityClasses.Advertisements;
import EntityClasses.Comments;
import EntityClasses.Employees;
import EntityClasses.Fmplususers;
import EntityClasses.Friends;
import EntityClasses.GroupMembers;
import EntityClasses.Groups;
import EntityClasses.Messages;
import EntityClasses.Pages;
import EntityClasses.Posts;
import EntityClasses.Preferences;
import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-29T18:10:36")
@StaticMetamodel(Users.class)
public class Users_ { 

    public static volatile SingularAttribute<Users, String> lastName;
    public static volatile CollectionAttribute<Users, Preferences> preferencesCollection;
    public static volatile CollectionAttribute<Users, Friends> friendsCollection;
    public static volatile SingularAttribute<Users, String> city;
    public static volatile CollectionAttribute<Users, GroupMembers> groupMembersCollection;
    public static volatile SingularAttribute<Users, String> emailId;
    public static volatile CollectionAttribute<Users, Messages> messagesCollection1;
    public static volatile SingularAttribute<Users, Pages> pages;
    public static volatile CollectionAttribute<Users, Accounts> accountsCollection;
    public static volatile CollectionAttribute<Users, Posts> postsCollection;
    public static volatile SingularAttribute<Users, String> state;
    public static volatile CollectionAttribute<Users, Comments> commentsCollection;
    public static volatile CollectionAttribute<Users, Messages> messagesCollection;
    public static volatile CollectionAttribute<Users, Friends> friendsCollection1;
    public static volatile SingularAttribute<Users, String> address;
    public static volatile SingularAttribute<Users, BigInteger> telephone;
    public static volatile CollectionAttribute<Users, Advertisements> advertisementsCollection;
    public static volatile SingularAttribute<Users, Integer> userId;
    public static volatile SingularAttribute<Users, Integer> zipcode;
    public static volatile SingularAttribute<Users, String> firstName;
    public static volatile SingularAttribute<Users, Fmplususers> fmplususers;
    public static volatile SingularAttribute<Users, String> userType;
    public static volatile CollectionAttribute<Users, Groups> groupsCollection;
    public static volatile SingularAttribute<Users, Employees> employees;
    public static volatile SingularAttribute<Users, String> psswd;

}