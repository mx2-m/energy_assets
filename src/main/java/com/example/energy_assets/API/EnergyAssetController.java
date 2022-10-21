package com.example.energy_assets.API;


import com.example.energy_assets.dao.EnergyAssetDataAcessService;
import com.example.energy_assets.model.EnergyAssetTimeseries;
import com.example.energy_assets.service.EnergyAssetService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;


import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api")
@RestController
public class EnergyAssetController {


    private final EnergyAssetService energyAssetService;


    public EnergyAssetController(EnergyAssetService energyAssetService) {
        this.energyAssetService = energyAssetService;
    }


    @Scheduled(fixedDelay = 30000)  //every 30 seconds
    @PostMapping
    public void add() {

        energyAssetService.addEnergyAsset();

        System.out.println("added to db");
    }

    @GetMapping
    public List<EnergyAssetTimeseries> getAll() {
        return energyAssetService.getAll();
    }


    @GetMapping(path = "{id}")
    public Optional<EnergyAssetTimeseries> selectById(@PathVariable("id") UUID id) {

        return energyAssetService.getEnergyAssetTimeseriesById(id);
    }

    @GetMapping(path = "/latestTimestamp")
    public Optional<EnergyAssetTimeseries> getLatestTimestamp() {

        return energyAssetService.selectLatestEnergyAsset();
    }

    @GetMapping(path = "{timeStart}/{timeEnd}")
    public List<EnergyAssetTimeseries> selectByTimeperiod(@PathVariable("timeStart") String lt1,
                                                          @PathVariable("timeEnd") String lt2) {

        Timestamp t1 = ValidateParameter(lt1);
        Timestamp t2 = ValidateParameter(lt2);


        return energyAssetService.getEnergyAssetTimeseriesByTimeperiod(t1, t2);
    }

    private Timestamp ValidateParameter(@PathVariable("timeStart") String lt) {
        if (lt.contains("T")) {
            LocalDateTime localDateTime = LocalDateTime.parse(lt);
            return Timestamp.valueOf(localDateTime);
        } else {
            try {
                LocalDate localDate = LocalDate.parse(lt);
                System.out.println(localDate);
                LocalDateTime localDateTime = localDate.atStartOfDay();
                return Timestamp.valueOf(localDateTime);
            } catch (Exception e) {
                System.out.println("Wrong parameters");
            }
        }
        return null;
    }

    @DeleteMapping(path = "{id}")
    public void deleteById(@PathVariable("id") UUID id) {

        energyAssetService.delete(id);
    }

    @PutMapping(path = "{id}")
    public void updateById(@PathVariable("id") UUID id, @RequestBody EnergyAssetTimeseries energyAssetTimeseries) {

        energyAssetService.update(id, energyAssetTimeseries);
    }


}
