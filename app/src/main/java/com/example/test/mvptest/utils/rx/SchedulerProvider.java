package com.example.test.mvptest.utils.rx;

import io.reactivex.Scheduler;

/**
 * Created by longzhijun on 2017/11/16.
 */

public interface SchedulerProvider {
    Scheduler ui();

    Scheduler computation();

    Scheduler io();

}
