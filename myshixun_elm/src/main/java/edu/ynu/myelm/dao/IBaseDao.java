package edu.ynu.myelm.dao;

import java.io.Serializable;
import java.util.List;

public interface IBaseDao<T> {
    public boolean save(T s);//保存记录的方法
    public boolean update(T s);//更新记录的方法
    public boolean delete(Serializable i);//删除记录的方法,通过主键删除
    public T getOne(Serializable i);//获取一条记录的方法
    public List<T> getAll();//获取全部记录的方法
}

