var qab = qab || {};

qab.mlis = function (obj){
	if($(obj).hasClass("disabled"))
		return;

	var select = $(obj);
	var val = select.data("item-value");
	var ipid = select.data("dropdown-input");
	var input = $('#'+ ipid);
	if(select.hasClass('active')){
		input.find('input').each(function(){
			if($(this).val() == val){
				$(this).remove();
			}
		});
		select.removeClass('active');
	} else {
		input.append('<input name="'+ipid+'" value="'+val+'" type="hidden"></input>');
		select.addClass('active');
	}
	//trigger change event
	input.change();
};

qab.ssdd = function(obj){
	if($(obj).hasClass("disabled"))
		return;

	var drop = $('#'+$(obj).data('dropdown'));
	
	if(drop.is(':hidden') && drop.find('a').length>0)	
		drop.slideDown('slow', function(){
			if(($(window).scrollTop() + $(window).height())<(drop.offset().top+drop.height()))
				$(window).scrollTop($(window).scrollTop()+drop.height());
		});		
	else
		drop.slideUp('fast');
};

qab.ssidd =function(obj){
	if($(obj).hasClass("disabled"))
		return;
	
	var drop = $('#'+$(obj).data('dropdown'));
	$('.input-list-group').each(function(){
		if(!$(this).is(':hidden'))
			$(this).slideUp('fast');			
	});
	
	if(drop.is(':hidden') && drop.find('a').length>0)
		drop.slideDown('slow');
	else
		drop.slideUp('fast');	
};

qab.ssime = function(obj,s,e){
	var fc = $(obj);
	if(!fc.hasClass('form-control')){
		if(fc.is(":focus") && e.type!="blur")
			return;
		
		fc = fc.parent();	
	}
	
	if(s){
		fc.addClass('form-select-focus');
	} else {
		fc.removeClass('form-select-focus');
	}
};

qab.olis = function (obj){
	if($(obj).hasClass("disabled"))
		return;

	var select = $(obj);
	var ipid = select.data('dropdown-input');
	var val = select.data('item-value');
	var dspid = select.data('display-span');
	
	select.parent().children().each(function(){
		if($(this).hasClass('active'))
			$(this).removeClass('active');
	});
	select.addClass('active');
	var input = $('#'+ipid);
	
	input.find('input').each(function(){
		if($(this).attr("id"))
			return;
		
		$(this).remove();
	});
	
	input.append('<input name="'+ipid+'" value="'+val+'" type="hidden"></input>');
	
	if(dspid){
		var element = $('#'+dspid);
		if(element.prop("tagName").toLowerCase() == "input"){
			element.val(select.html());
		} else {
			element.html(select.html());
		}
	}
	//trigger change event
	input.change();
};

qab.pp = function(obj){
	var id = $(obj).data("page-input");
	var input= $('#'+id);
	input.val(parseInt(input.val())-1);
	input.change();
};

qab.np = function(obj){
	var id = $(obj).data("page-input");
	var input= $('#'+id);
	input.val(parseInt(input.val())+1);
	input.change();
};

qab.gp = function(obj){
	var id = $(obj).data("page-input");
	var val = $(obj).data("page-value");
	var input= $('#'+id);
	input.val(val);
	input.change();
};

qab.sf = function(fo, nvpair) {
	qab.afv(fo, nvpair);
    var ft = fo.target;

    if (fo.onsubmit) {
        var result = fo.onsubmit();
        if ((typeof result == 'undefined') || result) {
            fo.submit();
        }
    } else {
        fo.submit();
    }
    fo.target = ft;
    qab.dpf(fo);
};

qab.dpf = function(f) {
    var adp = f.pairs;
    if (adp !== null) {
        for (var i = 0; i < adp.length; i++) {
            f.removeChild(adp[i]);
        }
    }
};

qab.afv = function(fo, nvpair) {
    var pairs = new Array();
    fo.pairs = pairs;
    var i = 0;
    for (var k in nvpair) {
        if (nvpair.hasOwnProperty(k)) {
            var p = document.createElement("input");
            p.type = "hidden";
            p.name = k;
            p.value = nvpair[k];
            fo.appendChild(p);
            pairs[i++] = p;
        }
    }
};

qab.rfv = function(fo) {
    var pairs = fo.pairs;
    if (pairs !== null) {
        for (var i = 0; i < pairs.length; i++) {
            fo.removeChild(pairs[i]);
        }
    }
};

