package com.interzonedev.httpcore;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.Cookie;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableMap;

/**
 * Immutable value object representing an HTTP response.
 */
public class Response {

    private final Request request;
    private final int status;
    private final String contentType;
    private final long contentLength;
    private final Map<String, List<String>> headers;
    private final Map<String, Cookie> cookies;
    private final String content;
    private final Locale locale;

    /**
     * Creates a new {@link Response} from the values set on the specified {@link Builder}.
     *
     * @param builder The {@link Builder} that holds the values for the {@link Response} to create.
     */
    private Response(Builder builder) {
        this.request = builder.request;
        this.status = builder.status;
        this.contentType = builder.contentType;
        this.contentLength = builder.contentLength;
        this.headers = builder.headers;
        this.cookies = builder.cookies;
        this.content = builder.content;
        this.locale = builder.locale;
    }

    /**
     * Gets a {@link Builder} with default initial values.
     *
     * @return Returns a {@link Builder} with default initial values.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets a {@link Builder} with initial values set from the specified template {@link Response}
     *
     * @param template A {@link Response} from which to get the initial values for the new {@link Builder}.
     *
     * @return Returns a {@link Builder} with initial values set from the specified template {@link Response}
     */
    public static Builder newBuilder(Response template) {
        return new Builder(template);
    }

    public Request getRequest() {
        return request;
    }

    public int getStatus() {
        return status;
    }

    public String getContentType() {
        return contentType;
    }

    public long getContentLength() {
        return contentLength;
    }

    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    public Map<String, Cookie> getCookies() {
        return cookies;
    }

    public String getContent() {
        return content;
    }

    public Locale getLocale() {
        return locale;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(request, status, contentType, contentLength, headers, cookies, content, locale);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Response)) {
            return false;
        }

        Response that = (Response) obj;

        return Objects.equal(request, that.request) && Objects.equal(status, that.status)
                && Objects.equal(contentType, that.contentType) && Objects.equal(contentLength, that.contentLength)
                && Objects.equal(headers, that.headers) && Objects.equal(cookies, that.cookies)
                && Objects.equal(content, that.content) && Objects.equal(locale, that.locale);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass().getName() + "@" + Integer.toHexString(hashCode()))
                .add("request", request).add("status", status).add("contentType", contentType)
                .add("contentLength", contentLength).add("headers", headers).add("cookies", cookies)
                .add("content", content).add("locale", locale).toString();
    }

    /**
     * Mutable builder for creating instances of {@link Response}. Allows for the individual setting of properties.
     */
    public static class Builder {

        private Request request;
        private int status;
        private String contentType;
        private long contentLength;
        private Map<String, List<String>> headers = ImmutableMap.of();
        private Map<String, Cookie> cookies = ImmutableMap.of();
        private String content;
        private Locale locale;

        /**
         * Default constructor. Allows for building a {@link Response} starting with default values.
         */
        private Builder() {
        }

        /**
         * Allows for building a {@link Response} starting with initial values set from the specified template
         * {@link Response}.
         *
         * @param template A {@link Response} from which to get the initial values for building a new {@link Response}.
         */
        private Builder(Response template) {
            this.request = template.request;
            this.status = template.status;
            this.contentType = template.contentType;
            this.contentLength = template.contentLength;
            this.headers = template.headers;
            this.cookies = template.cookies;
            this.content = template.content;
            this.locale = template.locale;
        }

        /**
         * Creates a new {@link Response} from the values set on this {@link Builder}.
         *
         * @return Returns a new {@link Response} from the values set on this {@link Builder}.
         */
        public Response build() {
            return new Response(this);
        }

        public Builder setRequest(Request request) {
            this.request = request;
            return this;
        }

        public Builder setStatus(int status) {
            this.status = status;
            return this;
        }

        public Builder setContentType(String contentType) {
            this.contentType = contentType;
            return this;
        }

        public Builder setContentLength(long contentLength) {
            this.contentLength = contentLength;
            return this;
        }

        public Builder setHeaders(Map<String, List<String>> headers) {
            if (null != headers) {
                this.headers = ImmutableMap.copyOf(headers);
            } else {
                this.headers = ImmutableMap.of();
            }
            return this;
        }

        public Builder setCookies(Map<String, Cookie> cookies) {
            if (null != cookies) {
                this.cookies = ImmutableMap.copyOf(cookies);
            } else {
                this.cookies = ImmutableMap.of();
            }
            return this;
        }

        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public Builder setLocale(Locale locale) {
            this.locale = locale;
            return this;
        }
    }
}
