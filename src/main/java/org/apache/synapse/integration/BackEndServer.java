package org.apache.synapse.integration;

import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseStatus;
import org.wso2.carbon.protocol.emulator.dsl.Emulator;
import org.wso2.carbon.protocol.emulator.http.server.contexts.HttpServerOperationBuilderContext;

import static org.wso2.carbon.protocol.emulator.http.server.contexts.HttpServerConfigBuilderContext.configure;
import static org.wso2.carbon.protocol.emulator.http.server.contexts.HttpServerRequestBuilderContext.request;
import static org.wso2.carbon.protocol.emulator.http.server.contexts.HttpServerResponseBuilderContext.response;

public class BackEndServer {
    public static void main(String[] args) {
        startHttpEmulator();
    }

    private static HttpServerOperationBuilderContext startHttpEmulator() {
        return Emulator.getHttpEmulator().server().given(configure().host("10.100.8.3").port(6065).context("/users").
                withEnableWireLog())

                .when(
                        request().withMethod(HttpMethod.POST).withPath("/user1")
                )
                .then(
                        response().withBody("User1").withStatusCode(HttpResponseStatus.OK)
                )

                .operation().start();
    }
}
