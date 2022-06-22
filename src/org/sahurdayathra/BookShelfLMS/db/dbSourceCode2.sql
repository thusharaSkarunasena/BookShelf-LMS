#************************************************* Creating Database *********************************************

#----------------------------------------------------- BookShelfLMS DataBase  ------------------------------------

CREATE DATABASE IF NOT EXISTS BookShelfLMS;
USE BookShelfLMS;




#************************************************ Creating Tables ***********************************************

#----------------------------------------------------- UserAcc Table --------------------------------------------

CREATE TABLE IF NOT EXISTS userAcc(
	userID VARCHAR (30) NOT NULL,
	name VARCHAR (90) NOT NULL,
	username VARCHAR (30) NOT NULL,
	password VARCHAR (30) NOT NULL,
        state VARCHAR (30) NOT NULL,
	CONSTRAINT PRIMARY KEY (userID, password)
) ENGINE = InnoDB;



#---------------------------------------------------- Members Table ----------------------------------------------

CREATE TABLE IF NOT EXISTS members(
	libRegNO VARCHAR (30) NOT NULL,
	admissionNo VARCHAR (30) NOT NULL,
	name VARCHAR (90) NOT NULL,
	address_no VARCHAR(30),
	address_street VARCHAR(60),
	address_village VARCHAR(60),
	address_city VARCHAR(60), 
	contactNO VARCHAR(30),
	otherDetail VARCHAR (240),
	CONSTRAINT PRIMARY KEY (libRegNO)
) ENGINE = InnoDB;



#----------------------------------------------------- Publisher Table  ------------------------------------------

CREATE TABLE IF NOT EXISTS publisher(
	pubID VARCHAR (30) NOT NULL,
	name VARCHAR (90) NOT NULL,
	address_no VARCHAR(30),
	address_street VARCHAR(60),
	address_village VARCHAR(60),
	address_city VARCHAR(60), 
	email VARCHAR (60),
	contactNO VARCHAR (30),
	otherDetail VARCHAR (240),
	CONSTRAINT PRIMARY KEY (pubID)
) ENGINE = InnoDB;



#--------------------------------------------------- Book Category Table  -----------------------------------------

CREATE TABLE IF NOT EXISTS bookCategory(
	bookCatCode VARCHAR (30) NOT NULL,
	name VARCHAR (90) NOT NULL,
	details VARCHAR (240),
	CONSTRAINT PRIMARY KEY (bookCatCode)
) ENGINE = InnoDB;



#----------------------------------------------------- Book Table  ------------------------------------------------

CREATE TABLE IF NOT EXISTS book(
	bookCode VARCHAR (30) NOT NULL,
	ISBN_NO VARCHAR (30) ,
	bookTitle VARCHAR (180) NOT NULL,
	edition VARCHAR (60),
	bookCatCode VARCHAR (30) NOT NULL,
	authorName VARCHAR (90) NOT NULL,
	pubID VARCHAR (30) NOT NULL,
	price DOUBLE (10,2),
	otherDetails VARCHAR (240),
    status VARCHAR (30) NOT NULL,
	CONSTRAINT PRIMARY KEY (bookCode),
	CONSTRAINT FOREIGN KEY (bookCatCode) REFERENCES bookCategory(bookCatCode),
	CONSTRAINT FOREIGN KEY (pubID) REFERENCES publisher(pubID)
) ENGINE = InnoDB;



#------------------------------------------------------ Borrow Table  -----------------------------------------------

CREATE TABLE IF NOT EXISTS borrow(
	borrowID VARCHAR (30) NOT NULL,
	libRegNO VARCHAR (30) NOT NULL,
	bookCode VARCHAR (30) NOT NULL,
	userID VARCHAR (30) NOT NULL,
	issuedDate DATE NOT NULL,
	dueDate DATE NOT NULL,
	returnedDate DATE NOT NULL,
	CONSTRAINT PRIMARY KEY (borrowID),
	CONSTRAINT FOREIGN KEY (libRegNO) REFERENCES members(libRegNO),
	CONSTRAINT FOREIGN KEY (bookCode) REFERENCES book(bookCode),
	CONSTRAINT FOREIGN KEY (userID) REFERENCES userAcc(userID)
) ENGINE = InnoDB;




