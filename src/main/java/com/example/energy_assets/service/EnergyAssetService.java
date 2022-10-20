package com.example.energy_assets.service;


import com.example.energy_assets.dao.EnergyAssetDAO;
import com.example.energy_assets.model.EnergyAssetTimeseries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EnergyAssetService {


    private final EnergyAssetDAO energyAssetDAO;

    @Autowired
    public EnergyAssetService(@Qualifier("postgres") EnergyAssetDAO energyAssetDAO) {
        this.energyAssetDAO = energyAssetDAO;
    }


    public int addEnergyAsset() {

        return energyAssetDAO.insertEnergyAssetTimeseries();
    }

    public List<EnergyAssetTimeseries> getAll() {
        return energyAssetDAO.selectEnergyAssetTimeseries();
    }

    public Optional<EnergyAssetTimeseries> getEnergyAssetTimeseriesById(UUID id) {
        return energyAssetDAO.selectEnergyAssetTimeseriesById(id);
    }

    public Optional<EnergyAssetTimeseries> selectLatestEnergyAsset() {
        return energyAssetDAO.selectLatestEnergyAsset();
    }

    public List<EnergyAssetTimeseries> getEnergyAssetTimeseriesByTimeperiod(String t1, String t2) {

        return energyAssetDAO.selectEnergyAssetTimeseriesByTimeperiod(t1, t2);
    }

    public int delete(UUID id) {
        return energyAssetDAO.deleteEnergyAssetTimeseriesById(id);
    }

    public int update(UUID id, EnergyAssetTimeseries energyAssetTimeseries) {
        return energyAssetDAO.updateEnergyAssetTimeseriesById(id, energyAssetTimeseries);
    }

}
