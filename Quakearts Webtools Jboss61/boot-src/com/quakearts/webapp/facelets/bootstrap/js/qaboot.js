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
}

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
}

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
}

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
}

qab.pp = function(obj){
	var id = $(obj).data("page-input");
	var input= $('#'+id);
	input.val(parseInt(input.val())-1);
	input.change();
}

qab.np = function(obj){
	var id = $(obj).data("page-input");
	var input= $('#'+id);
	input.val(parseInt(input.val())+1);
	input.change();
}

qab.gp = function(obj){
	var id = $(obj).data("page-input");
	var val = $(obj).data("page-value");
	var input= $('#'+id);
	input.val(val);
	input.change();
}

qab.sf = function(fo, nvpair, newTarget) {
	qab.afv(fo, nvpair);
    var ft = fo.target;
    if (newTarget) {
        fo.target = newTarget;
    }
    if (fo.onsubmit) {
        var result = fo.onsubmit();
        if ((typeof result == 'undefined') || result) {
            fo.submit();
        }
    } else {
        fo.submit();
    }
    fo.target = ft;
    mojarra.dpf(fo);
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
}

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
}

qab.rsel = [];
qab.rsi = function () {
	var windowWidth = $(window).width();
	for(var i=0;i<qab.rsel.length;i++){
		qab.rsel[i].resizeImage(windowWidth);
	}
}

qab.op = false;
qab.tm = function(obj){
	var sidemenu = $(obj);
	
	var left=this.op?"-13em":"0";
	sidemenu.animate({
		left:left
	});
	
	this.op = !this.op;
}

