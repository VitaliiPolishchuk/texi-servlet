var markers = [];
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
            console.log("markers = " + markers);
        }
    })
});

function addMarker(data){
    data = data['latLong'];
    var latLng = new google.maps.LatLng(data['latitude'],data['longitude']);
    var marker   = new google.maps.Marker({
      position: latLng
    });
    marker.setMap(map);
    markers.push(marker);
}

function removeMarkers(){
    console.log(markers.length);
    for(var i = 0; i < markers.length; i++){
        markers[i].setMap(null);
    }
    markers = [];
}

function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
      center: {lat: 50.4501, lng: 30.5234},
      zoom: 10
    });

}
