package com.rzandjavagit.core.jxml;

import android.content.Context;

import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class XMLFeedParser {
    private Context f1115a;
    private C0367b f1116b;
    private boolean f1117c = false;

    public XMLFeedParser(Context argContext) {
        this.f1115a = argContext;
        this.f1117c = false;
        this.f1116b = new C0367b(argContext);
    }

    public String onReadAssetsFile(String argFileName) throws IOException {
        return this.f1116b.m1390a(argFileName);
    }

    public XMLFeedParser withTag(String argTagKey) {
        this.f1116b.m1394b(argTagKey);
        return this;
    }

    public XMLFeedParser withAttribute(String argAttributeKey) {
        this.f1116b.m1395c(argAttributeKey);
        return this;
    }

    public List<Map<String, String>> getAttributeItems() {
        return this.f1116b.m1392a();
    }

    public XMLFeedParser onXMLPrepareItems(String argXMLString) throws XmlPullParserException, UnsupportedEncodingException, IOException {
        this.f1116b.m1396d(argXMLString);
        return this;
    }

    public List<Map<String, String>> getXMLParsedItems(String argItemStartingEndingTag) throws Exception {
        return this.f1116b.m1397e(argItemStartingEndingTag);
    }

    @Deprecated
    public List<Map<String, String>> getXMLParsedItems(List<String> argKeyList, String argItemStartingEndingTag) throws Exception {
        return this.f1116b.m1393a((List) argKeyList, argItemStartingEndingTag);
    }

    public String getXMLByTagAttribute(String argXMLString, String argXMLTag, String argXMLAttribute, String argXMLAttributeValue) throws IOException, SAXException, ParserConfigurationException, TransformerException {
        return this.f1116b.m1391a(argXMLString, argXMLTag, argXMLAttribute, argXMLAttributeValue);
    }
}
