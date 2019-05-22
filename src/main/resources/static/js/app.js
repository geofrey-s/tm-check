function loadTMStudentBlockInfo() {
    //var data = JSON.stringify($("#blockForm").serializeFormJSON());
    var blockId = $("#blockId").val();
    var studentId = $("#stuid").text();
    var url = "block_information?studentId=" + studentId + "&blockId=" + blockId;
    $.ajax({
        type: "GET",
        url: url,
        //data: data,
        contentType: "application/json",
        //dataType: "json",
        success: function (data) {
            console.log(data);
            //var result = JSON.parse(data);
            var result = JSON.stringify(data) ;
            console.log(result);
            //console.log(result);
            var html='No of days attended: '+result.noofdaysattended+'<br/>';
            html+='Total no of days: '+result.totalnoofdays+'<br/>';
            html+='Percentage attended: '+result.percentageattended+'<br/>';
            html+='Total marks: '+result.totalmarks+'<br/>';
            $('#tm-student-block-info').html(html);
        },

        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log(XMLHttpRequest.responseJSON);
            $("#tm-student-block-info").html(textStatus + ":" + XMLHttpRequest + ":" + errorThrown);
        }

    });
}

toggle_visibility = function (id) {
    var element = document.getElementById(id);
    if (element.style.display == 'block')
        element.style.display = 'none';
    else
        element.style.display = 'block';
}

make_hidden = function (id) {
    var element = document.getElementById(id);
    element.style.display = 'none';
}

make_visible = function (id) {
    var element = document.getElementById(id);
    element.style.display = 'block';
}

resetForm = function (id) {
    var element = document.getElementById(id);
    $(element)[0].reset();

}

// Translate form to array
// Then put in JSON format
function serializeObject(form) {
    var jsonObject = {};
    var array = form.serializeArray();
    $.each(array, function () {
        jsonObject[this.name] = this.value;
    });
    return jsonObject;

};

(function ($) {
    $.fn.serializeFormJSON = function () {

        var o = {};
        var a = this.serializeArray();
        $.each(a, function () {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };
})(jQuery);

