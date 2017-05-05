package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;

public class MainGenerator {


    private static final String PROJECT_DIR = System.getProperty("user.dir");

    public static void main(String[] args) {
        Schema schema = new Schema(1, "com.greendao.db");
        schema.enableKeepSectionsByDefault();

        addTables(schema);

        try {

            new DaoGenerator().generateAll(schema, PROJECT_DIR + "/app/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addTables(final Schema schema) {
        Entity question = addQuestion(schema);
        Entity userResponse = addUserResponse(schema);

        Property userId = userResponse.addLongProperty("userId").notNull().getProperty();
        question.addToMany(userResponse, userId, "userRepos");
    }

    private static Entity addQuestion(final Schema schema) {
        Entity question = schema.addEntity("Question");
        question.addIdProperty().primaryKey().autoincrement();
        question.addIntProperty("q_no").unique();
        question.addStringProperty("q_code");
        question.addStringProperty("exam_code");
        question.addStringProperty("max_marks");
        question.addStringProperty("question");
        question.addStringProperty("image");
        question.addStringProperty("option_json");
        question.addStringProperty("answer");

        return question;
    }

    private static Entity addUserResponse(final Schema schema) {
        Entity userResponse = schema.addEntity("UserResponses");
        userResponse.addIdProperty().primaryKey().autoincrement();
        userResponse.addIntProperty("q_no").unique();
        userResponse.addStringProperty("q_code");
        userResponse.addStringProperty("exam_code");
        userResponse.addStringProperty("max_marks");
        userResponse.addStringProperty("question");
        userResponse.addStringProperty("answer");
        userResponse.addStringProperty("user_response");

        return userResponse;
    }
}
