package com.interzonedev.httpcore;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RequestTest {

    private final String testUrl = "http://example.com?qk1=qv1";
    private final Method testMethod = Method.POST;
    private final Map<String, List<String>> testHeaders = new HashMap<>();
    private final Map<String, List<String>> testParameters = new HashMap<>();
    private final String testBody = "test body";

    {
        testHeaders.put("hk1", Arrays.asList(new String[]{"hv11", "hv12"}));
        testHeaders.put("hk2", Arrays.asList(new String[]{"hv21"}));

        testParameters.put("pk1", Arrays.asList(new String[]{"pv11", "pv12"}));
        testParameters.put("pk2", Arrays.asList(new String[]{"pv21"}));
    }

    @Test
    public void testDefaultValues() {
        Request testRequest = Request.newBuilder().build();

        assertNull(testRequest.getUrl());
        assertNull(testRequest.getMethod());
        assertTrue(testRequest.getHeaders().isEmpty());
        assertTrue(testRequest.getParameters().isEmpty());
        assertNull(testRequest.getBody());
    }

    @Test
    public void testSetValues() {
        Request testRequest = Request.newBuilder().setUrl(testUrl).setMethod(testMethod).setHeaders(testHeaders)
                .setParameters(testParameters).setBody(testBody).build();

        assertEquals(testUrl, testRequest.getUrl());
        assertEquals(testMethod, testRequest.getMethod());
        assertEquals(testHeaders, testRequest.getHeaders());
        assertEquals(testParameters, testRequest.getParameters());
        assertEquals(testBody, testRequest.getBody());
    }

    @Test
    public void testClonedValues() {
        Request testRequest = Request.newBuilder().setUrl(testUrl).setMethod(testMethod).setHeaders(testHeaders)
                .setParameters(testParameters).setBody(testBody).build();
        Request clonedRequest = Request.newBuilder(testRequest).build();

        assertEquals(testUrl, clonedRequest.getUrl());
        assertEquals(testMethod, clonedRequest.getMethod());
        assertEquals(testHeaders, clonedRequest.getHeaders());
        assertEquals(testParameters, clonedRequest.getParameters());
        assertEquals(testBody, clonedRequest.getBody());
    }

    @Test
    public void testSetNullHeaders() {
        Request.Builder testRequestBuilder = Request.newBuilder().setHeaders(testHeaders);
        testRequestBuilder.setHeaders(null);
        Request testRequest = testRequestBuilder.build();

        assertTrue(testRequest.getHeaders().isEmpty());
    }

    @Test
    public void testSetNullParameters() {
        Request.Builder testRequestBuilder = Request.newBuilder().setParameters(testParameters);
        testRequestBuilder.setParameters(null);
        Request testRequest = testRequestBuilder.build();

        assertTrue(testRequest.getParameters().isEmpty());
    }

}
