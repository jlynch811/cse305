package EntityClasses;

import EntityClasses.Users;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-29T18:10:36")
@StaticMetamodel(Messages.class)
public class Messages_ { 

    public static volatile SingularAttribute<Messages, Date> sentDate;
    public static volatile SingularAttribute<Messages, Users> senderId;
    public static volatile SingularAttribute<Messages, String> msgContent;
    public static volatile SingularAttribute<Messages, Users> receiverId;
    public static volatile SingularAttribute<Messages, String> msgSubject;
    public static volatile SingularAttribute<Messages, Integer> messageId;

}