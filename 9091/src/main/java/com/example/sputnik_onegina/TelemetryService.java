package com.example.sputnik_onegina;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import telemetry.Telemetry.*;
import telemetry.TelemetryServiceGrpc;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@GrpcService
public class TelemetryService extends TelemetryServiceGrpc.TelemetryServiceImplBase {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final Random random = new Random();
    private final SatelliteIdRepository satelliteIdRepository;

    @Override
    public void streamTelemetry(TelemetryRequest request,
        StreamObserver<TelemetryUpdate> responseObserver) {
        scheduler.scheduleAtFixedRate(() -> {
            try {
                List<Long> idList = new ArrayList<>(satelliteIdRepository.getActiveIds());
                Long satId = idList.get(random.nextInt(idList.size()));
                TelemetryUpdate update = TelemetryUpdate.newBuilder()
                .setDeviceId(satId)
                .setInnerTemperature(20 + random.nextDouble() * 10)
                .setOuterTemperature(-50 + random.nextDouble() * 30)
                .setTimestamp(Instant.now().getEpochSecond())
                .build();
                responseObserver.onNext(update);
            }
            catch (Exception e) {
                responseObserver.onError(e);
            }
        }, 0, 2, TimeUnit.SECONDS);
    }
}
