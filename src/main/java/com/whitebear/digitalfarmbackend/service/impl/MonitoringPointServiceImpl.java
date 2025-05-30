package com.whitebear.digitalfarmbackend.service.impl;

import com.whitebear.digitalfarmbackend.mapper.FarmBaseMapper;
import com.whitebear.digitalfarmbackend.mapper.MonitoringPointMapper;
import com.whitebear.digitalfarmbackend.model.dto.MPPageResult;
import com.whitebear.digitalfarmbackend.model.dto.MonitoringPointDTO;
import com.whitebear.digitalfarmbackend.model.entity.FarmBase;
import com.whitebear.digitalfarmbackend.model.entity.MonitoringPoint;
import com.whitebear.digitalfarmbackend.service.MonitoringPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class MonitoringPointServiceImpl implements MonitoringPointService {

    private final MonitoringPointMapper monitoringPointMapper;
    private final FarmBaseMapper farmBaseMapper;

    public MonitoringPointServiceImpl(MonitoringPointMapper monitoringPointMapper, FarmBaseMapper farmBaseMapper) {
        this.monitoringPointMapper = monitoringPointMapper;
        this.farmBaseMapper = farmBaseMapper;
    }

    @Override
    public List<MonitoringPointDTO> getAllMonitoringPoints() {
        List<MonitoringPoint> monitoringPoints = monitoringPointMapper.selectAllMonitoringPoints();
        return convertToDTO(monitoringPoints);
    }

    /**
     * 根据基地ID和关键词筛选监测点信息。
     *
     * <p>查询逻辑如下：</p>
     * <ul>
     *     <li>如果基地ID和关键词都不为空，则按两者组合条件查询</li>
     *     <li>如果只有基地ID不为空，则仅按基地ID查询</li>
     *     <li>如果只有关键词不为空，则按关键词模糊查询</li>
     *     <li>如果两者都为空，则返回所有监测点信息</li>
     * </ul>
     *
     * @param baseId  基地ID（字符串形式）
     * @param keyword 关键词，用于匹配监测点名称或位置等字段
     * @return 符合条件的监测点DTO列表
     */
    @Override
//    public List<MonitoringPointDTO> getMonitoringPointsByConditions(String baseId, String keyword, int pageNum, int pageSize) {
//        List<MonitoringPoint> monitoringPoints;
//        if(baseId != null && !baseId.isEmpty() && keyword != null && !keyword.isEmpty()){ // 基地ID和关键字都非空
//            monitoringPoints = monitoringPointMapper.selectMonitoringPointsByBaseIdAndKeyword(Integer.parseInt(baseId), keyword);
//        }
//        else if(baseId != null && !baseId.isEmpty()){ // 基地ID非空，返回基地ID下的
//            monitoringPoints = monitoringPointMapper.selectMonitoringPointsByBaseId(Integer.parseInt(baseId));
//        }
//        else if(keyword != null && !keyword.isEmpty()){ // 仅关键字非空，返回检索关键字的
//            monitoringPoints = monitoringPointMapper.selectMonitoringPointsByKeyword(keyword);
//        }
//        else{ // 都为空返回所有
//            monitoringPoints = monitoringPointMapper.selectAllMonitoringPoints();
//        }
//        return convertToDTO(monitoringPoints);
//    }
    public MPPageResult<MonitoringPointDTO> getMonitoringPointsByConditions(
            String baseId, String keyword, int pageNum, int pageSize) {

        // 参数校验
        if (pageNum <= 0 || pageSize <= 0) {
            throw new IllegalArgumentException("分页参数必须大于0");
        }

        // 计算起始位置（LIMIT offset, size）
        int offset = (pageNum - 1) * pageSize;

        // 查询当前页数据
        List<MonitoringPoint> list = monitoringPointMapper.selectByConditions(baseId, keyword, offset, pageSize);

        List<MonitoringPointDTO> listDTO = convertToDTO(list);
        // 查询总记录数
        long total = monitoringPointMapper.countByConditions(baseId, keyword);

        // 返回分页结果
        return new MPPageResult<>(listDTO, total, pageNum, pageSize);
    }

    @Override
    public List<Map<String, Object>> getBaseOptions() {
        List<FarmBase> farmBases = farmBaseMapper.selectBaseOptions();
        List<Map<String, Object>> options = new ArrayList<>();

        farmBases.forEach(farmBase -> {
            Map<String, Object> option = new HashMap<>(); // 创建一个选项
            option.put("label", farmBase.getBaseName()); //  将基地名称作为标签
            option.put("value", farmBase.getBaseId().toString()); //  将ID转换为字符串
            options.add(option); //  添加选项
        });
        return options;
    }
    private List<MonitoringPointDTO> convertToDTO(List<MonitoringPoint> monitoringPoints){
        List<MonitoringPointDTO> dtoList = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        for (MonitoringPoint point : monitoringPoints) {
            MonitoringPointDTO dto = new MonitoringPointDTO(); // 创建DTO对象
            //  将数据复制到DTO对象中
            dto.setPointId(point.getPointId()); //  设置pointId
            dto.setBaseName(point.getBaseName()); // 设置baseName基地名称
            dto.setPointName(point.getPointName()); //  设置pointName检测点名称
            dto.setLocation(point.getLocation()); //  设置location
            dto.setImgUrl(point.getImageUrl()); // 图片
            
            // Format LocalDateTime using DateTimeFormatter
            if (point.getCreateTime() != null) {
                dto.setCreateTime(formatter.format(point.getCreateTime()));
            } else {
                dto.setCreateTime(""); // Handle null dates
            }
            
            dtoList.add(dto); //  添加到列表中
        }
        return dtoList;
    }
}
