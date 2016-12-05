package EntityClasses;

import EntityClasses.Accounts;
import EntityClasses.Advertisements;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-12-05T00:32:16")
@StaticMetamodel(Sales.class)
public class Sales_ { 

    public static volatile SingularAttribute<Sales, Integer> noOfUnits;
    public static volatile SingularAttribute<Sales, Accounts> accountNo;
    public static volatile SingularAttribute<Sales, Advertisements> advId;
    public static volatile SingularAttribute<Sales, Date> transactionDate;
    public static volatile SingularAttribute<Sales, Integer> transactionId;

}