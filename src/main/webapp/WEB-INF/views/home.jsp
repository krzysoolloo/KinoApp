<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
		<h1 class="zolty">REPERTUAR</h1>
		<input type="text" id="datetimepicker" class="calendar">
		<table>
		<thead>
			<tr>
				<td id="szeproc">TYTUŁ FILMU</td>
				<td>GODZINY SEANSÓW</td>
			</tr>
		</thead>
		<tbody id="zawartosc">
		</tbody>
		</table>
		<div id="result"></div>
		<script>
var poprzednia = null;
	
function szukaj(a, b){
    if(b.val() != poprzednia)
        $.get( "http://localhost:8080/kinoapp/getshowings/"+b.val(), function( data ) {
        	//$.each(data, function(i, item) {
        	    //alert(item.movie.title);
        	//});
        	poprzednia = b.val();

    var obj = data.reduce(function(result, current) {
        result[current.movie.id] = result[current.movie.id] || [];
        //result[current.movie.id].push(current.movie);
        result[current.movie.id].opis = {"id":current.movie.id, "title":current.movie.title};
        var show = { "id": current.id, "date": current.date };
        
        result[current.movie.id].push(show);
        
        return result;
    }, {});
    
	$("#zawartosc").html('');
	var con = "";
	$.each(obj, function(i, item) {
		con += '<tr><td><a href="movie/'+item.opis.id+'" class="tytul">'+item.opis.title+'</a></td><td>';
		//console.log(item.opis);
		$.each(item, function(i2, item2) {
			var d = new Date(item2.date);
			con += '<a href="show/'+item2.id+'">'+d.getHours()+':'+('0'+d.getMinutes()).slice(-2)+'</a>';
			//console.log(item2);
		});
		con += "</td></tr>";
	});
	
			  $( "#zawartosc" ).html( con );
			  //alert( "Load was performed." );
			})
}

$.datetimepicker.setLocale('pl');
$('#datetimepicker').datetimepicker({
	dayOfWeekStart : 1,
	lang:'pl',
	minDate:0,
	startDate:new Date(),
	value: new Date(),
	timepicker:false,
	format:'d.m.Y',
	onChangeDateTime:function(dp,$input){
        //$('#result').text($input.val());
		szukaj(dp, $input);
    }
	});

szukaj(null,$('#datetimepicker'))

</script>