package com.rzandjavagit.prohttprequest.http;

public class Parameter {
    public Field field;
    public File file;
    public Header header;
    public Property property;
    public Query query;

    public Parameter() {
        //System.out.println("========================|CONSTRUCTOR_PARAMETER");
        field = new Field();
        file = new File();
        header = new Header();
        property = new Property();
        query = new Query();
    }

    public Field field() {
        return this.field;
    }

    public File file() {
        return this.file;
    }

    public Header header() {
        return this.header;
    }

    public Property property() {
        return this.property;
    }

    public Query query() {
        return this.query;
    }
}
