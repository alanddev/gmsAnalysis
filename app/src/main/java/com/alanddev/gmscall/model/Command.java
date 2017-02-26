package com.alanddev.gmscall.model;

/**
 * Created by td.long on 21/02/2017.
 */
public class Command  extends Model {
    private int id;
    private String cmd;
    private String server;
    private String timeTest;
    private String stream;
    private String number;
    private String timeToNext;
    private String user;
    private String pass;
    private String status;

    public Command(){

    }

    public Command(int id, String cmd, String server){
        this.id = id;
        this.cmd = cmd;
        this.server = server;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getTimeTest() {
        return timeTest;
    }

    public void setTimeTest(String timeTest) {
        this.timeTest = timeTest;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTimeToNext() {
        return timeToNext;
    }

    public void setTimeToNext(String timeToNext) {
        this.timeToNext = timeToNext;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
