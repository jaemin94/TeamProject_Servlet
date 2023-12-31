package Domain.Common.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import Domain.Common.Dto.OrderDto;



public class OrderDaoimpl extends ConnectionPool implements OrderDao{
	
	private static OrderDao instance;
	public static OrderDao getInstance() {
		if(instance==null)
			instance=new OrderDaoimpl();
		return instance;
	}
	
	
	
	public OrderDaoimpl()
	{
		super();
	}
	
	// 주문 전체 조회
	public List<OrderDto> select()
	{
		List<OrderDto> list = new ArrayList();
		OrderDto dto = null;
		
		try {
			pstmt = conn.prepareStatement("select * from tbl_order");
			rs = pstmt.executeQuery();
			System.out.println("error");
			if(rs != null)
			{
				while(rs.next())
				{
					dto = new OrderDto();
					dto.setOrder_id(rs.getString("order_id"));
					dto.setMember_id(rs.getString("member_id"));
					dto.setProduct_code(rs.getInt("product_code"));
					dto.setProduct_name(rs.getString("product_name"));
					dto.setAdr_addr(rs.getString("adr_addr"));
					dto.setOdr_amount(rs.getInt("odr_amount"));
					dto.setOdr_date(rs.getDate("odr_date"));
					dto.setPrice(rs.getInt("price"));
					list.add(dto);
				}
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;	
	}
	
	public List<OrderDto> select1(String member_id)
	{
		List<OrderDto> list = new ArrayList();
		OrderDto dto = null;
		
		try {
			pstmt = conn.prepareStatement("select * from tbl_order where member_id = ?");
			pstmt.setString(1, member_id);
			rs = pstmt.executeQuery();
			System.out.println("error");
			if(rs != null)
			{
				while(rs.next())
				{
					dto = new OrderDto();
					dto.setOrder_id(rs.getString("order_id"));
					dto.setMember_id(rs.getString("member_id"));
					dto.setProduct_code(rs.getInt("product_code"));
					dto.setProduct_name(rs.getString("product_name"));
					dto.setAdr_addr(rs.getString("adr_addr"));
					dto.setOdr_amount(rs.getInt("odr_amount"));
					dto.setOdr_date(rs.getDate("odr_date"));
					dto.setPrice(rs.getInt("price"));
					list.add(dto);
				}
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;	
	}
	
	// 주문 단건 조회
	public OrderDto select(String order_id)
	{
		OrderDto dto = null;
		
		try {
			pstmt = conn.prepareStatement("select * from tbl_order where order_id = ?");
			pstmt.setString(1, order_id);
			rs = pstmt.executeQuery();
			if(rs != null)
			{
				while(rs.next())
				{
					dto = new OrderDto();
					dto.setOrder_id(rs.getString("order_id"));
					dto.setMember_id(rs.getString("Member_id"));
					dto.setProduct_code(rs.getInt("product_code"));
					dto.setProduct_name(rs.getString("product_name"));
					dto.setAdr_addr(rs.getString("adr_addr"));
					dto.setOdr_amount(rs.getInt("odr_amount"));
					dto.setOdr_date(rs.getDate("odr_date"));
					dto.setPrice(rs.getInt("price"));
					
				}
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;	
	}
	
	// 주문하기
	public int insert(OrderDto dto)
	{
		try {
			pstmt = conn.prepareStatement("insert into tbl_order values(?,?,?,?,?,?,curdate(),?)");
			pstmt.setString(1, dto.getOrder_id());
			pstmt.setString(2, dto.getMember_id());
			pstmt.setInt(3, dto.getProduct_code());
			pstmt.setString(4, dto.getProduct_name());
			pstmt.setString(5, dto.getAdr_addr());
			pstmt.setInt(6, dto.getOdr_amount());
			pstmt.setInt(7, dto.getPrice());
			
			int result = pstmt.executeUpdate();
			pstmt.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	// 주문수정
	public int update(OrderDto dto)
	{
		try {
			pstmt = conn.prepareStatement("update tbl_order set member_id = ?, odr_amount = ? , price = ? where  order_id =?");
			pstmt.setString(1, dto.getMember_id());
			pstmt.setInt(2, dto.getOdr_amount());
			pstmt.setInt(3, dto.getPrice());
			pstmt.setString(4, dto.getOrder_id());
			int result = pstmt.executeUpdate();
			pstmt.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	

	
	// 주문삭제
	public int delete(String order_id)
	{
		
		
		
		 // order_id가 JSON 배열 형식인 경우 배열에서 문자열로 변환
	    if (order_id.startsWith("[") && order_id.endsWith("]")) {
	        try {
	            JSONArray jsonArray = new JSONArray(order_id);
	            order_id = jsonArray.optString(0);
	        } catch (JSONException e) {
	            e.printStackTrace();
	            return 0; // 변환에 실패하면 삭제를 진행하지 않고 0을 반환
	        }
	    }
		
		try {
			pstmt = conn.prepareStatement("delete from tbl_order where order_id = ?");
			pstmt.setString(1, order_id);
			int result = pstmt.executeUpdate();
			System.out.println("orderDado delete : " + result);
			pstmt.close();
			return result;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}
