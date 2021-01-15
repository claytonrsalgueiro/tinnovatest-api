/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tinnovatest.interfaces;

import br.com.tinnovatest.application.VehicleFacade;
import br.com.tinnovatest.config.ApiPageable;
import br.com.tinnovatest.config.security.RoleUtils;
import br.com.tinnovatest.domain.commons.SortValidationService;
import br.com.tinnovatest.domain.commons.VehicleSortEnum;
import br.com.tinnovatest.interfaces.dto.VehicleDTO;
import io.swagger.annotations.Api;
import java.io.IOException;
import java.net.URI;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author clayton.salgueiro
 */
@Api(tags = {"Veículos"})
@RestController
@RequestMapping("api/v1/veiculos")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
public class VehicleController {

    private final VehicleFacade vehicleFacade;

    /**
     * Creating a new Vehicle
     *
     * @param vehicleDTO
     * @return {@link VehicleDTO}
     */
    @PostMapping
    @PreAuthorize("hasAnyRole(" + RoleUtils.ADMIN + ")")
    public ResponseEntity<VehicleDTO> create(@RequestBody final VehicleDTO vehicleDTO) {

        final VehicleDTO vehicleSaved = this.vehicleFacade.create(vehicleDTO);

        URI uri = UriComponentsBuilder.fromUriString("api/v1/vehicle/{id}").buildAndExpand(vehicleSaved.getId())
                .toUri();
        return ResponseEntity.created(uri).body(vehicleSaved);
    }

    /**
     * Update a existing vehicle
     *
     * @param idVeiulo
     * @param vehicleDTO
     * @return {@link VehicleDTO}
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole(" + RoleUtils.ADMIN + ")")
    public ResponseEntity<VehicleDTO> update(@PathVariable(name = "id", required = true) final Long idVeiulo,
            @RequestBody final VehicleDTO vehicleDTO) {
        vehicleDTO.setId(idVeiulo);
        final VehicleDTO vehicleSaved = this.vehicleFacade.update(vehicleDTO);
        
        return ResponseEntity.ok(vehicleSaved);
    }

    /**
     * Change vehicle sold status
     *
     * @param idVeiulo
     * @param vehicleDTO
     * @return {@link VehicleDTO}
     * @throws IOException
     */
    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyRole(" + RoleUtils.ADMIN + ")")
    public ResponseEntity<VehicleDTO> updateSold(@PathVariable(name = "id", required = true) final Long idVeiulo,
            @RequestBody final VehicleDTO vehicleDTO) throws IOException {
        vehicleDTO.setId(idVeiulo);
        final VehicleDTO vehicleSaved = this.vehicleFacade.updateSold(vehicleDTO);

        return ResponseEntity.ok(vehicleSaved);
    }

    /**
     * Delete a existing vehicle
     *
     * @param idVeiulo
     * @return {@link Void}
     * @throws IOException
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole(" + RoleUtils.ADMIN + ")")
    public ResponseEntity<Void> delete(@PathVariable(name = "id", required = true) final Long idVeiulo) throws IOException {
        this.vehicleFacade.delete(idVeiulo);
        return ResponseEntity.ok().build();
    }

    /**
     * Find all existing vehicles
     *
     * @param pageable
     * @return Page<{@link VehicleDTO}>
     */
    @ApiPageable
    @GetMapping
    @PreAuthorize("hasAnyRole(" + RoleUtils.ADMIN + ")")
    public ResponseEntity<Page<VehicleDTO>> findAll(@PageableDefault(sort = "NAME", direction = Sort.Direction.ASC) final Pageable pageable) {

        final Pageable validatedPageable = SortValidationService.validateSortTypes(pageable,
                VehicleSortEnum.class);

        final Page<VehicleDTO> results = this.vehicleFacade.findAll(validatedPageable);

        if (Objects.isNull(results.getContent()) || results.getContent().isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(results);
    }

    /**
     * Find vehicles by search fields
     *
     * @param search
     * @param pageable
     * @return Page<{@link VehicleDTO}>
     */
    @ApiPageable
    @GetMapping("/find")
    @PreAuthorize("hasAnyRole(" + RoleUtils.ADMIN + ")")
    public ResponseEntity<Page<VehicleDTO>> findBySearch(
            @RequestParam(name = "search", required = false) final String search,
            @PageableDefault(sort = "NAME", direction = Sort.Direction.ASC) final Pageable pageable) {

        final Pageable validatedPageable = SortValidationService.validateSortTypes(pageable,
                VehicleSortEnum.class);

        final Page<VehicleDTO> results = this.vehicleFacade.findBySeach(search, validatedPageable);

        if (Objects.isNull(results.getContent()) || results.getContent().isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(results);
    }

    /**
     * Find a unique vehicle by ID
     *
     * @param idVehicle
     * @return Page<{@link VehicleDTO}>
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole(" + RoleUtils.ADMIN + ")")
    public ResponseEntity<VehicleDTO> findById(@PathVariable(name = "id", required = true) final Long idVehicle) {

        final VehicleDTO results = this.vehicleFacade.findById(idVehicle);

        return ResponseEntity.ok(results);
    }

}
