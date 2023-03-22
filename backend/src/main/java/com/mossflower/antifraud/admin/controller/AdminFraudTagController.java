package com.mossflower.antifraud.admin.controller;

import com.mossflower.antifraud.admin.service.AdminFraudTagService;
import com.mossflower.antifraud.common.R;
import com.mossflower.antifraud.entity.FraudTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author z's'b
 * @version 1.0
 * @date 2022/7/29 11:22
 */
@RestController
@RequestMapping("/admins/fraudTag")
public class AdminFraudTagController {

    @Autowired
    private AdminFraudTagService adminFraudTagService;

    @GetMapping("/list")
    public R<List<String>> getList() {
        List<String> fraudTagList = adminFraudTagService.getFraudTagList();
        return fraudTagList.size() == 0 ? R.err("暂无数据") : R.ok("获取成功", fraudTagList);
    }

    @PostMapping("/save")
    public R<String> save(@RequestBody FraudTag fraudTag) {
        Integer flag = adminFraudTagService.addFraudTag(fraudTag);
        if (flag == 2) {
            return R.err("该标签已存在");
        } else if (flag == 1) {
            return R.ok("添加成功");
        } else {
            return R.err("添加失败");
        }
    }
}