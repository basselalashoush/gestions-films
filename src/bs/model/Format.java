package bs.model;

public class Format {
	
	private int id_format;
	private String type;
	public int getId_format() {
		return id_format;
	}
	public void setId_format(int id_format) {
		this.id_format = id_format;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Format(int id_format, String type) {
		this.id_format = id_format;
		this.type = type;
	}
	public Format(String type) {
		super();
		this.type = type;
	}
	@Override
	public String toString() {
		return "Format [id_format=" + id_format + ", type=" + type + "]";
	}
	
	

}
