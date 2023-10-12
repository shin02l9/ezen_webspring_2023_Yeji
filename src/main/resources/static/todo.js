console.log("todo JS 실행");

// 1. post AJAX
function postTodo() {
    console.log("postTodo() 실행");

    let todoInput = document.querySelector(".todoInput").value;
    console.log('todo : '+todoInput);
    let todo = {
        todo : todoInput,

    }

    $.ajax({
        url : "/todo/dopost",
        method : "post",
        data : { todo : todo } ,
        success : function f(r){
            console.log("통신성공 : ")
            console.log(r)



        } ,
        error : function f(e){
            console.log("통신실패 : "+e)
        }
    })
}

// 2. get AJAX
function getTodo() {
    $.ajax({
        url : "/todo/doget",
        method : "get",
        data : {} ,
        success : function f(r){
            console.log("통신성공 : "+r)


        } ,
        error : function f(e){
            console.log("통신실패 : "+e)
        }
    })
}

// 3. put AJAX
function putTodo() {
    $.ajax({
        url : "/todo",
        method : "put",
        data : {} ,
        success : function f(r){
            console.log("통신성공 : "+r)
        } ,
        error : function f(e){
            console.log("통신실패 : "+e)
        }
    })
}

// 4. delete AJAX
function deleteTodo() {
    $.ajax({
        url : "/todo",
        method : "delete",
        data : {} ,
        success : function f(r){
            console.log("통신성공 : "+r)
        } ,
        error : function f(e){
            console.log("통신실패 : "+e)
        }
    })
}