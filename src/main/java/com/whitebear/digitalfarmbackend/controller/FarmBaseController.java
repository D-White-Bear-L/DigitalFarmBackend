package com.whitebear.digitalfarmbackend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.whitebear.digitalfarmbackend.model.entity.FarmBase;
import com.whitebear.digitalfarmbackend.service.FarmBaseService;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class FarmBaseController {
    @Autowired // 自动注入
    private FarmBaseService farmBaseService;
    @GetMapping("/base")
    public List<FarmBase> query(){
        List<FarmBase> list = farmBaseService.getBaseMapper().selectList(null);
        System.out.println(list);
        return list;
    }
}
