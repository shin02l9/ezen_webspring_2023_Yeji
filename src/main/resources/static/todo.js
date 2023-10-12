console.log("todo JS 실행");

// 1. post AJAX ---------------------------------------------------
function postTodo() {
    console.log("postTodo() 실행");

    let todoInput = document.querySelector(".todoInput").value;
    console.log('todo : '+todoInput);
    let todo = {
        tcontent : todoInput,
        tstate : false
    }
    let todoJson = JSON.stringify(todo);

    $.ajax({
        url : "/todo/dopost",
        method : "post",
        contentType: "application/json",
        data : todoJson ,
        success : function f(r){
            console.log(" post 통신성공 : "+r)
            if(r){
             alert("등록되었습니다.");
             getTodo();
            }
        } ,
        error : function f(e){
            console.log("통신실패 : "+e)
        }
    })
}

// 2. get AJAX ---------------------------------------------------
getTodo();
function getTodo() {
    console.log("getTodo() 실행");

    let todolist = document.querySelector(".todo_bottom");
    let HTML =``;
    $.ajax({
        url : "/todo/doget",
        method : "get",
        data : {} ,
        success : function f(r){
            console.log(" get 통신성공 : ")
            console.log(r)
            r.forEach( (todolist) => {

                if( !todolist.tstate ){ HTML +=`<div class="todo">`; }
                else { HTML +=`<div class="todo successTodo">`; }

                HTML +=`
                    <div class="tcontent"> ${todolist.tcontent} </div>
                    <div class="etcbtns">
                        <button onclick="putTodo(${todolist.tno},${todolist.tstate})" type="button"> 상태변경 </button>
                        <button onclick="deleteTodo(${todolist.tno})" type="button"> 제거하기 </button>
                   </div>
                 </div>
                `;
            });
            todolist.innerHTML = HTML;
        } ,
        error : function f(e){
            console.log("통신실패 : "+e)
        }
    })
}

// 3. put AJAX ---------------------------------------------------
function putTodo(tno,tstate) {
    console.log("putTodo() 실행");

    console.log('tno : '+tno);
    console.log('tstate : '+tstate);

    // 원래 상태의 반대로 바꾸기
    let uptatetstate = !tstate;

    let todoUpdate = {
            tno : tno,
            tstate : uptatetstate
        }
    let todoUpdateJson = JSON.stringify(todoUpdate);

    $.ajax({
        url : "/todo/doput",
        method : "put",
        data : todoUpdateJson ,
        contentType: "application/json",
        success : function f(r){
            console.log("통신성공 : "+r)
                if(r) { alert("상태가 수정 되었습니다.");}
                else { alert("수정을 실패했습니다.");}
                getTodo();

           /* let todoElement = document.querySelector
            if (todoElement.classList.contains("successTodo")) {
                todoElement.classList.remove("successTodo"); // 이미 클래스가 있으면 제거
            } else {
                todoElement.classList.add("successTodo"); // 클래스가 없으면 추가
            }
            getTodo();*/

        } ,
        error : function f(e){
            console.log("통신실패 : ")
            console.log(e)
        }
    })
}

// 4. delete AJAX ---------------------------------------------------
function deleteTodo(tno) {
    console.log("deleteTodo() 실행");

    $.ajax({
        url : "/todo/dodelete",
        method : "delete",
        data : { tno : tno } ,
        success : function f(r){
            console.log("통신성공 : "+r)
            if(r){
                 alert("삭제되었습니다.");
                 getTodo();
            }
        } ,
        error : function f(e){
            console.log("통신실패 : "+e)
        }
    })
}