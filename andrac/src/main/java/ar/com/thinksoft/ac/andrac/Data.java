package ar.com.thinksoft.ac.andrac;

public class Data {
	private Long id;
	private String xname;
	private int xvalue;

	public Data(String nombre, int valor) {
		this.setName(nombre);
		this.setValue(valor);
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setName(String name) {
		this.xname = name;
	}

	public String getName() {
		return xname;
	}

	public void setValue(int value) {
		this.xvalue = value;
	}

	public int getValue() {
		return xvalue;
	}

}