#************************************************ Creating Procedures **********************************************

#-------------------------------------- Inizialize User Account Table Procedure --------------------------------------

DROP PROCEDURE IF EXISTS inizializeDBTables;
DELIMITER $$;
CREATE PROCEDURE inizializeDBTables(
)
BEGIN
        INSERT INTO userAcc(
			userID,
			name,
			username,
			password,
			state
		) VALUES (
			'USR.0000',
			'Thushara Supun ~ (KAIRA)',
			'kaira',
			'kaira@1997',
			'superadmin'
		) ON DUPLICATE KEY UPDATE userID = 'USR.0000';
		
        INSERT INTO publisher(
			pubID, name,
			address_no,
			address_street,
			address_village,
			address_city,
			email,
			contactNO,
			otherDetail
		) VALUES (
			'PUB.0000',
			'Default',
			'',
			'',
			'',
			'',
			'',
			'',
			''
		) ON DUPLICATE KEY UPDATE pubID = 'PUB.0000';
END$$;
DELIMITER ;



#--------------------------------------------- Save User Account Procedure --------------------------------------------

DROP PROCEDURE IF EXISTS saveUserAcc;
DELIMITER $$;
CREATE PROCEDURE saveUserAcc(
	IN userID_vbl VARCHAR (30),
	IN name_vbl VARCHAR (60),
	IN username_vbl VARCHAR (30),
	IN password_vbl VARCHAR (30),
    IN state_vbl VARCHAR (30)
)
BEGIN
	INSERT INTO userAcc VALUES (userID_vbl, name_vbl, username_vbl, password_vbl, state_vbl);
END$$;
DELIMITER ;



#--------------------------------------------- Update User Account Procedure ------------------------------------------

DROP PROCEDURE IF EXISTS updateUserAcc;
DELIMITER $$;
CREATE PROCEDURE updateUserAcc(
	IN userID_vbl VARCHAR (30),
	IN name_vbl VARCHAR (60),
	IN username_vbl VARCHAR (30),
	IN password_vbl VARCHAR (30),
    IN state_vbl VARCHAR (30)
)
BEGIN
	UPDATE userAcc SET 
		userID = userID_vbl,
		name = name_vbl,
		username = username_vbl,
		password = password_vbl,
        state = state_vbl
	WHERE userID = userID_vbl;
END$$;
DELIMITER ;



#-------------------------------------------- Delete User Account Procedure -------------------------------------------

DROP PROCEDURE IF EXISTS deleteUserAcc;
DELIMITER $$;
CREATE PROCEDURE deleteUserAcc(
	IN userID_vbl VARCHAR (30)
)
BEGIN
	DELETE FROM userAcc WHERE userID = userID_vbl;
END$$;
DELIMITER ;



#---------------------------------------------- Get Last User ID Procedure --------------------------------------------

DROP PROCEDURE IF EXISTS getLastUserID;
DELIMITER $$;
CREATE PROCEDURE getLastUserID(
)
BEGIN
	SELECT userID FROM userAcc ORDER BY 1 DESC LIMIT 1;
END$$;
DELIMITER ;



#--------------------------------------------- Search User Account Procedure ----------------------------------------

DROP PROCEDURE IF EXISTS searchUserAcc;
DELIMITER $$;
CREATE PROCEDURE searchUserAcc(
	IN searchText VARCHAR(30)
)
BEGIN
	DECLARE newSearchText VARCHAR(30);
	SET newSearchText = CONCAT("%",searchText,"%");
	SELECT * FROM userAcc WHERE userID LIKE newSearchText OR name LIKE newSearchText OR state LIKE newSearchText;
END$$;
DELIMITER ;



#------------------------------------------------ Get User Procedure --------------------------------------------

