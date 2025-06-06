//package com.whitebear.digitalfarmbackend.controller;
//
//import com.whitebear.digitalfarmbackend.model.dto.ManualReportDTO;
//import com.whitebear.digitalfarmbackend.model.dto.PageResult;
//import com.whitebear.digitalfarmbackend.service.ManualReportService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/manualReport")
//public class ManualReportController {
//    @Autowired
//    private ManualReportService manualReportService;
//
//    @RequestMapping("/list")
//    public PageResult<ManualReportDTO> getAllManualReport(){
//
//    }
//
//    @RequestMapping("/add")
//    public Map<String, Object> addManualReport(ManualReportDTO manualReportDTO){
//
//    }
//
//    @RequestMapping("/update")
//    public Map<String, Object> updateManualReport(ManualReportDTO manualReportDTO){
//
//    }
//
//    @RequestMapping("/delete{simpleId}")
//    public Map<String, Object> deleteManualReport(Integer simpleId){
//        Map<String, Object> result = new HashMap<>();
//        try {
//            boolean success = manualReportService.deleteManualReport(simpleId);
//            System.out.println("Delete attempt for evaluationId: " + simpleId + ", success: " + success);
//
//            if (success) {
//                result.put("code", 200);
//                result.put("message", "删除人工上报数据成功");
//            } else {
//                result.put("code", 404);
//                result.put("message", "删除人工上报数据失败：记录不存在或已被删除");
//            }
//        } catch (Exception e) {
//            System.err.println("Error in delete operation for evaluationId " + simpleId + ": " + e.getMessage());
//            e.printStackTrace();
//            result.put("code", 500);
//            result.put("message", "删除人工上报数据失败：" + e.getMessage());
//        }
//        return result;
//    }
//}
