/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tinnovatest.domain.vehicle;

import br.com.tinnovatest.interfaces.dto.CountDTO;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author clayton.salgueiro
 */
@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    @Query("SELECT DISTINCT v from Vehicle v "
            + " where UPPER(v.vehicle) LIKE :search "
            + " or UPPER(v.brand) LIKE :search "
            + " or CONCAT(v.year) LIKE :search "
            + " or UPPER(v.description) LIKE :search ")
    Page<Vehicle> findBySearch(@Param("search") final String search, final Pageable pageable);

    @Query(" SELECT COUNT(v) from Vehicle v where v.sold = false ")
    Long countNotSold();

    @Query(" SELECT DISTINCT new br.com.tinnovatest.interfaces.dto.CountDTO(v.brand, (SELECT COUNT(DISTINCT vv.id) "
            + " from Vehicle vv where vv.brand = v.brand ) )"
            + " from Vehicle v ")
    List<CountDTO> countByBrand();
    
    @Query(" SELECT DISTINCT new br.com.tinnovatest.interfaces.dto.CountDTO(CONCAT(SUBSTRING(CONCAT(v.year), 0, 3), '0'), (SELECT COUNT(DISTINCT vv.id) "
            + " from Vehicle vv where SUBSTRING(CONCAT(vv.year), 0, 3) = SUBSTRING(CONCAT(v.year), 0, 3) ) ) "
            + " from Vehicle v ")
    List<CountDTO> countByTens();
    
}
