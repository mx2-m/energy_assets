package com.example.energy_assets.dao;

import com.example.energy_assets.model.EnergyAssetTimeseries;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EnergyAssetDAO {

    int insertEnergyAssetTimeseries(UUID id, Timestamp t, int activepower, int voltage);

    default int insertEnergyAssetTimeseries() {
        UUID id = UUID.randomUUID();
        Timestamp timestamp = Timestamp.from(Instant.now());
        int activepower;
        int voltage;
        int min = 1;
        int max = 1000;
        activepower = (int) (Math.random() * (max - min + 1) + min);
        voltage = (int) (Math.random() * (max - min + 1) + min);

        return insertEnergyAssetTimeseries(id, timestamp, activepower, voltage);
    }

    List<EnergyAssetTimeseries> selectEnergyAssetTimeseries();

    int deleteEnergyAssetTimeseriesById(UUID id);

    int updateEnergyAssetTimeseriesById(UUID id, EnergyAssetTimeseries energyAssetTimeseries);

    Optional<EnergyAssetTimeseries> selectEnergyAssetTimeseriesById(UUID id);

    List<EnergyAssetTimeseries> selectEnergyAssetTimeseriesByTimeperiod(Timestamp timeStart, Timestamp timeEnd);

    Optional<EnergyAssetTimeseries> selectLatestEnergyAsset();

}
