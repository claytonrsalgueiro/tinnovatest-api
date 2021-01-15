/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tinnovatest.domain.vehicle;

import br.com.tinnovatest.domain.commons.exception.NotAllowException;
import br.com.tinnovatest.interfaces.dto.CountDTO;
import br.com.tinnovatest.interfaces.dto.VehicleDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import static com.google.common.base.Preconditions.checkNotNull;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author clayton.salgueiro
 */
@Service
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Transactional
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final ObjectMapper objectMapper;

    @Override
    public VehicleDTO create(final VehicleDTO vehicleDTO) {
        checkNotNull(vehicleDTO, "Dados do veículo não podem ser nulos.");
        final Vehicle vehicle = Vehicle.of(vehicleDTO.getVehicle(), vehicleDTO.getBrand(), vehicleDTO.getYear(), vehicleDTO.getDescription(), vehicleDTO.isSold());
        final Vehicle vehicleSaved = this.vehicleRepository.save(vehicle);
        return this.objectMapper.convertValue(vehicleSaved, VehicleDTO.class);
    }

    @Override
    public VehicleDTO update(final VehicleDTO vehicleDTO) {
        checkNotNull(vehicleDTO, "Dados do veículo não podem ser nulos.");
        checkNotNull(vehicleDTO.getId(), "ID do veículo não pode ser nulo na atualização.");
        final Vehicle vehicle = this.vehicleRepository.findById(vehicleDTO.getId()).orElseThrow(() -> new NotAllowException("Veículo não encontrado na base de dados."));
        vehicle.update(vehicleDTO.getVehicle(), vehicleDTO.getBrand(), vehicleDTO.getYear(), vehicleDTO.getDescription(), vehicleDTO.isSold());
        final Vehicle vehicleSaved = this.vehicleRepository.save(vehicle);
        return this.objectMapper.convertValue(vehicleSaved, VehicleDTO.class);
    }

    @Override
    public VehicleDTO updateSold(final VehicleDTO vehicleDTO) {
        checkNotNull(vehicleDTO, "Dados do veículo não podem ser nulos.");
        checkNotNull(vehicleDTO.getId(), "ID do veículo não pode ser nulo na atualização.");
        final Vehicle vehicle = this.vehicleRepository.findById(vehicleDTO.getId()).orElseThrow(() -> new NotAllowException("Veículo não encontrado na base de dados."));
        vehicle.update(vehicleDTO.isSold());
        final Vehicle vehicleSaved = this.vehicleRepository.save(vehicle);
        return this.objectMapper.convertValue(vehicleSaved, VehicleDTO.class);
    }

    @Override
    public Page<VehicleDTO> findAll(final Pageable pageable) {
        checkNotNull(pageable, "Os dados de paginação não podem ser nulos.");
        final Page<Vehicle> result = this.vehicleRepository.findAll(pageable);
        final List<VehicleDTO> resultList = result.stream().map(veichle -> this.objectMapper.convertValue(veichle, VehicleDTO.class)).collect(Collectors.toList());
        return new PageImpl<>(resultList, pageable, result.getTotalElements());
    }

    @Override
    public VehicleDTO findById(final Long idVehicle) {
        return this.vehicleRepository.findById(idVehicle)
                .map(vehicle -> this.objectMapper.convertValue(vehicle, VehicleDTO.class))
                .orElseThrow(() -> new NotAllowException("Veículo não encontrado na base de dados."));
    }

    @Override
    public Page<VehicleDTO> findBySeach(final String search, final Pageable pageable) {
        checkNotNull(pageable, "Os dados de paginação não podem ser nulos.");
        final String searchField = Optional.ofNullable(search).map(sear -> "%".concat(sear.toUpperCase()).concat("%")).orElse("%%");
        final Page<Vehicle> result = this.vehicleRepository.findBySearch(searchField, pageable);
        final List<VehicleDTO> resultList = result.stream().map(veichle -> this.objectMapper.convertValue(veichle, VehicleDTO.class)).collect(Collectors.toList());
        return new PageImpl<>(resultList, pageable, result.getTotalElements());
    }

    @Override
    public void delete(final Long idVehicle) {
        checkNotNull(idVehicle, "ID do veículo não pode ser nulo na atualização.");
        final Vehicle vehicle = this.vehicleRepository.findById(idVehicle).orElseThrow(() -> new NotAllowException("Veículo não encontrado na base de dados."));
        this.vehicleRepository.delete(vehicle);
    }

    @Override
    public CountDTO countNotSold() {
        return CountDTO.builder()
                .count(this.vehicleRepository.countNotSold())
                .build();
    }

    @Override
    public List<CountDTO> countByBrand() {
        return this.vehicleRepository.countByBrand();
    }

    @Override
    public List<CountDTO> countByTens() {
        return this.vehicleRepository.countByTens();
    }
}
