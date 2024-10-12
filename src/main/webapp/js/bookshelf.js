$(function (){
    // 获取书架表格
    const bookshelfTable = document.querySelector('.bookshelf table');
    // const id = document.getElementById('id').value;
    // 删除按钮点击事件
    bookshelfTable.addEventListener('click', (event) => {
        if (event.target.classList.contains('delete-button')) {
            // 找到要删除的行
            const row = event.target.closest('tr');
            // 获取与按钮关联的ID
            const id = event.target.dataset.id;
            // 从表格中删除该行
            row.remove();
            $.ajax({
                type: 'POST',
                url: contextPath + "/DeleteBookForShelfServlet",
                data: {"id": id},
                success: function (mgs){}
            })
        }
    });

    // 获取全选按钮和复选框的引用
    const selectAllButton = document.getElementById('selectAllButton');
    const checkboxes = document.querySelectorAll('tbody input[type="checkbox"]');

     // 全选点击事件
    selectAllButton.addEventListener('click', function() {
        const selectAll = !this.classList.contains('selected');

        checkboxes.forEach(function(checkbox) {
            checkbox.checked = selectAll;
        });

        this.classList.toggle('selected', selectAll);
        this.textContent = selectAll ? '全不选' : '全选';
    });
})