(function ($){
	function inputCheckBeforeSubmit(elements, submit_id){
		var all_elements=elements;
		elements.forEach(function(element){
			if ($(element).is('select')){
				$(element).change(
						function() {
							if ($(this).val().length==0){
								$(submit_id).prop('disabled',true);
								$(this).addClass('invalid');
							}
							else{
								var disable_submit=false;
								all_elements.forEach(function(e){
									if ($(e).val().length<1){
										disable_submit=true;
										$(e).addClass("invalid");
									}
									else{
										$(e).removeClass("invalid");
									}
								});
								$(submit_id).prop('disabled',disable_submit);
							}
						}
						);		
			}
			else{
				$(element).on("input", null, null,function() {
					if ($(this).val().length==0){
						//	disable_submit=true;
						$(submit_id).prop('disabled',true);
						$(this).addClass('invalid');
					}
					else{
						var disable_submit=false;
						all_elements.forEach(function(e){
							if ($(e).val().length<1){
								disable_submit=true;
								$(e).addClass("invalid");
							}
							else{
								$(e).removeClass("invalid");
							}
						});
						$(submit_id).prop('disabled',disable_submit);
					}
				});
			}
			//for firefox
			//
			if ($(element).val().length<1){
				$(submit_id).prop('disabled',true);
			}
		});
	}






///
//
//			Class for Ui stuff
//
///

	$.ui={


		ModalWindow:		Class.extend({

			init: function(base_template_id, container_div){
				//base_template_id - id for base modal template
				//container_div - container where temporary live modal window
				//
				if (typeof(base_template_id)!="undefined"){
					this.base_template = $.templates("#"+base_template_id);
				}
				else{
					this.base_template=$.templates("#base-modal");
				}
				if (typeof(container_div)== "undefined"){
					this.container_div="#modal-tmp";
				}
				else{
					this.container_div="#"+container_div;
				}				
				this.base_context={"modal_id":Math.random().toString(36).substring(10),
								   "modal_title":"Modal window",
									};
				this.elements_for_check=[];
			},
			setBaseContext: function(context){
				//context={"modal_id":"value","modal_title":true, "key3":[1,2,3]}
				this.base_context=context;
			},
			updateBaseContext:function(context){
				this.base_context=$.extend({},base_context,context);
			},
			setModalContent: function(html){
				// content in center of widget
				this.base_context.modal_content=html;
			},
			setTitle:function(title){
				this.base_context.modal_title=title;
			},
			setModalId:function(modal_id){
				this.base_context.modal_id=modal_id;
			},
			renderModalContentFromTemplate: function(template_id, context){
				var template = $.templates("#"+template_id);
				var context=$.extend({},this.base_context, context);
				var html = template.render(context);
				this.base_context.modal_content=html;
			},

			setSubmitBtnId:function(submit_id){
				this.submit_id=submit_id;
			},
			onSubmitClick: function(callback){
				if (typeof(this.submit_id)!="undefined"){
					this.submit_id="#"+this.base_context.modal_id+"SubmitBtn";
					$(this.submit_id).click(function(){
							callback(this);
					});
				}
				else{
					$("#"+this.base_context['modal_id']+"SubmitBtn").click(function(){
						callback(this);
					});
				}
			},
			setCheckInputNotEmptyBeforeSubmit: function(elements_list, submit_id){
				this.elements_for_check=elements_list;
				if (typeof(submit_id)!="undefined"){
					this.submit_id=submit_id;
				}
			},
			checkInputNotEmptyBeforeSubmit: function (elements_list, submit_id){
				//elements_list=["password", "full_url","email1"]
				//
				//if one of elements empty, then submit button will be set to 
				//disabled, and all input gets 'invalid' class
				//
				//
				if (typeof(submit_id)=="undefined"){
					var submit_id="#"+this.base_context['modal_id']+"SubmitBtn";
				}
				else{
					this.submit_id=submit_id;
				}
				inputCheckBeforeSubmit(elements_list, submit_id);
			},
			setErrorMsg: function(error_msg){
				var html='<div class="alert alert-danger" role="alert" >'+error_msg+'</div>';
				$("#"+this.base_context['modal_id']+"Alert").html(html);
			},
			disableSubmitBtn: function(){
				if (typeof(this.submit_id)!="undefined"){
					$(this.submit_id).prop('disabled',true);
				}
				else{
					var submit_id="#"+this.base_context['modal_id']+"SubmitBtn";
					$(submit_id).prop('disabled',true);
				}
			},
			enableSubmitBtn: function(){
				if (typeof(this.submit_id)!="undefined"){
					$(this.submit_id).prop('disabled',false);
				}
				else{
					var submit_id="#"+this.base_context['modal_id']+"SubmitBtn";
					$(submit_id).prop('disabled',false);
				}
			},

			run: function(){
				var html=this.base_template.render(this.base_context);
				$(this.container_div).replaceWith(html);
				$('#'+this.base_context['modal_id']+"Modal").modal();
				if (this.elements_for_check.length>0){
					this.checkInputNotEmptyBeforeSubmit(this.elements_for_check,this.submit_id);
				}
			},
			hide: function(){
				$('#'+this.base_context['modal_id']+"Modal").modal('hide');
			},
		}),










	}
})(jQuery);
