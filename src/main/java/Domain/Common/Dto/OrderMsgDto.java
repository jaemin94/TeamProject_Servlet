package Domain.Common.Dto;

public class OrderMsgDto {
	private int msgId;
	private String orderId;
	private String msg;
	@Override
	public String toString() {
		return "OrderMessageDto [msgId=" + msgId + ", orderId=" + orderId + ", msg=" + msg + "]";
	}
	public int getMsgId() {
		return msgId;
	}
	public void setMsgId(int msgId) {
		this.msgId = msgId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public OrderMsgDto(int msgId, String orderId, String msg) {
		super();
		this.msgId = msgId;
		this.orderId = orderId;
		this.msg = msg;
	}
	public OrderMsgDto() {}
	
}
