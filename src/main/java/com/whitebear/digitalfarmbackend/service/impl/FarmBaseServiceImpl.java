package com.whitebear.digitalfarmbackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whitebear.digitalfarmbackend.mapper.FarmBaseMapper;
import com.whitebear.digitalfarmbackend.model.entity.FarmBase;
import com.whitebear.digitalfarmbackend.service.FarmBaseService;
import org.springframework.stereotype.Service;

@Service
public class FarmBaseServiceImpl extends ServiceImpl<FarmBaseMapper, FarmBase> implements FarmBaseService {
    // 实现特殊的业务方法
}