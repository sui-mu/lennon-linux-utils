package site.suimu.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import site.suimu.common.annotation.Excel;
import site.suimu.common.core.domain.BaseEntity;

/**
 * 链接分类关系对象 nav_cl_rel
 * 
 * @author ruoyi
 * @date 2021-11-27
 */
public class NavClRel extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 标签编码 */
    private Long linkId;

    /** 类型编码 */
    private Long classifyId;

    /** 人员编码 */
    @Excel(name = "人员编码")
    private Long userId;

    public void setLinkId(Long linkId) 
    {
        this.linkId = linkId;
    }

    public Long getLinkId() 
    {
        return linkId;
    }
    public void setClassifyId(Long classifyId) 
    {
        this.classifyId = classifyId;
    }

    public Long getClassifyId() 
    {
        return classifyId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("linkId", getLinkId())
            .append("classifyId", getClassifyId())
            .append("userId", getUserId())
            .toString();
    }
}
