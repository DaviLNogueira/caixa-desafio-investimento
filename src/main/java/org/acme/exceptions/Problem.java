package org.acme.exceptions;

public class Problem {
    public int status;
    public String title;
    public String detail;

    public String errorClass;
    public Integer errorLine;
    public String errorFile;

    public Problem() {}

    public Problem(int status, String title, String detail) {
        this.status = status;
        this.title = title;
        this.detail = detail;
    }
}
