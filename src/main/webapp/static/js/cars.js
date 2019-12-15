var cars = [];
var map;

$(document).on("click", ".collapsible-header", function(e) {  // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
    let typeId = $(this).attr("typeId");
    console.log(typeId)
    $.ajax({
        type: "POST",
        url: "get-cars",
        data: {"type_id": typeId},
        success: function (data) {
            console.log(data);
            removeMarkers();
            data.forEach(addMarker);
            console.log("markers = " + cars[0].car.id);
        }
    })
});

$(".collection-item").hover(
    function(e) {
        let carId = $(this).attr("carId");
        console.log(carId);
        for(var i = 0; i < cars.length; i++){
            if(cars[i].car.id != carId){
                cars[i].marker.setVisible(false);
            }
        }
    }, function(e) {
        let carId = $(this).attr("carId");
        for(var i = 0; i < cars.length; i++){
            cars[i].marker.setVisible(true);
        }
    })

function addMarker(data){

    latLong = data['latLong'];
    var latLngGoogle = new google.maps.LatLng(latLong['latitude'],latLong['longitude']);
    var marker   = new google.maps.Marker({
      position: latLngGoogle
    });
    marker.setMap(map);
    if(data.car.id == 1){
        console.log(marker);
        marker.scaledSize = new google.maps.Size(500    , 300);
    }
    data["marker"] = marker;
    cars.push(data);
}

function removeMarkers(){
    for(var i = 0; i < cars.length; i++){
        cars[i].marker.setMap(null);
    }
    cars = [];
}

function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
      center: {lat: 50.4501, lng: 30.5234},
      zoom: 10
    });

}
