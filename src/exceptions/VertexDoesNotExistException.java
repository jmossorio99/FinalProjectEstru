package exceptions;

public class VertexDoesNotExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3087933783294236048L;
	private String msg = "";

	public VertexDoesNotExistException(String msg) {
		this.msg = msg;
	}

	public VertexDoesNotExistException() {
		msg = "You have attempted to call a vertex that does not exist in the graph";
	}

	public String getMsg() {
		return msg;
	}

}
