package EntityClasses;

import EntityClasses.Users;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-29T18:10:36")
@StaticMetamodel(Fmplususers.class)
public class Fmplususers_ { 

    public static volatile SingularAttribute<Fmplususers, BigInteger> creditCardNumber;
    public static volatile SingularAttribute<Fmplususers, Integer> rating;
    public static volatile SingularAttribute<Fmplususers, Date> creationDate;
    public static volatile SingularAttribute<Fmplususers, Integer> userId;
    public static volatile SingularAttribute<Fmplususers, Users> users;

}