
/*打开添加banner的model*/
function turnToAdd(){
	$('#addbanner').modal({
	      keyboard: true
   });
}
/*打开添加首页大图的model*/
function turnToAddHomePage(){
	$('#addHomePage').modal({
	      keyboard: true
   });
}
/* 提交并验证bannner的大小以及尺寸 */
function submitBannerForm(){
	
	$("#bannerForm").submit();
}

$(function (){
   $(".bannerClass input").bind("change", function () {
	   /*判断文件上传类型*/
	   var photoExt =  this.value.substr(this.value.lastIndexOf(".")).toLowerCase();
	     if(mod(photoExt)){
	    	 layer.msg("请上传支持的文件格式");
	    	 $(this).val("");
		    	 return false;
		 } 

	});
   /*首页大图类型判断*/
   $(".homePageClass input").bind("change", function () {
	   /*判断文件上传类型*/
	   var photoExt =  this.value.substr(this.value.lastIndexOf(".")).toLowerCase();
	     if(mod(photoExt)){
	    	 layer.msg("请上传支持的文件格式");
	    	 $(this).val("");
		    	 return false;
		 } 

	});
});
/*上传首页大图*/
function submitHomePageForm(){
	
	$("#homePageForm").submit();
}
/*文件格式*/
function mod(obj){
  if(obj != ".jpg" && obj != ".jpeg" && obj != ".png"){
	  return true;
  }else{
	  return false;
  }
}
/*重置bannerForm*/
function resetBannerForm(){
	$(".bannerClass input").each(function(){
		$(this).val("");
	});
}
function resetHomePageForm(){
	$(".homePageClass input").each(function(){
		$(this).val("");
	});
}




