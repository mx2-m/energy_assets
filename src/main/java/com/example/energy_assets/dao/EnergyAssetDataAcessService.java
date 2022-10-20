package com.example.energy_assets.dao;

import com.example.energy_assets.model.EnergyAssetTimeseries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository("postgres")
public class EnergyAssetDataAcessService implements EnergyAssetDAO {


    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EnergyAssetDataAcessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertEnergyAssetTimeseries(UUID id, Timestamp t, int activepower, int voltage) {
        id = UUID.randomUUID();
        t = Timestamp.from(Instant.now());
        int min = 1;
        int max = 1000;
        activepower = (int) (Math.random() * (max - min + 1) + min);
        voltage = (int) (Math.random() * (max - min + 1) + min);

        String sql = "INSERT INTO energy (id,timestamp,activePower, voltage)  VALUES (?,?,?,?) ";
        return jdbcTemplate.update(
                sql,
                id,
                t,
                activepower,
                voltage
        );
    }

    @Override
    public List<EnergyAssetTimeseries> selectEnergyAssetTimeseries() {
        String sql = "SELECT id,timestamp,activePower, voltage FROM energy";

        List<EnergyAssetTimeseries> list = jdbcTemplate.query(sql, (resultSet, i) -> {
            return new EnergyAssetTimeseries(UUID.fromString(resultSet.getString("id")),
                    resultSet.getTimestamp("timestamp"),
                    resultSet.getInt("activepower"),
                    Integer.valueOf(resultSet.getString("voltage")));
        });

        return list;
    }

    @Override
    public int deleteEnergyAssetTimeseriesById(UUID id) {
        String sql = "DELETE FROM energy WHERE id=? ";

        return jdbcTemplate.update(sql, id);
    }

    @Override
    public int updateEnergyAssetTimeseriesById(UUID id, EnergyAssetTimeseries energyAssetTimeseries) {


        String sql = "UPDATE energy SET activepower = ?, voltage=?  WHERE id = ?";
        return jdbcTemplate.update(sql, energyAssetTimeseries.getActivePower(), energyAssetTimeseries.getVoltage(), id);
    }


    @Override
    public Optional<EnergyAssetTimeseries> selectEnergyAssetTimeseriesById(UUID id) {
        String sql = "SELECT id,timestamp,activepower, voltage FROM energy WHERE id=? ";

        EnergyAssetTimeseries energy = jdbcTemplate.queryForObject(sql,
                new Object[]{id},   //argumenti
                (resultSet, i) -> {

                    return new EnergyAssetTimeseries(UUID.fromString(resultSet.getString("id")),
                            resultSet.getTimestamp("timestamp"),
                            Integer.valueOf(resultSet.getString("activepower")),
                            Integer.valueOf(resultSet.getString("voltage")));
                });

        return Optional.ofNullable(energy);

    }

    @Override
    public List<EnergyAssetTimeseries> selectEnergyAssetTimeseriesByTimeperiod(String timeStart, String timeEnd) {

        System.out.println(timeStart);
        System.out.println(timeEnd);


        String sql = "SELECT id,timestamp,activepower, voltage FROM energy" +
                " WHERE timestamp BETWEEN '" + timeStart + "'AND '" + timeEnd + "' ";
        //String sql = "SELECT id,timestamp,activepower, voltage " +
        //     "FROM energy WHERE timestamp BETWEEN '2022-10-19 15:53:25.862' AND '2022-10-19 21:48:31.558'";


        List<EnergyAssetTimeseries> list = jdbcTemplate.query(sql,
                (resultSet, i) -> {
                    return new EnergyAssetTimeseries(UUID.fromString(resultSet.getString("id")),
                            resultSet.getTimestamp("timestamp"),
                            resultSet.getInt("activepower"),
                            Integer.valueOf(resultSet.getString("voltage")));
                });

        return list;

    }

    @Override
    public Optional<EnergyAssetTimeseries> selectLatestEnergyAsset() {
        String sql = "SELECT id,timestamp,activePower, voltage FROM energy  ORDER BY timestamp DESC LIMIT 1 ";

        EnergyAssetTimeseries energy = jdbcTemplate.queryForObject(sql,
                new Object[]{},   //argumenti
                (resultSet, i) -> {

                    return new EnergyAssetTimeseries(UUID.fromString(resultSet.getString("id")),
                            resultSet.getTimestamp("timestamp"),
                            resultSet.getInt("activepower"),
                            resultSet.getInt("voltage"));
                });

        return Optional.ofNullable(energy);

    }
}
