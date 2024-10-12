$(function (){
    const addToCartButton = document.querySelector('.details-add-to-cart');
    const messageElement = document.querySelector('.details-message');

    addToCartButton.addEventListener('click', () => {
        $.ajax({
            type: "POST",
            url: contextPath + "/AddToBookshelfServlet",
            data: {"id": id, "status": status},
            success: function (msg) {
                if (msg.toString().trim() == "加入借阅架成功") {
                    messageElement.classList.remove('error');
                    messageElement.classList.add('success');
                } else {
                    messageElement.classList.remove('success');
                    messageElement.classList.add('error');
                }
                messageElement.textContent = msg;
            }
        })
    });
})
