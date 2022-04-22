package cool.yunlong.cloud.commons.base;

import java.util.List;

public interface BaseMapper<T> {

    List<T> selectAll();

    int deleteByPrimaryKey(Long id);

    int insert(T record);

    T selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);
}
