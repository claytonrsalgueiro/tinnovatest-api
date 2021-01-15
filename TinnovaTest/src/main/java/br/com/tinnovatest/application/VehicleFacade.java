/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tinnovatest.application;

import br.com.tinnovatest.interfaces.dto.CountDTO;
import br.com.tinnovatest.interfaces.dto.VehicleDTO;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author clayton.salgueiro
 */
public interface VehicleFacade {

    public VehicleDTO create(final VehicleDTO vehicleDTO);

    public VehicleDTO update(final VehicleDTO vehicleDTO);

    public VehicleDTO updateSold(final VehicleDTO vehicleDTO);

    public Page<VehicleDTO> findAll(final Pageable pageable);

    public VehicleDTO findById(final Long idVehicle);

    public Page<VehicleDTO> findBySeach(final String search, final Pageable pageable);

    public void delete(final Long idVehicle);

    public CountDTO countNotSold();

    public List<CountDTO> countByBrand();

    public List<CountDTO> countByTens();
}
