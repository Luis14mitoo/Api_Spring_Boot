package com.example.demo.converter;




import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import com.example.demo.entity.*;
import com.example.demo.modelo.*;


@Component("convertidor")
public class Convertidor {

	public List<MNota> convertirLista(List<Nota> notas){
		List<MNota> mnotas = new ArrayList<>();
		
		for(Nota nota : notas) {
			mnotas.add(new MNota(nota));
			
		}		
		return mnotas;
		
	}	
}