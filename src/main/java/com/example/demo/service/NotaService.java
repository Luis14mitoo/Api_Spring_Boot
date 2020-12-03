package com.example.demo.service;



import java.util.List;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.converter.Convertidor;
import com.example.demo.entity.Nota;
import com.example.demo.modelo.MNota;
import com.example.demo.repositorio.NotaRepositorio;

@Service("servicio")
public class NotaService {
	
	@Autowired
	@Qualifier("repositorio")
	private NotaRepositorio repositorio;
	
	@Autowired
	@Qualifier("convertidor")
	private Convertidor convertidor;
	
	private static final Log logger =  LogFactory.getLog(NotaService.class);
	
	
	public boolean crear(Nota nota) {
		logger.info("CREANDO NOTA");
		try {
			
			repositorio.save(nota);
			logger.info("NOTA CREADA");
			return true;
		}catch(Exception e) {
			logger.error("Hubo un error");
			return false;
		}
	}
	
	public boolean actualizar(Nota nota) {
		try {
			repositorio.save(nota);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	public boolean borrar(String nombre, long id) {
		try {
			Nota nota = repositorio.findByNombreAndId(nombre, id);
			repositorio.delete(nota);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
	public List<MNota> obtener(){
		List<MNota> notas = null;		
		
		return convertidor.convertirLista(repositorio.findAll());
	}
	
	
	public MNota obtenerPorNombreTitulo(String nombre, String titulo) {
		return new MNota(repositorio.findByNombreAndTitulo(nombre, titulo));
	}
	
	public List<MNota> obtenerTitulo(String titulo){
		
		return convertidor.convertirLista(repositorio.findByTitulo(titulo));
	}
	
	
	public List<MNota> obtenerPorPaginacion(Pageable pageable){		
		
		return convertidor.convertirLista(repositorio.findAll(pageable).getContent());
	}	
	
}
