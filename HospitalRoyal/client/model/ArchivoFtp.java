package model;

import java.util.Date;

/**
 * 
 * Ejemplo r�pido de objeto que se necesita para la vista del cliente
 *
 */
public class ArchivoFtp implements Comparable<ArchivoFtp> {

	private String nombre, direccion, ultFechaModificacion;
	private int isCarpeta;

	public ArchivoFtp(String nombre, String ultFechaModificacion, int isCarpeta, String direccion) {
		this.nombre = nombre;
		this.ultFechaModificacion = ultFechaModificacion;
		this.isCarpeta = isCarpeta;
		this.direccion = direccion;
	}

	/**
	 * Metodo para ordenar un array de archivos por si es carpeta o no, teniendo
	 * preferencia carpeta
	 */
	@Override
	public int compareTo(ArchivoFtp o) {
		if (isCarpeta < o.isCarpeta) {
			return 1;
		}
		if (isCarpeta > o.isCarpeta) {
			return -1;
		}
		return 0;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUltFechaModificacion() {
		return ultFechaModificacion;
	}

	public void setUltFechaModificacion(String ultFechaModificacion) {
		this.ultFechaModificacion = ultFechaModificacion;
	}

	public int getIsCarpeta() {
		return isCarpeta;
	}

	public void setIsCarpeta(int isCarpeta) {
		this.isCarpeta = isCarpeta;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

}
