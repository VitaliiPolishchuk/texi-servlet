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
