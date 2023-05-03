let index = {
    init: function (){
        $("btn-comment-create").on("click",()=>{
            this.createComment();
        })
    },

    createComment: function (){
        const content = document.getElementById('comment-content');
        const postNo = [[${post.postNo}]];
        const data = {
            postNo : postNo,
            content : content.value,

        }


        $.ajax({
            type: "POST",
            url: `/post/${postNo}/comment`,
            data: JSON.stringify(data),
            contentType: "application/json: charset=utf-8",
            dataType: "json"
        }).done(function(resp){
            alert("댓글작성이 완료되었습니다.");
            location.href =`/post/${postNo}`;
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    }
}

function createComment(){
    const content = document.getElementById('comment-content');

    const postNo = [[${post.postNo}]];
    const data = {
        postNo : postNo,
        content: content.value
    }

    $.ajax({
        url: `/post/${postNo}/comment`,
        type: 'post',
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        data: JSON.stringify(data),
        async: false,
        success: function (response){
            console.log(response);
        },
        error: function (request, status, error){
            console.log(error)
        }
    })
}

window.onload = () => {
    findAllComments();
}

function findAllComments(){
    const postNo = [[${post.postNo}]];

    $.ajax({
        url: `/post/${postNo}/comment`,
        type: 'get',
        dataType: 'json',
        async: false,
        success: function (response){
            console.log(response);
        },
        error: function (request, status, error){
            console.log(error)
        }
    })
}

index.init();