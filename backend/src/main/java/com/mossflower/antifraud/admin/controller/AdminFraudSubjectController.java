package com.mossflower.antifraud.admin.controller;

import com.mossflower.antifraud.admin.service.AdminFraudSubjectService;
import com.mossflower.antifraud.common.R;
import com.mossflower.antifraud.entity.FraudSubject;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author z's'b
 * @version 1.0
 * @date 2022/7/17 9:37
 */
@RestController
@RequestMapping("/admins/fraudSubject")
public class AdminFraudSubjectController {

    @Autowired
    private AdminFraudSubjectService adminFraudSubjectService;

    @PostMapping("/save")
    public R<Long> save(@NotNull @RequestBody FraudSubject fraudSubject) {
        Integer flag = adminFraudSubjectService.save(fraudSubject);
        if (flag == 1) {
            return R.ok("保存成功", fraudSubject.getId());
        } else if (flag == 2) {
            return R.err("该题目已存在");
        } else {
            return R.err("保存失败");
        }
    }

    @DeleteMapping("/delete/{id}")
    public R<Boolean> deleteOne(@PathVariable Long id) {
        Boolean flag = adminFraudSubjectService.deleteOne(id);
        return flag ? R.ok("删除成功") : R.err("删除失败");
    }

    @DeleteMapping("/deleteBatch")
    public R<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean flag = adminFraudSubjectService.deleteBatch(ids);
        return flag ? R.ok("删除成功") : R.err("删除失败");
    }

    @PutMapping("/update")
    public R<Boolean> update(@RequestBody FraudSubject fraudSubject) {
        Boolean flag = adminFraudSubjectService.update(fraudSubject);
        return flag ? R.ok("更新成功") : R.err("更新失败");
    }

    @GetMapping("/list")
    public R<List<FraudSubject>> getAllFraudSubject() {
        List<FraudSubject> fraudSubjectList = adminFraudSubjectService.getAll();
        return fraudSubjectList.size() == 0 ? R.err("暂无数据"): R.ok("获取成功", fraudSubjectList);
    }

}