DROP PROCEDURE IF EXISTS getUser;
DELIMITER $$;
CREATE PROCEDURE getUser(
	IN userID_vbl VARCHAR (30)
)
BEGIN
	SELECT * FROM userAcc WHERE userID = userID_vbl;
END$$;
DELIMITER ;



#------------------------------------------- Get Logged User Account Procedure --------------------------------------

DROP PROCEDURE IF EXISTS getLoggedUserAcc;
DELIMITER $$;
CREATE PROCEDURE getLoggedUserAcc(
	IN username_vbl VARCHAR (30),
	IN password_vbl VARCHAR (30)
)
BEGIN
	SELECT * FROM userAcc WHERE userID = (SELECT userAcc.userID FROM userAcc WHERE username = username_vbl AND password = password_vbl);
END$$;
DELIMITER ;



#------------------------------------------- Get All User Accounts Procedure ---------------------------------------

DROP PROCEDURE IF EXISTS getAllUserAcc;
DELIMITER $$;
CREATE PROCEDURE getAllUserAcc()
BEGIN
	SELECT * FROM userAcc;
END$$;
DELIMITER ;



#----------------------------------------------- Save Member Procedure --------------------------------------------

DROP PROCEDURE IF EXISTS saveMember;
DELIMITER $$;
CREATE PROCEDURE saveMember(
	IN libRegNO_vbl VARCHAR (30),
	IN admissionNo_vbl VARCHAR (30),
	IN name_vbl VARCHAR (60),
    IN addressNo_vbl VARCHAR(30),
	IN addressStreet_vbl VARCHAR(30),
	IN addressVillage_vbl VARCHAR(30),
	IN addressCity_vbl VARCHAR(30), 
	IN contactNO_vbl VARCHAR(30),
	IN otherDetail_vbl VARCHAR (90)
)
BEGIN
	INSERT INTO members VALUES (
            libRegNO_vbl,
            admissionNo_vbl,
            name_vbl,
            addressNo_vbl,
            addressStreet_vbl,
            addressVillage_vbl,
            addressCity_vbl,
            contactNO_vbl,
            otherDetail_vbl
        );
END$$;
DELIMITER ;



#--------------------------------------------- Update Member Procedure -------------------------------------------

DROP PROCEDURE IF EXISTS updateMember;
DELIMITER $$;
CREATE PROCEDURE updateMember(
	IN libRegNO_vbl VARCHAR (30),
	IN admissionNo_vbl VARCHAR (30),
	IN name_vbl VARCHAR (60),
    IN addressNo_vbl VARCHAR(30),
	IN addressStreet_vbl VARCHAR(30),
	IN addressVillage_vbl VARCHAR(30),
	IN addressCity_vbl VARCHAR(30), 
	IN contactNO_vbl VARCHAR(30),
	IN otherDetail_vbl VARCHAR (90)
)
BEGIN
	UPDATE members SET 
		libRegNO = libRegNO_vbl,
		admissionNo = admissionNo_vbl,
		name = name_vbl,
        address_no = addressNo_vbl,
        address_street = addressStreet_vbl,
        address_village=addressVillage_vbl,
        address_city=addressCity_vbl,
        contactNO=contactNO_vbl,
		otherDetail = otherDetail_vbl
	WHERE libRegNO = libRegNO_vbl;
END$$;
DELIMITER ;



#--------------------------------------------- Delete Member Procedure -------------------------------------------

DROP PROCEDURE IF EXISTS deleteMember;
DELIMITER $$;
CREATE PROCEDURE deleteMember(
	IN libRegNO_vbl VARCHAR (30)
)
BEGIN
	DELETE FROM members WHERE libRegNO = libRegNO_vbl;
END$$;
DELIMITER ;



#------------------------------------- Get Last Member Register Number Procedure -----------------------------------

DROP PROCEDURE IF EXISTS getLastLibRegNO;
DELIMITER $$;
CREATE PROCEDURE getLastLibRegNO(
)
BEGIN
	SELECT libRegNO FROM members ORDER BY 1 DESC LIMIT 1;
END$$;
DELIMITER ;



#--------------------------------------------- Search Member Procedure --------------------------------------------

