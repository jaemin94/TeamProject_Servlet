/**@charset "UTF-8";
 * 
 */
const swiper_banner = new Swiper('.swiper_section .swiper', {
            // Optional parameters
            direction: 'horizontal',
            autoplay: { delay: 2000, 
                disableOnInteraction:false, 
            pauseOnMouseEnter : true,},
            
            loop: true,

            // If we need pagination
            pagination: {
                el: '.swiper-pagination',
                clickable:'true',
                type  : 'bullets',   // progressbar,fraction,bullets 등  버튼 모양을 바꿀수 있다
                                     // 직접 모양을 설정할수잇다.
            },                        

            // Navigation arrows
            navigation: {
                nextEl: '.swiper-button-next',
                prevEl: '.swiper-button-prev',
            },

            // And if we need scrollbar
            scrollbar: {
                el: '.swiper-scrollbar',
            },
            mousewheel : true,
            slidesPerView :3,
             centeredSlides : true,
            
        });