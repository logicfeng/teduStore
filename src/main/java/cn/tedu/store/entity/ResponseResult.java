package cn.tedu.store.entity;

public class ResponseResult<T> {

	public static final int STATE_ERR = 0;
	public static final int STATE_OK = 1;

	private Integer state;
	private String message;
	private T date;

	public ResponseResult() {
		super();
	}

	public ResponseResult(Integer state) {
		super();
		setState(state);
	}
	
	
	
	public ResponseResult(Integer state, String message) {
		super();
		setState(state);
		setMessage(message);
	}

	public ResponseResult(Exception e) {
		super();
		setState(STATE_ERR);
		setMessage(e.getMessage());
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getDate() {
		return date;
	}

	public void setDate(T date) {
		this.date = date;
	}

}
