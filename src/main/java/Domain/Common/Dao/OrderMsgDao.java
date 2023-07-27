package Domain.Common.Dao;

import java.util.List;

import Domain.Common.Dto.*;

public interface OrderMsgDao {

	//CRUD
	int insert(OrderMsgDto dto) throws Exception;

	List<OrderMsgDto> select(String orderid) throws Exception;

	int delete(String orderid) throws Exception;

	
}
