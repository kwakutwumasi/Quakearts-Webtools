var qaboot = qaboot || {};

qaboot.manyListItemSelected = function (obj, ipid, val){
	var input = $('#'+ipid);
	var select = $(obj);
	if(select.hasClass('active')){
		input.find('input').each(function(){
			if($(this).val() == val){
				$(this).remove();
			}
		});
		select.removeClass('active');
	} else {
		input.append('<input name="'+ipid.replace(/\\:/g,":")+'" value="'+val+'" type="hidden"></input>');
		select.addClass('active');
	}
	//trigger change event
	input.change();
}

qaboot.selectManyDropDown = function(drid){
	var obj = $('#'+drid);
	if(obj.hasClass('collapse'))
		obj.removeClass('collapse') ;
	else
		obj.addClass('collapse');
}

qaboot.selectInputDropDown =function(drid){
	var obj = $('#'+drid);
	if(obj.css('display')=='none')
		obj.css('display','inline-block');
	else
		obj.css('display','none');
}

qaboot.oneListItemSelected = function (obj,ipid,val,dspid){
	var select = $(obj);
	select.parent().children().each(function(){
		if($(this).hasClass('active'))
			$(this).removeClass('active');
	});
	select.addClass('active');
	var input = $('#'+ipid);
	input.find('input').each(function(){
		if(dspid && $(this).attr("id")==dspid.replace(/\\:/g,":"))
			return;
		
		$(this).remove();
	});
	input.append('<input name="'+ipid.replace(/\\:/g,":")+'" value="'+val+'" type="hidden"></input>');
	//trigger change event
	input.change();
	if(dspid){
		var element = document.getElementById(dspid.replace(/\\:/g,":"));
		if(element.nodeName == "INPUT"){
			$(element).val(select.html());
		} else {
			$('#'+dspid).html(select.html());
		}
	}
}

qaboot.prevPage = function(id){
	var input= $('#'+id);
	input.val(parseInt(input.val())-1);
	input.change();
}

qaboot.nextPage = function(id){
	var input= $('#'+id);
	input.val(parseInt(input.val())+1);
	input.change();
}

qaboot.gotoPage = function(id,val){
	var input= $('#'+id);
	input.val(val);
	input.change();
}

qaboot.submitForm = function(fo, nvpair, newTarget) {
	qaboot.addFormValues(fo, nvpair);
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

qaboot.addFormValues = function(fo, nvpair) {
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

qaboot.removeFormValues = function(fo) {
    var pairs = fo.pairs;
    if (pairs !== null) {
        for (var i = 0; i < pairs.length; i++) {
            fo.removeChild(pairs[i]);
        }
    }
};

qaboot.escape = function(text) {
	return text.replace(/([.*+?^=!:${}()|\[\]\/\\])/g, "\\$1");
};

qaboot.queueAction = function(obj,delay){
	if(qaboot.tmid>0)
		clearTimeout(qaboot.tmid);
	
	qaboot.tmid = setTimeout(obj, delay);
};

qaboot.tmid =0;