DROP PROCEDURE IF EXISTS searchMember;
DELIMITER $$;
CREATE PROCEDURE searchMember(
	IN searchText VARCHAR(30)
)
BEGIN
	DECLARE newSearchText VARCHAR(30);
	SET newSearchText = CONCAT("%",searchText,"%");
	SELECT * FROM members WHERE libRegNO LIKE newSearchText OR admissionNo LIKE newSearchText OR name LIKE newSearchText;
END$$;
DELIMITER ;



#------------------------------------------------ Get Member Procedure --------------------------------------------

DROP PROCEDURE IF EXISTS getMember;
DELIMITER $$;
CREATE PROCEDURE getMember(
	IN libRegNO_vbl VARCHAR (30)
)
BEGIN
	SELECT * FROM members WHERE libRegNO = libRegNO_vbl;
END$$;
DELIMITER ;



#---------------------------------------------- Get All Member Procedure ------------------------------------------

DROP PROCEDURE IF EXISTS getAllMember;
DELIMITER $$;
CREATE PROCEDURE getAllMember()
BEGIN
	SELECT * FROM members;
END$$;
DELIMITER ;



#--------------------------------------------- Save Publisher Procedure --------------------------------------------

DROP PROCEDURE IF EXISTS savePublisher;
DELIMITER $$;
CREATE PROCEDURE savePublisher(
	IN pubID_vbl VARCHAR (30),
	IN name_vbl VARCHAR (30),
	IN addressNo_vbl VARCHAR(30),
	IN addressStreet_vbl VARCHAR(30),
	IN addressVillage_vbl VARCHAR(30),
	IN addressCity_vbl VARCHAR(30), 
	IN email_vbl VARCHAR (60),
	IN contactNO_vbl VARCHAR (60),
	IN otherDetail_vbl VARCHAR (90)
)
BEGIN
	INSERT INTO publisher VALUES (
		pubID_vbl,
		name_vbl,
		addressNo_vbl,
        addressStreet_vbl,
        addressVillage_vbl,
        addressCity_vbl,
		email_vbl,
		contactNO_vbl,
		otherDetail_vbl
	);
END$$;
DELIMITER ;



#--------------------------------------------- Update Publisher Procedure -------------------------------------------

DROP PROCEDURE IF EXISTS updatePublisher;
DELIMITER $$;
CREATE PROCEDURE updatePublisher(
	IN pubID_vbl VARCHAR (30),
	IN name_vbl VARCHAR (30),
	IN addressNo_vbl VARCHAR(30),
	IN addressStreet_vbl VARCHAR(30),
	IN addressVillage_vbl VARCHAR(30),
	IN addressCity_vbl VARCHAR(30), 
	IN email_vbl VARCHAR (60),
	IN contactNO_vbl VARCHAR (60),
	IN otherDetail_vbl VARCHAR (90)
)
BEGIN
	UPDATE publisher SET 
		pubID = pubID_vbl,
		name = name_vbl,
		address_no = addressNo_vbl,
        address_street = addressStreet_vbl,
        address_village=addressVillage_vbl,
        address_city=addressCity_vbl,
		email = email_vbl,
		contactNO = contactNO_vbl,
		otherDetail = otherDetail_vbl
	WHERE pubID = pubID_vbl;
END$$;
DELIMITER ;



#--------------------------------------------- Delete Publisher Procedure -------------------------------------------

DROP PROCEDURE IF EXISTS deletePublisher;
DELIMITER $$;
CREATE PROCEDURE deletePublisher(
	IN pubID_vbl VARCHAR (30)
)
BEGIN
	DELETE FROM publisher WHERE pubID = pubID_vbl;
END$$;
DELIMITER ;



#--------------------------------------------- Get Last Publisher ID Procedure ----------------------------------------

DROP PROCEDURE IF EXISTS getLastPubID;
DELIMITER $$;
CREATE PROCEDURE getLastPubID(
)
BEGIN
	SELECT pubID FROM publisher ORDER BY 1 DESC LIMIT 1;
END$$;
DELIMITER ;




