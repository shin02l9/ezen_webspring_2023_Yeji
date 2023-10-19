
// CSS 파일 호출하기
import style from './Todo.css';

export default function Todo(props){
    return(<>

        <div class="todo">
            <div class="tcontent"> { props.content } </div>
            <div class="etcbtns">
                <button  type="button"> 상태변경 </button>
                <button  type="button"> 제거하기 </button>
           </div>
        </div>

    </>);
}