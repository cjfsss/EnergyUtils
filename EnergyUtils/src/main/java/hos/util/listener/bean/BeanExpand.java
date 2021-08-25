package hos.util.listener.bean;

import java.util.List;

/**
 * <p>Title: BeanExpand </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2021/3/22 15:59
 */
public interface BeanExpand {

    /**
     * 同级别的分组的索引位置
     *
     * @param position 位置
     */
    void setGroupPosition(int position);

    /**
     * 是否已展开
     *
     * @param isExpand true 展开
     */
    void setExpand(boolean isExpand);

    /**
     * 子列表
     *
     * @param sublist 子列表
     */
    <E> void setSublist(List<E> sublist);

    /**
     * 获取子列表
     *
     * @param <E> 泛型
     * @return 子列表
     */
    <E> List<E> getSublist();
}
