package com.swing.student.web.dto;

import java.util.Objects;

/**
 * @author swing
 */
public class ScoreDTO {
    /**
     * 用户ID
     */
    private Long id;
    /**
     * 真名
     */
    private String username;
    /**
     * 学号
     */
    private String studentNum;

    /**
     * 班级
     */
    private String className;
    /**
     * 课程名
     */
    private String courseName;

    /**
     * 成绩
     */
    private Integer score;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScoreDTO scoreDTO = (ScoreDTO) o;
        return Objects.equals(id, scoreDTO.id) &&
                Objects.equals(username, scoreDTO.username) &&
                Objects.equals(studentNum, scoreDTO.studentNum) &&
                Objects.equals(className, scoreDTO.className) &&
                Objects.equals(courseName, scoreDTO.courseName) &&
                Objects.equals(score, scoreDTO.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, studentNum, className, courseName, score);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(String studentNum) {
        this.studentNum = studentNum;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "ScoreDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", studentNum='" + studentNum + '\'' +
                ", className='" + className + '\'' +
                ", courseName='" + courseName + '\'' +
                ", score=" + score +
                '}';
    }
}
