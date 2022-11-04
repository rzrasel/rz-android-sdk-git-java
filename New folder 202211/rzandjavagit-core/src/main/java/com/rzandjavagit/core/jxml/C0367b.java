package com.rzandjavagit.core.jxml;

import android.content.Context;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

class C0367b {
    private Context f1107a;
    private XmlPullParserFactory f1108b;
    private XmlPullParser f1109c;
    private boolean f1110d = false;
    private HashSet<String> f1111e;
    private HashSet<String> f1112f;
    private List<Map<String, String>> f1113g;

    public C0367b(Context context) {
        this.f1107a = context;
        this.f1111e = new HashSet();
        this.f1111e.clear();
        this.f1112f = new HashSet();
        this.f1112f.clear();
        this.f1113g = new ArrayList();
        this.f1113g.clear();
        this.f1110d = false;
    }

    protected String m1390a(String str) throws IOException {
        try {
            InputStream open = this.f1107a.getAssets().open(str);
            byte[] bArr = new byte[open.available()];
            open.read(bArr);
            open.close();
            return new String(bArr);
        } catch (IOException e) {
            throw e;
        }
    }

    protected C0367b m1394b(String str) {
        if (!C0367b.m1389f(str)) {
            this.f1111e.add(str);
        }
        return this;
    }

    protected C0367b m1395c(String str) {
        if (!C0367b.m1389f(str)) {
            this.f1112f.add(str);
        }
        return this;
    }

