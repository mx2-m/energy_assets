package com.example.energy_assets.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

public class EnergyAssetTimeseries {

    private final UUID id;
    private final Timestamp timestamp;
    private final int activePower;
    private final int voltage;

    public EnergyAssetTimeseries(@JsonProperty("id") UUID id,
                                 @JsonProperty("timestamp") Timestamp timestamp,
                                 @JsonProperty("activePower") int activePower,
                                 @JsonProperty("voltage") int voltage) {
        this.id = id;
        this.timestamp = Timestamp.from(Instant.now());
        this.activePower = activePower;
        this.voltage = voltage;
    }

    public UUID getId() {
        return id;
    }

    public Timestamp getTimestamp() {

        return timestamp;
    }

    public int getActivePower() {
        return activePower;
    }

    public int getVoltage() {
        return voltage;
    }

    @Override
    public String toString() {
        return "EnergyAssetTimeseries{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", activePower=" + activePower +
                ", voltage=" + voltage +
                '}';
    }
}
