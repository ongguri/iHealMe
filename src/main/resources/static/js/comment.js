$(document).ready(function (){
    const postNo = [[${dto.postNo}]];
    const listGroup = $(".commentList");

    /*$(".comment-count").click(function (){
        $.getJSON('/comments/post/' + postNo, function(arr){
            console.log(arr);
        })
    })*/

    //날짜 처리
    function  formatTime(str){
        const date = new Date(str);
        return date.getFullYear() + '/' +
            (date.getMonth() + 1) + '/' +
            date.getDate() + ' ' +
            date.getHours() + ':' +
            date.getMinutes();
    }

    //특정 게시글의 댓글 처리
    function loadJSONData(){
        $.getJSON('/community/post/'+postNo, function (arr){
            console.log(arr);

            let str = "";
            $('.comment-count').html(" ( " + arr.length + " )");

            $.each(arr, function (idx, comment){
                console.log(comment);
                str += '    <div class="card-body" data-commNo=' + comment.commNo + '">' + comment.commNo;
                str += '    <h5 class="card-title">' + comment.content + '</h5>';
                str += '    <h6 class="card-subtitle mb-2 text-muted">' + comment.userEmail + '</h6>';
                str += '    <p class="card-text">' + formatTime(comment.regDate) + '</p>';
                str += '    </div>';
            })

            listGroup.html(str);
        });
    }
    $(".comment-count").click(function (){
        loadJSONData();
    })  //end click

    let modal = $('.modal');

    $(".addComment").click(function(){
        modal('show');
        $('input[name="commentContent"]').val('');

        $(".modal-footer .btn").hide();
        $(".commentSave, .commentClose").show();
    });

    $(".commentSave").click(function (){
        const comment = {
            postNo: postNo,
            content: $('input[name="commentContent"]').val(),
        };
        console.log(comment);
        $.ajax({
            url: '/comments/',
            method: 'post',
            data: JSON.stringify(comment),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            success: function (data){
                console.log(data);
                alert("댓글이 등록되었습니다.")
                modal('hide');
                loadJSONData();
            }
        })
    });

    $('.commentList').on("click", ".card-body", function(){
        const commNo = $(this).data("commNo");

        $("input[name='commentContent']").val($(this).find('.card-title').html());
        $("input[name='commNo']").val(commNo);

        $(".modal-footer .btn").hide();
        $(".commentDelete, .commentUpdate, .commentClose").show();

        modal('show');
    });

    $(".commentDelete").on("click", function (){
        const commNo = $("input[name='commNo']").val();

        $.ajax({
            url: '/comments/' + commNo,
            method: 'delete',
            success: function (result){
                console.log("result: " + result);
                if(result ==='success'){
                    alert("댓글이 삭제되었습니다.");
                    modal('hide');
                    loadJSONData();
                }
            }
        })
    });

    $(".commentUpdate").click(function(){
        const commNo = $("input[name='commNo']").val();

        const comment = {
            commNo: commNo,
            postNo: postNo,
            content: $('input[name="commentContent"]').val()
        };

        console.log(comment);
        $.ajax({
            url: '/comments/' + commNo,
            method: 'put',
            data: JSON.stringify(comment),
            contentType: 'application/json; charset=utf-8',
            success: function (result){
                console.log("RESULT: " + result);

                if(result === 'success'){
                    alert("댓글이 수정되었습니다.");
                    modal('hide');
                    loadJSONData();
                }
            }
        })
    })
});





