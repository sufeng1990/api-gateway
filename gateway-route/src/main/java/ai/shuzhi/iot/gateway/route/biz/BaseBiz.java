package ai.shuzhi.iot.gateway.route.biz;

import com.github.pagehelper.PageInfo;
import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;
import java.util.List;

/**
 * 统一业务处理接口
 * <p>
 * BaseBiz class
 *
 * @author zxb
 * @date 2019/05/20
 */
public interface BaseBiz<T, PK extends Serializable> {

    /**
     * 新增数据，值为 null 的字段不会保存（可以保留字段的默认值）
     *
     * @param record 待保存记录
     * @return 处理结果
     */
    int insert(T record);

    /**
     * 新增数据，值为 null 的字段也会保存
     *
     * @param record 待保存记录
     * @return 处理结果
     */
    int insertUnchecked(T record);

    /**
     * 批量新增数据，值为 null 的字段也会保存
     *
     * @param records 待保存记录
     * @return 处理结果
     */
    int insertBatch(List<T> records);

    /**
     * 根据主键更新数据，值为 null 的字段不会更新
     *
     * @param record 待更新数据
     * @return 更新结果
     */
    int update(T record);

    /**
     * 根据主键更新数据，值为 null 的字段会更新为 null
     *
     * @param record 待更新数据
     * @return 更新结果
     */
    int updateUnchecked(T record);

    /**
     * 根据 Example 条件更新数据，值为 null 的字段不会更新
     *
     * @param record  待更新数据
     * @param example Where条件
     * @return 更新结果
     */
    int updateByExample(T record, Example example);

    /**
     * 根据 Example 条件更新数据，值为 null 的字段会更新为 null
     *
     * @param record  待更新数据
     * @param example Where条件
     * @return 更新结果
     */
    int updateUncheckedByExample(T record, Example example);

    /**
     * 根据主键删除数据
     *
     * @param pk 主键
     * @return 删除结果
     */
    int deleteByPk(PK pk);

    /**
     * 根据主键集合删除数据
     *
     * @param pks 主键集合
     * @return 删除结果
     */
    int deleteByPks(Iterable<? extends PK> pks);

    /**
     * 根据 `=` 条件删除数据
     *
     * @param param where 参数
     * @return 删除结果
     */
    int delete(T param);

    /**
     * 删除全部数据
     *
     * @return 处理结果
     */
    int deleteAll();

    /**
     * 根据 Example 条件删除数据
     *
     * @param example 条件
     * @return 处理结果
     */
    int deleteByExample(Example example);

    /**
     * 根据主键查询数据
     *
     * @param pk 主键
     * @return 查询结果
     */
    T selectByPk(PK pk);

    /**
     * 根据主键集合查询数据
     *
     * @param pks 主键集合
     * @return 查询结果
     */
    List<T> selectByPks(Iterable<? extends PK> pks);

    /**
     * 根据 `=` 条件查询数据集合
     *
     * @param param 查询条件
     * @return 查询结果
     */
    List<T> select(T param);

    /**
     * 根据 `=` 条件查询单条数据
     *
     * @param param 查询条件
     * @return 查询结果
     */
    T selectOne(T param);

    /**
     * 查询全部数据
     *
     * @return 查询结果
     */
    List<T> selectAll();

    /**
     * 根据 `=` 条件查询数据数量
     *
     * @param param 查询条件
     * @return 查询结果
     */
    int selectCount(T param);

    /**
     * 根据 `=` 条件分页查询，不会查询 count
     *
     * @param param    查询条件
     * @param pageNum  当前页码
     * @param pageSize 每页展示条数
     * @return 查询结果
     */
    PageInfo<T> selectPage(T param, int pageNum, int pageSize);

    /**
     * 根据 `=` 条件分页查询，查询count 若同时需要排序，可手动指定PageHelper.orderBy()
     *
     * @param param    查询条件
     * @param pageNum  当前页码
     * @param pageSize 每页展示条数
     * @return 查询结果
     */
    PageInfo<T> selectPageAndCount(T param, int pageNum, int pageSize);

    /**
     * 根据 Example 条件查询数据集合
     *
     * @param example 查询条件
     * @return 查询结果
     */
    List<T> selectByExample(Example example);

    /**
     * 根据 Example 条件查询数据数量
     *
     * @param example 查询条件
     * @return 查询结果
     */
    int selectCountByExample(Example example);

    /**
     * 根据 Example 条件分页查询，不会查询 count
     *
     * @param example  查询条件
     * @param pageNum  当前页码
     * @param pageSize 每页展示条数
     * @return 查询结果
     */
    PageInfo<T> selectPageByExample(Example example, int pageNum, int pageSize);

    /**
     * 根据 Example 条件分页查询
     *
     * @param example  查询条件
     * @param pageNum  当前页码
     * @param pageSize 每页展示条数
     * @return 查询结果
     */
    PageInfo<T> selectPageAndCountByExample(Example example, int pageNum, int pageSize);
}
