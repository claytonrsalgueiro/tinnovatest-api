/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tinnovatest.domain.vehicle;

import br.com.tinnovatest.domain.commons.exception.NotAllowException;
import static com.google.common.base.Preconditions.checkNotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *
 * @author clayton.salgueiro
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbvehicle")
@Entity
public class Vehicle implements Serializable {

    private static final long serialVersionUID = 6987025977740231213L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tbvehicle_id", nullable = false)
    private Long id;

    @Column(name = "tbvehicle_name", nullable = false)
    private String vehicle;

    @Column(name = "tbvehicle_brand", nullable = false)
    private String brand;

    @Column(name = "tbvehicle_year", nullable = false)
    private Integer year;

    @Column(name = "tbvehicle_description", nullable = false)
    private String description;

    @Column(name = "tbvehicle_sold", nullable = false)
    private boolean sold;

    @Column(name = "tbvehicle_created", nullable = false)
    private LocalDateTime created;

    @Column(name = "tbvehicle_updated")
    private LocalDateTime updated;

    public static Vehicle of(final String vehicle, final String brand, final Integer year, final String description, final boolean sold) {
        checkNotNull(vehicle, "O nome do veículo não pode ser nulo");
        checkNotNull(brand, "A marca do veículo não pode ser nulo");
        checkNotNull(year, "O ano do veículo não pode ser nulo");
        checkNotNull(description, "A descrição do veículo não pode ser nulo");
        if (year < 1870) {
            throw new NotAllowException("Não é permitido criar veículos em que o ano seja menor que 1870.");
        }
        if (year > 2021) {
            throw new NotAllowException("Não é permitido criar veículos em que o ano seja maior que 2021.");
        }
        return new Vehicle(vehicle, brand, year, description, sold);
    }

    private Vehicle(final String vehicle, final String brand, final Integer year, final String description, final boolean sold) {
        this.vehicle = vehicle;
        this.brand = brand;
        this.year = year;
        this.description = description;
        this.sold = sold;
        this.created = LocalDateTime.now();
    }

    public void update(final String vehicle, final String brand, final Integer year, final String description, final boolean sold) {
        checkNotNull(vehicle, "O nome do veículo não pode ser nulo");
        checkNotNull(brand, "A marca do veículo não pode ser nulo");
        checkNotNull(year, "O ano do veículo não pode ser nulo");
        checkNotNull(description, "A descrição do veículo não pode ser nulo");
        if (year < 1870) {
            throw new NotAllowException("Não é permitido alterar o ano do veículo para menor do que 1870.");
        }
        if (year > 2021) {
            throw new NotAllowException("Não é permitido alterar o ano do veículo para maior do que 2021.");
        }

        this.vehicle = vehicle;
        this.brand = brand;
        this.year = year;
        this.description = description;
        this.sold = sold;
        this.updated = LocalDateTime.now();
    }

    public void update(final boolean sold) {
        this.sold = sold;
        this.updated = LocalDateTime.now();
    }

}
