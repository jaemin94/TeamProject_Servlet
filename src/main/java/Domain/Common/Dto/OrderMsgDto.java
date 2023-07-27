package Domain.Common.Dto;

public class OrderMsgDto {
	private int msgId;
	private int orderId;
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
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public OrderMsgDto(int msgId, int i, String msg) {
		super();
		this.msgId = msgId;
		this.orderId = i;
		this.msg = msg;
	}
	public OrderMsgDto() {}
	
}
