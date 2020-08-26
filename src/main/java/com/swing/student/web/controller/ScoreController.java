package com.swing.student.web.controller;

import com.swing.student.domain.Score;
import com.swing.student.service.ScoreService;
import com.swing.student.web.SkyResponse;
import com.swing.student.web.dto.ScoreDTO;
import com.swing.student.web.dto.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author swing
 */
@Controller
@RequestMapping("/student/score")
public class ScoreController {
    @Autowired
    private ScoreService scoreService;

    /**
     * 获取学生管理页面
     *
     * @return 页面
     */
    @GetMapping
    public String getManage() {
        return "student/score/score";
    }

    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ScoreDTO scoreDTO) {
        List<ScoreDTO> list = scoreService.listAll(scoreDTO);
        TableDataInfo rspData = new TableDataInfo();
        rspData.setTotal(list.size());
        rspData.setCode(200);
        rspData.setRows(list);
        return rspData;
    }

    /**
     * 新增视图
     */
    @GetMapping("/add")
    public String add() {
        return "student/score/add";
    }

    /**
     * 新增
     */
    @PostMapping("/add")
    @ResponseBody
    public SkyResponse addSave(@Validated Score score) {
        //判断课程名是否冲突
        scoreService.insert(score);
        return SkyResponse.success("新增成功");
    }

    /**
     * 修改视图
     */
    @GetMapping("/edit/{scoreId}")
    public String edit(@PathVariable("scoreId") Long scoreId, ModelMap map) {
        map.put("scoreDto", scoreService.getById(scoreId));
        return "student/score/edit";
    }

    /**
     * 修改
     */
    @PostMapping("/edit")
    @ResponseBody
    public SkyResponse editSave(Score score) {
        //判断学号是否冲突
        scoreService.update(score);
        return SkyResponse.success("更新成功");
    }

    /**
     * 批量删除接口
     */
    @PostMapping("/remove")
    @ResponseBody
    public SkyResponse remove(String ids) {
        scoreService.deleteByIds(ids);
        return SkyResponse.success("成功删除！");
    }
}
