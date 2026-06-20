package com.example.server;

import io.grpc.stub.StreamObserver;
import com.example.NumbersRequest;
import com.example.NumbersServiceGrpc;
import com.example.NumberResponse;

public class NumbersServiceImpl extends NumbersServiceGrpc.NumbersServiceImplBase {

    @Override
    public void getNumbersStream(NumbersRequest request, StreamObserver<NumberResponse> responseObserver) {
        int start = request.getFirstValue();
        int end = request.getLastValue();

        try {
            for (int i = start + 1; i <= end; i++) {
                NumberResponse response = NumberResponse.newBuilder().setValue(i).build();
                responseObserver.onNext(response);
                System.out.println("Sent value: " + i);
                Thread.sleep(2000);
            }
            responseObserver.onCompleted();
        } catch (InterruptedException e) {
            responseObserver.onError(e);
        }
    }
}