package com.example.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.NumbersRequest;
import com.example.NumbersServiceGrpc;
import java.util.concurrent.TimeUnit;

public class NumbersClient {
    private static final Logger logger = LoggerFactory.getLogger(NumbersClient.class);

    public static void main(String[] args) throws InterruptedException {
        logger.info("numbers Client is starting...");

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext()
                .build();

        NumbersServiceGrpc.NumbersServiceStub stub = NumbersServiceGrpc.newStub(channel);

        ClientStreamObserver observer = new ClientStreamObserver();

        stub.getNumbersStream(
                NumbersRequest.newBuilder()
                        .setFirstValue(0)
                        .setLastValue(30)
                        .build(),
                observer
        );

        int currentValue = 0;
        int lastServerValue = 0;

        for (int i = 0; i <= 50; i++) {
            if (observer.hasNewValue()) {
                lastServerValue = observer.getLastServerValue();
                observer.resetNewValueFlag();
            }

            currentValue = currentValue + lastServerValue + 1;
            logger.info("currentValue:{}", currentValue);

            lastServerValue = 0;

            Thread.sleep(1000);
        }

        logger.info("Client finished");
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }
}