DROP TABLE IF EXISTS PostLikes, CommentLikes, Sales, Accounts, Preferences, Advertisements, Comments, Posts, Group_Members, Messages, Friends, FMPlusUsers, Employees, Pages, Groups, Users;

CREATE TABLE Users (
	UserId INTEGER AUTO_INCREMENT,
    EmailId VARCHAR(60) NOT NULL UNIQUE,
    Psswd VARCHAR(60) NOT NULL,
	FirstName VARCHAR(30) NOT NULL,
	LastName VARCHAR(30) NOT NULL,
	Address VARCHAR(150) NOT NULL,
	City VARCHAR(30) NOT NULL,
	State VARCHAR(30) NOT NULL,
	Zipcode INTEGER NOT NULL,
	Telephone BIGINT,
	UserType ENUM('Employee', 'FMUser') NOT NULL,
	PRIMARY KEY (UserId)
);

CREATE TABLE Employees (
	UserId INTEGER,
	SsnNo INTEGER NOT NULL,
	StartDate DATE NOT NULL,
	HourlyRate INTEGER,
	EmpType ENUM('Manager', 'Representative') NOT NULL,
	PRIMARY KEY(UserId),
	FOREIGN KEY (UserId) REFERENCES Users(UserId)
);


CREATE TABLE FMPlusUsers (
	UserId INTEGER,
	CreationDate DATE NOT NULL,
	CreditCardNumber BIGINT,
	Rating INTEGER DEFAULT 0,
	PRIMARY KEY (UserId),
	FOREIGN KEY (UserId) REFERENCES Users(UserId)
);

CREATE TABLE Friends (
	Friend1_Id INTEGER NOT NULL,
    Friend2_Id INTEGER NOT NULL,
    RequestStatus ENUM('Friends', 'Pending') NOT NULL,
    PRIMARY KEY (Friend1_Id, Friend2_Id),
    FOREIGN KEY (Friend1_Id) REFERENCES Users(UserId),
    FOREIGN KEY (Friend2_Id) REFERENCES Users(UserId)
);

CREATE TABLE Messages (
	MessageId INTEGER AUTO_INCREMENT,
	SentDate DATE NOT NULL,
	MsgSubject VARCHAR(50),
	MsgContent VARCHAR(250),
	SenderId INTEGER,
	ReceiverId INTEGER,
	CHECK (SenderId != ReceiverId),
	PRIMARY KEY (MessageId),
	FOREIGN KEY (SenderId) REFERENCES Users(UserId),
	FOREIGN KEY (ReceiverId) REFERENCES Users(UserId)
);

CREATE TABLE Groups (
	GroupId INTEGER AUTO_INCREMENT,
	GroupName VARCHAR(60) NOT NULL,
	GroupType ENUM('Club', 'Organization', 'Association'),
	OwnerId INTEGER,
	PRIMARY KEY (GroupId),
	FOREIGN KEY (OwnerId) REFERENCES Users(UserId)
);

CREATE TABLE Group_Members(
	MemberId INTEGER NOT NULL,
	GroupId INTEGER,
	MemberType ENUM('Member', 'Owner') NOT NULL,
	PRIMARY KEY (MemberId, GroupId),
	FOREIGN KEY (MemberId) REFERENCES Users(UserId),
	FOREIGN KEY (GroupId) REFERENCES Groups(GroupId) ON DELETE CASCADE
);

CREATE TABLE Pages(
	PageId INTEGER AUTO_INCREMENT,
	PageType ENUM('Personal', 'Group'),
	OwnerId INTEGER UNIQUE,
	GroupId INTEGER UNIQUE,
	PostCount INTEGER,
	PRIMARY KEY (PageId),
	FOREIGN KEY (OwnerId) REFERENCES Users(UserId),
	FOREIGN KEY (GroupId) REFERENCES Groups(GroupId) ON DELETE CASCADE
);

CREATE TABLE Posts (
	PostId INTEGER AUTO_INCREMENT,
	AuthorId INTEGER NOT NULL,
	PageId INTEGER NOT NULL,
	PostDate DATE NOT NULL,
	PostContent VARCHAR(250) NOT NULL,
	CmntCount INTEGER DEFAULT 0,
	LikeCount INTEGER DEFAULT 0,
	PRIMARY KEY (PostId),
	FOREIGN KEY (AuthorId) REFERENCES Users(UserId),
	FOREIGN KEY (PageId) REFERENCES Pages(PageId) ON DELETE CASCADE
); 
    
CREATE TABLE Comments (
	CommentId INTEGER AUTO_INCREMENT,
	AuthorId INTEGER NOT NULL,
	PostId INTEGER NOT NULL,
	CmntDate DATE NOT NULL,
	CmntContent VARCHAR(250) NOT NULL,
	LikeCount INTEGER DEFAULT 0,
	PRIMARY KEY (CommentId),
	FOREIGN KEY (AuthorId) REFERENCES Users(UserId),
	FOREIGN KEY (PostId) REFERENCES Posts(PostId) ON DELETE CASCADE
);

CREATE TABLE Advertisements (
	AdvId INTEGER AUTO_INCREMENT,
	EmployeeId INTEGER NOT NULL,
	AdvType ENUM('Cars', 'Clothing', 'Computer') NOT NULL,
	AdvDate DATE NOT NULL,
	Company VARCHAR(60) NOT NULL,
	ItemName VARCHAR(60),
	Price INTEGER,
	Content VARCHAR (250),
	UnitsAvailable INTEGER,
	PRIMARY KEY (AdvId),
	FOREIGN KEY (EmployeeId) REFERENCES Users(UserId)
);

