package org.sahurdayathra.BookShelfLMS.repo;

import java.util.ArrayList;

/**
 *
 * @author Thushara Supun
 */
public interface CrudRepo<T, ID> extends SuperRepo {

    public boolean save(T entity) throws Exception;

    public boolean update(T entity) throws Exception;

    public boolean delete(ID id) throws Exception;

    public T get(ID id) throws Exception;

    public ArrayList<T> getAll() throws Exception;

}
