var buyhealthJS,pid=$.getUrlParam("pid");jQuery(function(){$("#goodsnum").val(1),buyhealthJS.init(pid)}),buyhealthJS={goodsData:void 0,_goods:void 0,_repertory:0,_color:"",_price:0,isOpen:!1,init:function(a){$.ajax({type:"get",url:"/shop/api/goods/all",data:{tname:a},dataType:"json",success:function(a){var b,c,d,e,f;buyhealthJS.goodsData=a,b=a.goodsList[0],buyhealthJS._goods=b,buyhealthJS._repertory=b.repertory,buyhealthJS._color=b.color,buyhealthJS._price=b.price,c=a.goodsType,$("#pro_type").html(b.tname),$("#pro_name").html(b.goodsName),$("#pro_price").html(b.price.toFixed(2)),$("#totalprice").text(accMul(1,buyhealthJS._price).toFixed(2)),$("#goodsImg").attr("src",b.imgs.split(",")[0]),d="",$.each(c.goodsInfo.split("|"),function(a,b){d+="<li><span>"+b+"</span></li>"}),$("#pro_info").html(d),e="",$.each(b.imgs.split(","),function(a,b){e+="<div data-index="+a+' onclick="buyhealthJS.changeImg(this)"><img src='+b+' width="100" heigth="100"/></div>'}),$("#proImgs").html(e),f="",$.each(a.goodsList,function(a,b){var c=b.color;f+=0==a?'&nbsp;<div class="colorChk" name="color" data-index='+a+" data-color="+c+">"+c+"</div>":'<div class="color" name="color" data-index='+a+" data-color="+c+">"+c+"</div>"}),$("#pro-color").after(f),buyhealthJS.band()},error:function(){location.href="400.html"}})},band:function(){$("#goodsnum").on("keyup afterpaste",function(){this.value=1==this.value.length?this.value.replace(/[^1-9]/g,""):this.value.replace(/\D/g,""),0==this.value.length&&(this.value=1)}),$("#goodsnum").on("blur",function(){var a=$("#goodsnum").val();return a>=buyhealthJS._repertory?($("#errinfo").html("自动调整为最大购买数量。"),$("#goodsnum").val(buyhealthJS._repertory),$("#totalprice").text(accMul(buyhealthJS._repertory,buyhealthJS._price).toFixed(2)),void 0):($("#totalprice").text(accMul(a,buyhealthJS._price).toFixed(2)),void 0)}),$("#minusBtn").on("click",function(){$("#errinfo").html("");var a=$("#goodsnum").val();1==a?($("#goodsnum").val(1),$("#totalprice").text(accMul(a,buyhealthJS._price).toFixed(2))):($("#goodsnum").val(a-1),$("#totalprice").text(accMul(a-1,buyhealthJS._price).toFixed(2)))}),$("#addBtn").on("click",function(){if(void 0==buyhealthJS._color)return $("#errinfo").html("请先选择颜色。"),void 0;var a=$("#goodsnum").val();return a>=buyhealthJS._repertory?($("#errinfo").html("库存搬空了，不能选择更多了。"),$("#goodsnum").val(buyhealthJS._repertory),$("#totalprice").text(accMul(buyhealthJS._repertory,buyhealthJS._price).toFixed(2)),void 0):(a=parseInt(a)+1,$("#goodsnum").val(a),$("#totalprice").text(accMul(a,buyhealthJS._price).toFixed(2)),void 0)}),$("div[name^='color']").on("click",function(){var a,b,c;return buyhealthJS._color=$(this).attr("data-color"),$(".colorChk").addClass("color").removeClass("colorChk"),$(this).addClass("colorChk"),$("#goodsnum").val(1),a=$(this).attr("data-index"),b=buyhealthJS.goodsData.goodsList[a],null==b||void 0==b?(location.href="400.html",!1):(price=b.price,buyhealthJS._goods=b,buyhealthJS._repertory=b.repertory,buyhealthJS._color=b.color,buyhealthJS._price=b.price,$("#pro_price").html(price.toFixed(2)),$("#totalprice").text(accMul(1,price).toFixed(2)),$("#goodsImg").attr("src",b.imgs.split(",")[0]),c="",$.each(b.imgs.split(","),function(a,b){c+="<div data-index="+a+' onclick="buyhealthJS.changeImg(this)"><img src='+b+' width="100" heigth="100"/></div>'}),$("#proImgs").html(c),void 0)}),$("#buyUrl").on("click",function(){$.ajax({type:"get",url:"/shop/api/user",success:function(a){if(7==a)$("#loginframediv").css("display","block"),$("#mnltt").css("display","block"),$("#loginframe").attr("width","400").attr("height","460");else if(1==a){if(void 0==buyhealthJS._color)return $("#errinfo").html("请先选择颜色。"),!1;var b=encodeURI("order.html?once=1&pid="+pid+"&color="+buyhealthJS._color+"&version=v001&&amount="+$("#goodsnum").val());location.href=encodeURI(b)}}})}),$("#addCart").on("click",function(){return buyhealthJS.isOpen=!1,$("#toolbarRight").animate({left:"0px"},500,"swing"),void 0==buyhealthJS._color?($("#errinfo").html("请先选择颜色。"),void 0):($.ajax({type:"post",url:"/shop/api/cart/add.do",data:{tname:pid,color:buyhealthJS._color,amount:$("#goodsnum").val()},dataType:"json",success:function(a){1==a?alert("加入购物车成功"):8==a.status&&($("#loginframediv").css("display","block"),$("#mnltt").css("display","block"),$("#loginframe").attr("width","400").attr("height","460"))},error:function(){location.href="400.html"}}),void 0)}),$("#mnlclose").on("click",function(){$("#loginframe").attr("width","0").attr("height","0"),$("#mnltt").css("display","none"),$("#loginframediv").css("display","none")}),$("#cartBtn").on("click",function(){return buyhealthJS.isOpen?(buyhealthJS.isOpen=!1,$("#toolbarRight").animate({left:"0px"},500,"swing"),void 0):($.ajax({type:"get",url:"/shop/api/cart/query.do",dataType:"json",success:function(a){return 8==a.status?($("#loginframediv").css("display","block"),$("#mnltt").css("display","block"),$("#loginframe").attr("width","400").attr("height","460"),void 0):(buyhealthJS.isOpen=!0,$("#toolbarRight").animate({left:"-371px"},500,"swing"),buyhealthJS.fullCart(a),void 0)},error:function(){location.href="400.html"}}),void 0)}),$("#closeCart").on("click",function(){buyhealthJS.isOpen=!1,$("#toolbarRight").animate({left:"0px"},500,"swing")})},changeImg:function(a){var b=$(a).attr("data-index");$("#goodsImg").attr("src",buyhealthJS._goods.imgs.split(",")[b])},removeenter:function(a){$(a).children().first().attr("src","img/n1/cart_item_remove.png")},removeleave:function(a){$(a).children().first().attr("src","img/n1/cart_item_remove_07.png")},cartRemove:function(a){$.ajax({type:"post",url:"/shop/api/cart/delete.do",data:{id:a},dataType:"json",success:function(a){return 8==a.status?($("#loginframediv").css("display","block"),$("#mnltt").css("display","block"),$("#loginframe").attr("width","400").attr("height","460"),void 0):(buyhealthJS.fullCart(a),void 0)},error:function(){location.href="400.html"}})},fullCart:function(a){var b,c;return cart=a.cart,items=cart.cartItems,void 0==items||0==items.length?($("#cartItem").html("购物车中什么也没有，前去购物吧~~"),$(".numTotal").html(0),$("#priceTotal").html(0),!1):(b="",c=0,$.each(items,function(a,d){c+=d.amount;var e=d.goods;b+='<div><div class="tb-val-item">',b+='<div class="tb-item-img"><img src='+e.imgs.split(",")[0]+"></div>",b+='<div class="tb-item-item">',b+="<div>"+e.goodsName+"</div>",b+='<div class="tb-item-item-num">x '+d.amount+"</div>",b+="</div>",b+='<div class="tb-item-price"><span>¥</span>'+accMul(d.amount,e.price).toFixed(2)+"</div>",b+='<div class="tb-item-handle" onmouseenter="buyhealthJS.removeenter(this)" onmouseleave="buyhealthJS.removeleave(this)" onclick="buyhealthJS.cartRemove('+e.id+')"><img src="img/n1/cart_item_remove_07.png"></div>',b+="</div>",b+='<div class="item-line"></div></div>'}),$(".numTotal").html(c),$("#priceTotal").html(cart.totalPrice),$("#cartItem").html(b),void 0)}};