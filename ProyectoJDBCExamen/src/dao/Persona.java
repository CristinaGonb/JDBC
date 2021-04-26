package dao;

public class Persona {

	private String dni;
	private String apellidos;
	private String nombre;
	private Cuenta cuenta;
	public Persona(String dni, String apellidos, String nombre, Cuenta cuenta) {
		super();
		this.dni = dni;
		this.apellidos = apellidos;
		this.nombre = nombre;
		this.cuenta = cuenta;
	}
	public Persona(String dni, String apellidos, String nombre) {
		super();
		this.dni = dni;
		this.apellidos = apellidos;
		this.nombre = nombre;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Cuenta getCuenta() {
		return cuenta;
	}
	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}
	@Override
	public String toString() {
		return "Persona [dni=" + dni + ", apellidos=" + apellidos + ", nombre=" + nombre + ", cuenta=" + cuenta + "]";
	}
	
	
	
}
