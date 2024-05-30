package com.global_hitss.api_rest.models.dto;

import java.util.List;

import com.global_hitss.api_rest.models.Producto;

public class ProductoResponseDTO {

	private List<Producto> productoList;
	private Integer codigoResultado;
	private String mensajeResultado;
	
	public ProductoResponseDTO() {}
	
	public ProductoResponseDTO(List<Producto> productoList, Integer codigoResultado, String mensajeResultado) {
		this.productoList = productoList;
		this.codigoResultado = codigoResultado;
		this.mensajeResultado = mensajeResultado;
	}

	public List<Producto> getProductoList() {
		return productoList;
	}

	public void setProductoList(List<Producto> productoList) {
		this.productoList = productoList;
	}

	public Integer getCodigoResultado() {
		return codigoResultado;
	}

	public void setCodigoResultado(Integer codigoResultado) {
		this.codigoResultado = codigoResultado;
	}

	public String getMensajeResultado() {
		return mensajeResultado;
	}

	public void setMensajeResultado(String mensajeResultado) {
		this.mensajeResultado = mensajeResultado;
	}
}
