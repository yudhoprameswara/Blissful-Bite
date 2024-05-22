(function(){
    qrisPaymentListener();
    cardPaymentListener();
    cashPaymentListener();
}())


function qrisPaymentListener(){
    $('.qr-payment').click(function(event){
        $('.modal-layer').addClass('modal-layer--opened')
    });

}

function cardPaymentListener(){
    $('.card-payment').click(function(event){
        $('.payment-image').html(
            `
            <div class="payment-logo">
                <img src="/resources/image/visa.png">
            </div>    
            <img src="/resources/image/atm.png">
            `
        );
        $('.modal-layer').addClass('modal-layer--opened')
    });
}

function cashPaymentListener(){
    $('.cash-payment').click(function(event){
        $('.payment-image').html(
            `
            <img src="/resources/image/cash.png">
            `
        )
        $('.modal-layer').addClass('modal-layer--opened')
    });
}