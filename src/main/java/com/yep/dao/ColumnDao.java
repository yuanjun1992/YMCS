package main.java.com.yep.dao;

import main.java.com.yep.pojo.ColumnInfo;

public interface ColumnDao {
    int deleteByPrimaryKey(Integer columnId);

    int insert(ColumnInfo record);

    int insertSelective(ColumnInfo record);

    ColumnInfo selectByPrimaryKey(Integer columnId);

    int updateByPrimaryKeySelective(ColumnInfo record);

    int updateByPrimaryKey(ColumnInfo record);
}