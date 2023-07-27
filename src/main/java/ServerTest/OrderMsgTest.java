package ServerTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.OrderController;
import Domain.Common.Dao.MemberDao;
import Domain.Common.Dao.MemberDaoimpl;
import Domain.Common.Dao.OrderDao;
import Domain.Common.Dao.OrderDaoimpl;
import Domain.Common.Dao.OrderMsgDao;
import Domain.Common.Dao.OrderMsgDaoimpl;
import Domain.Common.Dao.ProdDao;
import Domain.Common.Dao.ProdDaoimpl;
import Domain.Common.Dto.MemberDto;
import Domain.Common.Dto.OrderDto;
import Domain.Common.Dto.OrderMsgDto;
import Domain.Common.Dto.ProdDto;
import Domain.Common.Service.MemberService;
import Domain.Common.Service.MemberServiceImpl;
import Domain.Common.Service.OrderService;
import Domain.Common.Service.OrderServiceImpl;
import Domain.Common.Service.ProductService;
import Domain.Common.Service.ProductServiceImpl;

/**
 * Servlet implementation class Test2
 */
@WebServlet("/Test3")
public class OrderMsgTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private OrderService service;
	private MemberService memberService;
	private ProductService productService;
	private OrderDao oDao;
	private MemberDao mDao;
	private ProdDao pDao;
	private static OrderService instance;
	private OrderMsgDao omDao; //omDao 객체생성
	
	
	
	public OrderMsgTest() {
		service = OrderServiceImpl.getInstance();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.print("Flag!!");
		
		response.setContentType("text/html; charset=UTF-8");
		OrderController a = new OrderController();
	

		int product_code = 1;
		int odr_amount = 1;
		
		
		oDao = OrderDaoimpl.getInstance();
		mDao = MemberDaoimpl.getInstance();
		pDao = ProdDaoimpl.getInstance();
		memberService = MemberServiceImpl.getInstance();
		productService = ProductServiceImpl.getInstance();
		omDao=OrderMsgDaoimpl.getInstance();
		
	    MemberDto mdto = new MemberDto();
	    ProdDto pdto = new ProdDto();
	    OrderDto odto = new OrderDto();
	    

	    try {
			mdto = memberService.memberSearchOne("Role_user", "user1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    if (mdto != null) {
	        try {
				pdto = productService.reqProd(product_code);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        if (pdto != null) {
	            int currentStock = pdto.getAmount();
	            if (currentStock >= odr_amount) {
	                int updatedStock = currentStock - odr_amount;
	                pdto.setAmount(updatedStock);
	                // tbl_product 의 내용 수정(ok)
	                
	                // insert 유무에 관하여 T/F 설정
	                boolean updateSuccess = false;
					try {
						updateSuccess = productService.updateProdAmount(product_code, pdto);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} // 현재 상품재고량 수정

	                // insert가 되면 상품 재고 업데이트를 실행
	                if (updateSuccess) {
	                    int pp = odr_amount * pdto.getProd_price();
	                    String oid = UUID.randomUUID().toString();

	                    odto.setOrder_id(oid); // 주문 ID 설정
	                    odto.setProduct_code(product_code);

	                    // tbl_order 의 내용 추가(x)
	                    
	                    // insert 여부를 확인하는 코드
	                    int insertSuccess = oDao.insert(new OrderDto(oid, mdto.getId(), pdto.getProduct_code(),
	                            pdto.getProduct_name(), mdto.getAdr_addr(), odr_amount, null, pp));

	                    // insert 가 무사히 되었을시 true값을 리턴
	                    if (insertSuccess==1) {
	                        System.out.println("[INFO] 주문완료");
	                        try {
	                        	System.out.println("Flag!! write message127: ");
								omDao.insert(new OrderMsgDto(0, mdto.getId(), pdto.getProduct_code() + " 상품 주문 완료."));
	                        	System.out.println("Flag!! write message129: ");
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	                        return ;
	                        
	                    } 
	                    // insert가 되지 않았을시에 false값을 리턴
	                    else {
	                        System.out.println("[INFO] 주문 내역 추가 실패");
	                        // 주문 내역 추가 실패 시 상품 재고량을 원래대로 복구
	                        pdto.setAmount(currentStock);
	                        try {
								productService.updateProdAmount(product_code, pdto);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	                        return ;
	                    }
	                } 
	                // insert가 되지 않았을시 상품재고량 업데이트하지 않고 false 값을 리턴
	                else {
	                    System.out.println("[INFO] 상품 재고량 업데이트 실패");
	                    return ;
	                }
	            } else {
	                System.out.println("[INFO] 주문 수량이 재고보다 많습니다.");
	                return ;
	            }
	        } else {
	            System.out.println("[INFO] 해당 상품이 존재하지 않습니다.");
	            return ;
	        }
	    } else {
	        System.out.println("[INFO] 해당 회원이 존재하지 않습니다.");
	        return ;
	    }
	}
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
	

	// 상품 주문하기
