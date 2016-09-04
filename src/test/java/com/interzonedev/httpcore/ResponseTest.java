package com.interzonedev.httpcore;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.Cookie;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResponseTest {

    private static final Logger log = (Logger) LoggerFactory.getLogger(ResponseTest.class);

    private final Request testRequest = Request.newBuilder().build();
    private final int testStatus = 200;
    private final String testContentType = "application/json";
    private final long testContentLength = 11;
    private final Map<String, List<String>> testHeaders = new HashMap<>();
    private final Map<String, Cookie> testCookies = new HashMap<>();
    private final String testContent = "hello world";
    private final Locale testLocale = Locale.US;

    {
        testHeaders.put("hk1", Arrays.asList(new String[] { "hv11", "hv12" }));
        testHeaders.put("hk2", Arrays.asList(new String[] { "hv21" }));

        testCookies.put("c1", new Cookie("cn1", "cv1"));
        testCookies.put("c2", new Cookie("cn2", "cv2"));
    }

    @Test
    public void testDefaultValues() {
        log.debug("testDefaultValues: Start");

        Response testResponse = Response.newBuilder().build();

        Assert.assertNull(testResponse.getRequest());
        Assert.assertEquals(0, testResponse.getStatus());
        Assert.assertNull(testResponse.getContentType());
        Assert.assertEquals(0, testResponse.getContentLength());
        Assert.assertTrue(testResponse.getHeaders().isEmpty());
        Assert.assertTrue(testResponse.getCookies().isEmpty());
        Assert.assertNull(testResponse.getContent());
        Assert.assertNull(testResponse.getLocale());

        log.debug("testDefaultValues: End");
    }

    @Test
    public void testSetValues() {
        log.debug("testSetValues: Start");

        Response testResponse = Response.newBuilder().setRequest(testRequest).setStatus(testStatus)
                .setContentType(testContentType).setContentLength(testContentLength).setHeaders(testHeaders)
                .setCookies(testCookies).setContent(testContent).setLocale(testLocale).build();

        Assert.assertEquals(testRequest, testResponse.getRequest());
        Assert.assertEquals(testStatus, testResponse.getStatus());
        Assert.assertEquals(testContentType, testResponse.getContentType());
        Assert.assertEquals(testContentLength, testResponse.getContentLength());
        Assert.assertEquals(testHeaders, testResponse.getHeaders());
        Assert.assertEquals(testCookies, testResponse.getCookies());
        Assert.assertEquals(testContent, testResponse.getContent());
        Assert.assertEquals(testLocale, testResponse.getLocale());

        log.debug("testSetValues: End");
    }

    @Test
    public void testClonedValues() {
        log.debug("testClonedValues: Start");

        Response testResponse = Response.newBuilder().setRequest(testRequest).setStatus(testStatus)
                .setContentType(testContentType).setContentLength(testContentLength).setHeaders(testHeaders)
                .setCookies(testCookies).setContent(testContent).setLocale(testLocale).build();
        Response clonedResponse = Response.newBuilder(testResponse).build();

        Assert.assertEquals(testRequest, clonedResponse.getRequest());
        Assert.assertEquals(testStatus, clonedResponse.getStatus());
        Assert.assertEquals(testContentType, clonedResponse.getContentType());
        Assert.assertEquals(testContentLength, clonedResponse.getContentLength());
        Assert.assertEquals(testHeaders, clonedResponse.getHeaders());
        Assert.assertEquals(testCookies, clonedResponse.getCookies());
        Assert.assertEquals(testContent, clonedResponse.getContent());
        Assert.assertEquals(testLocale, clonedResponse.getLocale());

        log.debug("testClonedValues: End");
    }

    @Test
    public void testSetNullHeaders() {
        log.debug("testSetNullHeaders: Start");

        Response.Builder testResponseBuilder = Response.newBuilder().setHeaders(testHeaders);
        testResponseBuilder.setHeaders(null);
        Response testResponse = testResponseBuilder.build();

        Assert.assertTrue(testResponse.getHeaders().isEmpty());

        log.debug("testSetNullHeaders: End");
    }

    @Test
    public void testSetNullCookies() {
        log.debug("testSetNullCookies: Start");

        Response.Builder testResponseBuilder = Response.newBuilder().setCookies(testCookies);
        testResponseBuilder.setCookies(null);
        Response testResponse = testResponseBuilder.build();

        Assert.assertTrue(testResponse.getCookies().isEmpty());

        log.debug("testSetNullCookies: End");
    }

}
