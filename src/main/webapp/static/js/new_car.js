(function($){
  $(function(){
    // Plugin initialization
    $('select').not('.disabled').formSelect();
  });
})(jQuery);

$("#photo-url").change( function(e) {  // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
    console.log($(this).val())
    $("#photo-display").attr("src", $(this).val())
});

$(document).ready(function(){
    $('.modal').modal();
});


function initMap() {
    var mapProp = {
        center: {lat: 50.4501, lng: 30.5234},
        zoom: 11
    };
    var map = new google.maps.Map(document.getElementById("map"), mapProp);
    var input = document.getElementById("location-input");
    var autocomplete = new google.maps.places.Autocomplete(input);
    var marker = new google.maps.Marker({map: map});

    autocomplete.addListener('place_changed', function() {
        var place = autocomplete.getPlace();

      if (!place.geometry) {
        return;
      }

      if (place.geometry.viewport) {
        map.fitBounds(place.geometry.viewport);
      } else {
        map.setCenter(place.geometry.location);
        map.setZoom(17);
      }

      marker.setPlace({
        placeId: place.place_id,
        location: place.geometry.location
      });

      marker.setVisible(true);

        var place = autocomplete.getPlace();
        $("#location-id").val(place.place_id)
    });
}

$('#addCarForm').on("submit", function (e) {
        e.preventDefault();
        let id = $(this).attr("carId");
        let name = $('#name').val();
        let photoUrl = $('#photo-url').val();
        let carTypeId = $("option:selected").val();
        let locationId = $('#location-id').val();
        let isAvailable = $("input[type='radio']:checked").val();
        console.log(id)
        console.log(name)
        console.log(photoUrl)
        console.log(carTypeId)
        console.log(locationId)
        console.log($("input[type='radio']:checked").val())
        $.ajax({
            type: "POST",
            url: "validation",
            data: {"id": id,
                   "name": name,
                   "photoUrl": photoUrl,
                   "carTypeId": carTypeId,
                   "locationId": locationId,
                   "isAvailable": isAvailable},
            success: function (data) {
                console.log(data)
                if (data.success) {
                    $(location).attr('href', data.url)
                } else {
                    let textNode = document.createTextNode(data.message);
                    $('.error.message').append(textNode);
                }
            }
        })
    });
