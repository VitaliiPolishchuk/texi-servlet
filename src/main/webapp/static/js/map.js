var isOriginChosen = false,
    isDestinationChosen = false,
    isPathExist = false;



  function initMapFrom() {
    var directionsService = new google.maps.DirectionsService();
    var directionsRenderer = new google.maps.DirectionsRenderer();
    var map = new google.maps.Map(document.getElementById('map'), {
      center: {lat: 50.4501, lng: 30.5234},
      zoom: 11
    });
    directionsRenderer.setMap(map);
    var card = document.getElementById('pac-card');
    var inputFrom = document.getElementById('origin');
    var inputTo = document.getElementById('destination');
    var strictBounds = document.getElementById('strict-bounds-selector');


    var autocompleteFrom = new google.maps.places.Autocomplete(inputFrom);
    var autocompleteTo = new google.maps.places.Autocomplete(inputTo);

    autocompleteFrom.setFields(
        ['address_components', 'geometry', 'icon', 'name']);

    autocompleteTo.setFields(
        ['address_components', 'geometry', 'icon', 'name']);

    var marker   = new google.maps.Marker({
      map: map,
      anchorPoint: new google.maps.Point(0, -29)
    });

    function calculateAndDisplayRoute(directionsService, directionsRenderer) {
      isPathExist = true;
      marker.setVisible(false);
      directionsRenderer.setMap(map);
      directionsService.route(
      {
        origin: {query: document.getElementById('origin').value},
        destination: {query: document.getElementById('destination').value},
        travelMode: 'DRIVING'
      },
      function(response, status) {
        if (status === 'OK') {
          directionsRenderer.setDirections(response);
        } else {
          $('.error-message').text("Path doesn`t exist");
        }
      });
    }

    function removeRouteFromMap(directionsRenderer) {
      directionsRenderer.setMap(null);
    }


    function isPointFromExist(){
      return document.getElementById('origin').value != "";
    }

    function isPointToExist(){
      return document.getElementById('destination').value != "";
    }

    function isDisplayRoute() {
      return document.getElementById('origin').value != "" &&
              document.getElementById('destination').value != ""
    }

    function setDefaultMap() {
      marker.setVisible(false);
      map.setCenter({lat: 50.4501, lng: 30.5234});
      map.setZoom(11);
    }

    function displayMarket(autocomplete){
      var place = autocomplete.getPlace();
          if (!place.geometry) {
            window.alert("No details available for input: '" + place.name + "'");
            return;
          }

          if (place.geometry.viewport) {
            map.fitBounds(place.geometry.viewport);
          } else {
            map.setCenter(place.geometry.location);
            map.setZoom(17);  // Why 17? Because it looks good.
          }
          marker.setPosition(place.geometry.location);
          marker.setVisible(true);
    }

    autocompleteFrom.addListener('place_changed', function() {
      isOriginChosen = true;

      if(isDisplayRoute()){
        calculateAndDisplayRoute(directionsService, directionsRenderer);
      } else {
        isPathExist = false;
        removeRouteFromMap(directionsRenderer);
        if(!isPointFromExist()){
          if(isPointToExist()){
            displayMarket(autocompleteTo);
          } else {
            setDefaultMap()
          }
        } else {
          displayMarket(autocompleteFrom);
        }
      }
    });

    autocompleteTo.addListener('place_changed', function() {
      isDestinationChosen = true;

      if(isDisplayRoute()){
        calculateAndDisplayRoute(directionsService, directionsRenderer);
      } else {
        isPathExist = false;
        removeRouteFromMap(directionsRenderer);
        if(!isPointToExist()){
          if(isPointFromExist()){
            displayMarket(autocompleteFrom);
          } else {
            setDefaultMap();
          }
        } else {
          displayMarket(autocompleteTo);
        }
      }
    });

    var onChangeHandler = function() {
      if (document.getElementById('destination').value != "" &&
          document.getElementById('origin').value != ""){

      } else {
        isRouteExist = false;
      }
    };

    $('#mapForm').on("submit", function (e) {
          console.log(isDestinationChosen);
          console.log(isOriginChosen);
          console.log(isPathExist);
          if(isDestinationChosen && isOriginChosen){
              $('#mapForm').submit();
          } else {
              e.preventDefault();
              let error_message = $('.error-message');
              if(!isOriginChosen){
                error_message.text("Origin not chosen")
              } else if(!isDestinationChosen){
                error_message.text("Destination not chosen");
              } else {
                error_message.text("Path in not exist")
              }
          }


      })


  }