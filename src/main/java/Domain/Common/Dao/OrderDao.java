package Domain.Common.Dao;

import java.util.List;

import Domain.Common.Dto.OrderDto;

public interface OrderDao {

	List<OrderDto> select();

	OrderDto select1(String order_id);

	List<OrderDto> select(String keyword);

	List<OrderDto> select(String keyfield, String keyword);
	
	int insert(OrderDto dto);

	int update(OrderDto dto);

	int delete(String order_id);

}