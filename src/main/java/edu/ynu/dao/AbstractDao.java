package edu.ynu.dao;

import edu.ynu.entities.Business;
import edu.ynu.entities.User;

import java.sql.SQLException;
import java.util.List;

public abstract class AbstractDao<T> {

    public boolean save(T item) throws SQLException {
        return false;
    }

    public List<T> dimQuery(String roleName) throws SQLException {
        return null;
    }

    public List<T> exactQuery(Integer id,String password) throws SQLException{
        return null;
    }

    public List<T> exactQueryId(Integer id) throws SQLException{
        return null;
    }
    public void showOwnInfo(T item) throws SQLException{}

    public boolean update(T item) throws SQLException{
        return true;
    }

    public boolean updatePassword(Integer id, String password) throws  SQLException{
        return true;
    }

}

