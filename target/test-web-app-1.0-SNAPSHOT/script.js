function getAllItems() {
    getItems('stock');
    getItems('store');
}

function getItems(location) {
    var data = {}
    $.ajax({
        type: 'GET',
        url: 'rest/'+location.toLowerCase()+'/items',
        contentType: 'application/json',
    }).done(function (data) {
        printItemsTo(data, location);
    }).fail(function (msg) {
        console.log(msg);
    });
}

function addTo(location) {
    let item = {
        id: 0,
        type: "DRESS",
        color: "WHITE",
        size: 42,
        description: "...",
        cost: 0,
        location: location.toUpperCase()
    };

    $.ajax({
        type: 'POST',
        url: 'rest/'+location.toLowerCase()+'/items',
        contentType: 'application/json',
        data: JSON.stringify(item),
    }).done(function (data) {
        getItems(location);
    }).fail(function (msg) {
        console.log(msg);
    });
}

function save(id) {
    let item = getItemData(id);
    saveFrom(id, item.location);
}

function saveFrom(id, location) {
    let item = getItemData(id);
    $.ajax({
        type: 'PUT',
        url: 'rest/'+location.toLowerCase()+'/items/'+id,
        contentType: 'application/json',
        data: JSON.stringify(item),
    }).done(function (data) {
        getAllItems();
    }).fail(function (msg) {
        console.log(msg);
    });
}

function relocate(id) {
    currentLocation = $('.item-'+id+' .item-location').val();

    if (currentLocation == 'STOCK') {
        $('.item-'+id+' .item-location').val('STORE');
    } else {
        $('.item-'+id+' .item-location').val('STOCK');
    }

    saveFrom(id, currentLocation);
}

function del(id) {
    let item = getItemData(id);

    $.ajax({
        type: 'DELETE',
        url: 'rest/'+item.location.toLowerCase()+'/items/'+id,
        contentType: 'application/json',
    }).done(function (data) {
        getItems(item.location);
    }).fail(function (msg) {
        console.log(msg);
    });
}

function printItemsTo(items, location) {
    location = location.toLowerCase();
    let element = $('.'+location);
    element.html('');

    let iconRelocate = 'arrow-left';
    let titleRelocate = 'To Stock';
    if (location == 'stock') {
        iconRelocate = 'arrow-right';
        titleRelocate = 'To Store';
    }

    items.forEach(function(item) {
        let tmp = $('.card-template').html();
        tmp = tmp.replace(/\{id\}/g, item.id);
        tmp = tmp.replace(/\{item-description\}/g, item.description);
        tmp = tmp.replace(/\{item-cost\}/g, item.cost);
        tmp = tmp.replace(/\{item-location\}/g, item.location);
        tmp = tmp.replace(/\{icon-relocate\}/g, iconRelocate);
        tmp = tmp.replace(/\{title-relocate\}/g, titleRelocate);
        element.append(tmp);
        $('.item-'+item.id+' .item-type').val(item.type);
        $('.item-'+item.id+' .item-color').val(item.color);
        $('.item-'+item.id+' .item-size').val(item.size);
    });

    let tmp = $('.card-add').html();
    tmp = tmp.replace(/\{location\}/g, location);
    element.append(tmp);
}

function getItemData(id) {
    form = $('.item-'+id)
    return {
            id: id,
            type: form.find('.item-type').val(),
            color: form.find('.item-color').val(),
            size: parseInt(form.find('.item-size').val()),
            description: form.find('.item-description').val(),
            cost: parseInt(form.find('.item-cost').val()),
            location: form.find('.item-location').val(),
        };
}

$(function(){
    getAllItems();
});