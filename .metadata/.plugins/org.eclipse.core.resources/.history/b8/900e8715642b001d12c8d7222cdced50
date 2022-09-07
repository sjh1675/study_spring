<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<style>
	#pagination li {
		list-style: none;
		float: left;
		padding: 3px;
		border: 1px solid skyblue;
		margin: 3px;
	}
	
	#pagination li a{
		text-decoration:none;
		color: black;
	}
	
	#pagination li a.active{
		color: blue;
	}
</style>
<hr/>
<div class="container-md spad-sm">
	<!-- 댓글 -->
	<div class="anime__details__review">
		<div class="section-title">

			<h5>후기</h5>

		</div>
		<div id="comments" class="anime__review__item">
			
		</div>		
		<div class="form-row">
			<ul class="pagination" id="pa">
							
			</ul>
		</div>
	</div>

	<div class="anime__details__form">
		<div class="section-title">
			<h5>후기 작성</h5>
		</div>
		<form id="form">
			<input type="hidden" name="memberId" value="${user.id}">
			<input type="hidden" name="nickname" value="${user.nickname}">
			<input type="hidden" name="bookCode" value="${book.code}">
			<textarea id="" name="comment" placeholder="Your Comment"></textarea>
			<button id="submit">
				<i class="fa fa-location-arrow"></i> 확인
			</button>
		</form>
	</div>

</div>

<script>

var isbn = ${book.code};

$(function(){
	ajaxList(1);
});


$('#submit').on("click",function (e) {
	e.preventDefault();
    var formData = $("#form").serialize();

    $.ajax({
        type: "post",
        url: "${path}/books/"+isbn+"/comment",
        data: formData,
        dataType: 'text',
        success: function (data) {
            console.log(data);
            ajaxList(1);
            $("textarea[name='comment']").val('');
        },
        error: function (request, status, error) {
            console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
    });
});



function ajaxList(pageNum){
	$.ajax({
		type : 'post',
		url : "${path}/books/"+isbn+"/comments",
		data : {
			page : pageNum
		},
		dataType : 'json',
		success : function(result){
			console.log(result.list);
			console.log(result.pm);
			printList(result.list);
			printPage(result.pm);
		}
	});
}

function printList(list){
	var str = "";
	$(list).each(function(){
		let img = this.img;
		let nickname = this.nickname;
		let date = new Date(this.regdate);
		let formatDate = date.getFullYear()+"/"+(date.getMonth()+1)+"/"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
		let comment = this.comment;
		
		str += "<div class='anime__review__item__pic'>";
		str += "<img src='${path}/resources/img/member/thumbnail/"+img+"' alt=''>";
		str += "</div>";
		str += "<div class='anime__review__item__text'>";
		str += "<h6>";
		str += nickname + " - <span>" + formatDate + "</span>";
		if (nickname == "${user.nickname}"){
			str += "<button id='removeBtn' class='btn btn-secondary ml-3 float-right' onclick='delComment("+this.num+")'>삭제</button>";
		}
		str += "</h6>";		
		str += "<p>" + comment + "</p>";		
		str += "</div></div>";
		str += "<br>"
	});
	$("#comments").html(str);
}


function printPage(pm) {
	var str = "";
	if (pm.prev) {
		str  += "<li><a href='" + (pm.startPage - 1) + "'> << </a></li>";
	}
	
	for (var i = pm.startPage; i <= pm.endPage; i++) {
		if (pm.cri.page == i) {
			str  += "<li><a href='" + i + "' class='active'>" + i + "</a></li>";
		} else {
			str  += "<li><a href='" + i + "'>" + i + "</a></li>";
		}
	}
	
	if (pm.next) {
		str  += "<li><a href='" + (pm.endPage + 1) + "'> >> </a></li>";
	}
	
	$("#pa").html(str);
}

$("#pa").on("click", "li a",function(e){																
	e.preventDefault();						
	var commentPage = $(this).attr("href");
	page = commentPage;
	ajaxList(page);
});

function delComment(e) {
	var delNum = e;
	var userId = "${user.id}";
	var pass = prompt('비밀번호를 입력해주세요', '');  
	console.log(userId);
	console.log(pass);
	$.ajax({
		type : 'delete',
		url : "${path}/books/"+isbn+"/comment/" + delNum,
		data : {
			cno : delNum,
			id : userId,
			pw : pass
		},
		dataType : 'text',
		success : function(result) {
			console.log(result);
			if(result == "Success"){
				alert('해당 댓글이 삭제되었습니다.');
				ajaxList(1);
			} else {
				alert('비밀번호를 확인해주세요.');
			}			
		},
		error : function(result) {
			alert('비밀번호를 확인해주세요. 오류발생');
		}
	});
}




</script>