#--------------------------------------------- Search Publisher Procedure -------------------------------------------

DROP PROCEDURE IF EXISTS searchPublisher;
DELIMITER $$;
CREATE PROCEDURE searchPublisher(
	IN searchText VARCHAR(30)
)
BEGIN
	DECLARE newSearchText VARCHAR(30);
	SET newSearchText = CONCAT("%",searchText,"%");
	SELECT * FROM publisher WHERE pubID LIKE newSearchText OR name LIKE newSearchText;
END$$;
DELIMITER ;



#------------------------------------------------ Get Publisher Procedure -------------------------------------------

DROP PROCEDURE IF EXISTS getPublisher;
DELIMITER $$;
CREATE PROCEDURE getPublisher(
	IN pubID_vbl VARCHAR (30)
)
BEGIN
	SELECT * FROM publisher WHERE pubID = pubID_vbl;
END$$;
DELIMITER ;



#----------------------------------------------- Get All Publisher Procedure ----------------------------------------

DROP PROCEDURE IF EXISTS getAllPublisher;
DELIMITER $$;
CREATE PROCEDURE getAllPublisher()
BEGIN
	SELECT * FROM publisher;
END$$;
DELIMITER ;



#--------------------------------------------- Save Book Category Procedure -----------------------------------------

DROP PROCEDURE IF EXISTS saveBookCategory;
DELIMITER $$;
CREATE PROCEDURE saveBookCategory(
	IN bookCatCode_vbl VARCHAR (30),
	IN name_vbl VARCHAR (30),
	IN details_vbl VARCHAR (90)
)
BEGIN
	INSERT INTO bookCategory VALUES (bookCatCode_vbl, name_vbl, details_vbl);
END$$;
DELIMITER ;



#-------------------------------------------- Update Book Category Procedure ----------------------------------------

DROP PROCEDURE IF EXISTS updateBookCategory;
DELIMITER $$;
CREATE PROCEDURE updateBookCategory(
	IN bookCatCode_vbl VARCHAR (30),
	IN name_vbl VARCHAR (30),
	IN details_vbl VARCHAR (90)
)
BEGIN
	UPDATE bookCategory SET 
		bookCatCode = bookCatCode_vbl,
		name = name_vbl,
		details = details_vbl
	WHERE bookCatCode = bookCatCode_vbl;
END$$;
DELIMITER ;



#------------------------------------------ Delete Book Category Procedure ------------------------------------------

DROP PROCEDURE IF EXISTS deleteBookCategory;
DELIMITER $$;
CREATE PROCEDURE deleteBookCategory(
	IN bookCatCode_vbl VARCHAR (30)
)
BEGIN
	DELETE FROM bookCategory WHERE bookCatCode = bookCatCode_vbl;
END$$;
DELIMITER ;



#------------------------------------------ Search Book Category Procedure -----------------------------------------

DROP PROCEDURE IF EXISTS searchBookCategory;
DELIMITER $$;
CREATE PROCEDURE searchBookCategory(
	IN searchText VARCHAR(30)
)
BEGIN
	DECLARE newSearchText VARCHAR(30);
	SET newSearchText = CONCAT("%",searchText,"%");
	SELECT * FROM bookCategory WHERE bookCatCode LIKE newSearchText OR name LIKE newSearchText;
END$$;
DELIMITER ;



#------------------------------------------- Get Book Category Procedure ------------------------------------------

DROP PROCEDURE IF EXISTS getBookCategory;
DELIMITER $$;
CREATE PROCEDURE getBookCategory(
	IN bookCatCode_vbl VARCHAR (30)
)
BEGIN
	SELECT * FROM bookCategory WHERE bookCatCode = bookCatCode_vbl;
END$$;
DELIMITER ;



#------------------------------------------- Get All Book Category Procedure --------------------------------------

DROP PROCEDURE IF EXISTS getAllBookCategory;
DELIMITER $$;
CREATE PROCEDURE getAllBookCategory()
BEGIN
	SELECT * FROM bookCategory;
END$$;
DELIMITER ;



