package Domain.Common.Dao;

import java.util.ArrayList;
import java.util.List;

import Domain.Common.Dto.OrderMsgDto;

public class OrderMsgDaoimpl extends ConnectionPool implements OrderMsgDao{
	
	
	private static OrderMsgDao instance;
	public static OrderMsgDao getInstance() {
		if(instance==null)
			instance=new OrderMsgDaoimpl();
		return instance;
	}
	private OrderMsgDaoimpl() {
		
	}
	
	//CRD
	@Override
	public int insert(OrderMsgDto dto) throws Exception{
		pstmt=conn.prepareStatement("insert into tbl_order_message values(null,?,?)");
		pstmt.setString(1, dto.getOrderId());
		pstmt.setString(2,dto.getMsg());
		int result=pstmt.executeUpdate();
		pstmt.close();
		return result;
	}
	
	
	@Override
	public List<OrderMsgDto> select(String orderid) throws Exception{
		List<OrderMsgDto> list = new ArrayList();
		OrderMsgDto dto=null;
		pstmt=conn.prepareStatement("select * from tbl_order_message where orderId=?");
		pstmt.setString(1, orderid);
		rs=pstmt.executeQuery();
		if(rs!=null)
		{
			while(rs.next()) {
				dto=new OrderMsgDto();
				dto.setMsgId(rs.getInt("msgId"));
				dto.setOrderId(rs.getString("orderid"));
				dto.setMsg(rs.getString("msg"));	
				list.add(dto);
			}
			rs.close();
		}
		
		pstmt.close();
			
		return list;
	}
		

	@Override
	public int delete(String orderid)  throws Exception{
//		System.out.println("Flag!! OrderMsgDaoimpl50 : " + orderid);
		pstmt=conn.prepareStatement("delete from tbl_lend_message where orderId=?");
		pstmt.setString(1, orderid);	
		int result=pstmt.executeUpdate();
		pstmt.close();
		
		return result;
		
	}

	
}
