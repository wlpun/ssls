// 获取登录页面中的表单元素
const loginForm = document.querySelector('form');
const emailInput = document.getElementById('email');
const passwordInput = document.getElementById('password');
const loginBtn = document.getElementById('login-btn');

// 获取警示框元素及相关按钮
const alertBox = document.getElementById('alert');
const alertText = document.getElementById('alert-text');
const alertCloseBtn = document.getElementById('alert-close');

// 显示警示框函数
function showAlert(message) {
    alertText.textContent = message;
    alertBox.style.display = 'flex';
}

// 关闭警示框函数
alertCloseBtn.addEventListener('click', () => {
    alertBox.style.display = 'none';
});

// 登录表单提交事件
loginForm.addEventListener('submit', (e) => {
    e.preventDefault(); // 阻止表单默认提交行为

    // 获取用户输入的邮箱和密码
    const email = emailInput.value.trim();
    const password = passwordInput.value.trim();

    // 后台验证
    $.ajax({
        type: "POST",
        url: contextPath + "/LoginServlet",
        data: {"email": email, "password": password},
        success: function (msg) {
            if(msg.toString().trim() == "success") {
                window.location.href = contextPath + "/IndexServlet";
            }
            else if(msg.toString().trim() == "admin") {
                window.location.href = contextPath + "/IndexForAdminServlet";
            }
            else {
                showAlert("用户名或密码错误");
            }
        }
    })
});