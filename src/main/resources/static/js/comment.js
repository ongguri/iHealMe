/*
let index= {
    init: function (){
        $("btn-comment-save").on("click", () => {
            this.commentSave();
        });
    },

    commentSave: function (){
        let postNo = $("#postNo").val();
        let data = {
            content: $("#comment-content").val()
        };

        $.ajax({
            url: `/community/${postNo}/comment`,
            type: 'post',
            data: JSON.stringify(data),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
        }).done(function (resp){
            alert("댓글이 등록되었습니다.")
            location.href = `/community/${postNo}`;
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    }
}

index.init();
*/


$(document).ready(function () {
    let postNo = $("#postNo").val();
    const listGroup = $(".commentList");

//특정 게시글의 댓글 처리
    function loadJSONData() {
        $.getJSON('/community/' + postNo, function (arr) {
            console.log(arr);

            let str = "";
            $('.comment-count').html(" ( " + arr.length + " )");

            $.each(arr, function (idx, comment) {
                console.log(comment);
                str += ' <div class="card-body" id="' + comment.commNo + '">';
                str += ' <div class="card-subtitle mb-2 text-muted">' + comment.email + '</div>';
                str += ' <div class="card-title">' + comment.content + '</div>';
                str += ' <div class="card-text" th:text="${#temporals.format(comment.regDate, \'yyyy/MM/dd HH:mm\')}">' + comment.regDate + '</div>';
                str += ' </div>';
            })

            listGroup.html(str);
        });
    }

    $(".comment-count").click(function () {
        loadJSONData();
    }) //end click

    var modal = $('.modal');

    $(".addComment").click(function () {
        modal.modal('show');
        // 댓글 입력 부분 초기화
        $('input[name="commentContent"]').val('');

        $(".modal-footer .btn").hide();     //모달 내 버튼 숨기기
        $(".commentSave, .commentClose").show();    //필요한 버튼만 표시
    });

    $(".commentSave").click(function () {
        // 자바스크립트 객체 생성
        const comment = {
            postNo: postNo,
            content: $('input[name="commentContent"]').val()
        };
        console.log(comment);

        // JSON 문자열로 바꿔서 전송
        $.ajax({
            url: '/community/' + postNo,
            method: 'post',
            data: JSON.stringify(comment),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            success: function (data) {
                console.log(data);
                alert("댓글이 등록되었습니다.")
                modal.modal('hide');
                loadJSONData();
            }
        })
    });

    $(".commentClose").click(function () {
        modal.modal('hide');
    });

    $('.commentList').on("click", ".card-body", function () {
        const commNo = this.id;
        console.log(commNo);

        $("input[name='commentContent']").val($(this).find('.card-title').html());
        $("input[name='commNo']").val(commNo);

        $(".modal-footer .btn").hide();
        $(".commentDelete, .commentUpdate, .commentClose").show();

        modal.modal('show');
    });

    $(".commentDelete").on("click", function () {
        const commNo = this.id;

        $.ajax({
            url: '/community/comment/' + commNo,
            method: 'delete',
            success: function (result) {
                console.log("result: " + result);
                if (result === 'success') {
                    alert("댓글이 삭제되었습니다.");
                    modal.modal('hide');
                    loadJSONData();
                }
            }
        })
    });

    $(".commentUpdate").click(function () {
        const commNo = $("input[name='commNo']").val();
        console.log(commNo); // Add this line

        const comment = {
            commNo: commNo,
            postNo: postNo,
            content: $('input[name="commentContent"]').val()
        };

        console.log(comment);
        $.ajax({
            url: '/community/comment/' + commNo,
            method: 'put',
            data: JSON.stringify(comment),
            contentType: 'application/json; charset=utf-8',
            success: function (result) {
                console.log("RESULT: " + result);

                if (result === 'success') {
                    alert("댓글이 수정되었습니다.");
                    modal.modal('hide');
                    loadJSONData();
                }
            }
        })
    })

    window.onload=function (){
        loadJSONData();
    };
});

