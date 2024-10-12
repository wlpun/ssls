$(function() {
    // 续借按钮点击事件
    $('.renew-button').on('click', function() {
        const bookId = $(this).data('id'); // 获取data-id的值

        // 发起Ajax请求
        $.ajax({
            type: 'POST',
            url: contextPath + 'ContinueBorrowServlet',
            data: { "bookId": bookId },
        });
    });

    document.getElementById("pay-fine-button").addEventListener("click", function() {

        var fineAmount = $(this).attr('data-fine');
        var book_id = $(this).attr('data-id');

        // 检查余额是否足够支付罚款
        if (balance >= fineAmount) {
            $.ajax({
                type: 'POST',
                url: contextPath + "/PayFineServlet",
                data: {"bookId": book_id, "fineAmount": fineAmount},
                success: function (msg) {
                    window.location.href = contextPath + "/MyBorrowServlet";
                }
            })
        } else {
            // 余额不足，提示用户充值或其他操作
            alert("余额不足，请前往个人中心充值");

        }
    });
});
