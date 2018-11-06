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
	$('#'+ipid+"_change").change();
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
	$('#'+ipid+"_change").change();
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

qab.tmid2 = 0;
qab.tm = function(obj,opst){
	var sm = $(obj);
	
	if(qab.tmid2 = 0){
		clearTimeout(qab.tmid2);
	}
	
	setTimeout(function () {
		var val = opst?"0":"-13em";
		sm.animate({
			left:val
		});
	}, opst?0:500);	
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
qab.dc = function(day,month,year,hour,minute,second,
		hrstep,mnstep,scstep,is24hr,isAM,
		idBase,type){
	var dsel=idBase+"_day",
	msel=idBase+"_month",
	ysel=idBase+"_year",
	insel=idBase+"_input",
	chngsel=idBase+"_change",
	dbsel=idBase+"_btn_day",
	hrsel=idBase+"_hour",
	mnsel=idBase+"_min",
	scsel=idBase+"_sec";
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
		"chngsel":chngsel,
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
		"hrsel":hrsel,
		"hrstep":hrstep,
		"hour":hour,
		"minhr":is24hr?0:1,
		"maxhr":is24hr?23:12,
		"is24hr":is24hr,
		"hrup":function(){
			if(this.hour<this.maxhr && (this.hour+this.hrstep)<=this.maxhr){
				this.hour+=(this.hrstep - (this.hour % this.hrstep));
				$(this.hrsel).val(this.hour);
				this.uc();
			}
		},
		"hrdown":function(){
			if(this.hour>this.minhr && (this.hour-this.hrstep)>=this.minhr){
				var subtrahand = this.hour % this.hrstep;
				this.hour-=(subtrahand===0?hrstep:subtrahand);
				$(this.hrsel).val(this.hour);
				this.uc();
			}
		},
		"vhr":function(obj){
			if(!this.vn(obj))
				return;
				
			var value = parseInt(obj.value);
			if(this.minhr > value || value > this.maxhr){
				obj.value = this.minhr+"";
				this.hour = this.minhr;
			} else {
				this.hour = value;
			}
			this.uc();
		},
		"mnsel":mnsel,
		"mnstep":mnstep,
		"minute":minute,
		"mnup":function(){
			if(this.minute<60 && (this.minute+this.mnstep)<=59){
				this.minute+=(this.mnstep - (this.minute % this.mnstep));
				$(this.mnsel).val(this.minute);
				this.uc();
			}
		},
		"mndown":function(){
			if(this.minute>0 && (this.minute-this.mnstep)>=0){
				var subtrahand = this.minute % this.mnstep;
				this.minute-=(subtrahand === 0?mnstep:subtrahand);
				$(this.mnsel).val(this.minute);
				this.uc();
			}
		},
		"vmn":function(obj){
			if(!this.vn(obj))
				return;
				
			var value = parseInt(obj.value);
			if(0 > value || value > 60){
				obj.value = "0";
				this.minute = 0;
			} else {
				this.minute = value;
			}
			this.uc();
		},
		"scsel":scsel,
		"scstep":scstep,
		"second":second,
		"scup":function(){
			if(this.second<60 && (this.second+this.scstep)<=59){
				this.second+=(this.scstep - (this.second % this.scstep));
				$(this.scsel).val(this.second);
				this.uc();
			}
		},
		"scdown":function(){
			if(this.second>0 && (this.second-this.scstep)>=0){
				var subtrahand = (this.second % this.scstep);
				this.second-=(subtrahand === 0?scstep:subtrahand);
				$(this.scsel).val(this.second);
				this.uc();
			}
		},
		"vsc":function(obj){
			if(!this.vn(obj))
				return;
				
			var value = parseInt(obj.value);
			if(0 > value || value > 60){
				obj.value = "0";
				this.second = 0;
			} else {
				this.second = value;	
			}
			this.uc();
		},
		"vn":function(obj){
			var numbexp = /^\d+$/;
			if(!numbexp.test(obj.value)){
				obj.value = "0";
				return false;
			}
			return true;
		},
		"isAM":isAM,
		"tglampm":function(obj, isAM){
			this.isAM = isAM;
			$(obj).parent().children().removeClass('active');
			$(obj).addClass('active');
			this.uc();
		},
		"cc" : function() {
			$(this.insel).val('');
			$(this.dsel).html('&nbsp;');
			$(this.msel).html('&nbsp;');
			$(this.ysel).html('&nbsp;');
			$(this.hrsel).val('');
			$(this.mnsel).val('');
			$(this.scsel).val('');
			
			this.day = "";
			this.month = 0;
			this.year = "";
			this.hour = 0;
			this.minute = 0;
			this.second = 0;
		},
		"uc" : function() {
			var hour = this.hour;
			if(!this.is24hr){
				if(this.isAM){
					if(this.hour === 12){
						hour = 0;
					}
				} else {
					if(this.hour !== 12){
						hour+=12;
					}
				}
			}
			var mnStr = (this.month<10 ? "0" : "")+ this.month + "";
			var hourString = (hour<10 ? "0" : "") + hour + "";
			var minuteString = (this.minute<10 ? "0" : "") + this.minute + "";
			var secondString = (this.second<10 ? "0" : "") + this.second + "";
			var dateString = "";
			var parts = this.type.split("");
			for(var i=0;i<parts.length;i++){
				var part = parts[i];
				switch(part){
				case 'd':
					if(this.day.length == 0)
						return;
					
					dateString = this.day.length == 1 ? "0" + this.day : this.day;
					break;
				case 'm':
					if(this.month == 0)
						return;
					
					if(dateString.length>0)
						dateString = dateString + "/";
					dateString = dateString + mnStr;
					break;
				case 'y':
					if(this.year.length == 0)
						return;
					
					if(dateString.length>0)
						dateString = dateString + "/";
					dateString = dateString + this.year
					break;
				case 'h':				
					if(dateString.length>0)
						dateString = dateString + " ";
					dateString = dateString + hourString;
					break;
				case 'n':
					if(dateString.length>0)
						dateString = dateString + ":";
					dateString = dateString + minuteString;
					break;
				case 's':
					if(dateString.length>0)
						dateString = dateString + ":";
					dateString = dateString + secondString;
					break;
				default:
					break;
				}
			};
			$(this.insel).val(dateString);
			$(this.chngsel).change();
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

qab.hclk = function(e){
  var drgbl= $(e.currentTarget).parent();
  if(!drgbl.hasClass("dragging")){
  	drgbl.addClass("dragging");
	  if(e.stopPropagation){
		  e.stopPropagation();
	  } else {
		  window.event.cancelBubble = true;
	  }
  }
}

qab.pclk = function(e) {
  var drgbl= $(e.currentTarget);
  if(drgbl.hasClass("dragging")){
	  drgbl.removeClass("dragging");
  }
}

qab.pmv= function(e){
	var drgbl= $(e.currentTarget);
  if(drgbl.hasClass("dragging")){
  	var offset = drgbl.offset();
    drgbl.css("left",e.pageX);
    drgbl.css("top",e.pageY);    
  }
}

qab.cls=function(e){
	$(e.currentTarget).parent().parent().fadeOut();
	if (e.stopPropagation) {
		e.stopPropagation();
	} else {
		window.event.cancelBubble = true;
	}
}