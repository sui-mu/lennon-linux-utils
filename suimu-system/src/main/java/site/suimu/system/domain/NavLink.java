package site.suimu.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import site.suimu.common.annotation.Excel;
import site.suimu.common.core.domain.BaseEntity;

/**
 * 导航链接对象 nnav_link
 * 
 * @author lennon
 * @date 2023-07-18
 */
public class NavLink extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编码 */
    private Long id;

    /** 分类编码 */
    @Excel(name = "分类编码")
    private Long classifyId;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 图标 */
    @Excel(name = "图标")
    private String icon;

    /** 排序 */
    @Excel(name = "排序")
    private Long sortNum;

    /** 简介 */
    @Excel(name = "简介")
    private String intro;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setClassifyId(Long classifyId) 
    {
        this.classifyId = classifyId;
    }

    public Long getClassifyId() 
    {
        return classifyId;
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
    public void setIntro(String intro) 
    {
        this.intro = intro;
    }

    public String getIntro() 
    {
        return intro;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("classifyId", getClassifyId())
            .append("name", getName())
            .append("icon", getIcon())
            .append("sortNum", getSortNum())
            .append("intro", getIntro())
            .toString();
    }
}
