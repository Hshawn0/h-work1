package com.swing.student.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * score
 *
 * @author swing
 */
public class Score implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 学生id
     */
    private Long studentId;

    /**
     * 课程id
     */
    private Long courseId;

    /**
     * 成绩
     */
    private Integer score;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return Objects.equals(id, score1.id) &&
                Objects.equals(studentId, score1.studentId) &&
                Objects.equals(courseId, score1.courseId) &&
                Objects.equals(score, score1.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, studentId, courseId, score);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", courseId=" + courseId +
                ", score=" + score +
                '}';
    }
}