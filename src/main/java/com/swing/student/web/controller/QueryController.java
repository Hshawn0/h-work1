package com.swing.student.web.controller;

import com.swing.student.service.ScoreService;
import com.swing.student.web.dto.ScoreDTO;
import com.swing.student.web.dto.TableDataInfo;
import com.swing.student.web.utils.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author swing
 */
@Controller
@RequestMapping("/student/query")
public class QueryController {
    @Autowired
    private ScoreService scoreService;

    /**
     * 获取学生管理页面
     *
     * @return 页面
     */
    @GetMapping
    public String getManage() {
        return "student/query/query";
    }

    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ScoreDTO scoreDTO) {
        //获取学号
        String studentNum = (String) ServletUtils.getRequest().getSession().getAttribute("studentNum");
        scoreDTO.setStudentNum(studentNum);
        List<ScoreDTO> list = scoreService.listAll(scoreDTO);
        TableDataInfo rspData = new TableDataInfo();
        rspData.setTotal(list.size());
        rspData.setCode(200);
        rspData.setRows(list);
        return rspData;
    }
}
