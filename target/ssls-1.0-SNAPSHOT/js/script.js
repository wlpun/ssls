// 获取登录页面中的表单元素
// const loginForm = document.querySelector('form');
// const emailInput = document.getElementById('email');
// const passwordInput = document.getElementById('password');
// const loginBtn = document.getElementById('login-btn');

// // 获取注册页面中的表单元素
// const registerForm = document.querySelector('form');
// const usernameInput = document.getElementById('username');
// const regEmailInput = document.getElementById('email');
// const regPasswordInput = document.getElementById('password');
// const confirmPasswordInput = document.getElementById('confirm-password');
// const regPhone = document.getElementById("phone");
// const regAddress = document.getElementById("address");
// const registerBtn = document.getElementById('register-btn');

// 获取警示框元素及相关按钮
// const alertBox = document.getElementById('alert');
// const alertText = document.getElementById('alert-text');
// const alertCloseBtn = document.getElementById('alert-close');

// // 登录表单提交事件
// loginForm.addEventListener('submit', (e) => {
//     e.preventDefault(); // 阻止表单默认提交行为
//
//     // 获取用户输入的邮箱和密码
//     const email = emailInput.value.trim();
//     const password = passwordInput.value.trim();
//
//     // 判断邮箱和密码是否为空
//     if (email === '' || password === '') {
//         showAlert('请输入邮箱和密码');
//         return;
//     }
//
//     // 模拟后台验证
//     if (email === 'example@gmail.com' && password === '123456') {
//         window.location.href = 'success.html'; // 验证通过跳转到成功页面
//     } else {
//         showAlert('邮箱或密码错误，请重新输入');
//     }
// });

// 注册表单提交事件
// registerForm.addEventListener('submit', (e) => {
//     e.preventDefault(); // 阻止表单默认提交行为
//
//     // 获取用户输入的信息
//     const username = usernameInput.value.trim();
//     const email = regEmailInput.value.trim();
//     const password = regPasswordInput.value.trim();
//     const confirmPassword = confirmPasswordInput.value.trim();
//     const phone = regPhone.value.trim();
//     const address = regAddress.value.trim();
//
//     // 判断信息是否为空
//     if (username === '' || email === '' || password === '' || confirmPassword === '' || phone === '' || address === '') {
//         showAlert('请填写完整信息');
//         return;
//     }
//
//     // 判断密码和确认密码是否一致
//     if (password !== confirmPassword) {
//         showAlert('密码和确认密码不一致，请重新输入');
//         return;
//     }
//
//     // 接下来我将讲解这一段函数的作用， 因前面使用了e.preventDefault()阻止表单默认提交行为，
//     // 表单的信息无法提交到servlet， 所以这里使用FormDate先获取表单数据，通过fetch传递到表单，
//     // 注意，我这里的contextPath是提前在jsp页面写好的，所以这里可以直接使用，这是一个比较巧妙
//     // 的方法，.then首先返回看能否正确返回response，可以则返回response.text()(即servlet传递来的信息)
//     // 再传递给result进行比较。
//     const formData = new FormData(registerForm);
//     fetch(contextPath + "/RegServlet", {
//         method: 'POST',
//         body: formData,
//     }).then((response) => {
//         if (!response.ok) {
//             throw new Error('注册失败');
//         }
//         return response.text();
//     }).then((result) => {
//         if (result === 'success') {
//             showAlert('注册成功');
//         } else {
//             showAlert('注册失败,用户名已存在');
//             window.location.href = contextPath + '/register.jsp';
//         }
//     }).catch((error) => {
//         showAlert(error.message);
//     });
// });

// // 显示警示框函数
// function showAlert(message) {
//     alertText.textContent = message;
//     alertBox.style.display = 'flex';
// }
//
// // 关闭警示框函数
// alertCloseBtn.addEventListener('click', () => {
//     alertBox.style.display = 'none';
// });