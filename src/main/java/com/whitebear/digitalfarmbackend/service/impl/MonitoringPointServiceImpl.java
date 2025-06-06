package com.whitebear.digitalfarmbackend.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whitebear.digitalfarmbackend.mapper.FarmBaseMapper;
import com.whitebear.digitalfarmbackend.mapper.MonitoringPointMapper;
import com.whitebear.digitalfarmbackend.model.dto.PageResult;
import com.whitebear.digitalfarmbackend.model.dto.MonitoringPointDTO;
import com.whitebear.digitalfarmbackend.model.entity.FarmBase;
import com.whitebear.digitalfarmbackend.model.entity.MonitoringPoint;
import com.whitebear.digitalfarmbackend.service.MonitoringPointService;

@Service
public class MonitoringPointServiceImpl implements MonitoringPointService {

    @Autowired
    private final MonitoringPointMapper monitoringPointMapper;
    @Autowired
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
    public PageResult<MonitoringPointDTO> getMonitoringPointsByConditions(String baseId, String keyword, int pageNum, int pageSize) {

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
        return new PageResult<>(listDTO, total, pageNum, pageSize);
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

    @Override
    public boolean addMonitoringPoint(MonitoringPointDTO monitoringPointDTO) {
        MonitoringPoint monitoringPoint = new MonitoringPoint();
        monitoringPoint.setBaseId(monitoringPointDTO.getBaseId());
        monitoringPoint.setBaseName(monitoringPointDTO.getBaseName());;
        monitoringPoint.setPointName(monitoringPointDTO.getPointName());
        monitoringPoint.setLocation(monitoringPointDTO.getLocation());
        monitoringPoint.setImageUrl(monitoringPointDTO.getImageUrl());
        monitoringPoint.setLongitude(monitoringPointDTO.getLongitude());
        monitoringPoint.setLatitude(monitoringPointDTO.getLatitude());
        monitoringPoint.setCreateTime(LocalDateTime.now());
        monitoringPoint.setUpdateTime(LocalDateTime.now());

        return monitoringPointMapper.insertMonitoringPoint(monitoringPoint) > 0;
    }

    @Override
    public boolean updateMonitoringPoint(MonitoringPointDTO monitoringPointDTO) {
        MonitoringPoint monitoringPoint = new MonitoringPoint();
        monitoringPoint.setPointId(monitoringPointDTO.getPointId());
        monitoringPoint.setBaseId(monitoringPointDTO.getBaseId());
        monitoringPoint.setPointName(monitoringPointDTO.getPointName());
        monitoringPoint.setLocation(monitoringPointDTO.getLocation());
        monitoringPoint.setImageUrl(monitoringPointDTO.getImageUrl());
        monitoringPoint.setLongitude(monitoringPointDTO.getLongitude());
        monitoringPoint.setLatitude(monitoringPointDTO.getLatitude());
        monitoringPoint.setUpdateTime(LocalDateTime.now());

        return monitoringPointMapper.updateMonitoringPoint(monitoringPoint) > 0;
    }

    @Override
    public boolean deleteMonitoringPoint(Integer pointId) {
        return monitoringPointMapper.deleteMonitoringPoint(pointId) > 0; // 返回删除结果
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
            dto.setImageUrl(point.getImageUrl()); // 图片
            dto.setLongitude(point.getLongitude()); // 经度
            dto.setLatitude(point.getLatitude()); // 纬度
            
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
