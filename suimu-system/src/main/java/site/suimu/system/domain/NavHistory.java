package site.suimu.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import site.suimu.common.annotation.Excel;
import site.suimu.common.core.domain.BaseEntity;

/**
 * 历史记录对象 nav_history
 * 
 * @author ruoyi
 * @date 2021-11-27
 */
public class NavHistory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编码（年月日+链接+用户编码） */
    private String id;

    /** 链接编码 */
    @Excel(name = "链接编码")
    private Long linkId;

    /** 用户编码 */
    @Excel(name = "用户编码")
    private Long userId;

    /** 1=点击；2=点赞 */
    @Excel(name = "1=点击；2=点赞")
    private String type;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setLinkId(Long linkId) 
    {
        this.linkId = linkId;
    }

    public Long getLinkId() 
    {
        return linkId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("linkId", getLinkId())
            .append("userId", getUserId())
            .append("type", getType())
            .toString();
    }
}
