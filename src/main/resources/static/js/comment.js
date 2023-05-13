$(document).ready(function () {
    let postNo = $("#postNo").val();
    const listGroup = $(".commentList");
    const commentPageFooter = $(".commentFooter");
    let pageNum = 1;

    function formatTime(str){
        let date = new Date(str);

        return date.getFullYear() + '/' +
            (date.getMonth() + 1) + '/' +
            date.getDate() + ' ' +
            date.getHours() + ':' +
            date.getMinutes();
    }

    function getList(param, callback, error){
        let page = param.page || 1;

        $.getJSON('/community/' + postNo + "/" + page, function(data){
            if(callback){
                callback(data.commentCnt, data.list);
            }
        }).fail(function (xhr, status, err){
            if(error){
                error();
            }
        });
    }

    //페이징 버튼 기능
    function showCommentPage(commentCnt){
        let endNum = Math.ceil(pageNum / 3.0) * 3;
        let startNum = endNum - 2;
        let prev = startNum !== 1;
        let next = false;

        if(endNum * 3 >= commentCnt){
            endNum = Math.ceil(commentCnt / 3.0);
        }

        if(endNum * 3 < commentCnt){
            next = true;
        }

        let str = "<ul class='pagination pull-right'>";

        if(prev){
            str += "<li class='page-item'><a class='page-link' href='" + (startNum-1)+"'>이전</a></li> "
        }
        let active;
        for (let i = startNum; i <= endNum; i++) {
            active = pageNum == i ? "active" : "";
            str += "<li class='page-item " + active + " '><a class='page-link' href='" + i + "'>" + i + "</a></li>";
        }
        if(next){
            str += "<li class='page-item'><a class='page-link' href='" + (endNum + 1) + "'>다음</a></li>";
        }
        str += "</ul>";
        commentPageFooter.html(str);
    }



//특정 게시글의 댓글 처리
    function loadJSONData(page) {
        getList({postNo, page : page||1}, function (commentCnt, arr) {
            console.log(arr);

            if(page == -1){
                pageNum = Math.ceil(commentCnt/3.0);
                loadJSONData(pageNum);
                return;
            }

            let str = "";
            $('.comment-count').html(" ( " + commentCnt + " )");

            // 댓글이 해당 페이지에 없는 경우
            if( arr === null || !arr.length){
                if(pageNum > 1){    //댓글 삭제했을 때
                    pageNum -= 1;
                    loadJSONData(pageNum);
                }
                //댓글이 없을 때
                document.querySelector('.commentList').innerHTML = '<div class="comment-none"><p>등록된 댓글이 없습니다.</p></div>';
                return false;
            }

            $.each(arr, function (idx, comment) {
                console.log(comment);
                str += ' <div class="card-body" id="' + comment.commNo + '">';
                str += ' <table>';
                str += ' <tr><td class="card-subtitle" rowspan="2"><span class="card-subtitle mb-2 text-muted">' + comment.email + '</span></td>';
                str += ' <td><span class="card-title">' + comment.content + '</span></td></tr>';
                str += ' <tr><td><span class="card-text">' + formatTime(comment.regDate) + '</span></td>';
                str += ' </tr></table>';
                str += ' </div>';
            })

            listGroup.html(str);
            showCommentPage(commentCnt);
        });
    }


    commentPageFooter.on("click", "li a", function(e){
        e.preventDefault();
        console.log("page click");

        let targetPageNum = $(this).attr("href");
        console.log("targetPageNum: " + targetPageNum);
        pageNum = targetPageNum;
        loadJSONData(pageNum);
    })


    // $(".comment-count").click(function () {
    //     loadJSONData(1);
    // }) //end click

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
                loadJSONData(pageNum);
            }
        })
    });

    $(".commentClose").click(function () {
        modal.modal('hide');
    });

    //댓글 모달 클릭 시 값 넘겨주는 기능
    $('.commentList').on("click", ".card-body", function () {
        const commNo = this.id;
        pageNum = $("li.active > a").html();

        console.log(commNo);

        $("input[name='commentContent']").val($(this).find('.card-title').html());
        $("input[name='commNo']").val(commNo);

        $(".modal-footer .btn").hide();
        $(".commentDelete, .commentUpdate, .commentClose").show();

        modal.modal('show');
    });

    $(".commentDelete").on("click", function () {
        const commNo = $("input[name='commNo']").val();

        $.ajax({
            url: '/community/comment/' + commNo,
            method: 'delete',
            success: function (result) {
                console.log("result: " + result);
                if (result === 'success') {
                    alert("댓글이 삭제되었습니다.");
                    modal.modal('hide');
                    loadJSONData(pageNum);
                }
            }
        })
    });

    $(".commentUpdate").click(function () {
        const commNo = $("input[name='commNo']").val();
        console.log(commNo);

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
                    loadJSONData(pageNum);
                }
            }
        })
    })

    window.onload=function (){
        loadJSONData(pageNum);
    };
});


