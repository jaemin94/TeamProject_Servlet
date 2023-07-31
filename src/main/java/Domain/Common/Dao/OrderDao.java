package Domain.Common.Dao;

import java.sql.SQLException;
import java.util.List;

import Domain.Common.Dto.OrderDto;

public interface OrderDao {

	List<OrderDto> select();
	
	public List<OrderDto> select1(String member_id);

	OrderDto select(String order_id);

	int insert(OrderDto dto);

	int update(OrderDto dto);

	int delete(String order_id);

}