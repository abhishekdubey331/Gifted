package com.gifted.app.giftededucation.pojo;

import com.orm.SugarRecord;

/**
 * Created by DIC on 4/21/2017.
 */

public class UserResponses extends SugarRecord {

    private String q_id, exam_code, max_marks, right_answer, user_response;


    public UserResponses(String q_id, String exam_code, String max_marks, String right_answer, String user_response) {
        this.q_id = q_id;
        this.exam_code = exam_code;
        this.max_marks = max_marks;
        this.right_answer = right_answer;
        this.user_response = user_response;
    }


    public String getQ_id() {
        return q_id;
    }

    public void setQ_id(String q_id) {
        this.q_id = q_id;
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

    public String getRight_answer() {
        return right_answer;
    }

    public void setRight_answer(String right_answer) {
        this.right_answer = right_answer;
    }

    public String getUser_response() {
        return user_response;
    }

    public void setUser_response(String user_response) {
        this.user_response = user_response;
    }
}
