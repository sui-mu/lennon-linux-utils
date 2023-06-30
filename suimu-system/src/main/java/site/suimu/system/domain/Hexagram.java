package site.suimu.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import site.suimu.common.annotation.Excel;
import site.suimu.common.core.domain.BaseEntity;

/**
 * 卦对象 hexagram
 *
 * @author ruoyi
 * @date 2023-06-30
 */
public class Hexagram extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 卦象 */
    @Excel(name = "卦象")
    private String id;

    /** 卦名 */
    @Excel(name = "卦名")
    private String name;

    /** 简述 */
    @Excel(name = "简述")
    private String info;

    /** 卦象 */
    @Excel(name = "卦象")
    private String image;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    /** 卦形 */
    @Excel(name = "卦形")
    private String shape;

    /** 标志 */
    @Excel(name = "标志")
    private String tag;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
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
    public void setInfo(String info)
    {
        this.info = info;
    }

    public String getInfo()
    {
        return info;
    }
    public void setImage(String image)
    {
        this.image = image;
    }

    public String getImage()
    {
        return image;
    }
    public void setContent(String content)
    {
        this.content = content;
    }

    public String getContent()
    {
        return content;
    }
    public void setShape(String shape)
    {
        this.shape = shape;
    }

    public String getShape()
    {
        return shape;
    }
    public void setTag(String tag)
    {
        this.tag = tag;
    }

    public String getTag()
    {
        return tag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("name", getName())
                .append("info", getInfo())
                .append("image", getImage())
                .append("content", getContent())
                .append("shape", getShape())
                .append("tag", getTag())
                .toString();
    }
}