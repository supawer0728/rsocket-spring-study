package com.parfait.study.rsocket.client.config;

import java.net.InetSocketAddress;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;
import org.springframework.util.MimeTypeUtils;

import io.rsocket.frame.decoder.PayloadDecoder;
import io.rsocket.transport.netty.client.TcpClientTransport;

@Configuration
public class RSocketConfiguration {

    @Bean
    public RSocketRequester rSocketRequester(RSocketStrategies strategies) {
        InetSocketAddress address = new InetSocketAddress("localhost", 7000);

        return RSocketRequester.builder()
                               .rsocketConnector(connector -> connector.metadataMimeType(MimeTypeUtils.ALL_VALUE).payloadDecoder(PayloadDecoder.ZERO_COPY))
                               .rsocketStrategies(strategies)
                               .connect(TcpClientTransport.create(address))
                               .retry()
                               .block();

    }
}
