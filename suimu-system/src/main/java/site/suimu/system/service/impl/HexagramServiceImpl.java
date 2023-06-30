package site.suimu.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.suimu.system.mapper.HexagramMapper;
import site.suimu.system.domain.Hexagram;
import site.suimu.system.service.IHexagramService;

/**
 * 卦Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-06-30
 */
@Service
public class HexagramServiceImpl implements IHexagramService 
{
    @Autowired
    private HexagramMapper hexagramMapper;

    /**
     * 查询卦
     * 
     * @param id 卦主键
     * @return 卦
     */
    @Override
    public Hexagram selectHexagramById(String id)
    {
        return hexagramMapper.selectHexagramById(id);
    }

    /**
     * 查询卦列表
     * 
     * @param hexagram 卦
     * @return 卦
     */
    @Override
    public List<Hexagram> selectHexagramList(Hexagram hexagram)
    {
        return hexagramMapper.selectHexagramList(hexagram);
    }

    /**
     * 新增卦
     * 
     * @param hexagram 卦
     * @return 结果
     */
    @Override
    public int insertHexagram(Hexagram hexagram)
    {
        return hexagramMapper.insertHexagram(hexagram);
    }

    /**
     * 修改卦
     * 
     * @param hexagram 卦
     * @return 结果
     */
    @Override
    public int updateHexagram(Hexagram hexagram)
    {
        return hexagramMapper.updateHexagram(hexagram);
    }

    /**
     * 批量删除卦
     * 
     * @param ids 需要删除的卦主键
     * @return 结果
     */
    @Override
    public int deleteHexagramByIds(String[] ids)
    {
        return hexagramMapper.deleteHexagramByIds(ids);
    }

    /**
     * 删除卦信息
     * 
     * @param id 卦主键
     * @return 结果
     */
    @Override
    public int deleteHexagramById(String id)
    {
        return hexagramMapper.deleteHexagramById(id);
    }
}
