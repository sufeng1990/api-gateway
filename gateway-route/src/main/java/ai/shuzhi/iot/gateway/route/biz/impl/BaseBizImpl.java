package ai.shuzhi.iot.gateway.route.biz.impl;

import ai.shuzhi.iot.gateway.route.biz.BaseBiz;
import ai.shuzhi.iot.gateway.route.mapper.BaseMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;
import java.util.List;

/**
 * 统一业务处理接口实现
 * <p>
 * BaseBizImpl class
 *
 * @author zxb
 * @date 2019/05/20
 */
public abstract class BaseBizImpl<T, PK extends Serializable> implements BaseBiz<T, PK> {

    @Autowired
    protected BaseMapper<T> mapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(T record) {
        Preconditions.checkNotNull(record);
        return mapper.insertSelective(record);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertUnchecked(T record) {
        Preconditions.checkNotNull(record);
        return mapper.insert(record);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertBatch(List<T> records) {
        return mapper.insertList(records);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(T record) {
        Preconditions.checkNotNull(record);
        return mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateUnchecked(T record) {
        Preconditions.checkNotNull(record);
        return mapper.updateByPrimaryKey(record);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateByExample(T record, Example example) {
        Preconditions.checkNotNull(record);
        Preconditions.checkNotNull(example);
        return mapper.updateByExampleSelective(record, example);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateUncheckedByExample(T record, Example example) {
        Preconditions.checkNotNull(record);
        Preconditions.checkNotNull(example);
        return updateByExample(record, example);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByPk(PK pk) {
        Preconditions.checkNotNull(pk);
        return mapper.deleteByPrimaryKey(pk);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByPks(Iterable<? extends PK> pks) {
        Preconditions.checkNotNull(pks);
        String pksStr = Joiner.on(',').skipNulls().join(pks);
        return mapper.deleteByIds(pksStr);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(T param) {
        Preconditions.checkNotNull(param);
        return mapper.delete(param);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteAll() {
        return mapper.delete(null);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByExample(Example example) {
        Preconditions.checkNotNull(example);
        return mapper.deleteByExample(example);
    }

    @Override
    public T selectByPk(PK pk) {
        Preconditions.checkNotNull(pk);
        return mapper.selectByPrimaryKey(pk);
    }

    @Override
    public List<T> selectByPks(Iterable<? extends PK> pks) {
        Preconditions.checkNotNull(pks);
        String pksStr = Joiner.on(',').skipNulls().join(pks);
        return mapper.selectByIds(pksStr);
    }

    @Override
    public List<T> select(T param) {
        Preconditions.checkNotNull(param);
        return mapper.select(param);
    }

    @Override
    public T selectOne(T param) {
        Preconditions.checkNotNull(param);
        T result;
        try {
            PageHelper.clearPage();
            PageMethod.offsetPage(0, 1, false);
            result = mapper.selectOne(param);
        } finally {
            PageHelper.clearPage();
        }
        return result;
    }

    @Override
    public List<T> selectAll() {
        return mapper.select(null);
    }

    @Override
    public int selectCount(T param) {
        Preconditions.checkNotNull(param);
        return mapper.selectCount(param);
    }

    @Override
    public PageInfo<T> selectPage(T param, int pageNum, int pageSize) {
        Preconditions.checkNotNull(param);
        PageInfo<T> result;
        try {
            PageHelper.clearPage();
            result = PageMethod.startPage(pageNum, pageSize, false)
                    .doSelectPageInfo(() -> mapper.select(param));
        } finally {
            PageHelper.clearPage();
        }
        return result;
    }

    @Override
    public PageInfo<T> selectPageAndCount(T param, int pageNum, int pageSize) {
        Preconditions.checkNotNull(param);
        PageInfo<T> result;
        try {
            PageHelper.clearPage();
            result = PageMethod.startPage(pageNum, pageSize)
                    .doSelectPageInfo(() -> mapper.select(param));
        } finally {
            PageHelper.clearPage();
        }
        return result;
    }

    @Override
    public List<T> selectByExample(Example example) {
        Preconditions.checkNotNull(example);
        return mapper.selectByExample(example);
    }

    @Override
    public int selectCountByExample(Example example) {
        Preconditions.checkNotNull(example);
        return mapper.selectCountByExample(example);
    }

    @Override
    public PageInfo<T> selectPageByExample(Example example, int pageNum, int pageSize) {
        Preconditions.checkNotNull(example);
        PageInfo<T> result;
        try {
            PageHelper.clearPage();
            result = PageMethod.startPage(pageNum, pageSize, false)
                    .doSelectPageInfo(() -> mapper.selectByExample(example));
        } finally {
            PageHelper.clearPage();
        }
        return result;
    }

    @Override
    public PageInfo<T> selectPageAndCountByExample(Example example, int pageNum, int pageSize) {
        Preconditions.checkNotNull(example);
        PageInfo<T> result;
        try {
            PageHelper.clearPage();
            result = PageMethod.startPage(pageNum, pageSize)
                    .doSelectPageInfo(() -> mapper.selectByExample(example));
        } finally {
            PageHelper.clearPage();
        }
        return result;
    }
}