qab.escape = function(text) {
	return text.replace(/([.*+?^=!:${}()|\[\]\/\\])/g, "\\$1");
};

qab.qact = function(obj,delay){
	if(qab.tmid>0)
		clearTimeout(qab.tmid);
	
	qab.tmid = setTimeout(obj, delay);
};

qab.tmid =0;

qab.sdn = function (obj){
	var dropDown = $('#'+$(obj).data("dropdown"));
	dropDown.animate({scrollTop:dropDown.scrollTop()+dropDown.height()},{duration:500,queue:true,done:function(){
		if(dropDown.scrollTop()+dropDown.height()==dropDown.prop('scrollHeight')){
			$(obj).prop("disabled",true);
		}
		var upbtn = $('#'+$(obj).data("up-button"));
		if(upbtn.prop("disabled"))
			upbtn.prop("disabled",false);										
	}})
	return false;
};

qab.sup = function (obj){
	var dropDown = $('#'+$(obj).data("dropdown"));
	dropDown.animate({scrollTop:dropDown.scrollTop()-dropDown.height()},{duration:500,queue:true, done:function(){
		if(dropDown.scrollTop()==0){
			$(obj).prop("disabled",true);
		}
		var dnbtn = $('#'+$(obj).data("down-button"));
		if(dnbtn.prop("disabled"))
			dnbtn.prop("disabled",false);										
	}})
	return false;
};

qab.rsel = [];
qab.rsi = function () {
	var windowWidth = $(window).width();
	for(var i=0;i<qab.rsel.length;i++){
		qab.rsel[i].resizeImage(windowWidth);
	}
};

qab.tm = function(obj,opst){
	var sm = $(obj);
	
	var val=opst?"0":"-13em";
	sm.animate({
		left:val
	});
};

qab.icbe = function(obj){
	var checkbtn = $(obj);
	var input = $("input[name="+$(obj).data("input-control")+"]");
	if(checkbtn.prop("checked")){
		input.prop("disabled",false);
	} else {
		input.prop("disabled",true);		
	}
};
qab.mons=new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
qab.dc = function(day,month,year,idBase,type){
	var dsel=idBase+"_day",
	msel=idBase+"_month",
	ysel=idBase+"_year",
	insel=idBase+"_input",
	dbsel=idBase+"_btn_day";
	return  {
		"day":day,
		"month":month,
		"year":year,
		"dsel":dsel,
		"msel":msel,
		"ysel":ysel,
		"insel":insel,
		"dbsel":dbsel,
		"type":type,
		"ud" : function(obj) {
			this.day = obj.innerHTML;
			this.uc();
			$(this.dsel).html($(obj).html());
		},
		"um" : function(obj, val) {
			this.month = val;
			var totalDays = qab.mons[val - 1];
			if (this.day > totalDays) {
				this.day = totalDays;
				$(this.dsel).html(totalDays + '');
			}
			this.uc();
			$(this.msel).html($(obj).html());
			this.sd();
		},
		"uy" : function(obj) {
			this.year = obj.innerHTML;
			$(this.ysel).html(this.year);
			if(this.month)
				this.sd();
			else {
				$(this.msel).html("Jan");
				this.month = 1;
				this.sd();
			}
			this.uc();
		},
		"yi": function(obj){
			if(obj.value.length==4 && obj.value.match(/[1-2][\d]{3}/)){
			  	this.uy({innerHTML:obj.value});
			}
		},
		"sd" : function() {
			if(this.type !="dm" && this.type!="dmy")
				return;

			var totalDays = qab.mons[this.month - 1];
			if(this.month==2){
				if(this.year % 100==0 && this.year % 400==0){
					totalDays+=1;
				} else if(this.year % 4==0){
					totalDays+=1;
				}
			}
			
			var count = -8;
			var offset = new Date(this.year?this.year:new Date().getFullYear(), this.month-1, 1, 0, 0, 0, 0).getDay();
			$(this.dbsel).parent().find('.buffer').width(offset*39);
			$(this.dbsel).parent().find('div.dropdown-menu')
					.children().each(function() {
						count++;
						if (totalDays < count) {
							$(this).addClass('collapse');
							$(this).removeClass('day');
						} else {
							if ($(this).hasClass('collapse')) {
								$(this).removeClass('collapse');
								$(this).addClass('day');
							}
						}
					});
		},
		"cc" : function() {
			$(this.insel).val('');
			$(this.dsel).html('&nbsp;');
			$(this.msel).html('&nbsp;');
			$(this.ysel).html('&nbsp;');
		},
		"uc" : function() {
			var mnStr = this.month + "";
			switch(this.type){
			case "dm":
					$(this.insel).val((this.day.length == 1 ? "0" + this.day : this.day)
							+ "/"+ (mnStr.length == 1 ? "0" + mnStr: mnStr));
					break;
			case "m":
					$(this.insel).val(mnStr.length == 1 ? "0" + mnStr: mnStr);
					break;
			case "my":
					$(this.insel).val((mnStr.length == 1 ? "0" + mnStr: this.month) 
							+ "/" + this.year);
					break;
			case "y":
					$(this.insel).val(this.year);
					break;
			default:
				$(this.insel).val((this.day.length == 1 ? "0" + this.day : this.day)
									+ "/"+ (mnStr.length == 1 ? "0" + mnStr: mnStr) 
									+ "/" + this.year);
			}
			$(this.insel).change();
		}
	};
};

