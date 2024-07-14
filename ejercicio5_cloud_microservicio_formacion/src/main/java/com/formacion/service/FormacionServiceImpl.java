package com.formacion.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.formacion.model.Curso;
import com.formacion.model.Formacion;

@Service
public class FormacionServiceImpl implements FormacionService {

	@Autowired
	private RestTemplate restTemplate; // cliente HTTP para realizar peticiones REST

	// private String url = "http://localhost:8090/cursos"; // URL base para las
	// peticiones HTTP
	// acceso a través de Eureka a otro microservicio
	private static final String url = "http://05-servicio-cursos/cursos";

	@Override
	public List<Formacion> obtenerFormaciones() {
		try {
			Curso[] cursos = restTemplate.getForObject(url, Curso[].class);
			List<Formacion> formaciones = new ArrayList<>();
			for (Curso curso : cursos) {
				formaciones.add(mapCursoToFormacion(curso));
			}
			return formaciones;
		} catch (Exception e) {
			System.err.println("Error al obtener formaciones: " + e);
			e.printStackTrace();
			throw new RuntimeException("Error al obtener formaciones", e);
		}
	}

	@Override
	public List<Formacion> altaFormacion(Formacion formacion) {
		try {
			Curso[] cursos = restTemplate.getForObject(url, Curso[].class);
			boolean cursoExiste = Arrays.stream(cursos)
					.anyMatch(c -> c.getNombre().equalsIgnoreCase(formacion.getCurso()));

			if (!cursoExiste) {
				Curso nuevoCurso = mapFormacionToCurso(formacion);
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				HttpEntity<Curso> request = new HttpEntity<>(nuevoCurso, headers);
				restTemplate.postForObject(url, request, Curso.class);
			}

			return obtenerFormaciones();
		} catch (Exception e) {
			System.err.println("Error al dar de alta formación: " + e);
			e.printStackTrace();
			throw new RuntimeException("Error al dar de alta formación", e);
		}
	}

	/**
	 * Convierte un objeto Curso a un objeto Formacion
	 */
	private Formacion mapCursoToFormacion(Curso curso) {
		int asignaturas;

		// Determina el número de asignaturas basado en la duración del curso
		if (curso.getDuracion() >= 50) {
			asignaturas = 10;
		} else {
			asignaturas = 5;
		}

		// Crea y retorna un nuevo objeto Formacion
		return new Formacion(curso.getNombre(), asignaturas, curso.getPrecio());
	}

	/**
	 * Convierte un objeto Formacion a un objeto Curso
	 */
	private Curso mapFormacionToCurso(Formacion formacion) {
		// Calcula la duración basada en el número de asignaturas
		int duracion = formacion.getAsignaturas() * 10;

		// Genera el código del curso
		String codCurso = generarCodigoCurso(formacion.getCurso(), duracion);

		// Crea y retorna un nuevo objeto Curso
		return new Curso(codCurso, formacion.getCurso(), duracion, formacion.getPrecio());
	}

	/**
	 * Genera el código del curso basado en el nombre y la duración
	 */
	private String generarCodigoCurso(String nombreCurso, int duracion) {
		// Toma los primeros 3 caracteres del nombre del curso
		String iniciales = nombreCurso.substring(0, 3);

		// Añade la duración y retorna el código del curso
		return iniciales + duracion;
	}

}
