package com.interzonedev.httpcore;

import java.util.List;
import java.util.Map;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableMap;

/**
 * Immutable value object representing an HTTP request.
 * 
 * @author mark@interzonedev.com
 */
public class Request {

    private final String url;
    private final Method method;
    private final Map<String, List<String>> headers;
    private final Map<String, List<Object>> parameters;
    private final String body;

    /**
     * Creates a new {@link Request} from the values set on the specified {@link Builder}.
     *
     * @param builder The {@link Builder} that holds the values for the {@link Request} to create.
     */
    private Request(Builder builder) {
        this.url = builder.url;
        this.method = builder.method;
        this.headers = builder.headers;
        this.parameters = builder.parameters;
        this.body = builder.body;
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
     * Gets a {@link Builder} with initial values set from the specified template {@link Request}
     *
     * @param template A {@link Request} from which to get the initial values for the new {@link Builder}.
     *
     * @return Returns a {@link Builder} with initial values set from the specified template {@link Request}
     */
    public static Builder newBuilder(Request template) {
        return new Builder(template);
    }

    public String getUrl() {
        return url;
    }

    public Method getMethod() {
        return method;
    }

    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    public Map<String, List<Object>> getParameters() {
        return parameters;
    }

    public String getBody() {
        return body;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(url, method, headers, parameters, body);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Request)) {
            return false;
        }

        Request that = (Request) obj;

        return Objects.equal(url, that.url) && Objects.equal(method, that.method)
                && Objects.equal(headers, that.headers) && Objects.equal(parameters, that.parameters)
                && Objects.equal(body, that.body);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass().getName() + "@" + Integer.toHexString(hashCode())).add("url", url)
                .add("method", method).add("headers", headers).add("parameters", parameters).add("body", body)
                .toString();
    }

    /**
     * Mutable builder for creating instances of {@link Request}. Allows for the individual setting of properties.
     */
    public static class Builder {

        private String url;
        private Method method;
        private Map<String, List<String>> headers = ImmutableMap.of();
        private Map<String, List<Object>> parameters = ImmutableMap.of();
        private String body;

        /**
         * Default constructor. Allows for building a {@link Request} starting with default values.
         */
        private Builder() {
        }

        /**
         * Allows for building a {@link Request} starting with initial values set from the specified template
         * {@link Request}.
         *
         * @param template A {@link Request} from which to get the initial values for building a new {@link Request}.
         */
        private Builder(Request template) {
            this.url = template.url;
            this.method = template.method;
            this.headers = template.headers;
            this.parameters = template.parameters;
            this.body = template.body;
        }

        /**
         * Creates a new {@link Request} from the values set on this {@link Builder}.
         *
         * @return Returns a new {@link Request} from the values set on this {@link Builder}.
         */
        public Request build() {
            return new Request(this);
        }

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder setMethod(Method method) {
            this.method = method;
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

        public Builder setParameters(Map<String, List<Object>> parameters) {
            if (null != parameters) {
                this.parameters = ImmutableMap.copyOf(parameters);
            } else {
                this.parameters = ImmutableMap.of();
            }
            return this;
        }

        public Builder setBody(String body) {
            this.body = body;
            return this;
        }
    }
}
