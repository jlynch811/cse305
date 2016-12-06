package EntityClasses;

import EntityClasses.Users;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-12-05T23:56:04")
@StaticMetamodel(Employees.class)
public class Employees_ { 

    public static volatile SingularAttribute<Employees, Integer> ssnNo;
    public static volatile SingularAttribute<Employees, String> empType;
    public static volatile SingularAttribute<Employees, Integer> hourlyRate;
    public static volatile SingularAttribute<Employees, Integer> userId;
    public static volatile SingularAttribute<Employees, Date> startDate;
    public static volatile SingularAttribute<Employees, Users> users;

}