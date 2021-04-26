package dao;

public class Cuenta {
	private int idCuenta;
	private String numeroCuenta;
	private float saldo;
	
	
	
	public Cuenta(int idCuenta, String numeroCuenta, float saldo) {
		super();
		this.idCuenta = idCuenta;
		this.numeroCuenta = numeroCuenta;
		this.saldo = saldo;
	}

	public Cuenta(String numeroCuenta, float saldo) {
		super();
		this.numeroCuenta = numeroCuenta;
		this.saldo = saldo;
	}

	public int getIdCuenta() {
		return idCuenta;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	public float getSaldo() {
		return saldo;
	}
	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	@Override
	public String toString() {
		return "Cuenta [idCuenta=" + idCuenta + ", numeroCuenta=" + numeroCuenta + ", saldo=" + saldo + "]";
	}
	
	
}
