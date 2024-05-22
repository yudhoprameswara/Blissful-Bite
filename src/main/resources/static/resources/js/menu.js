(function(){

    addToCartListener();

}());

function addToCartListener(){

    $('.add-to-cart').click(function(event){
        let id = $(this).attr('data-id');
        let dto ={
         foodId:id
        }
     
        $.ajax({
         method: 'POST',
         url:'/api/menu/addToCart',
         data: JSON.stringify(dto),
         contentType : 'application/json',
         success : function(response){
            $('.succeed-container').addClass('succeed-container--opened')
     
            setTimeout(function () {
             $(".succeed-container").removeClass("succeed-container--opened");
           }, 3000);

           location.reload();
         }
     });
    })
}