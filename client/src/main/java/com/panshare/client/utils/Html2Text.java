package com.panshare.client.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Html2Text extends HTMLEditorKit.ParserCallback {
    private static final Logger log = LoggerFactory.getLogger(Html2Text.class);
    private static final Html2Text html2Text = new Html2Text();
    StringBuffer s;

    public void parse(String str) throws IOException {
        InputStream iin = new ByteArrayInputStream(str.getBytes());
        Reader in = new InputStreamReader(iin);
        this.s = new StringBuffer();
        ParserDelegator delegator = new ParserDelegator();
        delegator.parse(in, this, Boolean.TRUE.booleanValue());
        iin.close();
        in.close();
    }

    public void handleText(char[] text, int pos) {
        this.s.append(text);
    }

    public String getText() {
        return this.s.toString();
    }

    public static String getContent(String str) {
        try {
            html2Text.parse(str);
        } catch (IOException e) {
            log.error("html转文本失败");
        }
        return html2Text.getText();
    }
}
