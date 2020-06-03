package com.interzonedev.httpcore;

import org.junit.jupiter.api.Test;

import javax.servlet.http.Cookie;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ResponseTest {

    private final Request testRequest = Request.newBuilder().build();
    private final int testStatus = 200;
    private final String testContentType = "application/json";
    private final long testContentLength = 11;
    private final Map<String, List<String>> testHeaders = new HashMap<>();
    private final Map<String, Cookie> testCookies = new HashMap<>();
    private final String testContent = "hello world";
    private final Locale testLocale = Locale.US;

    {
        testHeaders.put("hk1", Arrays.asList(new String[]{"hv11", "hv12"}));
        testHeaders.put("hk2", Arrays.asList(new String[]{"hv21"}));

        testCookies.put("c1", new Cookie("cn1", "cv1"));
        testCookies.put("c2", new Cookie("cn2", "cv2"));
    }

    @Test
    public void testDefaultValues() {
        Response testResponse = Response.newBuilder().build();

        assertNull(testResponse.getRequest());
        assertEquals(0, testResponse.getStatus());
        assertNull(testResponse.getContentType());
        assertEquals(0, testResponse.getContentLength());
        assertTrue(testResponse.getHeaders().isEmpty());
        assertTrue(testResponse.getCookies().isEmpty());
        assertNull(testResponse.getContent());
        assertNull(testResponse.getLocale());
    }

    @Test
    public void testSetValues() {
        Response testResponse = Response.newBuilder().setRequest(testRequest).setStatus(testStatus)
                .setContentType(testContentType).setContentLength(testContentLength).setHeaders(testHeaders)
                .setCookies(testCookies).setContent(testContent).setLocale(testLocale).build();

        assertEquals(testRequest, testResponse.getRequest());
        assertEquals(testStatus, testResponse.getStatus());
        assertEquals(testContentType, testResponse.getContentType());
        assertEquals(testContentLength, testResponse.getContentLength());
        assertEquals(testHeaders, testResponse.getHeaders());
        assertEquals(testCookies, testResponse.getCookies());
        assertEquals(testContent, testResponse.getContent());
        assertEquals(testLocale, testResponse.getLocale());
    }

    @Test
    public void testClonedValues() {
        Response testResponse = Response.newBuilder().setRequest(testRequest).setStatus(testStatus)
                .setContentType(testContentType).setContentLength(testContentLength).setHeaders(testHeaders)
                .setCookies(testCookies).setContent(testContent).setLocale(testLocale).build();
        Response clonedResponse = Response.newBuilder(testResponse).build();

        assertEquals(testRequest, clonedResponse.getRequest());
        assertEquals(testStatus, clonedResponse.getStatus());
        assertEquals(testContentType, clonedResponse.getContentType());
        assertEquals(testContentLength, clonedResponse.getContentLength());
        assertEquals(testHeaders, clonedResponse.getHeaders());
        assertEquals(testCookies, clonedResponse.getCookies());
        assertEquals(testContent, clonedResponse.getContent());
        assertEquals(testLocale, clonedResponse.getLocale());
    }

    @Test
    public void testSetNullHeaders() {
        Response.Builder testResponseBuilder = Response.newBuilder().setHeaders(testHeaders);
        testResponseBuilder.setHeaders(null);
        Response testResponse = testResponseBuilder.build();

        assertTrue(testResponse.getHeaders().isEmpty());
    }

    @Test
    public void testSetNullCookies() {
        Response.Builder testResponseBuilder = Response.newBuilder().setCookies(testCookies);
        testResponseBuilder.setCookies(null);
        Response testResponse = testResponseBuilder.build();

        assertTrue(testResponse.getCookies().isEmpty());
    }

}
