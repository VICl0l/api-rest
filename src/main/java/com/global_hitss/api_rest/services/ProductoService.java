package com.global_hitss.api_rest.services;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import oracle.jdbc.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import com.global_hitss.api_rest.models.Producto;
import com.global_hitss.api_rest.models.dto.ProductoResponseDTO;

@Service
public class ProductoService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public ProductoResponseDTO insertAndList(Integer idProducto, String nombre, Date fec_registro) {
		
		// Definición de JdbcCall
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName("sp_insertAndListProducts")
				.declareParameters(
						// Parametros IN
						new SqlParameter("p_id_producto", OracleTypes.NUMBER),
						new SqlParameter("p_nombre", OracleTypes.VARCHAR),
						new SqlParameter("p_fec_registro", OracleTypes.DATE),
						// Parametros OUT
						new SqlOutParameter("p_cursor", OracleTypes.CURSOR, (ResultSet rs, int rowNum) -> {
							Producto producto = new Producto();
							producto.setId_producto(rs.getInt("id_producto"));
							producto.setNombre(rs.getString("nombre"));
							producto.setFec_registro(rs.getDate("fec_registro"));
							return producto;
						}), 
						new SqlOutParameter("p_codigo_respuesta", OracleTypes.NUMBER),
						new SqlOutParameter("p_mensaje_respuesta", OracleTypes.VARCHAR));

		// Ejecución de SP
		Map<String, Object> result = jdbcCall.execute(idProducto, nombre, fec_registro);

		// Obtención de Parametros OUT
		List<Producto> productoList = (List<Producto>) result.get("p_cursor");
		BigDecimal _codigoRespuesta = (BigDecimal) result.get("p_codigo_respuesta");
		Integer codigoRespuesta = Integer.valueOf(_codigoRespuesta.intValue());
		String mensajeRespuesta = (String) result.get("p_mensaje_respuesta");

		// Construcción del response 
		return new ProductoResponseDTO(productoList, codigoRespuesta, mensajeRespuesta);
	}
}
