package com.example.client;

import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.NumberResponse;

public class ClientStreamObserver implements StreamObserver<NumberResponse> {

    private static final Logger logger = LoggerFactory.getLogger(ClientStreamObserver.class);

    private volatile int lastServerValue = 0;
    private volatile boolean hasNewValue = false;

    public int getLastServerValue() {
        return lastServerValue;
    }

    public boolean hasNewValue() {
        return hasNewValue;
    }

    public void resetNewValueFlag() {
        hasNewValue = false;
    }

    @Override
    public void onNext(NumberResponse value) {
        lastServerValue = value.getValue();
        hasNewValue = true;
        logger.info("new value:{}", lastServerValue);
    }

    @Override
    public void onError(Throwable t) {
        logger.error("Error in stream", t);
    }

    @Override
    public void onCompleted() {
        logger.info("request completed");
    }
}