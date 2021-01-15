/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tinnovatest.domain.vehicle;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author clayton.salgueiro
 */
@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class VehicleServiceTest {

    @Autowired
    private VehicleService vehicleService;
    
    @Test
    public void createTest() {

    }
}
