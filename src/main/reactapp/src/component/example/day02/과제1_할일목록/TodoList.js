import Todo from './Todo.js';
export default function TodoList(props){
    // 예시 : AJAX가 응답한 데이터
    let response = [
           {content : '과제하기'} ,
           {content : '이력서쓰기'} ,
           {content : 'PPT만들기'}
       ];

    return(<>
        <div className="todowrap">
                    <h1> 나만의 할일 목록 </h1>
                    <div className="todo_top">
                        <input  className="todoInput" type="text"/>
                        <button type="button"> 등록 </button>
                    </div>

                    <div class="todo_bottom">
                        {
                            response.map( (r) => {
                                return (<Todo content = { r.content } />);
                            })
                        }
                    </div>
                </div>
    </>);
}