CREATE TABLE Preferences (
	UserId INTEGER NOT NULL,
	PrefCategory ENUM('Cars', 'Clothing', 'Computer') NOT NULL,
	PRIMARY KEY (UserId, PrefCategory),
	FOREIGN KEY (UserId) REFERENCES Users(UserId));
    
CREATE TABLE Accounts (
	AccountNo INTEGER,
	UserId INTEGER NOT NULL,
	PRIMARY KEY (AccountNo),
	FOREIGN KEY (UserId) REFERENCES Users(UserId)
);

CREATE TABLE Sales (
	TransactionId INTEGER AUTO_INCREMENT,
	AdvId INTEGER NOT NULL,
	AccountNo INTEGER NOT NULL,
	TransactionDate DATE NOT NULL,
	NoOfUnits INTEGER NOT NULL,
	PRIMARY KEY (TransactionId),
	FOREIGN KEY (AdvId) REFERENCES Advertisements(AdvId),
	FOREIGN KEY (AccountNo) REFERENCES Accounts(AccountNo)
);

CREATE TABLE PostLikes (
	UserId INTEGER NOT NULL,
    PostId INTEGER NOT NULL,
    PRIMARY KEY (UserId, PostId),
    FOREIGN KEY (UserId) REFERENCES Users(UserId) ON DELETE CASCADE,
    FOREIGN KEY (PostId) REFERENCES Posts(PostId) ON DELETE CASCADE
    
);

CREATE TABLE CommentLikes (
	UserId INTEGER NOT NULL,
    CommentId INTEGER NOT NULL,
    PRIMARY KEY (UserId, CommentId),
    FOREIGN KEY (UserId) REFERENCES Users(UserId) ON DELETE CASCADE,
    FOREIGN KEY (CommentId) REFERENCES Comments(CommentId) ON DELETE CASCADE
    
);
	

# Users
INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("t.lohani@gmail.com", "abcdefgh", "Tarun", "Lohani", "Shivalik Nagar", "Haridwar", "Uttarakhand", 249403, 9458944328, "FMUser");
INSERT INTO FMPlusUsers VALUES (LAST_INSERT_ID(), "2012-08-12", NULL, 0);
INSERT INTO Pages(PageType, OwnerId, GroupId, PostCount) VALUES ("Personal", LAST_INSERT_ID(), NULL, 0);

INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("princear2111@gmail.com", "iamprincearora", "Prince", "Arora", "Jwalapur", "Haridwar", "Uttarakhand", 249408, 7813958568, "FMUser");
INSERT INTO FMPlusUsers VALUES (LAST_INSERT_ID(), "2015-10-20", 8427484929495967, 113);
INSERT INTO Pages(PageType, OwnerId, GroupId, PostCount) VALUES ("Personal", LAST_INSERT_ID(), NULL, 0);

INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("bharadwajk28@gmail.com", "kapdibhari", "Kapil", "Bharadwaj", "Sector 2", "Haridwar", "Uttarakhand", 249405, 8445858768, "FMUser");
INSERT INTO FMPlusUsers VALUES (LAST_INSERT_ID(), "2011-01-18", 9473859328345074, 0);
INSERT INTO Pages(PageType, OwnerId, GroupId, PostCount) VALUES ("Personal", LAST_INSERT_ID(), NULL, 0);

INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("ankitwalia1989@gmail.com", "ahluwalia", "Ankit", "Walia", "Shivalik Nagar", "Haridwar", "Uttarakhand", 249403, 8352749844, "FMUser");
INSERT INTO FMPlusUsers VALUES (LAST_INSERT_ID(), "2007-07-06", NULL, 0);
INSERT INTO Pages(PageType, OwnerId, GroupId, PostCount) VALUES ("Personal", LAST_INSERT_ID(), NULL, 0);

INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("preeti.bh89@gmail.com", "preetibhshiv", "Preeti", "Bhandari", "Sector 3", "Roorkee", "Uttar Pradesh", 244566, 9924845032, "FMUser");
INSERT INTO FMPlusUsers VALUES (LAST_INSERT_ID(), "2010-02-22", 2375885930334596, 89);
INSERT INTO Pages(PageType, OwnerId, GroupId, PostCount) VALUES ("Personal", LAST_INSERT_ID(), NULL, 0);

INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("sainisrishti27@gmail.com", "ssangel@130", "Srishti", "Saini", "Indra Nagar", "Roorkee", "Uttar Pradesh", 244578, 7166538760, "FMUser");
INSERT INTO FMPlusUsers VALUES (LAST_INSERT_ID(), "2009-06-12", NULL, 0);
INSERT INTO Pages(PageType, OwnerId, GroupId, PostCount) VALUES ("Personal", LAST_INSERT_ID(), NULL, 0);

INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("sumaiyya.khan@gmail.com", "humerkhan", "Sumaiyya", "Khan", "Kondapur", "Hyderabad", "Andhra Pradesh", 650097, 8265788910, "FMUser");
INSERT INTO FMPlusUsers VALUES (LAST_INSERT_ID(), "2008-10-08", 3885648959757780, 24);
INSERT INTO Pages(PageType, OwnerId, GroupId, PostCount) VALUES ("Personal", LAST_INSERT_ID(), NULL, 0);

INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("gksharma1203@gmail.com", "handsomegks", "Gaurav", "Sharma", "Mahadevpura", "Bangalore", "Karnataka", 560040, 9987234511, "FMUser");
INSERT INTO FMPlusUsers VALUES (LAST_INSERT_ID(), "2011-09-27", NULL, 0);
INSERT INTO Pages(PageType, OwnerId, GroupId, PostCount) VALUES ("Personal", LAST_INSERT_ID(), NULL, 0);

INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("abhicoolest@gmail.com", "chanduabhi", "Kumar", "Abhishek", "Mahadevpura", "Bangalore", "Karnataka", 560040, 9755468732, "FMUser");
INSERT INTO FMPlusUsers VALUES (LAST_INSERT_ID(), "2013-03-23", 4626298078875463, 95);
INSERT INTO Pages(PageType, OwnerId, GroupId, PostCount) VALUES ("Personal", LAST_INSERT_ID(), NULL, 0);

INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("jaineti1290@gmail.com", "iameti@1990", "Eti", "Jain", "Whitefields", "Hyderabad", "Andhra Pradesh", 650045, 8813877001, "FMUser");
INSERT INTO FMPlusUsers VALUES (LAST_INSERT_ID(), "2014-05-10", 5727859303887593, 143);
INSERT INTO Pages(PageType, OwnerId, GroupId, PostCount) VALUES ("Personal", LAST_INSERT_ID(), NULL, 0);

INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("aks.divekar24@gmail.com", "mumbaidiva", "Akshara", "Divekar", "Koramangala", "Bangalore", "Karnataka", 560034, 9533498981, "FMUser");
INSERT INTO FMPlusUsers VALUES (LAST_INSERT_ID(), "2008-12-17", 8305267759205839, 65);
INSERT INTO Pages(PageType, OwnerId, GroupId, PostCount) VALUES ("Personal", LAST_INSERT_ID(), NULL, 0);

INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("nishantkumar8907@gmail.com", "budhhadumka", "Nishant", "Kumar", "Bellandur", "Bangalore", "Karnataka", 561098, 9923437775, "FMUser");
INSERT INTO FMPlusUsers VALUES (LAST_INSERT_ID(), "2009-02-04", 0664882764528394, 0);
INSERT INTO Pages(PageType, OwnerId, GroupId, PostCount) VALUES ("Personal", LAST_INSERT_ID(), NULL, 0);

INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("kala.osin0706@gmail.com", "parifromeast", "Osin", "Kala", "Sector 4", "Haridwar", "Uttarakhand", 249404, 9834255490, "FMUser");
INSERT INTO FMPlusUsers VALUES (LAST_INSERT_ID(), "2013-06-21", 7224435009845676, 84);
INSERT INTO Pages(PageType, OwnerId, GroupId, PostCount) VALUES ("Personal", LAST_INSERT_ID(), NULL, 0);

INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("absiminz@gmail.com", "shaangudli@14", "Abhilasha", "Minz", "Telco Colony", "Jamshedpur", "Jharkhand", 332890, 8933470909, "FMUser");
INSERT INTO FMPlusUsers VALUES (LAST_INSERT_ID(), "2012-07-13", 5887309332765887, 37);
INSERT INTO Pages(PageType, OwnerId, GroupId, PostCount) VALUES ("Personal", LAST_INSERT_ID(), NULL, 0);

INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("gulati.verrukt@gmail.com", "iamgulati", "Sagar", "Gulati", "Lajpat Nagar", "Lucknow", "Uttar Pradesh", 435450, 7839889750, "FMUser");
INSERT INTO FMPlusUsers VALUES (LAST_INSERT_ID(), "2011-09-17", NULL, 0);
INSERT INTO Pages(PageType, OwnerId, GroupId, PostCount) VALUES ("Personal", LAST_INSERT_ID(), NULL, 0);

INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("divyabharati09@gmail.com", "hazribaagqueen", "Divya", "Bharti", "Adityapur", "Jamshedpur", "Jharkhand", 332876, 9285763418, "FMUser");
INSERT INTO FMPlusUsers VALUES (LAST_INSERT_ID(), "2008-11-09", 7535479992759309, 129);
INSERT INTO Pages(PageType, OwnerId, GroupId, PostCount) VALUES ("Personal", LAST_INSERT_ID(), NULL, 0);

INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("sushantojal0892@gmail.com", "iitroorkee", "Sushant", "Ojal", "Bishtupur", "Jamshedpur", "Jharkhand", 332899, 8355498012, "FMUser");
INSERT INTO FMPlusUsers VALUES (LAST_INSERT_ID(), "2015-07-25", 3665980052188957, 73);
INSERT INTO Pages(PageType, OwnerId, GroupId, PostCount) VALUES ("Personal", LAST_INSERT_ID(), NULL, 0);

INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("aggarwal.sneha28@gmail.com", "utaustinwin", "Sneha", "Aggarwal", "Adityapur", "Jamsedpur", "Jharkhand", 332876, 7876789902, "FMUser");
INSERT INTO FMPlusUsers VALUES (LAST_INSERT_ID(), "2012-03-14", 8872645558960408, 119);
INSERT INTO Pages(PageType, OwnerId, GroupId, PostCount) VALUES ("Personal", LAST_INSERT_ID(), NULL, 0);

INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("arpitmeh.luck15@gmail.com", "hr@xlri", "Arpit", "Mehrotra", "Gomti Nagar", "Lucknow", "Uttar Pradesh", 435480, 7724058300, "FMUser");
INSERT INTO FMPlusUsers VALUES (LAST_INSERT_ID(), "2010-11-13", NULL, 0);
INSERT INTO Pages(PageType, OwnerId, GroupId, PostCount) VALUES ("Personal", LAST_INSERT_ID(), NULL, 0);

INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("verma.kanika5136@gmail.com", "iim@ahemadabad", "Kanika", "Verma", "Sahara Ganj", "Lucknow", "Uttar Pradesh", 435427, 8834504887, "FMUser");
INSERT INTO FMPlusUsers VALUES (LAST_INSERT_ID(), "2008-04-02", 4886459066083959, 54);
INSERT INTO Pages(PageType, OwnerId, GroupId, PostCount) VALUES ("Personal", LAST_INSERT_ID(), NULL, 0);

INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("jayaangelthomas@gmail.com", "proudchrist", "Jaya", "Thomas", "Shivalik Nagar", "Haridwar", "Uttarakhand", 249403, 9767342328, "FMUser");
INSERT INTO FMPlusUsers VALUES (LAST_INSERT_ID(), "2010-10-30", NULL, 0);
INSERT INTO Pages(PageType, OwnerId, GroupId, PostCount) VALUES ("Personal", LAST_INSERT_ID(), NULL, 0);

INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("ming.dheeraj26@gmail.com", "machoman", "Dheeraj", "Minglani", "Jwalapur", "Haridwar", "Uttarakhand", 249408, 7866510988, "FMUser");
INSERT INTO FMPlusUsers VALUES (LAST_INSERT_ID(), "2014-08-13", 3546784929495233, 65);
INSERT INTO Pages(PageType, OwnerId, GroupId, PostCount) VALUES ("Personal", LAST_INSERT_ID(), NULL, 0);

INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("uniyalnaveenkv@gmail.com", "naaniitaly", "Naveen", "Uniyal", "Sector 2", "Haridwar", "Uttarakhand", 249405, 9884858541, "FMUser");
INSERT INTO FMPlusUsers VALUES (LAST_INSERT_ID(), "2011-01-18", 0094854354345074, 0);
INSERT INTO Pages(PageType, OwnerId, GroupId, PostCount) VALUES ("Personal", LAST_INSERT_ID(), NULL, 0);

INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("shikha.s149@gmail.com", "princessshikha", "Shikha", "Kashyap", "Shivalik Nagar", "Haridwar", "Uttarakhand", 249403, 9442746614, "FMUser");
INSERT INTO FMPlusUsers VALUES (LAST_INSERT_ID(), "2009-09-21", NULL, 0);
INSERT INTO Pages(PageType, OwnerId, GroupId, PostCount) VALUES ("Personal", LAST_INSERT_ID(), NULL, 0);

INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("ishaan.handsome89@gmail.com", "shantikehnanamanti", "Ishaan", "Sharma", "Sector 3", "Roorkee", "Uttar Pradesh", 244566, 7977645032, "FMUser");
INSERT INTO FMPlusUsers VALUES (LAST_INSERT_ID(), "2011-07-12", 9354685930887296, 53);
INSERT INTO Pages(PageType, OwnerId, GroupId, PostCount) VALUES ("Personal", LAST_INSERT_ID(), NULL, 0);

INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("galaxy.ar0912@gmail.com", "teachersday", "Galaxy", "Arora", "Indra Nagar", "Roorkee", "Uttar Pradesh", 244578, 988650923, "FMUser");
INSERT INTO FMPlusUsers VALUES (LAST_INSERT_ID(), "2011-02-22", NULL, 0);
INSERT INTO Pages(PageType, OwnerId, GroupId, PostCount) VALUES ("Personal", LAST_INSERT_ID(), NULL, 0);

INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("hardworkingza09@gmail.com", "iamzubair", "Zubair", "Alam", "Kondapur", "Hyderabad", "Andhra Pradesh", 650097, 7169984910, "FMUser");
INSERT INTO FMPlusUsers VALUES (LAST_INSERT_ID(), "2008-10-08", 0646788959665180, 49);
INSERT INTO Pages(PageType, OwnerId, GroupId, PostCount) VALUES ("Personal", LAST_INSERT_ID(), NULL, 0);

INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("goelgaurav0310@gmail.com", "splitpersonality", "Gaurav", "Goel", "Mahadevpura", "Bangalore", "Karnataka", 560040, 9347238711, "FMUser");
INSERT INTO FMPlusUsers VALUES (LAST_INSERT_ID(), "2010-04-24", NULL, 0);
INSERT INTO Pages(PageType, OwnerId, GroupId, PostCount) VALUES ("Personal", LAST_INSERT_ID(), NULL, 0);

INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("mishra.baibhaw@gmail.com", "dhanbadtoblore", "Baibhaw", "Mishra", "Mahadevpura", "Bangalore", "Karnataka", 560040, 8755468707, "FMUser");
INSERT INTO FMPlusUsers VALUES (LAST_INSERT_ID(), "2011-03-08", 3887698078879954, 128);
INSERT INTO Pages(PageType, OwnerId, GroupId, PostCount) VALUES ("Personal", LAST_INSERT_ID(), NULL, 0);

INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("vidisha.nitsurat@gmail.com", "cutegirlnit", "Vidisha", "Thapa", "Whitefields", "Hyderabad", "Andhra Pradesh", 650045, 8819934308, "FMUser");
INSERT INTO FMPlusUsers VALUES (LAST_INSERT_ID(), "2013-01-16", 9727857657887586, 12);
INSERT INTO Pages(PageType, OwnerId, GroupId, PostCount) VALUES ("Personal", LAST_INSERT_ID(), NULL, 0);

INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("vishwa.sushma04@gmail.com", "sriborionssir", "Sushma", "Vishwakarma", "Koramangala", "Bangalore", "Karnataka", 560034, 9840490581, "FMUser");
INSERT INTO FMPlusUsers VALUES (LAST_INSERT_ID(), "2013-02-19", 7305998059205449, 155);
INSERT INTO Pages(PageType, OwnerId, GroupId, PostCount) VALUES ("Personal", LAST_INSERT_ID(), NULL, 0);

INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("habib.ansarynit@gmail.com", "nithamirpur", "Habibullah", "Ansary", "Bellandur", "Bangalore", "Karnataka", 561098, 9855437741, "FMUser");
INSERT INTO FMPlusUsers VALUES (LAST_INSERT_ID(), "2009-08-16", 7688882594527761, 0);
INSERT INTO Pages(PageType, OwnerId, GroupId, PostCount) VALUES ("Personal", LAST_INSERT_ID(), NULL, 0);

INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("komalagg.brave@gmail.com", "iitkgp@engg", "Komal", "Aggarwal", "Sector 4", "Haridwar", "Uttarakhand", 249404, 7734258398, "FMUser");
INSERT INTO FMPlusUsers VALUES (LAST_INSERT_ID(), "2012-10-09", 9935435085445634,109);
INSERT INTO Pages(PageType, OwnerId, GroupId, PostCount) VALUES ("Personal", LAST_INSERT_ID(), NULL, 0);

INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("ankita.sri0389@gmail.com", "akkuhappyjsr", "Ankita", "Shrivastava", "Telco Colony", "Jamshedpur", "Jharkhand", 332890, 9443470449, "FMUser");
INSERT INTO FMPlusUsers VALUES (LAST_INSERT_ID(), "2010-05-27", 2899569332765530, 77);
INSERT INTO Pages(PageType, OwnerId, GroupId, PostCount) VALUES ("Personal", LAST_INSERT_ID(), NULL, 0);

INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("dineswor.maibam@gmail.com", "sribnortheast", "Maibam", "Dineswor", "Lajpat Nagar", "Lucknow", "Uttar Pradesh", 435450, 8819889760, "FMUser");
INSERT INTO FMPlusUsers VALUES (LAST_INSERT_ID(), "2009-07-07", NULL, 0);
INSERT INTO Pages(PageType, OwnerId, GroupId, PostCount) VALUES ("Personal", LAST_INSERT_ID(), NULL, 0);

INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("neha.beauty0390@gmail.com", "nitsurat@cse", "Neha", "Tomar", "Adityapur", "Jamshedpur", "Jharkhand", 332876, 9943760918, "FMUser");
INSERT INTO FMPlusUsers VALUES (LAST_INSERT_ID(), "2008-03-21", 9335475292759303, 69);
INSERT INTO Pages(PageType, OwnerId, GroupId, PostCount) VALUES ("Personal", LAST_INSERT_ID(), NULL, 0);

INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("rahulgupta.nit225@gmail.com", "adityapurtous", "Rahul", "Gupta", "Bishtupur", "Jamshedpur", "Jharkhand", 332899, 9326498952, "FMUser");
INSERT INTO FMPlusUsers VALUES (LAST_INSERT_ID(), "2015-09-11", 7765987324188982, 12);
INSERT INTO Pages(PageType, OwnerId, GroupId, PostCount) VALUES ("Personal", LAST_INSERT_ID(), NULL, 0);

INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("nivi.trendy@gmail.com", "ucsd@trendygirl", "Nivedita", "Prasad", "Adityapur", "Jamsedpur", "Jharkhand", 332876, 9876783492, "FMUser");
INSERT INTO FMPlusUsers VALUES (LAST_INSERT_ID(), "2012-11-29", 4522649658960328, 156);
INSERT INTO Pages(PageType, OwnerId, GroupId, PostCount) VALUES ("Personal", LAST_INSERT_ID(), NULL, 0);

INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("perni.advardhan@gmail.com", "restinpeace", "Aditya", "Vardhan", "Gomti Nagar", "Lucknow", "Uttar Pradesh", 435480, 9724056587, "FMUser");
INSERT INTO FMPlusUsers VALUES (LAST_INSERT_ID(), "2010-04-23", NULL, 0);
INSERT INTO Pages(PageType, OwnerId, GroupId, PostCount) VALUES ("Personal", LAST_INSERT_ID(), NULL, 0);

INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("ananya.roychowdhary@gmail.com", "nitjsrtotamu", "Ananya", "Roy", "Sahara Ganj", "Lucknow", "Uttar Pradesh", 435427, 9234576885, "FMUser");
INSERT INTO FMPlusUsers VALUES (LAST_INSERT_ID(), "2009-08-18", 6345459085483961, 176);
INSERT INTO Pages(PageType, OwnerId, GroupId, PostCount) VALUES ("Personal", LAST_INSERT_ID(), NULL, 0);


