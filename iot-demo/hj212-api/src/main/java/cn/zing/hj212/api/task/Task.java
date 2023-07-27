package cn.zing.hj212.api.task;

public interface Task {

    void start(String name);

    void pause(String name);

    void interrupt(String name);

    void sleep(String name, long millis);

    void dispatch();

}
