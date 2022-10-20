package com.example.energy_assets.service;

import com.example.energy_assets.dao.EnergyAssetDAO;
import com.example.energy_assets.model.EnergyAssetTimeseries;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
class EnergyAssetServiceTest {


    @InjectMocks
    EnergyAssetService service;

    @Mock
    EnergyAssetDAO dao;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllEmployeesTest() {
        List<EnergyAssetTimeseries> list = new ArrayList<EnergyAssetTimeseries>();
        int min = 1;
        int max = 1000;


        EnergyAssetTimeseries e1 = new EnergyAssetTimeseries(UUID.randomUUID(), Timestamp.from(Instant.now()), (int) (Math.random() * (max - min + 1) + min), (int) (Math.random() * (max - min + 1) + min));
        EnergyAssetTimeseries e2 = new EnergyAssetTimeseries(UUID.randomUUID(), Timestamp.from(Instant.now()), (int) (Math.random() * (max - min + 1) + min), (int) (Math.random() * (max - min + 1) + min));
        EnergyAssetTimeseries e3 = new EnergyAssetTimeseries(UUID.randomUUID(), Timestamp.from(Instant.now()), (int) (Math.random() * (max - min + 1) + min), (int) (Math.random() * (max - min + 1) + min));

        list.add(e1);
        list.add(e2);
        list.add(e3);

        when(dao.selectEnergyAssetTimeseries()).thenReturn(list);

        //test
        List<EnergyAssetTimeseries> empList = service.getAll();

        assertEquals(3, empList.size());
        verify(dao, times(1)).selectEnergyAssetTimeseries();
    }

    @Test
    public void getByIdTest() {
        UUID id=UUID.randomUUID();
        int min = 1;
        int max = 1000;
        Timestamp time=Timestamp.valueOf("2022-10-20 20:59:47.078");
        int number=(int) (Math.random() * (max - min + 1) + min);

        when(dao.selectEnergyAssetTimeseriesById(id)).
                thenReturn(Optional.of(new EnergyAssetTimeseries(id, time, number, number)));


        Optional<EnergyAssetTimeseries> emp=service.getEnergyAssetTimeseriesById(id);

        assertEquals(number, emp.get().getActivePower());
        assertEquals(number, emp.get().getVoltage());

    }

    @Test
    public void createTest() {
        int min = 1;
        int max = 1000;

        service.addEnergyAsset();

        verify(dao, times(1)).insertEnergyAssetTimeseries();
    }


}