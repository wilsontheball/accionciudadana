package ar.com.thinksoft.ac.wilsond;

public class Data {
	private Long id;
	private String name;
	private int value;

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
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
