package models;

import lombok.Data;

@Data
public class TestModel {
    public int id;
    public String name;
    public int status_id;
    public String method_name;
    public int project_id;
    public int session_id;
    public String start_time;
    public String end_time;
    public String env;
    public String browser;
    public int author_id;

    public TestModel(String name, int status_id, String method_name, int project_id, int session_id, String start_time, String end_time, String env, String browser, int author_id) {
        this.name = name;
        this.status_id = status_id;
        this.method_name = method_name;
        this.project_id = project_id;
        this.session_id = session_id;
        this.start_time = start_time;
        this.end_time = end_time;
        this.env = env;
        this.browser = browser;
        this.author_id = author_id;
    }
    public TestModel(int id, int author_id, String browser, String end_time, String env, String method_name, String name, int project_id, int session_id, String start_time, int status_id) {
        this.id = id;
        this.author_id = author_id;
        this.browser = browser;
        this.end_time = end_time;
        this.env = env;
        this.method_name = method_name;
        this.name = name;
        this.session_id = session_id;
        this.project_id = project_id;
        this.status_id = status_id;
        this.start_time = start_time;
    }
}
