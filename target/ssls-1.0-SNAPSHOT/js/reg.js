
$(function (){
    // 获取注册页面中的表单元素
    const registerForm = document.querySelector('form');
    const usernameInput = document.getElementById('username');
    const regEmailInput = document.getElementById('email');
    const regPasswordInput = document.getElementById('password');
    const confirmPasswordInput = document.getElementById('confirm-password');
    const regPhone = document.getElementById("phone");
    const regAddress = document.getElementById("address");
    const registerBtn = document.getElementById('register-btn');


    // 获取警示框元素及相关按钮
    const alertBox = document.getElementById('alertReg');
    const alertText = document.getElementById('alert-textReg');
    const alertCloseBtn = document.getElementById('alert-closeReg');

    // 显示警示框函数
    function showAlert(message) {
        alertText.textContent = message;
        alertBox.style.display = 'flex';
    }

    // 关闭警示框函数
    alertCloseBtn.addEventListener('click', () => {
        alertBox.style.display = 'none';
    });

    // 注册表单提交事件
    registerForm.addEventListener('submit', (e) => {
        e.preventDefault(); // 阻止表单默认提交行为

        const formData = new FormData(registerForm);

        // 获取用户输入的信息
        const username = usernameInput.value.trim();
        const email = regEmailInput.value.trim();
        const password = regPasswordInput.value.trim();
        const confirmPassword = confirmPasswordInput.value.trim();
        const phone = regPhone.value.trim();
        const address = regAddress.value.trim();

        // 判断密码和确认密码是否一致
        if (password !== confirmPassword) {
            showAlert('密码和确认密码不一致，请重新输入');
            return;
        }

        $.ajax({
            type: "POST",
            url: contextPath + "/RegServlet",
            data: {"username": username, "email": email, "password" : password, "phone": phone, "address" : address},
            success: function (msg) {
               showAlert(msg);
            }
        })
    });
})