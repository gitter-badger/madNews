(function ($){
	//		Load color-style for site from cookies
	//
	var colors={};
	if (Cookies.get("@bgColor")){
		colors['@bgColor']=Cookies.get("@bgColor");
	}
	if (Cookies.get("@mainTextColor")){
		colors['@mainTextColor']=Cookies.get("@mainTextColor");
	}
	if (Cookies.get("@mainColor")){
		colors['@mainColor']=Cookies.get("@mainColor");
	}
	if (Cookies.get("@mainColor2")){
		colors['@mainColor2']=Cookies.get("@mainColor2");
	}
	less.modifyVars(colors);




	$.fn.renderTemplate=function(template_id, context){
		var template = $.templates("#"+template_id);
		return template.render(context);
	}



	//
	//	Хак для расширения основного контента страницы
	//	по ширине для xs-дисплеев
	//

	$.fn.expandContent=function(){
		if ($(window).width()>768){
			$("#news-content").removeClass("expand-content-xs");
			$("#newsSingle").removeClass("expand-content-news-single-xs");
		}
		else{
			$("#news-content").addClass("expand-content-xs");
			var s=$("#newsSingle");
			s.addClass("expand-content-news-single-xs");
  
		}
	}
	$(document).ready(function() {
		$.fn.expandContent();
		window.onresize = function(event) {
			$.fn.expandContent();
		};
	});

	//
	//	Функция для смены цветового стиля страницы
	//

	$.fn.modifyStyle = function(bgColor, mainTextColor,mainColor, mainColor2){
		less.modifyVars({"@bgColor":bgColor,
						"@mainTextColor":mainTextColor,
						"@mainColor":mainColor,
						"@mainColor2":mainColor2});
		Cookies.set('@bgColor', bgColor);
		Cookies.set('@mainTextColor', mainTextColor);
		Cookies.set('@mainColor', mainColor);
		Cookies.set('@mainColor2', mainColor2);
	}

	//
	//
	//
	$.fn.getIndexFromJsonById=function(json, id){
		for(var i = 0; i < json.length; i++)
		{
			if(json[i].id == id)
			{
				return i;
			}
		}
		return null;
	}
})(jQuery);
