package org.sahurdayathra.BookShelfLMS.business;

import org.sahurdayathra.BookShelfLMS.business.custom.impl.BookBOImpl;
import org.sahurdayathra.BookShelfLMS.business.custom.impl.BookCategoryBOImpl;
import org.sahurdayathra.BookShelfLMS.business.custom.impl.BookPublisherBOImpl;
import org.sahurdayathra.BookShelfLMS.business.custom.impl.BorrowBOImpl;
import org.sahurdayathra.BookShelfLMS.business.custom.impl.DamagedBookBOImpl;
import org.sahurdayathra.BookShelfLMS.business.custom.impl.LogInBOImpl;
import org.sahurdayathra.BookShelfLMS.business.custom.impl.MemberBOImpl;
import org.sahurdayathra.BookShelfLMS.business.custom.impl.UserBOImpl;

/**
 *
 * @author Thushara Supun
 */
public class BOFactory {

    public enum BOTypes {

        LOGIN, MEMBER, BOOKCATEGORY, BOOKPUBLISHER, BOOK, USER, BORROW, DAMAGEDBOOK;
    }

    private static BOFactory bOFactory;

    private BOFactory() {
    }

    public static BOFactory getInstance() {
        if (bOFactory == null) {
            bOFactory = new BOFactory();
        }
        return bOFactory;
    }

    public SuperBO getBO(BOTypes bOTypes) {
        switch (bOTypes) {
            case LOGIN:
                return new LogInBOImpl();
            case MEMBER:
                return new MemberBOImpl();
            case BOOKCATEGORY:
                return new BookCategoryBOImpl();
            case BOOKPUBLISHER:
                return new BookPublisherBOImpl();
            case BOOK:
                return new BookBOImpl();
            case USER:
                return new UserBOImpl();
            case BORROW:
                return new BorrowBOImpl();
            case DAMAGEDBOOK:
                return new DamagedBookBOImpl();
            default:
                return null;
        }
    }

}
