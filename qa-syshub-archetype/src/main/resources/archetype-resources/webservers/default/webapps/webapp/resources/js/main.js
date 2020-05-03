var syshub = {};
syshub.value = function(obj1){
	return {
		"isGreaterThan": function(obj2){
			return obj1 > obj2;
		}
	}
}
