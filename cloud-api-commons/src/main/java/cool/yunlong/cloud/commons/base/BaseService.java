package cool.yunlong.cloud.commons.base;

/**
 * service接口，和mybatis generator 配套使用
 *
 * @param <T>
 */
public interface BaseService<T> {
    /**
     * 根据id删除数据，硬删除
     *
     * @param id id
     * @return int
     */
    int deleteById(Long id);

    /**
     * 插入数据,完整数据插入
     *
     * @param record record
     * @return int
     */
    int insert(T record);


    /**
     * 根据id查询数据
     *
     * @param id id
     * @return T
     */
    T selectById(Long id);


    /**
     * 更新完整的数据
     *
     * @param record record
     * @return int 更新的条数
     */
    int updateById(T record);
}
