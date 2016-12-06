package EntityClasses;

import EntityClasses.Sales;
import EntityClasses.Users;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-12-05T23:56:04")
@StaticMetamodel(Accounts.class)
public class Accounts_ { 

    public static volatile CollectionAttribute<Accounts, Sales> salesCollection;
    public static volatile SingularAttribute<Accounts, Integer> accountNo;
    public static volatile SingularAttribute<Accounts, Users> userId;

}