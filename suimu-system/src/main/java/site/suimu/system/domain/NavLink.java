package site.suimu.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import site.suimu.common.annotation.Excel;
import site.suimu.common.core.domain.BaseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 链接对象 nav_link
 *
 * @author ruoyi
 * @date 2021-11-27
 */
public class NavLink extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编码 */
    private Long id;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 简介 */
    @Excel(name = "简介")
    private String intro;

    /** 图标 */
    @Excel(name = "图标")
    private String icon;

    /** 链接 */
    @Excel(name = "链接")
    private String linkUrl;

    /** 排序 */
    @Excel(name = "排序")
    private String sort;

    /** 链接状态 */
    @Excel(name = "链接状态")
    private String status;

    /** 部门编码 */
    @Excel(name = "部门编码")
    private Long deptId;

    private List<NavClassify> classifies = new ArrayList<>();

    private List<NavClassify> tags = new ArrayList<>();

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setIntro(String intro)
    {
        this.intro = intro;
    }

    public String getIntro()
    {
        return intro;
    }
    public void setIcon(String icon)
    {
        this.icon = icon;
    }

    public String getIcon()
    {
        return icon;
    }
    public void setLinkUrl(String linkUrl)
    {
        this.linkUrl = linkUrl;
    }

    public String getLinkUrl()
    {
        return linkUrl;
    }
    public void setSort(String sort)
    {
        this.sort = sort;
    }

    public String getSort()
    {
        return sort;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public List<NavClassify> getClassifies() {
        return classifies;
    }

    public void setClassifies(List<NavClassify> classifies) {
        this.classifies = classifies;
    }

    public List<NavClassify> getTags() {
        return tags;
    }

    public void setTags(List<NavClassify> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("name", getName())
                .append("intro", getIntro())
                .append("icon", getIcon())
                .append("linkUrl", getLinkUrl())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .append("sort", getSort())
                .append("status", getStatus())
                .toString();
    }
}