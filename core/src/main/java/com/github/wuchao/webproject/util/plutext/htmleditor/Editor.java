/*
    This file is part of docx-html-editor.

	docx-html-editor is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package com.github.wuchao.webproject.util.plutext.htmleditor;

import org.docx4j.Docx4J;
import org.docx4j.XmlUtils;
import org.docx4j.convert.out.ConversionFeatures;
import org.docx4j.convert.out.HTMLSettings;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Editor {

    private static final Logger jul = Logger.getLogger(Editor.class
            .getName());

    protected static final String APP_KEY = "DOCX4J_EDITOR:";

    // TODO rethink this. The issue is that context is null
    // until service method starts
    // (injection happens when you enter service method)
    // so just get it locally in the service methods
    private static String APP_CONTEXT = null;

    protected static String getContextPath() {
        // eg /docx4j-web-editor-1.0.0-SNAPSHOT
        return APP_CONTEXT;
    }

    protected static void setContextPath(String contextPath) {
        APP_CONTEXT = contextPath;
    }


    // Templates are thread safe
    private static Templates CKEditor3_XSLT;
    private static Templates BARE_XSLT;  // BARE is still intended for CKEditor3

    static {
        jul.info("Servlet loaded");

        Logger.getLogger("com.sun.jersey").setLevel(Level.WARNING);
        Logger.getLogger("org.glassfish.jersey").setLevel(Level.WARNING);

        try {
            CKEditor3_XSLT = XmlUtils.getTransformerTemplate(
                    new StreamSource(
                            org.docx4j.utils.ResourceUtils.getResource("docx2xhtml_CKEditor2013.xslt")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            BARE_XSLT = XmlUtils.getTransformerTemplate(
                    new StreamSource(
                            org.docx4j.utils.ResourceUtils.getResource("docx2xhtml_Bare.xslt")));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * XSLT extension function which encodes the specified URL by including the session ID in it, or, if encoding is not needed, returns the URL unchanged.
     * The implementation of this method includes the logic to determine whether the session ID needs to be encoded in the URL.
     * For example, if the browser supports cookies, or session tracking is turned off, URL encoding is unnecessary.
     * <p>
     * For robust session tracking, all URLs emitted by a servlet should be run through this method.
     * Otherwise, URL rewriting cannot be used with browsers which do not support cookies.
     *
     * @param url
     * @return
     */
    public static String encodeURL(HttpServletResponse response, String url) {

        return response.encodeURL(url);
    }

    protected InputStream getCMISResource(HttpServletRequest req, String browseUrl) throws ServletException, IOException {

        // get content
        URL url = new URL(browseUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoInput(true);
        conn.setDoOutput(false);
        conn.setRequestMethod("GET");
        String authHeader = req.getHeader("Authorization");
        if (authHeader != null) {
            conn.setRequestProperty("Authorization", authHeader);
        }
        conn.connect();

//            // ask for login
//            if (conn.getResponseCode() == HttpURLConnection.HTTP_UNAUTHORIZED) {
//                resp.setHeader("WWW-Authenticate", conn.getHeaderField("WWW-Authenticate"));
//                resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authorization Required");
//                return;
//            }
//
//            // debug messages
//            if (log.isDebugEnabled()) {
//                log.debug("'" + browseUrl + "' -> '" + conn.getContentType() + "'");
//            }

        return conn.getInputStream();
    }


    public static String getStackTrace(Throwable aThrowable) {
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);
        aThrowable.printStackTrace(printWriter);
        return result.toString();
    }


    /**
     * For testing purposes only
     */
    public static void main(String[] args) throws Exception {

        // .. the HtmlSettings object
        final HTMLSettings htmlSettings = new HTMLSettings();
        htmlSettings.getFeatures().remove(ConversionFeatures.PP_COMMON_DEEP_COPY);


        try {
            Source xsltSource = new StreamSource(org.docx4j.utils.ResourceUtils.getResource(
                    "org/plutext/htmleditor/docx2xhtml_CKEditor2013.xslt"));
            htmlSettings.setCustomXsltTemplates(XmlUtils.getTransformerTemplate(xsltSource));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }


//	    	htmlSettings.setImageHandler(new SessionImageHandler(session));
//
//	    	htmlSettings.setStyleElementHandler(new SessionStyleHandler(session));

        htmlSettings.getFeatures().remove(ConversionFeatures.PP_COMMON_DEEP_COPY);
        htmlSettings.getFeatures().remove(ConversionFeatures.PP_COMMON_MOVE_PAGEBREAK);


        String inputfilepath = System.getProperty("user.dir")
//					+ "/sample-docx.docx";
//					+ "/sample-docs/word/2003/word2003xml.xml";
                + "/docx-samples/Demo-Hayden-Management-v2.docx";


        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage
                .load(new java.io.File(inputfilepath));

        htmlSettings.setWmlPackage(wordMLPackage);

        ByteArrayOutputStream output = new ByteArrayOutputStream();

        Docx4J.toHTML(htmlSettings, output, Docx4J.FLAG_NONE);

        System.out.println(output.toString("UTF-8"));

    }

}