    protected C0367b m1396d(String str) throws XmlPullParserException, UnsupportedEncodingException, IOException {
        if (C0367b.m1389f(str)) {
            this.f1110d = true;
            return this;
        }
        try {
            this.f1108b = XmlPullParserFactory.newInstance();
            this.f1109c = this.f1108b.newPullParser();
            this.f1109c.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            InputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes("UTF8"));
            this.f1109c.setInput(byteArrayInputStream, null);
            byteArrayInputStream.close();
            return this;
        } catch (XmlPullParserException e) {
            throw e;
        } catch (UnsupportedEncodingException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        }
    }

    protected List<Map<String, String>> m1397e(String str) throws Exception {
        List<Map<String, String>> arrayList = new ArrayList();
        if (this.f1111e.size() <= 0) {
            return arrayList;
        }
        Map hashMap = new HashMap();
        this.f1113g = new ArrayList();
        Map hashMap2 = new HashMap();
        if (this.f1110d) {
            return arrayList;
        }
        try {
            int eventType = this.f1109c.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType != 0) {
                    switch (eventType) {
                        case XmlPullParser.START_DOCUMENT:
                            arrayList.clear();
                            f1113g.clear();
                            break;
                        case XmlPullParser.START_TAG:
                            String name = this.f1109c.getName();
                            if (!name.equals(str)) {
                                if (m1387a(this.f1111e, name)) {
                                    Object attributeValue;
                                    String str2 = null;
                                    if (this.f1109c.getAttributeCount() > 0) {
                                        str2 = this.f1109c.getAttributeName(0);
                                        attributeValue = this.f1109c.getAttributeValue("", str2);
                                    } else {
                                        attributeValue = null;
                                    }
                                    hashMap.put(name, this.f1109c.nextText().replaceAll("\\s+", " ").trim().replaceAll("^\\s+", "").replaceAll("\\s+$", "").trim());
                                    if (!m1387a(this.f1112f, str2)) {
                                        hashMap2.put(name, "attribute_not_found");
                                        break;
                                    }
                                    hashMap2.put(str2, attributeValue);
                                    break;
                                }
                            }
                            hashMap = new HashMap();
                            hashMap.clear();
                            hashMap2 = new HashMap();
                            hashMap2.clear();
                            break;
                        case XmlPullParser.END_TAG:
                            if (this.f1109c.getName().equalsIgnoreCase(str)) {
                                arrayList.add(hashMap);
                                if (this.f1113g != null) {
                                    this.f1113g.add(hashMap2);
                                    break;
                                }
                            }
                            break;
                        default:
                            break;
                    }
                }
                eventType = this.f1109c.next();
            }
            return arrayList;
        } catch (Exception e) {
            throw e;
        }
    }

    @Deprecated
    protected List<Map<String, String>> m1393a(List<String> list, String str) throws Exception {
        List<Map<String, String>> arrayList = new ArrayList();
        Map hashMap = new HashMap();
        this.f1113g = new ArrayList();
        Map hashMap2 = new HashMap();
        if (this.f1110d) {
            return arrayList;
        }
        try {
            int eventType = this.f1109c.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType != 0) {
                    switch (eventType) {
                        case XmlPullParser.START_DOCUMENT:
                            arrayList.clear();
                            this.f1113g.clear();
                            break;
                        case XmlPullParser.START_TAG:
                            String name = this.f1109c.getName();
                            if (!name.equals(str)) {
                                if (m1388b(list, name)) {
                                    Object attributeValue;
                                    String str2 = null;
                                    if (this.f1109c.getAttributeCount() > 0) {
                                        str2 = this.f1109c.getAttributeName(0);
                                        attributeValue = this.f1109c.getAttributeValue("", str2);
                                    } else {
                                        attributeValue = null;
                                    }
                                    hashMap.put(name, this.f1109c.nextText().replaceAll("\\s+", " ").trim().replaceAll("^\\s+", "").replaceAll("\\s+$", "").trim());
                                    if (!m1387a(this.f1112f, str2)) {
                                        hashMap2.put(name, "attribute_not_found");
                                        break;
                                    }
                                    hashMap2.put(str2, attributeValue);
                                    break;
                                }
                            }
                            hashMap = new HashMap();
                            hashMap.clear();
                            hashMap2 = new HashMap();
                            hashMap2.clear();
                            break;
                        case XmlPullParser.END_TAG:
                            if (this.f1109c.getName().equalsIgnoreCase(str)) {
                                arrayList.add(hashMap);
                                if (this.f1113g != null) {
                                    this.f1113g.add(hashMap2);
                                    break;
                                }
                            }
                            break;
                        default:
                            break;
                    }
                }
                eventType = this.f1109c.next();
            }
            return arrayList;
        } catch (Exception e) {
            throw e;
        }
    }

    protected List<Map<String, String>> m1392a() {
        return this.f1113g;
    }

    protected String m1391a(String str, String str2, String str3, String str4) throws ParserConfigurationException, SAXException, IOException, TransformerException {
        if (C0367b.m1389f(str)) {
            return null;
        }
        InputSource inputSource = new InputSource(new StringReader(str));
        DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
        newInstance.setCoalescing(true);
        try {
            NodeList elementsByTagName = newInstance.newDocumentBuilder().parse(inputSource).getElementsByTagName(str2);
            for (int i = 0; i < elementsByTagName.getLength(); i++) {
                Node item = elementsByTagName.item(i);
                if (item.getAttributes().getNamedItem(str3).getNodeValue().equalsIgnoreCase(str4)) {
                    return m1386a(item);
                }
            }
            return null;
        } catch (ParserConfigurationException e) {
            throw e;
        } catch (SAXException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        }
    }


    private String m1386a(Node node) throws TransformerConfigurationException, TransformerException {
        DOMSource dOMSource = new DOMSource();
        Writer stringWriter = new StringWriter();
        StreamResult streamResult = new StreamResult(stringWriter);
        try {
            Transformer newTransformer = TransformerFactory.newInstance().newTransformer();
            newTransformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            newTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
            newTransformer.transform(new DOMSource(node), new StreamResult(stringWriter));
            return stringWriter.toString();
        } catch (TransformerConfigurationException e) {
            throw e;
        } catch (TransformerException e) {
            throw e;
        }
    }

    private boolean m1387a(HashSet<String> hashSet, String str) {
        if (C0367b.m1389f(str)) {
            return false;
        }
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            String str2 = (String) it.next();
            if (C0367b.m1389f(str2)) {
                return false;
            }
            str = str.toLowerCase();
            if (str2.trim().toLowerCase().contains(str)) {
                return true;
            }
        }
        return false;
    }

    private boolean m1388b(List<String> list, String str) {
        if (C0367b.m1389f(str)) {
            return false;
        }
        for (String str2 : list) {
            if (C0367b.m1389f(str2)) {
                return false;
            }
            str = str.toLowerCase();
            if (str2.trim().toLowerCase().contains(str)) {
                return true;
            }
        }
        return false;
    }

    private static boolean m1389f(String str) {
        if (str == null) {
            return true;
        }
        str = str.replaceAll("\\s+", "");
        return str.trim().isEmpty() || str.equalsIgnoreCase("null");
    }
}
