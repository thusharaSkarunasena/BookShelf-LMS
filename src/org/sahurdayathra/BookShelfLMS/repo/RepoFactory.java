package org.sahurdayathra.BookShelfLMS.repo;

import org.sahurdayathra.BookShelfLMS.repo.custom.impl.BookCategoryRepoImpl;
import org.sahurdayathra.BookShelfLMS.repo.custom.impl.BookPublisherRepoImpl;
import org.sahurdayathra.BookShelfLMS.repo.custom.impl.BookRepoImpl;
import org.sahurdayathra.BookShelfLMS.repo.custom.impl.BorrowRepoImpl;
import org.sahurdayathra.BookShelfLMS.repo.custom.impl.DamagedBookRepoImpl;
import org.sahurdayathra.BookShelfLMS.repo.custom.impl.LogInRepoImpl;
import org.sahurdayathra.BookShelfLMS.repo.custom.impl.MemberRepoImpl;
import org.sahurdayathra.BookShelfLMS.repo.custom.impl.UserRepoImpl;

/**
 *
 * @author Thushara Supun
 */
public class RepoFactory {

    public enum RepoTypes {

        LOGIN, MEMBER, BOOKCATEGORY, BOOKPUBLISHER, BOOK, USER, BORROW, DAMAGEDBOOK;
    }

    private static RepoFactory repoFactory;

    private RepoFactory() {
    }

    public static RepoFactory getInstance() {
        if (repoFactory == null) {
            repoFactory = new RepoFactory();
        }
        return repoFactory;
    }

    public SuperRepo getRepo(RepoTypes repoTypes) {
        switch (repoTypes) {
            case LOGIN:
                return new LogInRepoImpl();
            case MEMBER:
                return new MemberRepoImpl();
            case BOOKCATEGORY:
                return new BookCategoryRepoImpl();
            case BOOKPUBLISHER:
                return new BookPublisherRepoImpl();
            case BOOK:
                return new BookRepoImpl();
            case USER:
                return new UserRepoImpl();
            case BORROW:
                return new BorrowRepoImpl();
            case DAMAGEDBOOK:
                return new DamagedBookRepoImpl();
            default:
                return null;
        }
    }

}
