package site.suimu.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import site.suimu.common.annotation.Excel;
import site.suimu.common.core.domain.TreeEntity;

import java.util.List;

/**
 * 分类对象 nav_classify
 *
 * @author ruoyi
 * @date 2021-12-01
 */
public class NavClassify extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** 编码 */
    private Long id;

    /** 名称 */
    @Excel(name = "名称")
    private String label;

    /** 1=分类，2=标签 */
    @Excel(name = "1=分类，2=标签")
    private String type;

    /** 排序 */
    @Excel(name = "排序")
    private String sort;

    /** 是否公开 */
    @Excel(name = "是否公开")
    private String share;

    /** 部门ID */
    @Excel(name = "部门ID")
    private Long deptId;


    private String userKey;

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    private List<NavLink> navLinks;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setLabel(String label)
    {
        this.label = label;
    }

    public String getLabel()
    {
        return label;
    }
    public void setType(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return type;
    }
    public void setSort(String sort)
    {
        this.sort = sort;
    }

    public String getSort()
    {
        return sort;
    }
    public void setShare(String share)
    {
        this.share = share;
    }

    public String getShare()
    {
        return share;
    }
    public void setDeptId(Long deptId)
    {
        this.deptId = deptId;
    }

    public Long getDeptId()
    {
        return deptId;
    }

    public List<NavLink> getNavLinks() {
        return navLinks;
    }

    public void setNavLinks(List<NavLink> navLinks) {
        this.navLinks = navLinks;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("label", getLabel())
                .append("parentId", getParentId())
                .append("type", getType())
                .append("sort", getSort())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("share", getShare())
                .append("ancestors", getAncestors())
                .append("deptId", getDeptId())
                .toString();
    }
}