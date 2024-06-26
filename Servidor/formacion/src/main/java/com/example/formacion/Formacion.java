package com.example.formacion;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Formacion {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String nombre;
	
	private String precio;
		
	private String centro;
	
	public Formacion() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}


	public String getCentro() {
		return centro;
	}

	public void setCentro(String centro) {
		this.centro = centro;
	}

	public Formacion(Long id, String nombre, String precio, String centro) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.centro = centro;
	}

	@Override
	public String toString() {
		return "Formacion [id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", centro="
				+ centro + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(centro, id, nombre, precio);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Formacion other = (Formacion) obj;
		return Objects.equals(centro, other.centro) && Objects.equals(id, other.id)
				&& Objects.equals(nombre, other.nombre) && Objects.equals(precio, other.precio);
	}
	
	

}
