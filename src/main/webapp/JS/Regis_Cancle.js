/**@charset "UTF-8";
 * 
 */

	const baseURL = "http://localhost:8080/TeamProject2";
	let pagePath = null;
	let fullURL = null;
 document.getElementById("cancle").addEventListener("click",function(){
	 			pagePath = "/member/auth/Login.jsp"
			fullURL = baseURL + pagePath;
	
			location.href = fullURL;
 });