#-------------------------------------------------- Save Book Procedure --------------------------------------------

DROP PROCEDURE IF EXISTS saveBook;
DELIMITER $$;
CREATE PROCEDURE saveBook(
	IN bookCode_vbl VARCHAR (30),
	IN ISBN_NO_vbl VARCHAR (30),
	IN bookTitle_vbl VARCHAR (60),
	IN edition_vbl VARCHAR (60),
	IN bookCatCode_vbl VARCHAR (30),
	IN authorName_vbl VARCHAR (60),
	IN pubID_vbl VARCHAR (30),
	IN price_vbl DOUBLE (10,2),
	IN otherDetails_vbl VARCHAR (60),
    IN status_vbl VARCHAR (30)
)
BEGIN
	INSERT INTO book VALUES (
		bookCode_vbl,
		ISBN_NO_vbl,
		bookTitle_vbl,
		edition_vbl,
		bookCatCode_vbl,
		authorName_vbl,
		pubID_vbl,
		price_vbl,
		otherDetails_vbl,
                status_vbl
	);
END$$;
DELIMITER ;



#---------------------------------------------- Update Book Procedure ---------------------------------------------

DROP PROCEDURE IF EXISTS updateBook;
DELIMITER $$;
CREATE PROCEDURE updateBook(
	IN bookCode_vbl VARCHAR (30),
	IN ISBN_NO_vbl VARCHAR (30),
	IN bookTitle_vbl VARCHAR (60),
	IN edition_vbl VARCHAR (60),
	IN bookCatCode_vbl VARCHAR (30),
	IN authorName_vbl VARCHAR (60),
	IN pubID_vbl VARCHAR (30),
	IN price_vbl DOUBLE (10,2),
	IN otherDetails_vbl VARCHAR (60),
    IN status_vbl VARCHAR (30)
)
BEGIN
	UPDATE book SET 
		bookCode = bookCode_vbl,
		ISBN_NO = ISBN_NO_vbl,
		bookTitle = bookTitle_vbl,
		edition = edition_vbl,
		bookCatCode = bookCatCode_vbl,
		authorName = authorName_vbl,
		pubID = pubID_vbl,
		price = price_vbl,
		otherDetails = otherDetails_vbl,
        status = status_vbl
	WHERE bookCode = bookCode_vbl;
END$$;
DELIMITER ;



#---------------------------------------------- Delete Book Procedure ---------------------------------------------

DROP PROCEDURE IF EXISTS deleteBook;
DELIMITER $$;
CREATE PROCEDURE deleteBook(
	IN bookCode_vbl VARCHAR (30)
)
BEGIN
	DELETE FROM book WHERE bookCode = bookCode_vbl;
END$$;
DELIMITER ;




#------------------------------------------------ Search Book Procedure -------------------------------------------

DROP PROCEDURE IF EXISTS searchBook;
DELIMITER $$;
CREATE PROCEDURE searchBook(
	IN searchText VARCHAR(30)
)
BEGIN
	DECLARE newSearchText VARCHAR(30);
	SET newSearchText = CONCAT("%",searchText,"%");
	SELECT distinct b.* FROM book b, publisher p WHERE (b.bookCode LIKE newSearchText OR b.bookTitle LIKE newSearchText OR b.authorName LIKE newSearchText OR p.name LIKE newSearchText) AND status <> 'MEM.999999';
END$$;
DELIMITER ;



#------------------------------------------------ Search Damaged Book Procedure -------------------------------------------

DROP PROCEDURE IF EXISTS searchDamagedBook;
DELIMITER $$;
CREATE PROCEDURE searchDamagedBook(
	IN searchText VARCHAR(30)
)
BEGIN
	DECLARE newSearchText VARCHAR(30);
	SET newSearchText = CONCAT("%",searchText,"%");
	SELECT distinct b.* FROM book b, publisher p WHERE (b.bookCode LIKE newSearchText OR b.bookTitle LIKE newSearchText OR b.authorName LIKE newSearchText OR p.name LIKE newSearchText) AND status = 'MEM.999999';
