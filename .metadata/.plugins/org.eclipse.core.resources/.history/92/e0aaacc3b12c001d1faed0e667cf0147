<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Footer Section Begin -->
<div class="spad-footer"></div>

<footer class="footer fixed-bottom">
    <div class="page-up">
        <a href="#" id="scrollToTopButton"><span class="arrow_carrot-up"></span></a>
    </div>
    <div class="container-md">
        <div class="row">
            <div class="col-md-3">
                <div class="footer__logo">
                    <a href="${path}/">
                    <img style="height: 40px" src="${path}/resources/img/logo/betty2.png" alt="main">
                    </a>
                </div>
            </div>
            <div class="col-md-6">
                <div class="footer__nav">
                    <ul>
                        <li class="active"><a href="${path}/"><s:message code="text.home"/></a></li>
                    </ul>
                </div>
            </div>
            <div class="col-md-3" style="margin-bottom:0.3rem">
                <p style="font-size:0.8rem; line-height:15px"><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                  Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved <br/> 
                  This template is made with <i class="fa fa-heart" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
            </div>
        </div>
    </div>
  </footer>
  
  <!-- Search model Begin -->
  <div class="search-model">
    <div class="h-100 d-flex align-items-center justify-content-center">
        <div class="search-close-switch"><i class="icon_close"></i></div>
        <form class="search-model-form">
            <input type="text" id="search-input" placeholder="Search here.....">
        </form>
    </div>
</div>
<!-- Search model end -->
</body>
</html>
<!-- Js Plugins -->
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="${path}/resources/js/bootstrap.min.js"></script>
<script src="${path}/resources/js/player.js"></script>
<script src="${path}/resources/js/mixitup.min.js"></script>
<script src="${path}/resources/js/jquery.slicknav.js"></script>
<script src="${path}/resources/js/owl.carousel.min.js"></script>
<script src="${path}/resources/js/jquery.nice-select.min.js"></script>
<script src="${path}/resources/js/main.js"></script>
<script src="${path}/resources/ckeditor/ckeditor.js"></script>
<script src="${path}/resources/ckeditor/adapters/jquery.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.5/dist/jquery.validate.min.js"></script>
<script src="${path}/resources/js/jquery.simple-calendar.js"></script>
<script src="${path}/resources/js/jquery-ui.min.js"></script>

<!-- 다음 주소 api -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="${path}/resources/js/addr.js"></script>

<script src="${path}/resources/js/datepicker.js"></script>
<script src="${path}/resources/js/jquery.cookie.js"></script>

<script>

	$(function (){
		let lang = $.cookie('lang');
		if(lang == 'ko'){
			langToKo();
		} else {
			langToEn();
		}
	})

	$('#ko').click(function(ev){
		ev.preventDefault();
		$.get('${path}?lang=ko',function(){
			langToKo();
			location.reload();
		})
	})
	
	$('#en').click(function(ev){
		ev.preventDefault();
		$.get('${path}?lang=en',function(){
			langToEn();
			location.reload();
		})
	})
	
	function langToKo(){
		$('#ko').addClass('active');
		$('#ko').removeClass('wait');
		$('#en').removeClass('active');
		$('#en').addClass('wait');
	}
	
	function langToEn(){
		$('#en').addClass('active');
		$('#en').removeClass('wait');
		$('#ko').removeClass('active');
		$('#ko').addClass('wait');
	}
</script>