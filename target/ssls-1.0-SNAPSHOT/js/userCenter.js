$(document).ready(function() {
    $('.change-password-button').click(function() {
        $('.password-modal').fadeIn();
    });

    $('.recharge').click(function() {
        $('.balance').fadeIn();
    });

    $('.close1').click(function() {
        $('.password-modal').fadeOut();
    });
    $('.close2').click(function() {
        $('.balance').fadeOut();
    });

    $('#changePasswordForm').submit(function(event) {
        event.preventDefault();
        // 获取输入的密码
        var newPassword = $('input[name="newPassword"]').val();
        // 检查密码是否为空
        if (newPassword.trim() === '') {
            alert('密码不能为空');
            return;
        }
        // 发送Ajax请求
        $.ajax({
            type: 'POST',
            url: contextPath + '/ChangePasswordServlet',
            data: {"pwd": newPassword},
            success: function () {
                window.location.href = contextPath + "/LogoutServlet";
            }
        })
        $('.password-modal').fadeOut();
    });

    $('#recharge').submit(function(event) {
        event.preventDefault();
        // 获取输入的金额
        var recharge = $('input[name="recharge"]').val();
        // 检查是否为空
        if (recharge.trim() === '') {
            alert('充值金额不能为空');
            return;
        }
        // 发送Ajax请求或执行其他操作
        $.ajax({
            type: 'POST',
            url: contextPath + '/RechargeServlet',
            data: {"money": recharge},
            success: function (msg){
                let m = $(".userBalance");
                m.text(msg);
            }
        })
        $('.balance').fadeOut();
    });
});