END$$;
DELIMITER ;



#-------------------------------------------------- Get Book Procedure --------------------------------------------

DROP PROCEDURE IF EXISTS getBook;
DELIMITER $$;
CREATE PROCEDURE getBook(
	IN bookCode_vbl VARCHAR (30)
)
BEGIN
	SELECT * FROM book WHERE bookCode = bookCode_vbl;
END$$;
DELIMITER ;



#------------------------------------------------- Get All Book Procedure ------------------------------------------

DROP PROCEDURE IF EXISTS getAllBook;
DELIMITER $$;
CREATE PROCEDURE getAllBook()
BEGIN
	SELECT * FROM book WHERE status <> 'MEM.999999';
END$$;
DELIMITER ;



#--------------------------------------------- Get All Damaged Book Procedure --------------------------------------

DROP PROCEDURE IF EXISTS getAllDamagedBook;
DELIMITER $$;
CREATE PROCEDURE getAllDamagedBook()
BEGIN
	SELECT * FROM book WHERE status = 'MEM.999999';
END$$;
DELIMITER ;



#------------------------------------------------- Save Borrow Procedure --------------------------------------------

DROP PROCEDURE IF EXISTS saveBorrow;
DELIMITER $$;
CREATE PROCEDURE saveBorrow(
	IN borrowID_vbl VARCHAR (30),
	IN libRegNO_vbl VARCHAR (30),
	IN bookCode_vbl VARCHAR (30),
	IN userID_vbl VARCHAR (30),
	IN issuedDate_vbl DATE,
	IN dueDate_vbl DATE,
	IN returnedDate_vbl DATE
)
BEGIN
	INSERT INTO borrow VALUES (borrowID_vbl, libRegNO_vbl, bookCode_vbl, userID_vbl, issuedDate_vbl, dueDate_vbl, returnedDate_vbl);
END$$;
DELIMITER ;



#-------------------------------------------- Update Book With Save Borrow Procedure ------------------------------------------

DROP PROCEDURE IF EXISTS updateBookWithSaveBorrow;
DELIMITER $$;
CREATE PROCEDURE updateBookWithSaveBorrow(
	IN libRegNO_vbl VARCHAR (30),
	IN bookCode_vbl VARCHAR (30)
)
BEGIN
	UPDATE book SET status = libRegNO_vbl WHERE bookCode = bookCode_vbl;
END$$;
DELIMITER ;



#----------------------------------------------- Update Borrow Procedure --------------------------------------------

DROP PROCEDURE IF EXISTS updateBorrow;
DELIMITER $$;
CREATE PROCEDURE updateBorrow(
	IN borrowID_vbl VARCHAR (30),
	IN libRegNO_vbl VARCHAR (30),
	IN bookCode_vbl VARCHAR (30),
	IN userID_vbl VARCHAR (30),
	IN issuedDate_vbl DATE,
	IN dueDate_vbl DATE,
	IN returnedDate_vbl DATE
)
BEGIN
	UPDATE borrow SET 
		borrowID = borrowID_vbl,
		libRegNO = libRegNO_vbl,
		bookCode = bookCode_vbl,
		userID = userID_vbl,
		issuedDate = issuedDate_vbl,
		dueDate = dueDate_vbl,
		returnedDate = returnedDate_vbl
	WHERE borrowID = borrowID_vbl;
END$$;
DELIMITER ;



#------------------------------------------- Update Book With Update Borrow Procedure ------------------------------------------

DROP PROCEDURE IF EXISTS updateBookWithUpdateBorrow;
DELIMITER $$;
CREATE PROCEDURE updateBookWithUpdateBorrow(
	IN libRegNO_vbl VARCHAR (30),
	IN bookCode_vbl VARCHAR (30)
)
BEGIN
	UPDATE book SET status = libRegNO_vbl WHERE bookCode = bookCode_vbl;
END$$;
DELIMITER ;



#------------------------------------------------ Delete Borrow Procedure --------------------------------------------

