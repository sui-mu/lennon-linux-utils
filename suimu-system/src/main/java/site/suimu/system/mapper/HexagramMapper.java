package site.suimu.system.mapper;

import java.util.List;
import site.suimu.system.domain.Hexagram;

/**
 * 卦Mapper接口
 * 
 * @author ruoyi
 * @date 2023-06-30
 */
public interface HexagramMapper 
{
    /**
     * 查询卦
     * 
     * @param id 卦主键
     * @return 卦
     */
    public Hexagram selectHexagramById(String id);

    /**
     * 查询卦列表
     * 
     * @param hexagram 卦
     * @return 卦集合
     */
    public List<Hexagram> selectHexagramList(Hexagram hexagram);

    /**
     * 新增卦
     * 
     * @param hexagram 卦
     * @return 结果
     */
    public int insertHexagram(Hexagram hexagram);

    /**
     * 修改卦
     * 
     * @param hexagram 卦
     * @return 结果
     */
    public int updateHexagram(Hexagram hexagram);

    /**
     * 删除卦
     * 
     * @param id 卦主键
     * @return 结果
     */
    public int deleteHexagramById(String id);

    /**
     * 批量删除卦
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHexagramByIds(String[] ids);
}
