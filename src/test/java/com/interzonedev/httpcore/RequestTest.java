package com.interzonedev.httpcore;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestTest {

    private static final Logger log = (Logger) LoggerFactory.getLogger(RequestTest.class);

    private final String testUrl = "http://example.com?qk1=qv1";
    private final Method testMethod = Method.POST;
    private final Map<String, List<String>> testHeaders = new HashMap<>();
    private final Map<String, List<Object>> testParameters = new HashMap<>();
    private final String testBody = "test body";

    {
        testHeaders.put("hk1", Arrays.asList(new String[] { "hv11", "hv12" }));
        testHeaders.put("hk2", Arrays.asList(new String[] { "hv21" }));

        testParameters.put("pk1", Arrays.asList(new String[] { "pv11", "pv12" }));
        testParameters.put("pk2", Arrays.asList(new String[] { "pv21" }));
    }

    @Test
    public void testDefaultValues() {
        log.debug("testDefaultValues: Start");

        Request testRequest = Request.newBuilder().build();

        Assert.assertNull(testRequest.getUrl());
        Assert.assertNull(testRequest.getMethod());
        Assert.assertTrue(testRequest.getHeaders().isEmpty());
        Assert.assertTrue(testRequest.getParameters().isEmpty());
        Assert.assertNull(testRequest.getBody());

        log.debug("testDefaultValues: End");
    }

    @Test
    public void testSetValues() {
        log.debug("testSetValues: Start");

        Request testRequest = Request.newBuilder().setUrl(testUrl).setMethod(testMethod).setHeaders(testHeaders)
                .setParameters(testParameters).setBody(testBody).build();

        Assert.assertEquals(testUrl, testRequest.getUrl());
        Assert.assertEquals(testMethod, testRequest.getMethod());
        Assert.assertEquals(testHeaders, testRequest.getHeaders());
        Assert.assertEquals(testParameters, testRequest.getParameters());
        Assert.assertEquals(testBody, testRequest.getBody());

        log.debug("testSetValues: End");
    }

    @Test
    public void testClonedValues() {
        log.debug("testClonedValues: Start");

        Request testRequest = Request.newBuilder().setUrl(testUrl).setMethod(testMethod).setHeaders(testHeaders)
                .setParameters(testParameters).setBody(testBody).build();
        Request clonedRequest = Request.newBuilder(testRequest).build();

        Assert.assertEquals(testUrl, clonedRequest.getUrl());
        Assert.assertEquals(testMethod, clonedRequest.getMethod());
        Assert.assertEquals(testHeaders, clonedRequest.getHeaders());
        Assert.assertEquals(testParameters, clonedRequest.getParameters());
        Assert.assertEquals(testBody, clonedRequest.getBody());

        log.debug("testClonedValues: End");
    }

    @Test
    public void testSetNullHeaders() {
        log.debug("testSetNullHeaders: Start");

        Request.Builder testRequestBuilder = Request.newBuilder().setHeaders(testHeaders);
        testRequestBuilder.setHeaders(null);
        Request testRequest = testRequestBuilder.build();

        Assert.assertTrue(testRequest.getHeaders().isEmpty());

        log.debug("testSetNullHeaders: End");
    }

    @Test
    public void testSetNullParameters() {
        log.debug("testSetNullParameters: Start");

        Request.Builder testRequestBuilder = Request.newBuilder().setParameters(testParameters);
        testRequestBuilder.setParameters(null);
        Request testRequest = testRequestBuilder.build();

        Assert.assertTrue(testRequest.getParameters().isEmpty());

        log.debug("testSetNullParameters: End");
    }

}
