/*!
 * Created by xiao on 2015/10/23.
 */
 function getstatic(){$.getJSON("js/runner.json",function(a){var c,d,e,f,g,b=a[index];for($("#pro-name").text(b.name),$("#pro-exp").text(b.intro),$("#pro-type").text(b.type),1==b.isBuy?$("#buyUrl").attr("href","javascript:;"):($("#addCart").css("display","none"),$("#buyUrl").find("div").html("近期上市"),$("#buyUrl").css("background","#A9A9A9")),c=b.fun,d="<ul>",e=0;e<c.length;e++)d+="<li>"+c[e]+"</li>";d+="</ul>",$("#funexp").html(d),$("#pro-img").attr("src","img/"+pid+"/max/01.png"),$("#oneMinImg img").attr("src","img/"+pid+"/min/01.png"),$("#twoMinImg img").attr("src","img/"+pid+"/min/02.png"),$("#threeMinImg img").attr("src","img/"+pid+"/min/03.png"),f=index>=arr.length-1?0:index+1,$("#next").attr("href","runner.html?pid="+arr[f]),g=0==index?arr.length-1:index-1,$("#prev").attr("href","runner.html?pid="+arr[g])})}function getAll(){$.ajax({type:"get",url:"/shop/api/goods/all",data:{tname:pid},dataType:"json",success:function(a){goodsData=a}})}function getCart(){$.ajax({type:"get",url:"/shop/api/cart/query.do",dataType:"json",success:function(a){var b,c,d;return 8==a.status?($(".color").css("background",""),confirm("需要登录后才能继续操作，确定跳转到登陆页面吗？")?(location.href="login.html",!1):!1):(b=a.cart,c=b.cartItems,void 0==c||""==c?($("#mycart").html("购物车中什么也没有，前去购物吧~~"),!1):(d="",$.each(c,function(a,b){var c=b.goods;d+="<div><span>"+c.goodsName+"  "+c.color+"</span><span>￥"+c.price+"×"+b.amount+'</span><span>小计：<label class="subtotal">￥'+b.subtotal.toFixed(2)+'</label></span><span><a href="javascript:;" onclick="del('+c.id+')">删除</a></span></div>'}),d+='<div><span>共计<label class="subtotal">'+c.length+'</label>件商品</span><span>共计：<label class="subtotal">￥'+b.totalPrice+'</label></span><span><a href="order.html" target="_blank">结算</a></span></div>',$("#mycart").html(d),void 0))},error:function(){alert("系统繁忙，请稍后再试...")}})}function del(a){$.ajax({type:"post",url:"/shop/api/cart/delete.do",data:{id:a},dataType:"json",success:function(a){var b,c,d;return 8==a.status?(location.href="login.html",!1):(b=a.cart,c=b.cartItems,void 0==c||""==c?($("#mycart").html("购物车中什么也没有，前去购物吧~~"),!1):(d="",$.each(c,function(a,b){var c=b.goods;d+="<div><span>"+c.goodsName+"  "+c.color+"</span><span>￥"+c.price+"×"+b.amount+'</span><span>小计：<label class="subtotal">￥'+b.subtotal.toFixed(2)+'</label></span><span><a href="javascript:;" onclick="del('+c.id+')">删除</a></span></div>'}),d+='<div><span>共计<label class="subtotal">'+c.length+'</label>件商品</span><span>共计：<label class="subtotal">￥'+b.totalPrice+'</label></span><span><a href="order.html" target="_blank">结算</a></span></div>',$("#mycart").html(d),void 0))},error:function(){alert("系统繁忙，请稍后再试...")}})}var color,price,goodsData,arr=["j055","b005","j086","j087"],pid=$.getUrlParam("pid"),index=$.inArray(pid,arr),_repertory=0;jQuery(function(){$("#procolor").val(""),$("#goodsnum").val(1),getstatic(),getAll()}),$("#oneMinImg").on("click",function(){$("#pro-img").attr("src","img/"+pid+"/max/01.png"),$(".imgchked").removeClass("imgchked").addClass("unimgchked"),$(this).find("div").addClass("imgchked")}),$("#twoMinImg").on("click",function(){$("#pro-img").attr("src","img/"+pid+"/max/02.png"),$(".imgchked").removeClass("imgchked").addClass("unimgchked"),$(this).find("div").addClass("imgchked")}),$("#threeMinImg").on("click",function(){$("#pro-img").attr("src","img/"+pid+"/max/03.png"),$(".imgchked").removeClass("imgchked").addClass("unimgchked"),$(this).find("div").addClass("imgchked")}),$("div[name^='color']").on("click",function(){color=$(this).children().text(),$(".color").css("background",""),$(this).css("background","url(img/color.png) 50% 50%"),$("#goodsnum").val(1);var a=goodsData["goods_"+color];return null==a||void 0==a?(location.href="400.html",!1):(price=a.price,$("#price").text(price.toFixed(2)),$("#totalprice").text(price.toFixed(2)),_repertory=a.repertory,void 0)}),$("#minusBtn").on("click",function(){var a=$("#goodsnum").val();1==a?($("#goodsnum").val(1),$("#totalprice").text(accMul(a,price).toFixed(2))):($("#goodsnum").val(a-1),$("#totalprice").text(accMul(a-1,price).toFixed(2)))}),$("#addBtn").on("click",function(){if(void 0==color)return alert("请先选择颜色"),void 0;var a=$("#goodsnum").val();return a>=_repertory?(alert("达到最大库存..."),void 0):a>=99?($("#goodsnum").val(99),void 0):(a=parseInt(a)+1,$("#goodsnum").val(a),$("#totalprice").text(accMul(a,price).toFixed(2)),void 0)}),$("#goodsnum").on("blur",function(){var a,b;return void 0==color?($(this).val(1),alert("请先选择颜色"),void 0):(a=/^[1-9]+[0-9]*]*$/,b=$(this).val(),b>_repertory?(alert("超过最大库存了"),$(this).val(99),void 0):(a.test(b)||$(this).val(1),void 0))}),$("#addCart").on("click",function(){return void 0==color?(alert("请先选择颜色"),void 0):($.ajax({type:"post",url:"/shop/api/cart/add.do",data:{tname:pid,color:color,amount:$("#goodsnum").val()},dataType:"json",success:function(a){return 8==a.status?(location.href="login.html",!1):(1==a?alert("加入购物车成功"):location.href="400.html",void 0)},error:function(){return location.href="400.html",!1}}),void 0)}),$("#cart").on("mouseenter",function(){$("#mycart").css("display","block"),getCart()}),$("#mycart").on("mouseleave",function(){$(this).css("display","none")}),$("#buyUrl").on("click",function(){if(void 0==color)return alert("请先选择颜色"),!1;var a=encodeURI("order.html?once=1&pid="+pid+"&color="+color+"&version=v001&&amount="+$("#goodsnum").val());$(this).attr("href",encodeURI(a))});