/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tinnovatest.config.security;

import java.io.Serializable;
import lombok.experimental.UtilityClass;

/**
 *
 * @author clayton.salgueiro
 */
@UtilityClass
public class RoleUtils implements Serializable {

    private static final long serialVersionUID = -6565882010985565758L;
    
	public static final String ADMIN = "'ADMIN'";

	public static final String ALL_ROLES = ADMIN ;

}