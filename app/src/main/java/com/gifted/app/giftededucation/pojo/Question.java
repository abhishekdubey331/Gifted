package com.gifted.app.giftededucation.pojo;

import com.orm.SugarRecord;

/**
 * Created by DIC on 4/18/2017.
 */

public class Question extends SugarRecord {

    private String q_no_, q_code, exam_code, max_marks, question, image, option_json, answer;

    public Question() {
    }


    public Question(String q_no_, String q_code, String exam_code, String max_marks, String question, String image, String option_json, String answer) {
        this.q_no_ = q_no_;
        this.q_code = q_code;
        this.exam_code = exam_code;
        this.max_marks = max_marks;
        this.question = question;
        this.image = image;
        this.option_json = option_json;
        this.answer = answer;
    }


    public String getQ_no_() {
        return q_no_;
    }

    public void setQ_no_(String q_no_) {
        this.q_no_ = q_no_;
    }

    public String getQ_code() {
        return q_code;
    }

    public void setQ_code(String q_code) {
        this.q_code = q_code;
    }

    public String getExam_code() {
        return exam_code;
    }

    public void setExam_code(String exam_code) {
        this.exam_code = exam_code;
    }

    public String getMax_marks() {
        return max_marks;
    }

    public void setMax_marks(String max_marks) {
        this.max_marks = max_marks;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getOption_json() {
        return option_json;
    }

    public void setOption_json(String option_json) {
        this.option_json = option_json;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
