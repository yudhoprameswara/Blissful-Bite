(function(){
    FoodListEventListener();
    CreateButtonListener();
}())

function FoodListEventListener(){

    $('.food-list-td').click(function(event){
        let id = $(this).attr('data-id');

        $.ajax({
            url:`/api/food-edit/${id}`,
            success: function(response){
                ImageChanger(response)
                populateForm(response)
            }
        });

    });
    
}

function ImageChanger({imagePath}){
    $('.image-viewer').html(
        ` <img src="/resources/image/food/${imagePath}" alt="/resources/image/food/no-image.jpg">`
    )
}

function populateForm({id,name,category,price,availability,description}){
    $('.id-form').val(id);
    $('.name-form').val(name);
    $('.category-form').val(category);
    $('.price-form').val(price);
    if(availability == true){
        $('.availability-form').prop('checked',true)
    }
    $('.description-form').val(description);

}

function saveButtonListener(){

    $('.form-index').submit(function(event){
        event.preventDefault();
        var formData = new FormData();
        formData.append('textIdValue', $('.id-form').val());
        formData.append('textValue', $('.name-form').val());
        formData.append('selectValue' , $('.category-form').val());
        formData.append('numericValue', $('.price-form').val());
        
        let checkbox = $('.avalability-form').val();
        let availability = false
        if (checkbox == 'On'){
            availability = true;
        }
        
        formData.append('checkBoxValue',availability);
        formData.append('textareaValue', $('.description-form').val());
        formData.append('file', $('.image-form').val());


        // let dto = collectInputForm();
        $.ajax({
            method: 'POST',
            url:'/api/food-edit/upsert',
            contentType: false,
            processData: false,
            data: formData,
            success : function(response){
                location.reload();
            }
        });

    });
   
}


function collectInputForm(){
    let id = $('.id-form').val();
    let name =  $('.name-form').val();
    let category = $('.category-form').val();
    let price = $('.price-form').val();
    let checkbox =  $('.availability-form').val();

    let availability = false
    if (checkbox == 'On'){
        availability = true;
    }
    let description =  $('.description-form').val();
    var image = $('.image-form')[0];
    

    let dto = {
        id,
        name,
        category,
        price,
        availability,
        description,
        image
    }
    return dto;
}

function CreateButtonListener(){

 $('.create-button').click(function(event){
    $('.id-form').val("");
    $('.name-form').val("");
    $('.category-form').val(null);
    $('.price-form').val(0);
    $('.availability-form').val(false);
    $('.description-form').val("");

    $('.image-viewer').html(`
        <img src="/resources/image/food/no-image.jpg"></img>
    `);
 });
    
}