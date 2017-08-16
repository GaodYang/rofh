package com.boco.rofh.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * The persistent class for the ATTEMP_AN_ONU database table.
 * 
 */
@Entity
@Table(name="AN_ONU")
public class AnOnu extends AnOnuBase implements Serializable {
	private static final long serialVersionUID = 1L;

}