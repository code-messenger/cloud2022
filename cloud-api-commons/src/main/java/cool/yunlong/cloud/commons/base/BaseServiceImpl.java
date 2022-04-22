package cool.yunlong.cloud.commons.base;

public abstract class BaseServiceImpl<T> implements BaseService<T> {

    protected abstract BaseMapper<T> getEntityMapper();
    @Override
    public int deleteById(Long id) {
        return getEntityMapper().deleteByPrimaryKey(id);
    }

    @Override
    public int insert(T record) {
        return getEntityMapper().insert(record);
    }

    @Override
    public T selectById(Long id) {
        return getEntityMapper().selectByPrimaryKey(id);
    }


    @Override
    public int updateById(T record) {
        return getEntityMapper().updateByPrimaryKey(record);
    }
}
