package com.global_hitss.api_rest.controllers;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.global_hitss.api_rest.models.dto.ProductoResponseDTO;
import com.global_hitss.api_rest.services.ProductoService;

@RestController
@RequestMapping("/producto")
public class ProductoController {

	@Autowired
	private ProductoService productoService;

	@PostMapping(value = "/insertAndList", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductoResponseDTO> insertAndList(@RequestParam Integer idProducto, 
															 @RequestParam String nombre, 
															 @RequestParam Date fec_registro) {
		try {
			// Ejecución de SP y obtención de response
			ProductoResponseDTO productoResponse = productoService.insertAndList(idProducto, nombre, fec_registro);
			
			// Valicación de código resultado
			if (productoResponse.getCodigoResultado() == 0) {
				return new ResponseEntity<>(productoResponse, HttpStatus.OK);
			}
			return new ResponseEntity<>(productoResponse, HttpStatus.INTERNAL_SERVER_ERROR);

		} catch (Exception err) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
