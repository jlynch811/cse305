package EntityClasses;

import EntityClasses.Sales;
import EntityClasses.Users;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-12-05T23:56:04")
@StaticMetamodel(Advertisements.class)
public class Advertisements_ { 

    public static volatile CollectionAttribute<Advertisements, Sales> salesCollection;
    public static volatile SingularAttribute<Advertisements, Date> advDate;
    public static volatile SingularAttribute<Advertisements, String> itemName;
    public static volatile SingularAttribute<Advertisements, Integer> unitsAvailable;
    public static volatile SingularAttribute<Advertisements, Integer> price;
    public static volatile SingularAttribute<Advertisements, Integer> advId;
    public static volatile SingularAttribute<Advertisements, String> advType;
    public static volatile SingularAttribute<Advertisements, String> company;
    public static volatile SingularAttribute<Advertisements, Users> employeeId;
    public static volatile SingularAttribute<Advertisements, String> content;

}