# Employee
# Representatives
INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("das.dipankar@gmail.com", "samsungking", "Dipankar", "Das", "Sakchi", "Jamshedpur", "Jharkhand", 332859, 8486749960, "Employee");
INSERT INTO Employees VALUES (LAST_INSERT_ID(), 800962749, "2009-01-26", 34, "Representative");

INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("sonika.sri58@gmail.com", "iiitbngalore", "Sonika", "Shrivastava", "Aliganj", "Lucknow", "Uttar Pradesh", 435466, 8983567741, "Employee");
INSERT INTO Employees VALUES (LAST_INSERT_ID(), 932892399, "2015-10-31", 30, "Representative");

INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("balugu.raju82@gmail.com", "chennailove", "Raju", "Balugu", "Begampet", "Hyderabad", "Andhra Pradesh", 650061, 8998378009, "Employee");
INSERT INTO Employees VALUES (LAST_INSERT_ID(), 628484829, "2016-09-06", 28, "Representative");

INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("manju.pari30@gmail.com", "akkatelugu", "Manjusha", "Parichi", "Jubliee Hills", "Hyderabad", "Andhra Pradesh", 650088, 8908578009, "Employee");
INSERT INTO Employees VALUES (LAST_INSERT_ID(), 882929384, "2012-04-28", 33, "Representative");

INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("uditverma.nitjsr@gmail.com", "bajasae", "Udit", "Verma", "Jugsalai", "Jamshedpur", "Jharkhand", 332877, 9995645032, "Employee");
INSERT INTO Employees VALUES (LAST_INSERT_ID(), 662399053, "2015-08-17", 30, "Representative");

INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("aks.rafugar06@gmail.com", "angel@aks", "Akshata", "Rafugar", "J P Nagar", "Bangalore", "Karnataka", 560075, 7199843060, "Employee");
INSERT INTO Employees VALUES (LAST_INSERT_ID(), 243846964, "2013-11-19", 32, "Representative");

INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("rane.pratik12@gmail.com", "mumbaidon", "Pratik", "Rane", "Brahampuri", "Haridwar", "Uttarakhand", 249415, 9009882657, "Employee");
INSERT INTO Employees VALUES (LAST_INSERT_ID(), 772353718, "2008-12-29", 39, "Representative");

INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("jyotijohri0188@gmail.com", "highheels", "Jyoti", "Johri", "CV Raman Nagar", "Bangalore", "Karnataka", 560029, 9009833511, "Employee");
INSERT INTO Employees VALUES (LAST_INSERT_ID(), 512148439, "2009-03-03", 37, "Representative");

INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("malays.july10@gmail.com", "iiitallahabad", "Malay", "Singh", "Tihri", "Haridwar", "Uttarkhand", 249408, 9043734391, "Employee");
INSERT INTO Employees VALUES (LAST_INSERT_ID(), 547574949, "2008-11-17", 38, "Representative");

INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("yadavtapasya.0205@gmail.com", "nitkurukshetra", "Tapasya", "Yadav", "Police Lines", "Lucknow", "Uttar Pradesh", 435423, 9554833511, "Employee");
INSERT INTO Employees VALUES (LAST_INSERT_ID(), 477823239, "2010-07-19", 36, "Representative");

INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("sameer.namdeo04@gmail.com", "sribradha", "Sameer", "Namdeo", "Sakchi", "Jamshedpur", "Jharkhand", 332859, 908675463, "Employee");
INSERT INTO Employees VALUES (LAST_INSERT_ID(), 934262949, "2009-09-23", 34, "Representative");

INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("sanchita.aggr89@gmail.com", "lovelysanchita", "Sanchita", "Aggarwal", "Aliganj", "Lucknow", "Uttar Pradesh", 435466, 9951167767, "Employee");
INSERT INTO Employees VALUES (LAST_INSERT_ID(), 540093451, "2015-03-14", 30, "Representative");

INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("bhavesh.patel23@gmail.com", "madhavpur@ah", "Bhavesh", "Patel", "Begampet", "Hyderabad", "Andhra Pradesh", 650061, 9228377559, "Employee");
INSERT INTO Employees VALUES (LAST_INSERT_ID(), 455389522, "2016-02-25", 28, "Representative");

INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("gargi.sahaneu@gmail.com", "mumbaitoneu", "Gargi", "Saha", "Jubliee Hills", "Hyderabad", "Andhra Pradesh", 650088, 9772578924, "Employee");
INSERT INTO Employees VALUES (LAST_INSERT_ID(), 435597280, "2012-09-15", 33, "Representative");

INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("parida.mousumi88@gmail.com", "peggasyssrib", "Mousumi", "Parida", "Jugsalai", "Jamshedpur", "Jharkhand", 332877, 8935645554, "Employee");
INSERT INTO Employees VALUES (LAST_INSERT_ID(), 722949833, "2015-02-05", 30, "Representative");


# Managers
INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("raj.roybrillint@gmail.com", "stylishraj", "Raj", "Roy", "Ferns City", "Bangalore", "Karnataka", 560037, 9947649960, "Employee");
INSERT INTO Employees VALUES (LAST_INSERT_ID(), 745527230, "2005-10-27", 45, "Manager");

INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("sandip.bh@gmail.com", "parabole", "Sandip", "Bhaumik", "Ranipur", "Haridwar", "Uttarakhand", 249476, 9898243277, "Employee");
INSERT INTO Employees VALUES (LAST_INSERT_ID(), 993272428, "2003-12-16", 48, "Manager");

INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("galisasi.mech@gmail.com", "konytoms", "Sashidhar", "Gali", "Manikonda", "Hyderabad", "Andhra Pradesh", 650041, 8900423709, "Employee");
INSERT INTO Employees VALUES (LAST_INSERT_ID(), 600387455, "2008-04-05", 42, "Manager");

INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("shahina.jin@gmail.com", "iimcalcutta", "Shahina", "Jindal", "Lajpat Nagar", "Lucknow", "Uttar Pradesh", 435450, 7899576775, "Employee");
INSERT INTO Employees VALUES (LAST_INSERT_ID(), 533287669, "2007-08-31", 44, "Manager");

INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("preetirani.deka24@gmail.com", "sribangalore", "Preeti", "Deka", "Silk Board", "Bangalore", "Karnataka", 560088, 8826344560, "Employee");
INSERT INTO Employees VALUES (LAST_INSERT_ID(), 822645659, "2006-11-17", 45, "Manager");

INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("raghunatha.reddy2@gmail.com", "iimcalcutta", "Kachana", "Reddy", "Medhalli", "Bangalore", "Karnataka", 560041, 9008433437, "Employee");
INSERT INTO Employees VALUES (LAST_INSERT_ID(), 955763785, "2006-06-06", 45, "Manager");

INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("prakhyat.hegde@gmail.com", "cheifengineer", "Prakhyat", "Hegde", "Jugsalai", "Jamshedpur", "Jharkhand", 332883, 9121265698, "Employee");
INSERT INTO Employees VALUES (LAST_INSERT_ID(), 533287669, "2008-04-12", 43, "Manager");

INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("palem.chandana@gmail.com", "techlead@kony", "Chandana", "Palem", "Ameerpet", "Hyderabad", "Andhra Pradesh", 650061, 9984535520, "Employee");
INSERT INTO Employees VALUES (LAST_INSERT_ID(), 733240096, "2006-09-26", 45, "Manager");

INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("gopi.redyy0510@gmail.com", "qaleadkony", "Gopinadha", "Reddy", "Kukatpally", "Hyderabad", "Andhra Pradesh", 650078, 7790943451, "Employee");
INSERT INTO Employees VALUES (LAST_INSERT_ID(), 546997246, "2008-01-22", 43, "Manager");

INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType)
VALUES ("yellamraju.harsha53@gmail.com", "independent", "Harsha", "Yellamaraju", "Jadugoda", "Jamshedpur", "Jharkhand", 332898, 8435411209, "Employee");
INSERT INTO Employees VALUES (LAST_INSERT_ID(), 166734307, "2005-09-17", 46, "Manager");

# SELECT * FROM Users;

# SELECT * FROM Employees;

# SELECT * FROM FMPlusUsers;

# SELECT * FROM Pages;

# TRUNCATE TABLE FMPlusUsers;


INSERT INTO Messages(SentDate, MsgSubject, MsgContent, SenderId, ReceiverId)
VALUES ("2016-01-09", "Hello", "Hi Prince, How are you??", 1, 2);

INSERT INTO Messages(SentDate, MsgSubject, MsgContent, SenderId, ReceiverId)
VALUES ("2016-01-10", NULL, "Hi Tarun. I am good. How are you?", 2, 1);

INSERT INTO Messages(SentDate, MsgSubject, MsgContent, SenderId, ReceiverId)
VALUES ("2016-01-26", "Accept Request", "Hi Eti, remember we met at Summaiya's B'day party. Please accept my friend request", 3, 10);

INSERT INTO Messages(SentDate, MsgSubject, MsgContent, SenderId, ReceiverId)
VALUES ("2016-01-15", "Notes", "Hey, I skipped last database lecture. Can you please share notes", 4, 1);

INSERT INTO Messages(SentDate, MsgSubject, MsgContent, SenderId, ReceiverId)
VALUES ("2016-01-16", NULL, "Yea sure, I have mailed you", 1, 4);

INSERT INTO Messages(SentDate, MsgSubject, MsgContent, SenderId, ReceiverId)
VALUES ("2016-02-02", "Let's Party", "Hey man. You have any plans today? Let's party at my place in the evening",8, 9);

INSERT INTO Messages(SentDate, MsgSubject, MsgContent, SenderId, ReceiverId)
VALUES ("2016-02-02", NULL, "Hi. Sure, tell me when should I come", 9, 8);

INSERT INTO Messages(SentDate, MsgSubject, MsgContent, SenderId, ReceiverId)
VALUES ("2016-02-02", NULL, "My last lecture will be over by 5:30. I'll reach home by 6. You can come over anytime after 6.", 8, 9);

INSERT INTO Messages(SentDate, MsgSubject, MsgContent, SenderId, ReceiverId)
VALUES ("2016-03-12", "Hppy Birthday", "Happy B'day, have fun :)", 12, 11);

