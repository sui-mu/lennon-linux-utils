package site.suimu.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import site.suimu.common.annotation.Excel;
import site.suimu.common.core.domain.BaseEntity;

/**
 * 命名空间对象 nnav_namespace
 * 
 * @author lennon
 * @date 2023-07-18
 */
public class NavNamespace extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 空间编码 */
    private Long id;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 关键词 */
    @Excel(name = "关键词")
    private String key;

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
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setKey(String key) 
    {
        this.key = key;
    }

    public String getKey() 
    {
        return key;
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
            .append("name", getName())
            .append("key", getKey())
            .append("sortNum", getSortNum())
            .append("remark", getRemark())
            .toString();
    }
}
