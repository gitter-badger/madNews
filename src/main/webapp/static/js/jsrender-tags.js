	$.views.tags({
		truncateString: function (str, max_char) {
			console.log(this);
			return str.slice(0, max_char);
		},
		getHumanReadableTime: function(timestamp){
			var date = new Date();
			date.setTime(timestamp * 1000);
			var minutes=String(date.getUTCMinutes());
			if (minutes.length<2){
				minutes="0"+minutes;
			}
			var time=date.getUTCHours()+":"+minutes;
			return time;
		},
		getHumanReadableDateTime: function(timestamp){
			var date = new Date();
			var month = new Array(12);
			month[0] = "January";
			month[1] = "February";
			month[2] = "March";
			month[3] = "April";
			month[4] = "May";
			month[5] = "June";
			month[6] = "July";
			month[7] = "August";
			month[8] = "September";
			month[9] = "October";
			month[10] = "November";
			month[11] = "December";

			date.setTime(timestamp * 1000);
			var minutes=String(date.getUTCMinutes());
			if (minutes.length<2){
				minutes="0"+minutes;
			}
			var datetime=month[date.getUTCMonth()]+" "+date.getUTCDay()+", "+date.getUTCHours()+":"+minutes;
			return datetime;
		},		
	});