INSERT INTO Messages(SentDate, MsgSubject, MsgContent, SenderId, ReceiverId)
VALUES ("2016-03-12", NULL, "Hey, thanks a lot.", 11, 12);

INSERT INTO Messages(SentDate, MsgSubject, MsgContent, SenderId, ReceiverId)
VALUES ("2016-04-15", "Exam syllabus", "Hey!! Can you tell me where has professor posted the syllabus for the midterm exam?", 15, 22);

INSERT INTO Messages(SentDate, MsgSubject, MsgContent, SenderId, ReceiverId)
VALUES ("2016-04-15", NULL, "Yeah it is on sullstice. Check in the announcements", 22, 15);

INSERT INTO Messages(SentDate, MsgSubject, MsgContent, SenderId, ReceiverId)
VALUES ("2016-04-15", NULL, "Ohh, okk. Thanks man!", 15, 22);

INSERT INTO Messages(SentDate, MsgSubject, MsgContent, SenderId, ReceiverId)
VALUES ("2016-04-15", NULL, "Welcome :)", 22, 15);

INSERT INTO Messages(SentDate, MsgSubject, MsgContent, SenderId, ReceiverId)
VALUES ("2016-04-27", "Project discussion", "Hi Galaxy. Shall we discuss about the Project phase 2 after the lecture tomorrow if you're noy busy", 32, 26);

INSERT INTO Messages(SentDate, MsgSubject, MsgContent, SenderId, ReceiverId)
VALUES ("2016-04-28", NULL, "Hey sorry I couldn't reply yesterday, I slept early. Sure, we can meet after the lecture to discuss things", 26, 32);

INSERT INTO Messages(SentDate, MsgSubject, MsgContent, SenderId, ReceiverId)
VALUES ("2016-04-28", NULL, "No problem. Ok, see ya after the lecture.", 32, 26);

INSERT INTO Messages(SentDate, MsgSubject, MsgContent, SenderId, ReceiverId)
VALUES ("2016-05-12", NULL, "Hey Maibam, are you in touch with gaurav. I don't have his munber. Wanted to talk with him.", 32, 35);

INSERT INTO Messages(SentDate, MsgSubject, MsgContent, SenderId, ReceiverId)
VALUES ("2016-05-13", NULL, "Try this number - 9347238711. I am not sure if this number is still active or not. I also talked to him long back", 35, 32);

INSERT INTO Messages(SentDate, MsgSubject, MsgContent, SenderId, ReceiverId)
VALUES ("2016-05-13", NULL, "Ok, I thought you guys would be in touch. Anyways I'll try to contact him with this number. Tx :)", 32, 35);

INSERT INTO Messages(SentDate, MsgSubject, MsgContent, SenderId, ReceiverId)
VALUES ("2016-05-13", NULL, "No man, I have been a bit busy these days.", 35, 32);

INSERT INTO Messages(SentDate, MsgSubject, MsgContent, SenderId, ReceiverId)
VALUES ("2016-06-17", "Happy Anniversry", "Wish you a very very happy Anniversary :)", 31, 37);

INSERT INTO Messages(SentDate, MsgSubject, MsgContent, SenderId, ReceiverId)
VALUES ("2016-06-19", NULL, "Hey, thanks a lot :) We went to Kodaikanal trip this weekend to celebrate, didn't see your message. How are you?", 37, 31);

INSERT INTO Messages(SentDate, MsgSubject, MsgContent, SenderId, ReceiverId)
VALUES ("2016-06-19", NULL, "Ohh, wow.. great. I am good, will be coming to Jamshedpur this Diwali. Let's plan to meet, been a long time", 31, 37);

INSERT INTO Messages(SentDate, MsgSubject, MsgContent, SenderId, ReceiverId)
VALUES ("2016-06-19", NULL, "Ohh, that's great. Yeah will meet for sure. Message me when you reach here.", 37, 31);

# SELECT * FROM Messages;

INSERT INTO Groups(GroupName, GroupType, OwnerId)
VALUES ("Chelsea", "Club", 1);

INSERT INTO Groups(GroupName, GroupType, OwnerId)
VALUES ("Barcelona", "Club", 1);

INSERT INTO Groups(GroupName, GroupType, OwnerId)
VALUES ("Real Madrid", "Club", 3);

INSERT INTO Groups(GroupName, GroupType, OwnerId)
VALUES ("Liverpool", "Club", 5);

INSERT INTO Groups(GroupName, GroupType, OwnerId)
VALUES ("Bayern Munchen", "Club", 8);

INSERT INTO Groups(GroupName, GroupType, OwnerId)
VALUES ("Arsenal", "Club", 8);

INSERT INTO Groups(GroupName, GroupType, OwnerId)
VALUES ("Manchaster United", "Club", 10);

INSERT INTO Groups(GroupName, GroupType, OwnerId)
VALUES ("Manchaster City", "Club", 11);

INSERT INTO Groups(GroupName, GroupType, OwnerId)
VALUES ("Atletico Madrid", "Club", 11);

INSERT INTO Groups(GroupName, GroupType, OwnerId)
VALUES ("Juventus", "Club", 11);

INSERT INTO Groups(GroupName, GroupType, OwnerId)
VALUES ("Paris Saint Germain", "Club", 4);

INSERT INTO Groups(GroupName, GroupType, OwnerId)
VALUES ("Roma", "Club", 3);

Select * from Fmplususers;
Select * from Employees;
Select * from Users;