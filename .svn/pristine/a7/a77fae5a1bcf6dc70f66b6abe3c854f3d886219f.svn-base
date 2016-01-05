package com.haoxuan.worknote.util;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/**
 * Created by skateboard on 2016/1/4.
 */
public class XMLParser {

    public static <T> void  parser(Reader input,T data)
    {
        try {
            XmlPullParserFactory factory=XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser parser=factory.newPullParser();
            parser.setInput(input);
            int eventType=parser.getEventType();
            while(eventType!=XmlPullParser.END_DOCUMENT)
            {
                switch(eventType)
                {
                    case XmlPullParser.START_TAG:
                        break;
                    case XmlPullParser.TEXT:
                        break;
                    case XmlPullParser.END_TAG:
                        break;
                }
                eventType=parser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
