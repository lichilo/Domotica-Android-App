package com.electronicsenterprice.finaltry2_3.db;

public class ConfiguracionesValues {
    private int id;
    private String IP;
    private String Host;
    private String DBase;
    private String Table;
    private String Username;
    private String Password;
    private String URL;


    public int getId(String string) {
        return id;
    }

    public String setId(int id) {
        this.id = id;
        return null;
    }

    public String getIP(String string) {
        return IP;
    }

    public String setIP(String IP) {
        this.IP = IP;
        return IP;
    }

    public String getHost(String string) {
        return Host;
    }

    public String setHost(String host) {
        Host = host;
        return host;
    }

    public String getDBase(String string) {
        return DBase;
    }

    public String setDBase(String DBase) {
        this.DBase = DBase;
        return DBase;
    }

    public String getUsername(String string) {
        return Username;
    }

    public String setUsername(String username) {
        Username = username;
        return username;
    }

    public String getPassword(String string) {
        return Password;
    }

    public String setPassword(String password) {
        Password = password;
        return password;
    }

    public String getTable(String string) { return Table; }

    public String setTable(String table) {
        Table = table;
        return table;
    }

    public String getURL() {
        return URL;
    }


    public String setURL(String URL) {
        this.URL = URL;
        return URL;
    }
}