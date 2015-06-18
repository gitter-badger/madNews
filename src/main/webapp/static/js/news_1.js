(function ($){

	$(document).ready(function() {
		console.log($.db_fixtures);
		var html = $.fn.renderTemplate("news-single", $.db_fixtures.news_single);
		console.log(html);
		$('#newsSingle').replaceWith(html);
		$.fn.expandContent();


		/* * * CONFIGURATION VARIABLES * * */
		var disqus_shortname = 'k-gerasev-school-dataart';
		var disqus_identifier= 'news-tmp-'+$.db_fixtures.news_single.id;

		/* * * DON'T EDIT BELOW THIS LINE * * */
		(function() {
			var dsq = document.createElement('script'); dsq.type = 'text/javascript'; dsq.async = true;
			dsq.src = '//' + disqus_shortname + '.disqus.com/embed.js';
			(document.getElementsByTagName('head')[0] || document.getElementsByTagName('body')[0]).appendChild(dsq);
		})();


	});


	//
	//
	//		Callbacks
	//
	//
	$.fn.onSubscribeBtnClick=function(){
		var modal= new $.ui.ModalWindow()
		modal.setModalId("subscribe");
		modal.setTitle("Newsletter")
		modal.renderModalContentFromTemplate("subscribe-modal-content",{});
		modal.run();
	}


})(jQuery);
