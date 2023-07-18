package site.suimu.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import site.suimu.common.annotation.Excel;
import site.suimu.common.core.domain.BaseEntity;

/**
 * 导航分类对象 nnav_classify
 * 
 * @author lennon
 * @date 2023-07-18
 */
public class NavClassify extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 分类编码 */
    private Long id;

    /** 空间编码 */
    @Excel(name = "空间编码")
    private Long namespaceId;

    /** 分类名称 */
    @Excel(name = "分类名称")
    private String name;

    /** 图标 */
    @Excel(name = "图标")
    private String icon;

    /** 排序 */
    @Excel(name = "排序")
    private Long sortNum;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setNamespaceId(Long namespaceId) 
    {
        this.namespaceId = namespaceId;
    }

    public Long getNamespaceId() 
    {
        return namespaceId;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setIcon(String icon) 
    {
        this.icon = icon;
    }

    public String getIcon() 
    {
        return icon;
    }
    public void setSortNum(Long sortNum) 
    {
        this.sortNum = sortNum;
    }

    public Long getSortNum() 
    {
        return sortNum;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("namespaceId", getNamespaceId())
            .append("name", getName())
            .append("icon", getIcon())
            .append("sortNum", getSortNum())
            .append("remark", getRemark())
            .toString();
    }
}
