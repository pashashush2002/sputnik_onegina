package com.example.sputnik_onegina;

import org.springframework.stereotype.Service;

import io.grpc.stub.StreamObserver;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import telemetry.Telemetry.*;
import telemetry.Telemetry.TelemetryRequest;
import telemetry.Telemetry.TelemetryUpdate;
import telemetry.TelemetryServiceGrpc;


@Slf4j
@Service
@RequiredArgsConstructor
public class TelemetryClientService {
    @GrpcClient("telemetry-service")
    private TelemetryServiceGrpc.TelemetryServiceStub asyncStub;

    private final SatelliteRepository satelliteRepository;

    @PostConstruct
    public void startStreaming() {
        log.info("Подключение к 9092...");
        TelemetryRequest request = TelemetryRequest.getDefaultInstance();

        StreamObserver<TelemetryUpdate> responseObserver = new StreamObserver<>() {
            @Override
            public void onNext(TelemetryUpdate update) {
                log.info("Получена телеметрия: спутник " + update.getDeviceId());
                satelliteRepository.findById(update.getDeviceId()).ifPresent(sat -> {
                    sat.setInnerTemperature(update.getInnerTemperature());
                    sat.setOuterTemperature(update.getOuterTemperature());
                    satelliteRepository.save(sat);
                });
            }
            @Override
            public void onError(Throwable t) {
                log.error("Ошибка стрима телемметрии: " + t);
            }
            @Override
            public void onCompleted() {
                log.info("Стрим завершён!");
            }
        };

    }
}
