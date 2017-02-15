<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
   <h1 class="rozowy">REZERWACJA MIEJSC</h1>
<div class="error"></div>
   		<div id="seat-map">
			<div class="front">EKRAN</div>					
		</div>
		<div class="booking-details">
			<p>Film: <span>${show.movie.title }</span></p>
			<p>Czas: <span>${show.date }</span></p>
			<p>Sala: <span>${show.cinemaHalls.number }</span></p>
			<p>Miejsca: </p>
			<ul id="selected-seats"></ul>
			<p>Sztuk: <span id="counter">0</span></p>
			<p>Szacowany koszt: <b><span id="total">0</span>PLN</b></p>
			<form:form action="" method="post">		
			<input type="hidden" value="" id="zaznaczone" name="zaznaczone" />
			<button class="checkout-button">REZERWUJ</button>
			</form:form>
			<div id="legend"></div>
		</div>
		<div style="clear:both"></div>
<script>
var zarezerwowane = [];
var ii = 0;
console.log(zarezerwowane);
</script>
<c:forEach items="${reservations}" var="res">
<script>
zarezerwowane[ii] = ''+${res.row}+'_'+${res.cell}+'';
ii++;
</script>
</c:forEach>
<script>
console.log(zarezerwowane);
</script>
   
   <script>
   var mapa = [[]];
	var st = '';
	
	for (j = 0; j < ${show.cinemaHalls.cells}; j++) { 
	 	st += 'a';   
	}
	   for (i = 0; i < ${show.cinemaHalls.rows}; i++) { 
		   mapa[i] = st;
		   
		}

	   console.log(mapa);
   var price = 15; //price
   $(document).ready(function() {
   	var $cart = $('#selected-seats'), //Sitting Area
   	$counter = $('#counter'), //Votes
   	$total = $('#total'); //Total money
   	
   	var sc = $('#seat-map').seatCharts({
   		map: mapa,
   		naming : {
   			top : false,
   			getLabel : function (character, row, column) {
   				return column;
   			}
   		},
   		legend : { //Definition legend
   			node : $('#legend'),
   			items : [
   				[ 'a', 'available',   'Wolne' ],
   				[ 'a', 'unavailable', 'Zajęte']
   			]					
   		},
   		click: function () { //Click event
   			if (this.status() == 'available') { //optional seat
   				$('<li>R'+(this.settings.row+1)+' M'+this.settings.label+'</li>')
   					.attr('id', 'cart-item-'+this.settings.id)
   					.data('seatId', this.settings.id)
   					.appendTo($cart);

   				$counter.text(sc.find('selected').length+1);
   				$total.text(recalculateTotal(sc)+price);
   							
   				return 'selected';
   			} else if (this.status() == 'selected') { //Checked
   					//Update Number
   					$counter.text(sc.find('selected').length-1);
   					//update totalnum
   					$total.text(recalculateTotal(sc)-price);
   						
   					//Delete reservation
   					$('#cart-item-'+this.settings.id).remove();
   					//optional
   					return 'available';
   			} else if (this.status() == 'unavailable') { //sold
   				return 'unavailable';
   			} else {
   				return this.style();
   			}
   		}
   	});
   	//sold seat
   	sc.get(zarezerwowane).status('unavailable');
 
   	
   	$( "button" ).click(function() {
   		$( "#zaznaczone" ).val(sc.find('selected').seatIds);
   	   		console.log(sc.find('selected').seatIds);
   	   		return true;
   	});
   });
   //sum total money
   function recalculateTotal(sc) {
   	var total = 0;
   	sc.find('selected').each(function () {
   		total += price;
   	});
   		
   	
   	
   	return total;
   }
   
   
   </script>
   
   <style>
.front{margin: 5px 0px 45px 32px;background-color: #f0f0f0; color: #666;text-align: center;padding: 3px;border-radius: 5px;position:relative;} 
.booking-details {margin: 0 auto 0 auto; position: relative; width:100%;height: 450px; overflow: hidden;} 
.booking-details h3 {margin: 5px 5px 0 0;font-size: 16px;} 
.booking-details p{line-height:26px; font-size:16px; color:#999} 
.booking-details p span{color:#666} 
div.seatCharts-cell {color: #fff;height: 25px;width: 25px;line-height: 25px;margin: 3px;float: left;text-align: center;outline: none;font-size: 13px;} 
div.seatCharts-seat {color: #fff;cursor: pointer;-webkit-border-radius:5px;-moz-border-radius:5px;border-radius: 5px;} 
div.seatCharts-row {height: 35px;} 
div.seatCharts-seat.available {background-color: #B9DEA0;} 
div.seatCharts-seat.focused {background-color: #76B474;border: none;} 
div.seatCharts-seat.selected {background-color: #E6CAC4;} 
div.seatCharts-seat.unavailable {background-color: #472B34;cursor: not-allowed;} 
div.seatCharts-container {padding: 20px;float: left;} 
div.seatCharts-legend {padding-left: 0px;position: absolute;bottom: 16px;} 
ul.seatCharts-legendList {padding-left: 0px;} 
.seatCharts-legendItem{float:left; width:90px;margin-top: 10px;line-height: 2;} 
span.seatCharts-legendDescription {margin-left: 5px;line-height: 30px;} 
.checkout-button {display: block;width:80px; height:24px; line-height:20px;margin: 10px auto;border:1px solid #999;font-size: 14px; cursor:pointer} 
#selected-seats {max-height: 150px;overflow-y: auto;overflow-x: none;} 
#selected-seats li{float:left; width:72px; height:26px; line-height:26px; border:1px solid #d3d3d3; background:#f7f7f7; margin:6px; font-size:14px; font-weight:bold; text-align:center} 
</style>