DROP PROCEDURE IF EXISTS deleteBorrow;
DELIMITER $$;
CREATE PROCEDURE deleteBorrow(
	IN borrowID_vbl VARCHAR (30)
)
BEGIN
	DELETE FROM borrow WHERE borrowID = borrowID_vbl;
END$$;
DELIMITER ;



#--------------------------------------------- Get Last Borrow ID Procedure -------------------------------------------

DROP PROCEDURE IF EXISTS getLastBorrowID;
DELIMITER $$;
CREATE PROCEDURE getLastBorrowID(
)
BEGIN
	SELECT borrowID FROM borrow ORDER BY 1 DESC LIMIT 1;
END$$;
DELIMITER ;



#------------------------------------------------- Search Borrow Procedure --------------------------------------------

DROP PROCEDURE IF EXISTS searchBorrow;
DELIMITER $$;
CREATE PROCEDURE searchBorrow(
	IN searchText VARCHAR(30)
)
BEGIN
	DECLARE newSearchText VARCHAR(30);
	SET newSearchText = CONCAT("%",searchText,"%");
	SELECT * FROM borrow WHERE borrowID LIKE newSearchText OR libRegNO LIKE newSearchText OR bookCode LIKE newSearchText OR userID LIKE newSearchText;
END$$;
DELIMITER ;



#---------------------------------------------------- Get Borrow Procedure --------------------------------------------

DROP PROCEDURE IF EXISTS getBorrow;
DELIMITER $$;
CREATE PROCEDURE getBorrow(
	IN borrowID_vbl VARCHAR (30)
)
BEGIN
	SELECT * FROM borrow WHERE borrowID = borrowID_vbl;
END$$;
DELIMITER ;



#----------------------------------------------- Get All Borrow Procedure ----------------------------------------------

DROP PROCEDURE IF EXISTS getAllBorrow;
DELIMITER $$;
CREATE PROCEDURE getAllBorrow()
BEGIN
	SELECT * FROM borrow;
END$$;
DELIMITER ;



#----------------------------------------------- Get Not Returned Borrow Procedure ----------------------------------------------

DROP PROCEDURE IF EXISTS getNotReturnedBorrow;
DELIMITER $$;
CREATE PROCEDURE getNotReturnedBorrow()
BEGIN
	SELECT * FROM borrow WHERE returnedDate = '1970-01-01';
END$$;
DELIMITER ;



#----------------------------------------------- Get Returned Borrow Procedure ----------------------------------------------

DROP PROCEDURE IF EXISTS getReturnedBorrow;
DELIMITER $$;
CREATE PROCEDURE getReturnedBorrow()
BEGIN
	SELECT * FROM borrow WHERE returnedDate <> '1970-01-01';
END$$;
DELIMITER ;



#------------------------------------------- Search Not Returned Borrow Procedure ---------------------------------------

DROP PROCEDURE IF EXISTS searchNotReturnedBorrow;
DELIMITER $$;
CREATE PROCEDURE searchNotReturnedBorrow(
	IN searchText VARCHAR(30)
)
BEGIN
	DECLARE newSearchText VARCHAR(30);
	SET newSearchText = CONCAT("%",searchText,"%");
	SELECT * FROM borrow WHERE (borrowID LIKE newSearchText OR libRegNO LIKE newSearchText OR bookCode LIKE newSearchText OR userID LIKE newSearchText) AND returnedDate = '1970-01-01';
END$$;
DELIMITER ;



#------------------------------------------- Search Returned Borrow Procedure ---------------------------------------

DROP PROCEDURE IF EXISTS searchReturnedBorrow;
DELIMITER $$;
CREATE PROCEDURE searchReturnedBorrow(
	IN searchText VARCHAR(30)
)
BEGIN
	DECLARE newSearchText VARCHAR(30);
	SET newSearchText = CONCAT("%",searchText,"%");
	SELECT * FROM borrow WHERE (borrowID LIKE newSearchText OR libRegNO LIKE newSearchText OR bookCode LIKE newSearchText OR userID LIKE newSearchText) AND returnedDate <> '1970-01-01';
END$$;
DELIMITER ;

