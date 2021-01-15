/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tinnovatest.application.impl;

import br.com.tinnovatest.application.VehicleFacade;
import br.com.tinnovatest.domain.vehicle.VehicleService;
import br.com.tinnovatest.interfaces.dto.CountDTO;
import br.com.tinnovatest.interfaces.dto.VehicleDTO;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

/**
 *
 * @author clayton.salgueiro
 */
@Component
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class VehicleFacadeImpl implements VehicleFacade {

    private final VehicleService vehicleService;

    @Override
    public VehicleDTO create(final VehicleDTO vehicleDTO) {
        return this.vehicleService.create(vehicleDTO);
    }

    @Override
    public VehicleDTO update(final VehicleDTO vehicleDTO) {
        return this.vehicleService.update(vehicleDTO);
    }

    @Override
    public VehicleDTO updateSold(final VehicleDTO vehicleDTO) {
        return this.vehicleService.updateSold(vehicleDTO);
    }

    @Override
    public Page<VehicleDTO> findAll(final Pageable pageable) {
        return this.vehicleService.findAll(pageable);
    }

    @Override
    public VehicleDTO findById(final Long idVehicle) {
        return this.vehicleService.findById(idVehicle);
    }

    @Override
    public Page<VehicleDTO> findBySeach(final String search, final Pageable pageable) {
        return this.vehicleService.findBySeach(search, pageable);
    }

    @Override
    public void delete(final Long idVehicle) {
        this.vehicleService.delete(idVehicle);
    }

    @Override
    public CountDTO countNotSold() {
        return this.vehicleService.countNotSold();
    }

    @Override
    public List<CountDTO> countByBrand() {
        return this.vehicleService.countByBrand();
    }

    @Override
    public List<CountDTO> countByTens() {
        return this.vehicleService.countByTens();
    }

}
