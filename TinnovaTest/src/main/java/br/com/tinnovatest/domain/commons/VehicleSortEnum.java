/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tinnovatest.domain.commons;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * @author clayton.salgueiro
 */
@Getter
@AllArgsConstructor
public enum VehicleSortEnum implements SortParameters<VehicleSortEnum> {

    DESCRIPTION("description"),
    BRAND("brand"),
    YEAR("year"),
    NAME("vehicle");

    private String parameter;

}
