package com.example.test.mvptest.data.db;

import com.example.test.mvptest.data.db.module.Option;
import com.example.test.mvptest.data.db.module.Question;
import com.example.test.mvptest.data.db.module.User;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by longzhijun on 2017/11/16.
 */

public interface DbHelper {
    Observable<Long> insertUser(final User user);

    Observable<List<User>> getAllUsers();

    Observable<List<Question>> getAllQuestions();

    Observable<Boolean> isQuestionEmpty();

    Observable<Boolean> isOptionEmpty();

    Observable<Boolean> saveQuestion(Question question);

    Observable<Boolean> saveOption(Option option);

    Observable<Boolean> saveQuestionList(List<Question> questionList);

    Observable<Boolean> saveOptionList(List<Option> optionList);
}