qab.adid="";
qab.sd=0;
qab.ed=0;
qab.mlh = function(data){
    switch(data.status){
    case "begin":
        var obj = $("#"+qab.adid);
		 obj.removeClass("collapse").addClass("overlay");
        obj.animate({
            opacity: 0.8
            }, qab.sd, function() {});
        break;
    case "complete":
		$("#"+qab.adid).animate({
			opacity: 0.0
			}, qab.ed, function() {
			 	$("#"+qab.adid).removeClass("overlay").addClass("collapse");
			});
        break;
    case "success":
        break;
    default:
    }
};

qab.ovlimg="";
qab.ovlimgcss="";
qab.ovh = function(data){
    switch (data.status) {
		case "begin":
			var idobj = $("#" + data.source.id);
			var ovtarget;
			var targid = idobj.data("overlay-target");
			if(targid){
				ovtarget = $("#"+targid);
			} else {
				ovtarget = idobj;
				while (ovtarget.parent().length > 0 && ovtarget.prop("tagName") !="BODY") {
					if (ovtarget.hasClass("ajax-container")) {
						break;
					}
					ovtarget = ovtarget.parent();
				}
			}

			$('body').append("<div id='"+data.source.id
					+"_overlay' class='collapse'><img src='"+
					qab.ovlimg+"' border='0' "+
					qab.ovlimgcss+"/></div>"); 
			var obj= $("#"+data.source.id+"_overlay");
			obj.css({
				position : 'absolute',
				top : ovtarget.offset().top,
				left : ovtarget.offset().left,
				width : ovtarget.css('width'),
				height : ovtarget.css('height')
			});
			obj.removeClass("collapse").addClass("overlay");
			obj.animate({
				opacity : 0.8
			}, qab.sd, function() {});

			break;
		case "complete":
			if(!data.responseCode || data.responseCode !=200){
				var obj= $("#"+data.source.id+"_overlay");
				
				if(obj.length>0){
					obj.animate(
						{
							opacity : 0.0
						},
						qab.ed,
						function() {
							$("#"+data.source.id+"_overlay").remove();
						});
				}
			}
			break;
		case "success":
			var obj= $("#"+data.source.id+"_overlay");
			if(obj.length>0){
				var idobj = $("#" + data.source.id);
				var ovtarget;
				var targid = idobj.data("overlay-target");
				if(targid){
					ovtarget = $("#"+targid);
				} else {
					ovtarget = idobj;
					while (ovtarget.parent().length > 0 && ovtarget.prop("tagName") !="BODY") {
						if (ovtarget.hasClass("ajax-container")) {
							break;
						}
						ovtarget = ovtarget.parent();
					}
				}
				obj.css({
					width : ovtarget.css('width'),
					height : ovtarget.css('height')
				});
				if(obj.length>0){
					obj.animate(
						{
							opacity : 0.0
						},
						qab.ed,
						function() {
							$("#"+data.source.id+"_overlay").remove();
						});
				}				
			}
			break;
		default:
		}
};

qab.miniimg="";
qab.miniimgcss="";
qab.mnh = function(data){
    switch(data.status){
        case "begin":
            var parent = $("#"+data.source.id).parent();
            parent.append("<img src='"+qab.miniimg+"' id='"+data.source.id+"_img' "+qab.miniimgcss+"/>");
            break;
        case "complete":
            $("#"+data.source.id+"_img").remove();
            break;
        case "success":
            break;
        default:
    }
};
