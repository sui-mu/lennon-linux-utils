package site.suimu.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.suimu.common.annotation.DataScope;
import site.suimu.common.utils.DateUtils;
import site.suimu.common.utils.SecurityUtils;
import site.suimu.common.utils.StringUtils;
import site.suimu.system.mapper.NavClassifyMapper;
import site.suimu.system.domain.NavClassify;
import site.suimu.system.service.INavClassifyService;

/**
 * 分类Service业务层处理
 *
 * @author ruoyi
 * @date 2021-11-27
 */
@Service
public class NavClassifyServiceImpl implements INavClassifyService {
    @Autowired
    private NavClassifyMapper navClassifyMapper;

    /**
     * 查询分类
     *
     * @param id 分类主键
     * @return 分类
     */
    @Override
    public NavClassify selectNavClassifyById(Long id) {
        return navClassifyMapper.selectNavClassifyById(id);
    }

    /**
     * 查询分类列表
     *
     * @param navClassify 分类
     * @return 分类
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<NavClassify> selectNavClassifyList(NavClassify navClassify) {
        return navClassifyMapper.selectNavClassifyList(navClassify);
    }

    @Override
    public List<NavClassify> selectNavClassifyTree(NavClassify navClassify) {
        Long parentId = navClassify.getParentId();
        if (StringUtils.isNull(parentId)) {
            parentId = 0L;
        }
        List<NavClassify> origins = selectNavClassifyList(navClassify);
        List<NavClassify> tree = new ArrayList<>();
        for (NavClassify item : origins) {
            findChilds(origins, item);
            if (Objects.equals(item.getParentId(), parentId)) {
                tree.add(item);
            }
        }
        return tree;
    }

    private void findChilds(List<NavClassify> origins, NavClassify current) {
        List<NavClassify> childs = new ArrayList<>();
        for (NavClassify item : origins) {
            if (StringUtils.isEmpty(current.getAncestors())) {
                current.setAncestors(current.getParentId().toString());
                updateNavClassify(item);
            }
            if (current.getId().equals(item.getParentId())) {
                // 处理祖宗辈
                String ac = current.getAncestors() + "," + current.getId();
                if (!ac.equals(item.getAncestors())) {
                    item.setAncestors(ac);
                    updateNavClassify(item);
                }
                childs.add(item);
            }
        }
        current.setChildren(childs);
    }

    /**
     * 新增分类
     *
     * @param navClassify 分类
     * @return 结果
     */
    @Override
    public int insertNavClassify(NavClassify navClassify) {
        Long userId = SecurityUtils.getUserId();
        Long deptId = SecurityUtils.getDeptId();
        navClassify.setCreateBy(String.valueOf(userId));
        navClassify.setCreateTime(DateUtils.getNowDate());
        navClassify.setDeptId(deptId);
        return navClassifyMapper.insertNavClassify(navClassify);
    }



    /**
     * 修改分类
     *
     * @param navClassify 分类
     * @return 结果
     */
    @Override
    public int updateNavClassify(NavClassify navClassify) {
        Long userId = SecurityUtils.getUserId();
        Long deptId = SecurityUtils.getDeptId();
        navClassify.setUpdateTime(DateUtils.getNowDate());
        navClassify.setUpdateBy(String.valueOf(userId));
        navClassify.setDeptId(deptId);
        return navClassifyMapper.updateNavClassify(navClassify);
    }

    /**
     * 批量删除分类
     *
     * @param ids 需要删除的分类主键
     * @return 结果
     */
    @Override
    public int deleteNavClassifyByIds(Long[] ids) {
        return navClassifyMapper.deleteNavClassifyByIds(ids);
    }

    /**
     * 删除分类信息
     *
     * @param id 分类主键
     * @return 结果
     */
    @Override
    public int deleteNavClassifyById(Long id) {
        return navClassifyMapper.deleteNavClassifyById(id);
    }
}
